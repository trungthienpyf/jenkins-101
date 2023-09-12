package org.tribe_sdk.util;

import org.springframework.security.crypto.codec.Hex;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CryptoUtil {
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    public static IvParameterSpec generateIvValue() {
        return new IvParameterSpec(generateRandom16Chars().getBytes());
    }

    private static String generateRandom16Chars() {
        byte[] array = new byte[8];
        SecureRandom random = new SecureRandom();
        random.nextBytes(array);

        return bytesToHex(array);
    }

    private static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }

        return new String(hexChars, StandardCharsets.UTF_8);
    }
    public static String encryptSecret(PrivateKey privateKey, String secret) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptedSign = cipher.doFinal(secret.getBytes());

        return Base64.getEncoder().encodeToString(encryptedSign);
    }

    public static String encryptContent(String content, String secret) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        IvParameterSpec iv = generateIvValue();
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "AES");
        Cipher dataCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        dataCipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] encryptedDataBytes = dataCipher.doFinal(content.getBytes());

        return new String(iv.getIV()) + Base64.getEncoder().encodeToString(encryptedDataBytes);
    }

    public static String decryptSecret(PublicKey publicKey, String sign) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] encryptedDataBytes = Base64.getDecoder().decode(sign);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] secretByte = cipher.doFinal(encryptedDataBytes);
        return new String(secretByte);
    }

    public static String decryptContent(String encryptedContent, String secret) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String ivString = encryptedContent.substring(0, 16);
        String correctEncryptedData = encryptedContent.substring(16);
        byte[] encryptedDataBytes = Base64.getDecoder().decode(correctEncryptedData);
        IvParameterSpec iv = new IvParameterSpec(ivString.getBytes());
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "AES");

        Cipher dataCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        dataCipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

        byte[] decryptedDataBytes = dataCipher.doFinal(encryptedDataBytes);
        return new String(decryptedDataBytes);
    }

    public static PrivateKey convertStringToPrivateKey(String privateKeyString) throws Exception {
        privateKeyString = privateKeyString
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s*", "");

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public static PublicKey convertStringToPublicKey(String publicKeyString) throws GeneralSecurityException {

        publicKeyString = publicKeyString.replaceAll("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll("-----END PUBLIC KEY-----", "").replaceAll("\\s*", "");


        byte[] decodedKey = Base64.getDecoder().decode(publicKeyString);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(decodedKey);


        KeyFactory kf = KeyFactory.getInstance("RSA");

        return kf.generatePublic(spec);
    }

    public static String decryptDataSensitive(String encryptedData, String key) throws Exception {
        byte[] keyBytes = Hex.decode(key + key.substring(0, 16));
        byte[] iv = new byte[8];

        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "DESede");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        byte[] data = Base64.getDecoder().decode(encryptedData);

        byte[] decrypted = cipher.doFinal(data);

        return new String(decrypted, StandardCharsets.UTF_8);
    }
}
