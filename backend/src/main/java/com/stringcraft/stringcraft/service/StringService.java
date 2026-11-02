package com.stringcraft.stringcraft.service;

import org.springframework.stereotype.Service;

@Service
public class StringService {

    /**
     * 1. Reverse String using DSA two-pointer / in-place array swap approach
     */
    public String reverse(String text) {
        if (text == null) {
            return "";
        }
        char[] chars = text.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    /**
     * 2. Count Vowels (a, e, i, o, u case-insensitive)
     */
    public long countVowels(String text) {
        if (text == null) {
            return 0;
        }
        long count = 0;
        for (char c : text.toCharArray()) {
            char lower = Character.toLowerCase(c);
            if (lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u') {
                count++;
            }
        }
        return count;
    }

    /**
     * 3. Count Consonants (alphabetic chars that are not vowels)
     */
    public long countConsonants(String text) {
        if (text == null) {
            return 0;
        }
        long count = 0;
        for (char c : text.toCharArray()) {
            char lower = Character.toLowerCase(c);
            if (lower >= 'a' && lower <= 'z') {
                if (lower != 'a' && lower != 'e' && lower != 'i' && lower != 'o' && lower != 'u') {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 4. Count Digits (0-9)
     */
    public long countDigits(String text) {
        if (text == null) {
            return 0;
        }
        long count = 0;
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 5. Count Spaces (whitespace characters)
     */
    public long countSpaces(String text) {
        if (text == null) {
            return 0;
        }
        long count = 0;
        for (char c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 6. Remove Spaces
     */
    public String removeSpaces(String text) {
        if (text == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 7. Convert Case:
     * - UPPER: uppercase
     * - LOWER: lowercase
     * - TOGGLE: toggle case of each letter
     */
    public String convertCase(String text, String mode) {
        if (text == null) {
            return "";
        }
        if (mode == null || mode.trim().isEmpty()) {
            mode = "UPPER";
        }

        switch (mode.trim().toUpperCase()) {
            case "UPPER":
                return text.toUpperCase();
            case "LOWER":
                return text.toLowerCase();
            case "TOGGLE":
                StringBuilder sb = new StringBuilder();
                for (char c : text.toCharArray()) {
                    if (Character.isUpperCase(c)) {
                        sb.append(Character.toLowerCase(c));
                    } else if (Character.isLowerCase(c)) {
                        sb.append(Character.toUpperCase(c));
                    } else {
                        sb.append(c);
                    }
                }
                return sb.toString();
            default:
                throw new IllegalArgumentException("Invalid case mode: " + mode + ". Allowed values: UPPER, LOWER, TOGGLE");
        }
    }
}

