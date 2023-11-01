package model;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES {
    private SecretKey secretKey;
    public SecretKey createDesKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        this.secretKey = keyGenerator.generateKey();
        return this.secretKey;
    }

    public byte[] encrypt(String input) throws Exception {
        if (this.secretKey == null) {
            return new byte[0];
        } else {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, this.secretKey);
            byte[] cipherText = cipher.doFinal(input.getBytes("UTF-8"));
            System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(cipherText));
            return cipherText;
        }
    }

    public String decrypt(byte[] ciphertext) throws Exception {
        if (this.secretKey == null) {
            return null;
        } else {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, this.secretKey);
            byte[] plainText = cipher.doFinal(ciphertext);
            return new String(plainText, "UTF-8");
        }
    }

    public String encryptBase64(String input) throws Exception {
        if (this.secretKey == null) {
            return null;
        } else {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, this.secretKey);
            byte[] encryptedText = cipher.doFinal(input.getBytes("UTF-8"));
            String resultEncrypt = Base64.getEncoder().encodeToString(encryptedText);
            return resultEncrypt;
        }
    }

    public String decryptBase64(String cipherTextInput) throws Exception {
        if (this.secretKey == null) {
            return null;
        } else {
            byte[] cipherText = Base64.getDecoder().decode(cipherTextInput);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, this.secretKey);
            byte[] decryptedText = cipher.doFinal(cipherText);
            String reusultDecrypt = new String(decryptedText, "UTF-8");
            return reusultDecrypt;
        }
    }

    public void encryptFile(String srcFile, String desFile) throws Exception {
        if (this.secretKey == null) {
            throw new FileNotFoundException("Key not found");
        } else {
            File file = new File(srcFile);
            if (file.isFile()) {
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(1, this.secretKey);
                FileInputStream fis = new FileInputStream(file);
                FileOutputStream fos = new FileOutputStream(new File(desFile));

                byte[] input = new byte[1024];
                int bytesRead;
                byte[] output;

                while((bytesRead = fis.read(input)) != -1) {
                    output = cipher.update(input, 0, bytesRead);
                    if (output != null) {
                        fos.write(output);
                    }
                }

                output = cipher.doFinal();
                if (output != null) {
                    fos.write(output);
                }

                fis.close();
                fos.flush();
                fos.close();
                System.out.println("Encrypted");
            }

        }
    }

    public void decryptFile(String srcFile, String desFile) throws Exception {
        if (this.secretKey == null) {
            throw new FileNotFoundException("Key not found");
        } else {
            File file = new File(srcFile);
            if (file.isFile()) {
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(2, this.secretKey);
                FileInputStream fis = new FileInputStream(file);
                FileOutputStream fos = new FileOutputStream(new File(desFile));

                byte[] input = new byte[1024];
                int bytesRead;
                byte[] output;

                while((bytesRead = fis.read(input)) != -1) {
                    output = cipher.update(input, 0, bytesRead);
                    if (output != null) {
                        fos.write(output);
                    }
                }

                output = cipher.doFinal();
                if (output != null) {
                    fos.write(output);
                }

                fis.close();
                fos.flush();
                fos.close();
                System.out.println("Decrypted");
            }

        }
    }

    public String exportKey() {
        return Base64.getEncoder().encodeToString(this.secretKey.getEncoded());
    }

    public void importKey(String key) {
        byte[] decodeKey = Base64.getDecoder().decode(key);
        this.secretKey = new SecretKeySpec(decodeKey, 0, decodeKey.length, "AES");
    }

    public static void main(String[] args) throws Exception {
        String plaintext = "This is a simple DES encryption example.";
        AES aes=new AES();
        String input = "Israel chuẩn bị tấn công trên bộ, kêu gọi tất cả dân Gaza di dời trong 24 giờ";
        SecretKey desKey = aes.createDesKey();
//        byte[] cipher=aes.encrypt(input);
//        System.out.printf(aes.decrypt(cipher));

        String cipher=aes.encryptBase64(input);
        System.out.printf(cipher);
        System.out.println();
        System.out.printf(aes.decryptBase64(cipher));
//        String path="D:\\SV\\Code\\TEs\\src\\Main.java";
//        aes.encryptFile(path,"D:\\SV\\Code\\TEs\\src\\Main1.java");
//        aes.decryptFile("D:\\SV\\Code\\TEs\\src\\Main1.java","D:\\SV\\Code\\TEs\\src\\Main2.java");


    }
}
