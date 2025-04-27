package pl.edu.agh.kis.pz1.stringManagement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringConcatenationTest {

    @Test
    public void testAddWords_NormalCase() {
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("to");
        words.add("the");
        words.add("world");
        String result = StringConcatenation.addWords(words);
        assertEquals("Hello to the world", result, "The words should be concatenated with spaces.");
    }

    @Test
    public void testAddWords_EmptyList() {
        List<String> words = new ArrayList<>();
        String result = StringConcatenation.addWords(words);
        assertEquals("", result, "The result should be an empty string for an empty list.");
    }

    @Test
    public void testAddWords_SingleWord() {
        List<String> words = new ArrayList<>();
        words.add("Hello");
        String result = StringConcatenation.addWords(words);
        assertEquals("Hello", result, "The result should be the same word when the list has one word.");
    }

    @Test
    public void testAddWords_TrimTrailingSpace() {
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("to");
        words.add("the");
        words.add("world");
        String result = StringConcatenation.addWords(words);
        assertEquals("Hello to the world", result, "The final string should not have trailing spaces.");
    }

}