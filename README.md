# Wordle in Java
A console-based implementation of the popular word game Wordle, built entirely in Java.
Players have six attempts to guess a secret five-letter word, with feedback provided after each guess.
## Features
- Core Wordle gameplay mechanics (6 guesses, 5-letter words).
- Feedback after each guess:
Green → correct letter in correct spot.
Yellow → correct letter, wrong spot.
Gray → letter not in word.
Word list loaded via file I/O, ensuring replayability with a wide range of words.
Runs in the terminal with clear prompts and outputs.
## Tech Stack
Java (OOP, control flow, data handling)
File I/O (loading valid word lists)
## How to Run
Clone this repository:
git clone https://github.com/mehakkd/java_wordle.git
cd java_wordle
Compile the Java files:
javac Wordle.java
Run the game:
java Wordle
