package org.tribe_sdk.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.tribe_sdk.exception.TribeException;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class JdbcManagerLogRepository implements ManagerLogRepository {
    private JdbcTemplate jdbcTemplate;
    private boolean enableDb = false;

    public JdbcManagerLogRepository(String url, String username, String password) {
        if (url != null && username != null && password != null) {
            enableDb = true;
            try {
                DriverManagerDataSource dataSource = new DriverManagerDataSource();
                dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                dataSource.setUrl(url);
                dataSource.setUsername(username);
                dataSource.setPassword(password);
                runIfOpened(dataSource.getConnection());
                jdbcTemplate = new JdbcTemplate(dataSource);
                jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS testlai(request_id varchar(36)," +
                        "correlation_id varchar(255),duration long,external_action varchar(255),external_provider_code varchar(255)," +
                        "is_sent tinyint(1) default 0,is_success tinyint(1) default 0,request_date DATETIME ," +
                        "request_header TEXT,request_message TEXT,request_url TEXT,response_date DATETIME," +
                        "response_header TEXT,response_http_code INT,response_message TEXT, request_meta TEXT)");

            } catch (Exception e) {
                throw new TribeException(e.getMessage());

            }
        }


    }

    @Override
    public void save(ManagerLog managerLog) {
        if(!enableDb) return;
        int status = jdbcTemplate.update("INSERT INTO  testlai(request_id, correlation_id, request_url, external_provider_code, " +
                        "is_sent, request_date, request_message,external_action,request_header) VALUES(?,?,?,?,?,?,?,?,?)",
                managerLog.getRequestId(), managerLog.getCorrelationId(), managerLog.getRequestUrl(),
                managerLog.getExternalProviderCode(), managerLog.isSent(), managerLog.getRequestDate(),
                managerLog.getRequestMessage(), managerLog.getExternalAction(), managerLog.getRequestHeader());
        if (status < 1) {
            log.error("save fail");
        }

    }

    @Override
    public void update(ManagerLog managerLog) {
        if(!enableDb) return;
        int status = jdbcTemplate.update("UPDATE testlai SET is_success=?, response_date=?, response_message=?, response_http_code=?," +
                        " response_header=? WHERE request_id=?",
                managerLog.isSuccess(), managerLog.getResponseDate(), managerLog.getResponseMessage(),
                managerLog.getResponseHttpCode(), managerLog.getResponseHeader(), managerLog.getRequestId());
        if (status < 1) {
            log.error("save fail");
        }

    }

    private void runIfOpened(Connection connection) throws SQLException {
        if (connection != null && connection.isClosed()) {
            throw new TribeException("Db is not connected");
        }
    }
}
