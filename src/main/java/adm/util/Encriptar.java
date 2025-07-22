package adm.util;

import java.security.MessageDigest;

public class Encriptar {
	
	/* Retorna un hash a partir de un tipo y un texto */
    public static String getHash(String txt, String hashType){
    	// Datos válidos para hashType(MD2, MD5, SHA-1, SHA-256, SHA-348, SHA-512)
    	String claveEncriptada = "";
        try {        	
        	MessageDigest md = MessageDigest.getInstance(hashType);
        	md.update(txt.getBytes("UTF-8"));
        	byte[] mb = md.digest();
        	claveEncriptada = java.util.Base64.getEncoder().encodeToString(mb);
     	    //claveEncriptada = String.valueOf(Hex.encodeHex(mb));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return claveEncriptada;
    }
 
    /* Retorna un hash MD5 a partir de un texto */
    public static String md5ConBase64(String txt) {
        return Encriptar.getHash(txt, "MD5");
    }
 
    /* Retorna un hash SHA-1 a partir de un texto */
    public static String sha1ConBase64(String txt) {    	
        return Encriptar.getHash(txt, "SHA-1");
    }
    
    /* Retorna un hash SHA-256 a partir de un texto */
    public static String sha256ConBase64(String txt) {    	
        return Encriptar.getHash(txt, "SHA-256");
    }
    
    /* Retorna un hash SHA-512 a partir de un texto */
    public static String sha512ConBase64(String txt) {
        return Encriptar.getHash(txt, "SHA-512");
    }
	
//	/* Retorna un hash a partir de un tipo y un texto */
//    public static String getHash(String txt, String hashType) {
//        try {
//            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
//            byte[] array = md.digest(txt.getBytes());
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < array.length; ++i) {
//                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
//                        .substring(1, 3));
//            }
//            return sb.toString();
//        } catch (java.security.NoSuchAlgorithmException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }
 
//    /* Retorna un hash MD5 a partir de un texto */
//    public static String md5(String txt) {
//        return Encriptar.getHash(txt, "MD5");
//    }
// 
//    /* Retorna un hash SHA1 a partir de un texto */
//    public static String sha1(String txt) {
//        return Encriptar.getHash(txt, "SHA1");
//    }
//    
//    public static String md5ConBase64(String clave){
//    	String claveEncriptada = "";    	
//    	try{
//    		MessageDigest md5 		= MessageDigest.getInstance("MD5");
//    		md5.update(clave.getBytes("UTF-8"));
//    		byte claveByte[] 		= md5.digest();
//    		claveEncriptada = java.util.Base64.getEncoder().encodeToString(claveByte);
//    	}catch(Exception ex){
//    		System.out.println("Error: aca.util.Encriptar|"+ex);
//    	}
//    	
//    	return claveEncriptada;
//    }

}
