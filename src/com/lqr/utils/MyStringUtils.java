package com.lqr.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MyStringUtils {

	/**
	 * 使用MD5加密
	 * 
	 * @param value
	 * @return
	 */
	public static String getMD5Value(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 得到10进制的密文
			byte[] digest = md.digest(value.getBytes());
			// 需要转换成只考虑正数的16进制密文
			BigInteger bi = new BigInteger(1, digest);
			return bi.toString(16);
		} catch (NoSuchAlgorithmException e) {
			// 有异常则返回原值
			return value;
		}
	}

	/**
	 * 得到32位长度的UUID(去掉了-)
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
