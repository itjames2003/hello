package com.js.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

public class BCryptPasswordEncoderUtils {
	private static BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
	public static String bCryptPassword(String str){
		return bc.encode(str);
	}

	//public static void main(String[] args) {
//		String ss="456";
//		String s = bCryptPassword(ss);
//		System.out.println(s);

	//}
}
