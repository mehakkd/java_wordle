package org.cis1200.wordle;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * this class deals with game logic
 */

public class Wordle {
    //a 2D array to represent the gameboard: only contains
    //the letters of the user's guesses
    private char[][] gameboard = new char[6][5];

    //a 2D array to represent the colors of each square
    //in the actual wordle game: only contains Color
    //objects based on the accuracy of the user's
    //guesses
    private Color[][] gameboardColors = new Color[6][5];

    private String wordToGuess;

    private char[] wordToGuessList = new char[5];

    //how many tries have happened so far
    //max should be 5 (really 6, but it starts at 0)
    private int tries = 0;

    //only populate this list of words once
    private static List<String> wordsList = Wordle.populateWordsList();

    private List<String> usrGuessesThisRound = new LinkedList<>();

    //have a static method to read the file, get the words list b/c this should
    //only ever be run once
    private static List populateWordsList() {
        List<String> w = new LinkedList<>();
        try {
            File wordsFile = new File("files/5letterwords");
            Scanner scan = new Scanner(wordsFile);
            while (scan.hasNextLine()) {
                w.add(scan.nextLine());
            }
            scan.close();
        } catch (IOException e) {
            System.out.println("IOException, exception caught.");
        }
        return w;
    }

    public Wordle() {
        reset();
    }

    //to be used when loading game
    public Wordle(String wordToGuess, int tries) {
        this.wordToGuess = wordToGuess;
        this.tries = tries;

    }

    public Color[][] getGameboardColors() {
        return gameboardColors;
    }

