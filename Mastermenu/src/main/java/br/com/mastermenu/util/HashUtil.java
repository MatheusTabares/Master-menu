package br.com.mastermenu.util;

import java.util.UUID;

public class HashUtil {
	 public static String generateHash(String password, String SALT) {
	        String encryptedPassoword;
	        encryptedPassoword = org.apache.commons.codec.digest.DigestUtils.sha512Hex(password + SALT);
	        return encryptedPassoword;
		 }
		
		 public static String generateSALT() {
		    // Gera um valor aleatório
			UUID uuid = UUID.randomUUID();
			String myRandom = uuid.toString();
			return myRandom.substring(0, 20); // Retorno os 20 primeiros caracteres.	
		 }
		    
	     public static String generateNewPassword() {
	        UUID uuid = UUID.randomUUID();
	        String myRandom = uuid.toString();
	        return myRandom.substring(0, 6);
	     }
}
