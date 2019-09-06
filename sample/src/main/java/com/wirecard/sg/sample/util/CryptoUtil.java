package com.wirecard.sg.sample.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptoUtil {

	public static class Bcrypt {
		private static BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		
		public static String encrypt(String plainText) {
			return bcrypt.encode(plainText);
		}
		
		public static boolean match(String plainText, String encodedText) {
			return bcrypt.matches(plainText, encodedText);
		}
	}

	public static class SHA256 {
		private static MessageDigest digest;
		
		public static byte[] getBytes(String plainText) throws NoSuchAlgorithmException {
			digest = MessageDigest.getInstance("SHA-256");
			return digest.digest(plainText.getBytes(StandardCharsets.UTF_8));
		}
		
		public static String encode(String plainText) throws NoSuchAlgorithmException {
			return toHexString(getBytes(plainText));
		}

		public static String toHexString(byte[] hashBytes) {
			StringBuilder sb = new StringBuilder();
			for (byte hashByte : hashBytes) {
				String tmp = Integer.toHexString(0xff & hashByte);
				if (tmp.length() == 1)
					sb.append('0');
				sb.append(tmp);
			}
			return sb.toString();
		}
	}

	public static String encryptPassword(String password) throws NoSuchAlgorithmException {
		return Bcrypt.encrypt(SHA256.encode(password));
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(encryptPassword("amw"));
			System.out.println(SHA256.encode("amw"));
			System.out.println(Bcrypt.encrypt("27aec416ed2b08ef628548d8d273b1e38918f7180c03dfa7b79d01581033ce97"));
			System.out.println(Bcrypt.match("27aec416ed2b08ef628548d8d273b1e38918f7180c03dfa7b79d01581033ce97", "$2a$10$GAux7S1WadHEF1KpPZ84c.GjBeIOutWFxMGO.IYsLOssJcN7cWrdG"));
			System.out.println(Bcrypt.match("27aec416ed2b08ef628548d8d273b1e38918f7180c03dfa7b79d01581033ce97", "$2a$10$HINaH6SZvdPoLfBspEqrE.W53wr3zQDvmMUJGAnkLpVuWF6Fr8zzi"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