    public char[][] getGameboard() {
        return gameboard;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public char[] getWordToGuessList() {
        return wordToGuessList;
    }

    public List<String> getUsrGuessesThisRound() {
        return usrGuessesThisRound;
    }

    //sets wordToList equal to the contents of wordToGuess
    public void wordToList() {
        for (int i = 0; i < 5; i ++) {
            wordToGuessList[i] = wordToGuess.charAt(i);
        }
    }

    public void findNextWord() {
        int fileLength = 1317;
        //initalize the word with a base word, error (can track if the word has not
        //been properly found later in this method by checking if it equals error
        String word;

        Random rand = new Random();
        int intRandom = rand.nextInt(fileLength);

        word = wordsList.get(intRandom);

        wordToGuess = word;
    }

    //called every time a new Wordle object is created
    public void reset() {
        findNextWord();
        //calling this method gets the next word and sets
        //wordToGuess equal to it
        wordToList();
        tries = 0;
        usrGuessesThisRound = new LinkedList<>();
        //set the default color of every cell of gameboard colors as black
        //and the default char value as empty
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 5; c++) {
                gameboardColors[r][c] = Color.darkGray;
                gameboard[r][c] = ' ';
            }
        }
    }

    /**
     * runs when the "undo" button is clicked:
     * gets rid of the last guess the user made
     * do nothing if the amount of guesses so far is nothing
     * should remain in the existing game, not change the word
     * to be guessed, even if you undo one guess to zero
     */
    public void undo() {
        int len = usrGuessesThisRound.size();
        if (len != 0) {
            //remove the last guess from the list of guesses, reduce tries by one,
            //and get rid of this guess from both gameboards
            usrGuessesThisRound.remove(len - 1);
            tries--;
            char[] curArrayChars = gameboard[len - 1];
            Color[] curArrayColors =  gameboardColors[len - 1];
            for (int i = 0; i < 5; i++) {
                curArrayChars[i] = ' ';
                curArrayColors[i] = Color.darkGray;
            }
        }
    }

    //the file to be written to/read
    File file = new File("gameSaved.txt");

    /**
     * save the current game state to the file
     */
    public void save() {
        try {
            file.delete();
            BufferedWriter save = new BufferedWriter(new FileWriter(file));
            save.write(wordToGuess);
            save.newLine();
            save.write(tries + "");
            save.newLine();
            for (int i = 0; i < 5; i ++) {
                for (int j = 0; j < 5; j ++) {
                    save.write(gameboard[i][j]);
                }
            }
            save.close();
        } catch (IOException e) {
            System.out.println("IOException caught while writing to file.");
        }
    }

    /**
     * load the current game state to the file
     */
    public void load() {
        try {
            BufferedReader load = new BufferedReader(new FileReader(file));
            String lastWordToGuess = load.readLine();
            System.out.println("Word to guess from last game: " + lastWordToGuess);

            int newTries = Integer.parseInt((load.readLine()));

            file.delete();
            reset();
            wordToGuess = lastWordToGuess;
            wordToList();

            String gameboardState = load.readLine();
            //System.out.println("State of gameboard from last game: " + gameboardState);

            //update the state of the gameboard once more
            int k = 0;
            while (k < 25) {
                String curWord = "";
                for (int j = 0; j < 5; j++) {
                    char curChar = gameboardState.charAt(j + k);
                    //System.out.println("Current char: "+ curChar);
                    if (curChar != ' ') {
                        curWord += curChar;
                    }
                }
                //System.out.println(curWord);
                checkAndUpdateGuess(curWord);
                k += 5;
            }
            tries = newTries;

        } catch (IOException e) {
            System.out.println("IOException caught while reading file.");
        }
    }


    public static void main(String [] args) {

    }

    //only to be used if the letter is not at the correct pos but might still be
    //in the word
    private boolean checkLetterInGuess(char c) {
        for (char ltg : wordToGuessList) {
            if (ltg == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the duplicate letter the word has, 0 if no duplicate letter exist for
     * that word. if word has multiple duplicate letters, returns the first
     * duplicate letter.
     */
    public char checkWordHasDuplicateLetters(String guess) {
        char l1 = guess.charAt(0);
        char l2 = guess.charAt(1);
        char l3 = guess.charAt(2);
        char l4 = guess.charAt(3);
        char l5 = guess.charAt(4);

        if (l1 == l2) {
            return l1;
        } else if (l2 == l3) {
            return l2;
        } else if (l3 == l4) {
            return l3;
        } else if (l4 == l5) {
            return l4;
        } else if (l5 == l1) {
            return l5;
        } else if (l5 == l2) {
            return l5;
        } else if (l5 == l3) {
            return l5;
        } else if (l4 == l1) {
            return l4;
        } else if (l4 == l2) {
            return l4;
        } else if (l3 == l1) {
            return l3;
        } else {
            return 0;
        }
    }

    public void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    /**
     * check if the guess is right; update the gameboard
     * and the gameboardColors
     * if the user inputted word is not of length 5, do nothing
     */
    public void checkAndUpdateGuess(String guess) {
        if (guess.length() == 5 && tries <= 5) {
            usrGuessesThisRound.add(guess);
            System.out.println("Word to guess: " + wordToGuess);
            int dupLetterYellowCount = 0;
            char dupLetter = checkWordHasDuplicateLetters(guess);
            char dupLetterRight = 0;
            //System.out.println(dupLetter + " is the duplicate letter");
            //System.out.println("In checkAndUpdatedGuess, here is word: " + guess);
            //iterate through the guess and compare it to the
            //word to be guessed
            for (int i = 0; i < 5; i++) {
                char curLetter = guess.charAt(i);

                //add each letter guessed to the gameboard
                gameboard[tries][i] = curLetter;

                //if the letter is guessed in the correct spot, the
                //grid should be green at that location
                if (curLetter == wordToGuessList[i]) {
                    gameboardColors[tries][i] = Color.green.darker();
                    if (curLetter == dupLetter) {
                        dupLetterRight = curLetter;
                    }
                } else if (checkLetterInGuess(curLetter)) {
                    //otherwise if the word just contains that letter
                    //but not at the right spot, the grid should be
                    //yellow at that location
                    //System.out.println("Current letter: " +
                    //curLetter + " Letter guessed correctly: " + dupLetterGuessedCorrectly);
                    gameboardColors[tries][i] = Color.orange;
                    if (dupLetter == curLetter) {
                        dupLetterYellowCount++;
                    }
                } else {
                    //otherwise if the letter isn't even in the
                    //word to be guessed at all, the grid should
                    //be gray at that location
                    gameboardColors[tries][i] = Color.GRAY;
                }
            }


            //if there is more than one yellow duplicate letter,
            //the other has to be set to gray
            int dupLetterYellowCountAppearance = 0;
            if (!(isGuessRight(tries))) {
                if (dupLetterYellowCount > 1) {
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (gameboard[i][j] == dupLetter) {
                                dupLetterYellowCountAppearance ++;
                                if (dupLetterYellowCountAppearance > 1) {
                                    gameboardColors[i][j] = Color.GRAY;
                                }
                            }
                        }
                    }
                }


                //if the duplicate letter has a right occurence after the
                //yellow occurence, make sure the yellow one is changed to
                //gray bc it meant to check the later one
                if (dupLetterRight != 0 && (checkWordHasDuplicateLetters(wordToGuess) == 0)) {
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (gameboard[i][j] == dupLetter &&
                                    gameboardColors[i][j].equals(Color.orange)) {
                                gameboardColors[i][j] = Color.GRAY;
                            }
                        }
                    }
                }

            }


            tries++;
                //System.out.println(tries);
        }
    }

    public boolean isGuessRight(int lastTries) {
        if (lastTries <= 5) {
            for (int i = 0; i < 5; i++) {
                if (!(gameboardColors[lastTries][i].equals(Color.green.darker()))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
    //check if the guess is right; update the gameboard
    //and the gameboardColors
    public void checkAndUpdateGuess(char curLetter) {
        List l = Arrays.asList(wordToGuessList);

        //iterate through the word to be guessed and see if the
        //letter guessed is in it
        for (int i = 0; i < 5; i++) {
            //add each letter guessed to the gameboard
            gameboard[tries][i] = curLetter;

            //if the letter is guessed in the correct spot, the
            //grid should be green at that location
            if (curLetter == wordToGuessList[i]) {
                gameboardColors[tries][i] = Color.GREEN;
            } else if (l.contains(curLetter)) {
                //otherwise if the word just contains that letter
                //but not at the right spot, the grid should be
                //yellow at that location
                gameboardColors[tries][i] = Color.YELLOW;
            } else {
                //otherwise if the letter isn't even in the
                //word to be guessed at all, the grid should
                //be gray at that location
                gameboardColors[tries][i] = Color.GRAY;
            }
        }

        tries++;
    }
     */

    public int getTries() {
        return tries;
    }

    /**
     * printGameState prints the current game state
     * for debugging.
     */
    public void printGameState() {
        System.out.println("This is try #: " + tries);
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[i].length; j++) {
                System.out.print(gameboard[i][j]);
                if (j < 5) {
                    System.out.print(" | ");
                }
            }
            if (i < 6) {
                System.out.println("\n---------------------");
            }
        }
    }
}
