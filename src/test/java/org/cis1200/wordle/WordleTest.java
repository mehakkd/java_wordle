package org.cis1200.wordle;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class WordleTest {

    @Test
    public void testFindNextWord() {
        //call findNextWord to update getWordToGuess
        Wordle h = new Wordle();
        assertEquals(0, h.getUsrGuessesThisRound().size());
        assertNotEquals(null, h.getWordToGuess());
        String word1 = h.getWordToGuess();
        System.out.println(word1);

        Wordle h2 = new Wordle();
        assertEquals(0, h2.getUsrGuessesThisRound().size());
        assertNotEquals("error", h.getWordToGuess());
        String worde = h2.getWordToGuess();
        System.out.println("new object word " + worde);

        assertNotEquals(worde, word1);

        h.findNextWord();
        String word2 = h.getWordToGuess();
        System.out.println(word2);

        assertNotEquals(word1, word2);

        h.findNextWord();
        String word3 = h.getWordToGuess();
        System.out.println(word3);

        h.findNextWord();
        String word4 = h.getWordToGuess();
        System.out.println(word4);

        assertNotEquals(word1, word3);
        assertNotEquals(word3, word4);
        assertNotEquals(word2, word3);
        assertNotEquals(word2, word4);

    }

    @Test
    public void testWordToGuessList() {
        //start a wordle game
        Wordle h = new Wordle();
        assertEquals(h.getWordToGuessList().length, 5);
        assertEquals(h.getWordToGuess().charAt(0), h.getWordToGuessList()[0]);
        assertEquals(h.getWordToGuess().charAt(1), h.getWordToGuessList()[1]);
        assertEquals(h.getWordToGuess().charAt(2), h.getWordToGuessList()[2]);
        assertEquals(h.getWordToGuess().charAt(3), h.getWordToGuessList()[3]);
        assertEquals(h.getWordToGuess().charAt(4), h.getWordToGuessList()[4]);
    }

    @Test
    public void testUpdateGuessStringParamAndGetUsrGuessesAndRest() {
        Wordle w = new Wordle();
        w.printGameState();
        assertEquals(0, w.getUsrGuessesThisRound().size());

        //do nothing if the guess length is less than 5 or greater than 5
        w.checkAndUpdateGuess("like");
        w.printGameState();
        assertEquals(' ', w.getGameboard()[0][0]);
        assertEquals(0, w.getTries());
        w.checkAndUpdateGuess("likeskjhfjksd");
        w.printGameState();
        assertEquals(' ', w.getGameboard()[0][0]);
        assertEquals(0, w.getTries());

        w.checkAndUpdateGuess("likes");
        assertEquals(1, w.getUsrGuessesThisRound().size());
        w.printGameState();
        assertEquals('l', w.getGameboard()[0][0]);
        assertEquals(1, w.getTries());

        w.checkAndUpdateGuess("words");
        assertEquals(2, w.getUsrGuessesThisRound().size());
        w.printGameState();
        assertEquals('l', w.getGameboard()[0][0]);
        assertEquals('w', w.getGameboard()[1][0]);
        assertEquals(2, w.getTries());

        Wordle w2 = new Wordle();
        assertEquals(0, w2.getUsrGuessesThisRound().size());
        w2.checkAndUpdateGuess("mehak");
        assertEquals(1, w2.getUsrGuessesThisRound().size());

        w2.reset();
        assertEquals(0, w2.getUsrGuessesThisRound().size());
    }

    @Test
    public void testDuplicateLetters() {
        Wordle w = new Wordle();
        assertEquals('l', w.checkWordHasDuplicateLetters("hello"));
        assertEquals('e', w.checkWordHasDuplicateLetters("creek"));
        assertEquals(0, w.checkWordHasDuplicateLetters("mehak"));
        assertEquals('e', w.checkWordHasDuplicateLetters("beess"));
    }

    @Test
    public void testUndo() {
        Wordle w = new Wordle();
        String word = w.getWordToGuess();
        assertEquals(0, w.getUsrGuessesThisRound().size());

        w.checkAndUpdateGuess("likes");
        assertEquals(1, w.getUsrGuessesThisRound().size());
        assertEquals('l', w.getGameboard()[0][0]);
        assertEquals(1, w.getTries());
        w.undo();
        String word2 = w.getWordToGuess();
        assertEquals(0, w.getUsrGuessesThisRound().size());

        //undoing when no guesses have been made should do nothing
        w.undo();
        assertEquals(0, w.getUsrGuessesThisRound().size());
        assertEquals(word, word2);
    }


    //test that if there are duplicate letters guessed
    //in the wrong place and both are in the word, only
    //one should be yellow and the other gray
    //(this is how traditional wordle works)

    //also test that if there are duplicate letters guessed
    //one in the right place one in the wrong, the right one
    //is green and the wrong one is yellow
    @Test
    public void testDuplicateLettersYellowAndIsGuessRight() {
        Wordle w = new Wordle();
        w.setWordToGuess("alarm");
        w.wordToList();

        w.checkAndUpdateGuess("laram");
        assertEquals('l', w.getGameboard()[0][0]);

        assertEquals(Color.orange, w.getGameboardColors()[0][1]);
        assertEquals(Color.gray, w.getGameboardColors()[0][3]);
        assertEquals(Color.green.darker(), w.getGameboardColors()[0][4]);

        assertEquals(Color.orange, w.getGameboardColors()[0][0]);
        assertEquals(Color.orange, w.getGameboardColors()[0][2]);

        w.checkAndUpdateGuess("aalrm");
        assertFalse(w.isGuessRight(0));
        assertFalse(w.isGuessRight(1));
        assertEquals('a', w.getGameboard()[1][0]);

        assertEquals(Color.green.darker(), w.getGameboardColors()[1][0]);
        assertEquals(Color.orange, w.getGameboardColors()[1][1]);
        assertEquals(Color.orange, w.getGameboardColors()[1][2]);
        assertEquals(Color.green.darker(), w.getGameboardColors()[1][3]);
        assertEquals(Color.green.darker(), w.getGameboardColors()[1][4]);

        w.checkAndUpdateGuess("alarm");
        assertTrue(w.isGuessRight(2));
    }

    @Test
    public void testTooManyTriesDoesNothing() {
        Wordle w = new Wordle();

        assertEquals(0, w.getUsrGuessesThisRound().size());
        w.checkAndUpdateGuess("hello");
        assertEquals(1, w.getUsrGuessesThisRound().size());
        w.checkAndUpdateGuess("waves");
        assertEquals(2, w.getUsrGuessesThisRound().size());
        w.checkAndUpdateGuess("colds");
        assertEquals(3, w.getUsrGuessesThisRound().size());
        w.checkAndUpdateGuess("pains");
        assertEquals(4, w.getUsrGuessesThisRound().size());
        w.checkAndUpdateGuess("tales");
        assertEquals(5, w.getUsrGuessesThisRound().size());
        w.checkAndUpdateGuess("calls");
        assertEquals(6, w.getUsrGuessesThisRound().size());

        w.checkAndUpdateGuess("malls");
        assertEquals(6, w.getUsrGuessesThisRound().size());
        w.checkAndUpdateGuess("balls");
        assertEquals(6, w.getUsrGuessesThisRound().size());
        w.checkAndUpdateGuess("mehak");
        assertEquals(6, w.getUsrGuessesThisRound().size());
        w.checkAndUpdateGuess("rings");
        assertEquals(6, w.getUsrGuessesThisRound().size());

        assertFalse(w.getUsrGuessesThisRound().contains("malls"));
        assertFalse(w.getUsrGuessesThisRound().contains("balls"));
        assertFalse(w.getUsrGuessesThisRound().contains("mehak"));
        assertFalse(w.getUsrGuessesThisRound().contains("rings"));

        assertTrue(w.getUsrGuessesThisRound().contains("hello"));
        assertTrue(w.getUsrGuessesThisRound().contains("waves"));
        assertTrue(w.getUsrGuessesThisRound().contains("colds"));
        assertTrue(w.getUsrGuessesThisRound().contains("pains"));
        assertTrue(w.getUsrGuessesThisRound().contains("tales"));
        assertTrue(w.getUsrGuessesThisRound().contains("calls"));
    }

    @Test
    public void testCheckLetterInGuessNoDuplicates() {
        Wordle w = new Wordle();
        w.setWordToGuess("tubes");
        w.wordToList();

        w.checkAndUpdateGuess("tbbes");
        assertEquals(Color.GRAY, w.getGameboardColors()[0][1]);
        assertEquals(Color.green.darker(), w.getGameboardColors()[0][2]);
    }
}
