import javax.swing.*;  // for JFrame
import java.awt.*;     // for Graphics and Container
import java.util.Scanner;

public class Board
{
    public static int[][] createBoard(int r, int c)
    {
        //         Scanner kbReader = new Scanner(System.in);
        //         System.out.println("What level would you like to play? ");
        //         String level = kbReader.next();
        //         int rows = 0;
        //         int cols = 0;
        // 
        //         // instantiates number of rows & cols based on level
        //         if (level.equals("beginner")) { rows = 8; cols = 8; }
        //         else if (level.equals("intermediate")) { rows = 16; cols = 16; }
        //         else if (level.equals("expert")) { rows = 16; cols = 30; }

        // fills in board with zeros

        int rows = r;
        int cols = c;
        int[][] board = new int[rows][cols];

        // places random number of bombs based on level
        int numBombs = 0;
        int ranRow = 0;
        int ranCol = 0;
        if (cols == 8) { numBombs = 10;}
        else if (cols == 16) { numBombs = 40; }
        else if (cols == 30) { numBombs = 99; }

        for (int x = 0; x < numBombs; x++)
        {
            ranRow = (int) (Math.random() * rows);
            ranCol = (int) (Math.random() * cols);
            if (board[ranRow][ranCol] != -1)
                board[ranRow][ranCol] = -1;
            else
                x--;
        }
        // prints out board with 0s and -1s (bombs)
        //         for (r = 0; r < rows; r++)
        //         {
        //             for (c = 0; c < cols; c++)
        //             {
        //                 System.out.print(board[r][c] + "\t");
        //             }
        //             System.out.println("");
        //         }

        // incrememts numbers around bombs
        for (r = 0; r < rows; r++) // goes through rows
        {
            for (c = 0; c < cols; c++) // goes through cols
            {
                if (board[r][c] == -1) // checks to see if the spot is a bomb and if it is, increment all around
                {
                    for (int rr = r-1; rr <= r+1; rr++)
                    {
                        for (int cc = c-1; cc <= c+1; cc++)
                        {
                            if (rr > -1 && rr < rows && cc > -1 && cc < cols)
                            {
                                if (board[rr][cc] != -1)
                                    board[rr][cc]++;
                            }
                        }
                    }
                }
            }
        }

        //         System.out.println("");
        //         // print out final board
        //         for (r = 0; r < rows; r++)
        //         {
        //             for (c = 0; c < cols; c++)
        //             {
        //                 System.out.print(board[r][c] + "\t");
        //             }
        //             System.out.println("");
        //         }
        return board;
    }
}
