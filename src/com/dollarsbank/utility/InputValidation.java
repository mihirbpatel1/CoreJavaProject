package com.dollarsbank.utility;

import java.util.HashMap;
import java.util.Set;

public class InputValidation {

public static String passwordValidation(String password) {
		
		int count = 0;
		String notStrong = "";

		if (password.length() < 6) {
			count = 6 - password.length();
			notStrong += "Minimum required length is 6 characters. Please add " + count + " characters";
		}

		String numbers = "0123456789";
		String lower_case = "abcdefghijklmnopqrstuvwxyz";
		String upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		

		HashMap<Character, Boolean> map = new HashMap<>();
		map.put('n', false);
		map.put('l', false);
		map.put('u', false);
		


		for (int i = 0; i < password.length(); i++) {
			if (numbers.contains(password.substring(i, i + 1))) {
				map.replace('n', true);
			} else if (lower_case.contains(password.substring(i, i + 1))) {
				map.replace('l', true);
			} else if (upper_case.contains(password.substring(i, i + 1))) {
				map.replace('u', true);
			} 
		}
		
		
		Set<Character> set = map.keySet();
		for (char s : set) {
			if (map.get(s) == false) {
				if (s == 'n') {
					notStrong += "\nMissing a number.";
				} else if (s == 'l') {
					notStrong += "\nMissing a lower case letter.";
				} else if (s == 'u') {
					notStrong += "\nMissing a upper case letter.";
				} 
			}
		}
		
		if (notStrong.length()==0) {
			return "strong";
		} else {
			return notStrong;
		}
		
	}
	
	
	static String userNameValidation(String userName) {
		int count = 0;
		String userNameStrong = "";
		
		if (userName.length() < 6) {
			count = 10 - userName.length();
			userNameStrong += "Minimum required length is 6 characters. Please add " + count + " characters";
		}
		
		HashMap<Character, Boolean> map = new HashMap<>();
		map.put('n', false);
		map.put('l', false);

		if (userName.matches(".*[a-z].*")) {
			map.replace('l', true);
		}

		


		
		Set<Character> set = map.keySet();
		for (char s : set) {
			if (map.get(s) == false) {
				if (s == 'l') {
					userNameStrong += "\nMissing a lower case letter.";
				} 
			}
		}
		
		if (userNameStrong.length() == 0) {
			return "strong";
		} else {
			return userNameStrong;
		}
	}
}
