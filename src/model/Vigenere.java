package model;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Vigenere {
    public char[] bangChuCaiTiengViet = {
            'A', 'Á', 'À', 'Ả', 'Ã', 'Ạ',
            'Ă', 'Ắ', 'Ằ', 'Ẳ', 'Ẵ', 'Ặ',
            'Â', 'Ấ', 'Ầ', 'Ẩ', 'Ẫ', 'Ậ',
            'B',
            'C',
            'D',
            'Đ',
            'E', 'É', 'È', 'Ẻ', 'Ẽ', 'Ẹ',
            'Ê', 'Ế', 'Ề', 'Ể', 'Ễ', 'Ệ',
            'G',
            'H',
            'I', 'Í', 'Ì', 'Ỉ', 'Ĩ', 'Ị',
            'K',
            'L',
            'M',
            'N',
            'O', 'Ó', 'Ò', 'Ỏ', 'Õ', 'Ọ',
            'Ô', 'Ố', 'Ồ', 'Ổ', 'Ỗ', 'Ộ',
            'Ơ', 'Ớ', 'Ờ', 'Ở', 'Ỡ', 'Ợ',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'U', 'Ú', 'Ù', 'Ủ', 'Ũ', 'Ụ',
            'Ư', 'Ứ', 'Ừ', 'Ử', 'Ữ', 'Ự',
            'V',
            'X',
            'Y', 'Ý', 'Ỳ', 'Ỷ', 'Ỹ', 'Ỵ',
            'a', 'á', 'à', 'ả', 'ã', 'ạ',
            'ă', 'ắ', 'ằ', 'ẳ', 'ẵ', 'ặ',
            'â', 'ấ', 'ầ', 'ẩ', 'ẫ', 'ậ',
            'b',
            'c',
            'd', 'đ',
            'e', 'é', 'è', 'ẻ', 'ẽ', 'ẹ',
            'ê', 'ế', 'ề', 'ể', 'ễ', 'ệ',
            'g',
            'h',
            'i', 'í', 'ì', 'ỉ', 'ĩ', 'ị',
            'k',
            'l',
            'm',
            'n',
            'o', 'ó', 'ò', 'ỏ', 'õ', 'ọ',
            'ô', 'ố', 'ồ', 'ổ', 'ỗ', 'ộ',
            'ơ', 'ớ', 'ờ', 'ở', 'ỡ', 'ợ',
            'p',
            'q',
            'r',
            's',
            't',
            'u', 'ú', 'ù', 'ủ', 'ũ', 'ụ',
            'ư', 'ứ', 'ừ', 'ử', 'ữ', 'ự',
            'v',
            'x',
            'y', 'ý', 'ỳ', 'ỷ', 'ỹ', 'ỵ'
    };
    public char bangChuCaiAlphabet[] = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };
    private boolean type=true; // kiểu bảng chữ: true= bangChuCaiTiengViet, false; bangChuCaiAlphabet

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String generateKey(String text,String key){
        StringBuilder sb=new StringBuilder();
        int i=0;
        while (i<text.length()){
            if(i==key.length()) i=0;
            sb.append(key.charAt(i));
            if (sb.length()==text.length()) {
                break;
            }else {
                i+=1;
            }
        }
        return sb.toString();
    }

    public int checkIndex(char c){
        char[] bangchu= type?bangChuCaiTiengViet:bangChuCaiAlphabet;
        for (int i = 0; i < bangchu.length; i++) {
            if(c==bangchu[i]){
                return i;
            }
        }
       return -1;
   }
    public String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        key=generateKey(plaintext,key);
        for (int i = 0; i < plaintext.length(); i++) {
            char ch=plaintext.charAt(i);
            // kiểm tra phải là ký tự hay không
            if(Character.isLetter(ch)){
                int indexStart;
                if(!type){ // nếu là bảng mã alphabet
                    indexStart= Character.isUpperCase(ch)? 0:26;
                    int indexMove=(checkIndex(ch)+checkIndex(key.charAt(i)))%26;
                    ciphertext.append(bangChuCaiAlphabet[indexMove+indexStart]);
                }else {
                    indexStart= Character.isUpperCase(ch)? 0:89;
                    int indexMove=(checkIndex(ch)+checkIndex(key.charAt(i)))%89;
                    ciphertext.append(bangChuCaiTiengViet[indexMove+indexStart]);
                }


            }else {
                ciphertext.append(ch);
            }
        }


        return ciphertext.toString();
    }

    public String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        key=generateKey(ciphertext,key);
        for (int i = 0; i < ciphertext.length(); i++) {
            char ch=ciphertext.charAt(i);
            // kiểm tra phải là ký tự hay không
            if(Character.isLetter(ch)){
                int indexStart;
                if(!type){ // nếu là bảng mã alphabet
                    indexStart= Character.isUpperCase(ch)? 0:26;
                    int indexMove=(checkIndex(ch)-checkIndex(key.charAt(i))+26)%26;
                    plaintext.append(bangChuCaiAlphabet[indexMove+indexStart]);
                }else {
                    indexStart= Character.isUpperCase(ch)? 0:89;
                    int indexMove=(checkIndex(ch)-checkIndex(key.charAt(i))+89)%89;
                    plaintext.append(bangChuCaiTiengViet[indexMove+indexStart]);
                }


            }else {
                plaintext.append(ch);
            }
        }

        return plaintext.toString();
    }
    public void encryptFile(String srcFile, String desFile, String key) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(srcFile, StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new FileWriter(desFile, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String encryptedLine = encrypt(line, key);
                writer.write(encryptedLine);
                writer.newLine();
            }
            System.out.println("ENCRYPTED");
        }
    }

    public void decryptFile(String srcFile, String desFile, String key) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(srcFile, StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new FileWriter(desFile, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String decryptedLine = decrypt(line, key);
                writer.write(decryptedLine);
                writer.newLine();
            }
            System.out.println("DECRYPTED");
        }
    }

    public static void main(String[] args) {
//        System.out.println(new Vigenere().checkIndex('a'));
//        System.out.println(new Vigenere().bangChuCai.length);
        String plainText="Thiết giáp Stryker 5 triệu USD do Mỹ sản xuất liên tục thúc thủ trước hỏa lực Nga.";
        String cipherText=new Vigenere().encrypt(plainText,"BC");
        System.out.println(cipherText);
        System.out.println(new Vigenere().decrypt(cipherText,"BC"));
//        System.out.println(new Vigenere().generateKey("AB CDEFa","KSKS"));
//        try {
//            new Vigenere().encryptFile("D:\\SV\\Code\\TEs\\src\\Main.java","D:\\SV\\Code\\TEs\\src\\Main1.java","Khóa");
//            new Vigenere().decryptFile("D:\\SV\\Code\\TEs\\src\\Main1.java","D:\\SV\\Code\\TEs\\src\\Main2.java","Khóa");
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }
}
