package hsenid.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Vidushka on 26/09/17.
 */
public class EncryptPassword {

    private String password;

    public String encryptPassword(String password){
        this.password = password;

        StringBuffer sb = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());

            byte byteData[] = md.digest();
            sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
