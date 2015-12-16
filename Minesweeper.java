import javax.swing.*;  // for JFrame
import java.awt.*;     // for Graphics and Container
import java.util.Scanner;

public class Minesweeper extends JFrame
{
    //     public static void main(String[] args)
    //     {
    //         JFrame win = new JFrame("Minesweeper");
    //         
    //         
    //         Scanner kbReader = new Scanner(System.in);
    //         System.out.println("What level would you like to play? ");
    //         String level = kbReader.next();
    //         int rows = 0;
    //         int cols = 0;
    //         
    //         if (level.equals("beginner")) { rows = 8; cols = 8; }
    //         else if (level.equals("intermediate")) { rows = 16; cols = 16; }
    //         else if (level.equals("expert")) { rows = 16; cols = 30; }
    //         
    //         Board b = new Board(rows, cols);
    //         
    //     }

    public static void main(String[] args)
    {
        //         Scanner kbReader = new Scanner(System.in);
        //         System.out.println("What level would you like to play? ");
        //         String level = kbReader.next();
        //         int rows = 0;
        //         int cols = 0;
        //         
        //         if (level.equals("beginner")) { rows = 8; cols = 8; }
        //         else if (level.equals("intermediate")) { rows = 16; cols = 16; }
        //         else if (level.equals("expert")) { rows = 16; cols = 30; }
        //         
        Game game = new Game(8, 8, "beginner");
    }
}
