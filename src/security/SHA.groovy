package security

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * File Description/檔案描述:
 * @author JamesChang* @since 2020/5/15上午 09:33
 * @version 1.0* */
class SHA {


    static String encode(String planText) {
        String encodeText = new String()
        try {
            MessageDigest md = MessageDigest.getInstance("SHA")
            md.update(planText.getBytes())
            byte[] bb = md.digest()
            println bb
        }
        catch (NoSuchAlgorithmException ex) {
        }
        finally {
            return encodeText
        }
    }
}
