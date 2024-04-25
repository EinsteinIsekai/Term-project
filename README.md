Tetris Game:
It is a Java implementation of Tetris Game.
Need Java 11 to be installed.

“Main.java” is the main class.
“Piece.java” is the data model for this game.
“Sound.java” is the audio part.
“StartScreenPanel” is the start window part.
“Window.java” is the window initialize part.
“Tetris Panel” is the graphic part.
“Tetris” is the game operating part.

Run “main.java” to start the game

Set up:
Download all files, including Java files and source of audio effects and background images
Go to java files, input audio effects and background images, and other document paths from the “Source” folder in your computer. 
-	Change background and INPUT file and drop sound effect path at Tetris.java
-	Change the music background path at main.java
-	Change another sound effect path at Tetris.java
You can use different sound effects or image backgrounds, note the sound must be in .wav format (use online conversion if need)


Play rules:
After running program, choose 1/2 players or the quit button
1)	1 player:
-	Use “W” to rotate, “S” to move fast, “A” to move left, “D” to move right, “F” to hold and change piece, “G” to drop piece, “R” to restart game (if you feel cringe), “Q” to quit, (mean that you are a loser :> ).
-	Line clears if full
-	4 lines cleared = level up = +10% speed
-	Current lines > 20 = end game.

2)	2 players:
-	Player 1 uses the same keyboard function
-	Player 2 uses “Up” to rotate, “Down” to move fast, “Left” to move left, “Right” to move right, “K” to hold and change piece, “L” to drop piece, alse “R” to restart game and “Q” to quit.
-	Another setting is the same.
-	Combo clear line -> send a line of garbage to the bottom of the opponent screen.

![image](https://github.com/jungsnow/Term-project/assets/60716670/32c5e0fb-6079-48d3-ad1b-44ca88cb8ce6)
