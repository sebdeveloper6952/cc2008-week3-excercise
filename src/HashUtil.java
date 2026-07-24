import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class HashUtil {
    public String sha256(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(data.getBytes(StandardCharsets.UTF_8));
            String hash = HexFormat.of().formatHex(digest);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            return "el codigo explotó!!!!!!!!!!!!!!!!!!!11111111111";
        }
    }
}
