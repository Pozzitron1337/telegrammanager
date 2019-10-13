package Cryptography;




import java.security.MessageDigest;

public class Crypto {

    public static String SHA256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static String XORencryption(String text, String key){
        byte[] textBytes=text.getBytes();
        int textLength=text.length();

        byte[] keyBytes=key.getBytes();
        int keyLength=key.length();

        int j=0;
        byte[] cypherTextBytes=new byte[text.length()];
        while(j<text.length()){
            cypherTextBytes[j]=(byte)((textBytes[j]^keyBytes[j%key.length()])%128);
            j++;
        }
        return new String(cypherTextBytes);
    }





}
