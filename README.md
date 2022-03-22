# MatrixGame

Hello and welcome to the Matrix Game! This is a simple project which can really help you get a feel for double for loops.

The game is played by two players on a 4x4 board. You may notice that I added a 5x5 board in my project. This was to test some things out, but I haven't finished that part yet.

Here is what each square of the 4x4 board looks like: 
If the squares don't look like squares, click the <> view!

  #--------#      a--------b
  | Val: Z |  or  | Val: Z |
  | (X, Y) |      | (X, Y) |
  #--------#      d--------c

Where Z is the value of the square, X and Y are the coordinates of the square, and the letters represent that the square is available to be selected at that letter option.

Here is an example board:

  a--------# #--------# #--------# #--------b 
  | Val: 6 | | Val: 6 | | Val: 6 | | Val: 7 | 
  | (0, 0) | | (0, 1) | | (0, 2) | | (0, 3) | 
  #--------# #--------# #--------# #--------# 
  #--------# #--------# #--------# #--------# 
  | Val: 7 | | Val: 6 | | Val: 4 | | Val: 1 | 
  | (1, 0) | | (1, 1) | | (1, 2) | | (1, 3) | 
  #--------# #--------# #--------# #--------# 
  #--------# #--------# #--------# #--------# 
  | Val: 2 | | Val: 4 | | Val: 4 | | Val: 8 | 
  | (2, 0) | | (2, 1) | | (2, 2) | | (2, 3) | 
  #--------# #--------# #--------# #--------# 
  #--------# #--------# #--------# #--------# 
  | Val: 6 | | Val: 8 | | Val: 7 | | Val: 1 | 
  | (3, 0) | | (3, 1) | | (3, 2) | | (3, 3) | 
  d--------# #--------# #--------# #--------c 

Notice that options a, b, c, and d are in the four corners of the board.

How to Play:
Players take turns selecting one of the four available squares on the board. When all the squares have been selected, the player with the most points wins.

Each square of the 4x4 board is filled with a random integer between and including 1 and 9. This value represents the number of points you get for selecting that square. You can only select squares that have a letter in one of their four corners. Squares are available if they are the first unselected square of a certain option's iteration through the matrix.

That explanation may be a little confusing, so let me explain how options are found. 
A simple way to think about it is that the options together iterate in a spiral. A right, B down, C left, and D up. Below is a more detailed explanation.
  Option A starts at the beginning of the matrix, point (0, 0), and iterates normally. That is to say it iterates through the matrix as one would read a book, from right to left, starting at the right of the next row when the last square of the current row has been selected. 
  There are two ways to look at how Option B iterates. One could say that if you turn the board 90 degrees counter-clockwise, it iterates in the same way as Option A. Or you can view it as iterating down the rightmost column (starting at (0, 3)), and moving to the top square of the next column on the left once the last square in the current column has been selected. 
  Option C iterates in the opposite direction of Option A. It starts at the bottom right corner (point (3, 3)) and goes from right to left, moving up to the next row once the leftmost square in its current row has been selected. You could also view it as the same as Option A if you were to turn the board 180 degrees.
  Option D iterates in the opposite direction of Option B. It starts at the bottom left corner (point (3, 0)) and iterates up through the columns. Once the last square in its current column has been selected, Option D moves to the lowest row of the next column to its right. You could also view it as the same as Option A if you turned the board 270 degrees counter-clockwise.
  
If that's still confusing, I recommend trying a few games to get the feel for it. It's a lot less complicated to play than it is to describe in words. 
