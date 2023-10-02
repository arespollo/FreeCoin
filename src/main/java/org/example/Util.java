package org.example;
import org.bouncycastle.jcajce.provider.digest.SHA256;
import java.nio.charset.StandardCharsets;

public class Util {
    public static String sha256(String input){
        byte[] hash = new SHA256.Digest().digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');// 保证长度为2 如 01,02,1A
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
