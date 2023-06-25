package org.cis1200.wordle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * main GUI program
 * implements Runnable
 * draws stuff on screen
 * handles event listeners
 */

public class RunWordle implements Runnable {

    private Wordle w;

    public void run() {
        final JFrame frame = new JFrame("Wordle");

        frame.setBackground(Color.black);

        frame.setSize(900,1100);

        frame.setLayout(new GridLayout(1, 2));

        frame.setPreferredSize(new Dimension(1400, 950));

        WordlePanel wp = new WordlePanel();
        wp.setBackground(Color.black);
        frame.add(wp);
        w = wp.getH();

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        frame.setFocusable(true);

        /**
         * Listens for keys typed. Updates the model, then updates the game
         * board based off of the updated model.

        addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent arg0) {
                char c = arg0.getKeyChar();
                //update the 2D arrays with the new char entered by the user
                w.checkAndUpdateGuess(c);

                //repaint the panel after each char is entered
                wp.repaint();
            }
        });
        frame.requestFocusInWindow();
        **/

        JPanel combo = new JPanel();
        combo.setLayout(new GridLayout(4, 1));
        combo.setBackground(Color.BLACK);

        JLabel intro = new JLabel("Welcome to Mehak's Wordle!", SwingConstants.CENTER);
        intro.setForeground(Color.white);
        combo.add(intro);
        intro.setFont(new Font("Times New Roman", Font.BOLD, 30));

        JTextField guess = new JTextField();
        guess.setBackground(Color.black);
        guess.setForeground(Color.white);
        guess.setBorder(BorderFactory.createLineBorder(Color.white));
        combo.add(guess);
        guess.setPreferredSize(new Dimension(200, 400));

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 3));
        combo.add(buttons);

        JButton submit = new JButton("SUBMIT");
        submit.setOpaque(true);
        submit.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        submit.setFont(new Font("Times New Roman", Font.BOLD, 20));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        buttons.add(submit);

        JButton restart = new JButton("RESTART");
        restart.setOpaque(true);
        restart.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        restart.setFont(new Font("Times New Roman", Font.BOLD, 20));
        restart.setBackground(Color.BLACK);
        restart.setForeground(Color.WHITE);
        buttons.add(restart);

        JButton undo = new JButton("UNDO");
        undo.setOpaque(true);
        undo.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        undo.setFont(new Font("Times New Roman", Font.BOLD, 20));
        undo.setBackground(Color.BLACK);
        undo.setForeground(Color.WHITE);
        buttons.add(undo);

        JPanel saveLoad = new JPanel();
        saveLoad.setLayout(new GridLayout(1, 2));
        combo.add(saveLoad);

        JButton save = new JButton("SAVE");
        save.setOpaque(true);
        save.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        save.setFont(new Font("Times New Roman", Font.BOLD, 20));
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        saveLoad.add(save);

        JButton load = new JButton("LOAD");
        load.setOpaque(true);
        load.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        load.setFont(new Font("Times New Roman", Font.BOLD, 20));
        load.setBackground(Color.BLACK);
        load.setForeground(Color.WHITE);
        saveLoad.add(load);

        JOptionPane.showMessageDialog(frame, "Welcome to Mehak's Wordle!\n" +
                "This game is a type of implementation of the game Wordle, with the user " +
                "typing out their entire word to guess and then submitting it to be checked.\n\n" +
                "Goal: Guess the mystery 5-letter word in less than 6 attempts " +
                "(before the grid is filled).\n\n" +
                "Enter your 5-letter word guess in the box on the right of the " +
                "screen by typing out your guess" +
                " and click SUBMIT to enter it.\n" +
                "*Do not enter guesses with characters that are not letters. The " +
                "mystery word only contains" +
                " letters and entering any other character will only disadvantage you.*\n" +
                "*Guesses shorter or longer than this length will do nothing.*\n\n" +
                "This game follow the traditional rules of Wordle. The below instructions " +
                "explain those in more depth. \n" +
                "   If the word you guessed shares a letter with the mystery word in " +
                "that location, the box " +
                "containing that letter will turn green.\n" +
                "   If the word you guessed shares a letter with the mystery word but " +
                "not in the location that " +
                "you entered the letter, the box containing that letter will turn yellow.\n" +
                "   If the word you guessed does not share the letter you entered, " +
                "that letter's box will " +
                "turn red.\n" +
                "   If the you guess two letters and the mystery word contains both, " +
                "but you guess the " +
                "first one in the wrong space, only one will turn yellow. \n\n\n" +
                "Click RESTART to begin another round of the game with a new word.\n\n" +
                "To undo a previous guess, click UNDO.\n\n" +
                "If you want to save a game to come back to it later, click SAVE.\n\n" +
                "To load that game, click LOAD. Good luck!");

        /**
         * when the submit button is clicked, extract the text
         * from the text field and store it in a string
         * "userGuess". use this string to update the 2D
         * arrays and repaint the WordlePanel accordingly
         */
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //but should this be done only when the button is clicked?

                /*
                if (w.getTries() >= 6) {
                    //do something to show the word it was
                    w.reset();
                    System.out.println("Final tries value reached " + w.getTries());
                } else {
                }
                 */
                    String userGuess = guess.getText();
                    //System.out.println("In actionPerformed, here is word: " + userGuess);
                    int oldTries = w.getTries();
                    w.checkAndUpdateGuess(userGuess);
                    //System.out.println("Second value tries: " + w.getTries());
                    //System.out.println("Is correct word? : " + w.isGuessRight(oldTries));
                    //w.printGameState();
                    //for testing
                    //System.out.println("hello");
                    //System.out.println((wp.getGameboard()[0].toString()) + ": after call");
                    wp.repaint();
                    //w.setTries(w.getTries()+1);
                    guess.setText("");

                if (w.isGuessRight(oldTries)) {
                    //do something to show the word it was
                    //w.reset();
                    System.out.println("Final tries value reached " + w.getTries());
                    intro.setText("You won in " + w.getTries() + " tries! Click RESTART " +
                            "to play again.");
                } else if (w.getTries() == 6) {
                    System.out.println("Final tries value reached " + w.getTries());
                    intro.setText("You lost. The word was "+ w.getWordToGuess()+ ".\nClick 'restart' to play again.");
                }
            }
        });

        restart.addActionListener(e -> {
            intro.setText("Welcome to Mehak's Wordle!");
            w.reset();
            wp.repaint();
        });

        undo.addActionListener(e -> {
            intro.setText("Welcome to Mehak's Wordle!");
            w.undo();
            wp.repaint();
            guess.setText("");
        });

        save.addActionListener(e -> {
            w.save();
        });

        load.addActionListener(e -> {
            w.load();
            wp.repaint();
        });

        frame.add(combo);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


}
