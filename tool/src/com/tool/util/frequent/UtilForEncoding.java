package com.tool.util.frequent;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class UtilForEncoding {

	public static String encode(String url, String charset) {
		try {
			return URLEncoder.encode(url, charset);
		} catch (Exception e) {
			return null;
		}
	}

	public static String decode(String url, String charset) {
		try {
			return URLDecoder.decode(url, charset);
		} catch (Exception e) {
			return null;
		}
	}

	public static String parseByte2To16(byte bytes[]) {
		try {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				String hex = Integer.toHexString(bytes[i] & 0xFF);
				if (hex.length() == 1) {
					hex = '0' + hex;
				}
				buffer.append(hex.toUpperCase());
			}
			return buffer.toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static byte[] parseHex16To2(String content) {
		try {
			byte[] result = new byte[content.length() / 2];
			for (int i = 0; i < content.length() / 2; i++) {
				int high = Integer.parseInt(content.substring(i * 2, i * 2 + 1), 16);
				int low = Integer.parseInt(content.substring(i * 2 + 1, i * 2 + 2), 16);
				result[i] = (byte) (high * 16 + low);
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}
}
