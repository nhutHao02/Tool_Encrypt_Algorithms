package model;


import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.TwofishEngine;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class Twofish {
    public static String encrypt(String plainText, String key) throws Exception {
        byte[] keyBytes = Hex.decode(key);
        byte[] iv = generateRandomBytesOfIV(16);

        BlockCipher twofishEngine = new TwofishEngine();
        CFBBlockCipher cipher = new CFBBlockCipher(twofishEngine, 128);
        BufferedBlockCipher bufferedCipher = new BufferedBlockCipher(cipher);

        bufferedCipher.init(true, new ParametersWithIV(new KeyParameter(keyBytes), iv));
        // convert plaintext thành mảng byte
        byte[] input = plainText.getBytes(StandardCharsets.UTF_8);

        byte[] output = new byte[bufferedCipher.getOutputSize(input.length)];

        int len = bufferedCipher.processBytes(input, 0, input.length, output, 0);
        bufferedCipher.doFinal(output, len);

        // Nối IV và kết quả
        byte[] result = new byte[iv.length + output.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(output, 0, result, iv.length, output.length);

        // Chuyển kết quả thành chuỗi hex
        return new String(Hex.encode(result), StandardCharsets.UTF_8);
    }

    public static String decrypt(String ciphertext, String key) throws Exception {
        byte[] keyBytes = Hex.decode(key);
        byte[] input = Hex.decode(ciphertext);

        // Tách IV từ ciphertext
        byte[] iv = new byte[16];
        byte[] cipherBytes = new byte[input.length - iv.length];
        System.arraycopy(input, 0, iv, 0, iv.length);
        System.arraycopy(input, iv.length, cipherBytes, 0, input.length - iv.length);

        BlockCipher twofishEngine = new TwofishEngine();
        CFBBlockCipher cipher = new CFBBlockCipher(twofishEngine, 128);
        BufferedBlockCipher bufferedCipher = new BufferedBlockCipher(cipher);

        bufferedCipher.init(false, new ParametersWithIV(new KeyParameter(keyBytes), iv));

        byte[] output = new byte[bufferedCipher.getOutputSize(cipherBytes.length)];

        int len = bufferedCipher.processBytes(cipherBytes, 0, cipherBytes.length, output, 0);
        bufferedCipher.doFinal(output, len);

        // Chuyển kết quả giải mã thành chuỗi văn bản
        return new String(output, StandardCharsets.UTF_8);
    }
    private static byte[] generateRandomBytesOfIV(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[length];
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    public static byte[] generateRandomKey(int keyLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[keyLength / 8];
        secureRandom.nextBytes(keyBytes);
        return keyBytes;
    }
    public static void main(String[] args) throws Exception {
        String key = "0123456789abcdef0123456789abcdef";
        String plainText = "Hello, this is a secret message! con cạc";
        System.out.println("Plain Text: " + plainText);

        // Encrypt the plain text
        String encryptedText = encrypt(plainText, key);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the encrypted text
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);


        int keyLength = 128;

        // Tạo một khóa ngẫu nhiên
        byte[] randomKey = generateRandomKey(keyLength);

        // Chuyển khóa sang dạng chuỗi hex để lưu hoặc truyền đi
        String hexKey = new String(Hex.encode(randomKey));
        System.out.println( hexKey);
        System.out.println(key);
    }

}
