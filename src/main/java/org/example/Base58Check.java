package org.example;

import java.security.Security;
import org.bouncycastle.jcajce.provider.digest.SHA256;
import org.bouncycastle.util.encoders.Hex;
import java.math.BigInteger;
import java.util.Arrays;

public class Base58Check {
    private static final char[] ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
    public boolean decodeBase58Check(String input) {
        // 1. Base58 解码
        byte[] decoded = base58Decode(input);
        // 2. 检查长度
        if (decoded.length < 5) {
            throw new IllegalArgumentException("Input is too short");
        }

        // 3. 分离数据和校验和
        byte[] data = Arrays.copyOfRange(decoded, 0, decoded.length - 4);
        byte[] checksum = Arrays.copyOfRange(decoded, decoded.length - 4, decoded.length);

        // 4. 计算校验和
        byte[] hash = new SHA256.Digest().digest(data);
        hash = new SHA256.Digest().digest(hash);
        byte[] calculatedChecksum = Arrays.copyOfRange(hash, 0, 4);

        // 5. 验证校验和
        return Arrays.equals(checksum, calculatedChecksum);
    }
    public String encodeBase58Check(byte[] input) {
        // 1. 计算两次 SHA-256 哈希
        byte[] hash = new SHA256.Digest().digest(input);
        hash = new SHA256.Digest().digest(hash);

        // 2. 取哈希的前4个字节作为校验和
        byte[] checksum = Arrays.copyOfRange(hash, 0, 4);

        // 3. 将校验和添加到原始数据的末尾
        byte[] dataWithChecksum = new byte[input.length + 4];
        System.arraycopy(input, 0, dataWithChecksum, 0, input.length);
        System.arraycopy(checksum, 0, dataWithChecksum, input.length, 4);
        // 4. 使用 Base58 编码整个数据
        return base58Encode(dataWithChecksum);
    }
    private byte[] base58Decode(String input) {
        BigInteger value = BigInteger.ZERO;
        for (char c : input.toCharArray()) {
            value = value.multiply(BigInteger.valueOf(58))
                    .add(BigInteger.valueOf(new String(ALPHABET).indexOf(c)));
        }

        byte[] bytes = value.toByteArray();
        int start = bytes[0] == 0 ? 1 : 0;

        // 处理前导零
        int zeroCount = 0;
        while (zeroCount < input.length() && input.charAt(zeroCount) == ALPHABET[0]) {
            zeroCount++;
        }

        byte[] decoded = new byte[zeroCount + bytes.length - start];
        Arrays.fill(decoded, (byte) 0);
        System.arraycopy(bytes, start, decoded, zeroCount, bytes.length - start);

        return decoded;
    }
    private String base58Encode(byte[] input) {
        BigInteger value = new BigInteger(1, input);
        StringBuilder sb = new StringBuilder();

        while (value.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divmod = value.divideAndRemainder(BigInteger.valueOf(58));
            value = divmod[0];
            sb.append(ALPHABET[divmod[1].intValue()]);
        }

        // 处理前导零
        for (byte b : input) {
            if (b == 0) {
                sb.append(ALPHABET[0]);
            } else {
                break;
            }
        }

        return sb.reverse().toString();
    }
}
