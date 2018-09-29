package com.grvtech.dis.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.xml.bind.DatatypeConverter;

/***
 * Encryption and Decryption of String data; PBE(Password Based Encryption and
 * Decryption)
 * 
 * @author Vikram
 */
public class CryptoUtil {

	private static final String secret = "475256";

	static Cipher ecipher;
	static Cipher dcipher;
	// 8-byte Salt
	static byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3,
			(byte) 0x03 };
	// Iteration count
	static int iterationCount = 19;

	public CryptoUtil() {

	}

	/**
	 *
	 * @param secretKey
	 *            Key used to encrypt data
	 * @param plainText
	 *            Text input to be encrypted
	 * @return Returns encrypted text
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.spec.InvalidKeySpecException
	 * @throws javax.crypto.NoSuchPaddingException
	 * @throws java.security.InvalidKeyException
	 * @throws java.security.InvalidAlgorithmParameterException
	 * @throws java.io.UnsupportedEncodingException
	 * @throws javax.crypto.IllegalBlockSizeException
	 * @throws javax.crypto.BadPaddingException
	 *
	 */
	public static String encrypt(String secretKey, String plainText) throws NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		// Key generation for enc and desc
		KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
		SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
		// Prepare the parameter to the ciphers
		AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

		// Enc process
		ecipher = Cipher.getInstance(key.getAlgorithm());
		ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
		String charSet = "UTF-8";
		byte[] in = plainText.getBytes(charSet);
		byte[] out = ecipher.doFinal(in);
		String encStr = new String(Base64.getEncoder().encode(out));
		return encStr;
	}

	/**
	 * @param secretKey
	 *            Key used to decrypt data
	 * @param encryptedText
	 *            encrypted text input to decrypt
	 * @return Returns plain text after decryption
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.spec.InvalidKeySpecException
	 * @throws javax.crypto.NoSuchPaddingException
	 * @throws java.security.InvalidKeyException
	 * @throws java.security.InvalidAlgorithmParameterException
	 * @throws java.io.UnsupportedEncodingException
	 * @throws javax.crypto.IllegalBlockSizeException
	 * @throws javax.crypto.BadPaddingException
	 */
	public static String decrypt(String secretKey, String encryptedText) throws NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, IOException {
		// Key generation for enc and desc
		KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
		SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
		// Prepare the parameter to the ciphers
		AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
		// Decryption process; same key will be used for decr
		dcipher = Cipher.getInstance(key.getAlgorithm());
		dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		byte[] enc = Base64.getDecoder().decode(encryptedText);
		byte[] utf8 = dcipher.doFinal(enc);
		String charSet = "UTF-8";
		String plainStr = new String(utf8, charSet);
		return plainStr;
	}

	private static String constructKey() {
		byte[] bytes = DatatypeConverter.parseHexBinary(secret);
		String result = new String(bytes);
		return result;
	}

	private static String getChar(int n) {
		return String.valueOf(Character.toChars(n));
	}

	public static int getNumber(int pool) {
		Random rn = new Random();
		int r = 0;
		if (pool == 1) {
			int min = 48;
			int max = 57;
			r = rn.nextInt(max - min + 1) + min;
			r = r - min;
		} else if (pool == 2) {
			int min = 65;
			int max = 90;
			r = rn.nextInt(max - min + 1) + min;
			r = r - min;
		} else {
			int min = 97;
			int max = 122;
			r = rn.nextInt(max - min + 1) + min;
			r = r - min;
		}

		return r;
	}

	public static int getCharCode(int n, int p) {
		if (p == 1) {
			return n + 48;
		} else if (p == 2) {
			return n + 65;
		} else {
			return n + 97;
		}
	}

	public static ArrayList<Integer> combin(int sum, int factors) {
		new ArrayList<>();
		ArrayList<Integer> facts = new ArrayList<>(factors);
		for (int i = 1; i <= factors; i++) {
			if (i == factors) {
				facts.add(sum - getArraySum(facts));
			} else {
				Random rn = new Random();
				int min = 1;
				int max = sum - getArraySum(facts) - (factors - i);
				int r = rn.nextInt(max - min + 1) + min;
				System.out.println(min + "   " + max + "      " + r + "      " + getArraySum(facts));
				facts.add(r);
			}

		}
		int st = 0;
		for (int k = 0; k < facts.size(); k++) {
			System.out.println("el" + k + ":" + facts.get(k));
			st += facts.get(k);
		}
		System.out.println("total:" + st);
		return facts;
	}

	public static int getArraySum(ArrayList<Integer> arr) {
		int result = 0;
		for (int i = 0; i < arr.size(); i++) {
			result += arr.get(i);
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		CryptoUtil cryptoUtil = new CryptoUtil();
		String key = constructKey();
		String plain = "20180722";
		String enc = cryptoUtil.encrypt(key, plain);
		System.out.println("Original text: " + plain);
		System.out.println("Encrypted text: " + enc);
		String plainAfter = cryptoUtil.decrypt(key, enc);
		System.out.println("Original text after decryption: " + plainAfter);

		int sum = 0;
		for (int i = 0; i < 4; i++) {
			Random rn = new Random();
			int r = rn.nextInt(3) + 1;
			int p = getNumber(r);
			System.out.println("code : " + getChar(getCharCode(p, r)));
			System.out.println("num : " + p);
			sum += p;
		}
		System.out.println("sum : " + sum);

		// combin(57, 4);

		String base = "0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
		String Licence = "";
		ArrayList<Integer> set1 = combin(47, 6);
		ArrayList<Integer> set2 = combin(52, 6);
		ArrayList<Integer> set3 = combin(56, 6);
		for (int j = 0; j < set1.size(); j++) {
			// System.out.print(base.substring(set1.get(j), set1.get(j) + 1));
			Licence += base.substring(set1.get(j), set1.get(j) + 1);
		}
		// System.out.print("-");
		Licence += "-";
		for (int j = 0; j < set2.size(); j++) {
			// System.out.print(base.substring(set2.get(j), set2.get(j) + 1));
			Licence += base.substring(set2.get(j), set2.get(j) + 1);
		}
		// System.out.print("-");
		Licence += "-";
		for (int j = 0; j < set3.size(); j++) {
			// System.out.print(base.substring(set3.get(j), set3.get(j) + 1));
			Licence += base.substring(set3.get(j), set3.get(j) + 1);
		}

		System.out.print(Licence);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

		String d1 = "20180702";
		String d2 = "20181102";

		String tt = "E1916B-Hc4324-U11121";

		Date now = new Date();
		String encd1 = cryptoUtil.encrypt(tt, d1);
		String encd2 = cryptoUtil.encrypt(tt, d2);
		System.out.println();
		System.out.println("date 1 encripted : " + encd1);
		System.out.println("date 2 encripted : " + encd2);

		System.out.println("date now  : " + sdf1.format(now));

		Date dd1 = sdf.parse(cryptoUtil.decrypt(tt, encd1));
		Date dd2 = sdf.parse(cryptoUtil.decrypt(tt, encd2));
		if (now.after(dd1) && now.before(dd2)) {
			System.out.println("LICENCE IS VALID");
		} else {
			System.out.println("LICENCE NOT VALID");
		}

		String prehash = "1" + "E1916B-Hc4324-U11121" + encd1 + encd2;
		// String prehash = "1" + "E1916B-Hc4324-U11121";

		System.out.println(Base64.getEncoder().encodeToString(prehash.getBytes()));

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(prehash.getBytes(StandardCharsets.UTF_8));

		System.out.println();
		System.out.println(bytesToHex(encodedhash));

	}

	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}