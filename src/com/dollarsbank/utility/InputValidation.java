package com.dollarsbank.utility;

import java.util.HashMap;
import java.util.Set;

public class InputValidation {

public static String passwordValidation(String password) {
		
		int count = 0;
		String validatePassword = "";

		if (password.length() < 8) {
			count = 8 - password.length();
			validatePassword += "8 characters. Please add " + count + " characters";
		}

		String numbers = "0123456789";
		String lowerCase = "abcdefghijklmnopqrstuvwxyz";
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String special_characters = "!@#$%^&*()-+><?/{}[]";

		HashMap<Character, Boolean> map = new HashMap<>();
		map.put('1', false);
		map.put('i', false);
		map.put('A', false);
		map.put('@', false);


		for (int i = 0; i < password.length(); i++) {
			if (numbers.contains(password.substring(i, i + 1))) {
				map.replace('1', true);
			} else if (lowerCase.contains(password.substring(i, i + 1))) {
				map.replace('i', true);
			} else if (upperCase.contains(password.substring(i, i + 1))) {
				map.replace('A', true);
			} else if (special_characters.contains(password.substring(i, i + 1))) {
				map.replace('@', true);
			}
		}
		
		
		Set<Character> set = map.keySet();
		for (char s : set) {
			if (map.get(s) == false) {
				if (s == '1') {
					validatePassword += "\nMissing a number.";
				} else if (s == 'i') {
					validatePassword += "\nMissing a lower case letter.";
				} else if (s == 'A') {
					validatePassword += "\nMissing a upper case letter.";
				} else if (s == '@') {
					validatePassword += "\nMissing a special character - !@#$%^&*()-+><?/{}[]";
				}
			}
		}
		
		if (validatePassword.length()==0) {
			return "strong";
		} else {
			return validatePassword;
		}
		
	}
	
	
	static String userNameValidation(String userName) {
		int count = 0;
		String validateUserName = "";
		
		if (userName.length() < 10) {
			count = 10 - userName.length();
			validateUserName += "Minimum required length is 10 characters. Please add " + count + " characters";
		}
		
		HashMap<Character, Boolean> map = new HashMap<>();
		map.put('1', false);
		map.put('i', false);

		if (userName.matches(".*[a-z].*")) {
			map.replace('i', true);
		}

		if (userName.matches(".*[0-9].*")) {
			map.replace('1', true);
		}


		
		Set<Character> set = map.keySet();
		for (char s : set) {
			if (map.get(s) == false) {
				if (s == '1') {
					validateUserName += "\nMissing a number.";
				} else if (s == 'i') {
					validateUserName += "\nMissing a lower case letter.";
				} 
			}
		}
		
		if (validateUserName.length() == 0) {
			return "strong";
		} else {
			return validateUserName;
		}
	}
}
