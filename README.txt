=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: mehakkd
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays - I use 2D Arrays to represent my gameboard and the gameboardColors
  (colors representing if the user's guess for each letter was wrong, right,
   or contained in the word but not in that exact position). I update these
   arrays over the course of the game and display their contents on the
   WordlePanel. I use a 2D array of type char for the gameboard as each character
   of the user's guess, each letter, is easily stored as a char. I use a 2D array of
   type Color for the gameboardColors so I can easily set each square to be of that
   object type's color in WordlePanel.java.

  2. JUnit Testing - I use JUnit Testing to test the game logic and methods I wrote
  in the Wordle class. I test the public methods in my Wordle class, all independent
  of the use of the GUI elements of my project, and test for edge cases. I have
  more than five distinct tests.

  3. Collections - I store all of the user's guesses for each game in a Collection
  (a List, specifically a LinkedList, of type String). This allows the user to "undo"
  a guess they made, resizing the collection over the course of a game. The user can
  undo unlimited times up until the reach the point where there are no guesses from that
  game to undo. Each new round of the game, and each new game, has its own Collection
  of user guesses.

  4. File I/O - I use File I/O as a part of the save/load functionality of my game.
  When the user saves a game, I write relevant information from that game into a file.
  Then, in the future, if the user loads that game, I read the file and update the
  game state accordingly to reflect that past game.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

    The Wordle.java class contains all the game logic that is a part of my game.
    It stores and modifys the 2D arrays which are the gameboard and the gameboardColors
    (colors representing if the user's guess for each letter was wrong, right,
    or contained in the word but not in that exact position). It contains all
    functionatlity for what to do if the undo, restart, save, load, and submit
    buttons are hit in terms of what changes need to be made to these arrays.

    The WordlePanel.java class is a representation of the actual gameboard on the
    screen where the wordle game will take place. It extends the JPanel class
    and has a paintComponent method which overrides this method in its parent class.
    This method draws the actual gameboard and fills it with the colors and values
    from the gameboard and gameboardColors 2D arrays.

    The RunWordle.java class is the main GUI program, implementing Runnable.
    It draws all JButtons, the JTextField, and the WorldlePanel. It also
    handles event listeners for all of the buttons.

    The Game.java class runs the game by creating an instance of the RunWordle
    class.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

    Yes. Initially, I had attempted to create a different game, and then switched
    to Wordle early last week. When I first started out, I was having trouble
    visualizing where each part of my code would belong. I went to OH and a TA
    helped me understand how I should structure my files and what the role of
    each file would be. This really helped me make greater strides in the rest
    of the project!

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  I personally like how I organized my code into three files, as it really helped
  me better understand the structure of my program and how all pieces would fit
  together to ultimately run the code. I made sure to encapsulate private state
  and implemented getter and setter methods to get access to variables instead of
  just returning a public variable. If given the chance, I don't know if I would
  refactor anything, but I would like to try and implement "traditional" Wordle
  in which the user types in one letter at a time instead of the whole word at
  once.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

    I used a tutorial to figure out how to read a file using the Scanner class.
    https://www.java67.com/2012/11/how-to-read-file-in-java-using-scanner-example.html

    I also used part of a file of 5-letter words to create my "5letterwords" file.
    https://www-cs-faculty.stanford.edu/~knuth/sgb-words.txt

    Additionally, I used the JavaDocs greatly, particularly for the BufferedReader,
    BufferedWriter, JButton, JTextField, JPanel, and JFrame classes.