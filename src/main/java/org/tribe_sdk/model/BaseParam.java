package org.tribe_sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@EntityListeners(AuditingEntityListener.class)
@ToString
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public abstract class BaseParam implements Serializable {
    @JsonProperty("request_reference_id")
    protected String requestReferenceId;


    @JsonProperty("action")
    protected String action;
}
