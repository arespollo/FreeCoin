package org.example;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import java.security.*;
public class KeyGenerator {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private static Base58Check base58 = new Base58Check();
    KeyGenerator() {
        try {
        // 使用 Bouncy Castle 提供的椭圆曲线参数
        ECNamedCurveParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256r1");
        // 使用 ECDSA 算法生成密钥对生成器
        KeyPairGenerator g = KeyPairGenerator.getInstance("ECDSA", "BC");
        // 初始化密钥对生成器
        g.initialize(ecSpec, new SecureRandom());
        // 生成密钥对
        KeyPair keyPair = g.generateKeyPair();
        // 获取公钥和私钥
        publicKey = keyPair.getPublic();
        System.out.println(publicKey.getEncoded().length);
        privateKey = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    public String getPublicKey(String display) {
        if(display.equals("hex")){
            return bytesToHex(publicKey.getEncoded());
        }
        return base58.encodeBase58Check(publicKey.getEncoded());
    }

    public String getPrivateKey(String display) {
        if(display.equals("hex")){
            return bytesToHex(privateKey.getEncoded());
        }
        return base58.encodeBase58Check(privateKey.getEncoded());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
