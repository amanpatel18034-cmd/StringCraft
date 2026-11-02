package com.stringcraft.stringcraft.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringServiceTest {

    private StringService service;

    @BeforeEach
    void setUp() {
        service = new StringService();
    }

    @Test
    @DisplayName("1. Reverse String")
    void testReverse() {
        assertEquals("olleh", service.reverse("hello"));
        assertEquals("", service.reverse(""));
        assertEquals("", service.reverse(null));
        assertEquals("racecar", service.reverse("racecar"));
        assertEquals("dcba", service.reverse("abcd"));
    }

    @Test
    @DisplayName("2. Count Vowels")
    void testCountVowels() {
        assertEquals(2, service.countVowels("hello"));
        assertEquals(5, service.countVowels("AEIOU"));
        assertEquals(0, service.countVowels("rhythm"));
        assertEquals(0, service.countVowels(""));
    }

    @Test
    @DisplayName("3. Count Consonants")
    void testCountConsonants() {
        assertEquals(3, service.countConsonants("hello"));
        assertEquals(0, service.countConsonants("aeiou"));
        assertEquals(6, service.countConsonants("rhythm"));
        assertEquals(0, service.countConsonants("12345!@#"));
    }

    @Test
    @DisplayName("4. Count Digits")
    void testCountDigits() {
        assertEquals(4, service.countDigits("Code1234"));
        assertEquals(0, service.countDigits("NoDigitsHere"));
        assertEquals(5, service.countDigits("98765"));
    }

    @Test
    @DisplayName("5. Count Spaces")
    void testCountSpaces() {
        assertEquals(2, service.countSpaces("Hello World Test"));
        assertEquals(4, service.countSpaces(" a b c "));
        assertEquals(0, service.countSpaces("NoSpaces"));
    }

    @Test
    @DisplayName("6. Remove Spaces")
    void testRemoveSpaces() {
        assertEquals("HelloWorld", service.removeSpaces("Hello World"));
        assertEquals("StringCraftApp", service.removeSpaces("  String   Craft  App  "));
        assertEquals("Test", service.removeSpaces("Test"));
    }

    @Test
    @DisplayName("7. Convert Case - UPPER, LOWER, TOGGLE")
    void testConvertCase() {
        assertEquals("HELLO WORLD", service.convertCase("Hello World", "UPPER"));
        assertEquals("hello world", service.convertCase("Hello World", "LOWER"));
        assertEquals("hELLO wORLD 123", service.convertCase("Hello World 123", "TOGGLE"));
        assertThrows(IllegalArgumentException.class, () -> service.convertCase("test", "INVALID"));
    }
}

