package com.ryuuzakiumi.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Util {
	// String 값이면 int 타입인지 확인해보는 메소드
	// 127 -> true
	// 1A@S ->false

	public static int str2Int(String str) {
		// A50 -> 59
		int result;
		List<Character> chList = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				chList.add(str.charAt(i));
			}
		}
		StringBuilder stb = new StringBuilder();
		for (char cha : chList) {
			stb.append(cha);
		}
		if (stb.length() > 0) {
			result = Integer.parseInt(stb.toString());
		} else {
			return 0;
		}
		return result;
	}
	
	public static int str2Int2(String str) {
		String numberOnly = str.replaceAll("^[0-9", "");
		return Integer.parseInt(numberOnly);
	}
	
	public static boolean intCheck(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean intCheck2(String str) {
		boolean result = false;

		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {

			} else {
				return false;
			}

		}
		result = true;
		return result;
	}

	// ip 가져오기

	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// HTML 태그를 특수기호로 변경하기 < -> &lt > -> &gt
	public static String removeTag(String str) {
		str = str.replaceAll("<", "&lt");
		str = str.replaceAll(">", "&gt");

		return str;
	}

	// 엔터키 처리
	public static String addBR(String str) {
		return str.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
	}

	public static String maskIp(String ip) {

		if (ip.indexOf('.') != -1) {
			String[] parts = ip.split("\\.");
			parts[1] = "*";
			String maskedIp = String.join(".", parts);
			return maskedIp;

		} else {
			return ip;
		}

	}

	public static String ipMasking(String ip) {
		if (ip.indexOf('.') != -1) {
			StringBuffer sb = new StringBuffer();
			sb.append(ip.substring(0, ip.indexOf('.') + 1));
			sb.append("♡");
			sb.append(ip.substring(ip.indexOf('.', ip.indexOf('.') + 1)));
			return sb.toString();
		} else {
			return ip;
		}
	}

}
