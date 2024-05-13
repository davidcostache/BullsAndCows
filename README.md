# Bulls and Cows Game

This repository contains a Java implementation of the classic "Bulls and Cows" game, also known as "Mastermind". This project is developed as part of my learning journey on the Hyperskill platform and represents my third project there.

## About Bulls and Cows

Bulls and Cows is a logic game where the player needs to crack the secret code consisting of a series of unique symbols. Each guess is evaluated in terms of "bulls" (correct symbols in the correct positions) and "cows" (correct symbols in the wrong positions).

## Features

- **Dynamic Code Length:** Users can specify the length of the secret code.
- **Extensible Symbol Range:** The game supports not only digits (0-9) but extends to lowercase alphabetic characters (a-z) to increase complexity.
- **Enhanced Error Handling:** Robust handling of various input errors, including invalid lengths and characters, to ensure a smooth game experience.
- **Interactive Gameplay:** Continuous user interaction until the secret code is guessed correctly.

## How to Run

Ensure you have Java installed on your machine. You can compile and run the game using the following commands in your terminal:

```bash
javac BullsAndCows.java
java BullsAndCows