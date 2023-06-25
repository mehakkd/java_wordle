package org.cis1200.wordle;

import javax.swing.*;
import java.awt.*;

/**
 * paintCompenent
 */
public class WordlePanel extends JPanel {
    private Wordle h = new Wordle();
    private char[][] gameboard;
    private Color[][] gameboardColors;
    private int width = 660;
    private int height = 750;
    public WordlePanel() {
    }

    public char[][] getGameboard() {
        return gameboard;
    }

    public Wordle getH() {
        return h;
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawRect(10, 20, width + 10, height);

        int widthBlock = width / 5;
        int heightBlock = (height / 6);

        // Draws board grid
        g.drawLine(widthBlock + 10, 20, widthBlock + 10, height + 20);
        g.drawLine((widthBlock * 2) + 15, 20, (widthBlock * 2) + 15, height + 20);
        g.drawLine((widthBlock * 3) + 20, 20, (widthBlock * 3) + 20, height + 20);
        g.drawLine((widthBlock * 4) + 20, 20, (widthBlock * 4) + 20, height + 20);

        g.drawLine(10, heightBlock + 20, width + 20, heightBlock + 20);
        g.drawLine(10, (heightBlock * 2) + 20, width + 20, (heightBlock * 2) + 20);
        g.drawLine(10, (heightBlock * 3) + 20, width + 20, (heightBlock * 3) + 20);
        g.drawLine(10, (heightBlock * 4) + 20, width + 20, (int)(heightBlock * 4) + 20);
        g.drawLine(10, (heightBlock * 5) + 20, width + 20, (heightBlock * 5) + 20);

        //display the contents of the 2D array on the screen

        gameboard = h.getGameboard();
        gameboardColors = h.getGameboardColors();
        //h.printGameState();

        for (int r = 0; r < gameboard.length; r++) {
            for (int c = 0; c < gameboard[0].length; c++) {
                //System.out.println("in for loop");
                char curChar = gameboard[r][c];
                Color col = gameboardColors[r][c];

                //set the color of the square appropriately
                g.setColor(col);
                g.fillRect(20 + ((135 * c)), 30 + (125 * r), widthBlock - 20, heightBlock - 20);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
                g.drawString(Character.toUpperCase(curChar) + "", 65 + (135 * c), 95 + (125 * r));
            }
        }
    }
}

