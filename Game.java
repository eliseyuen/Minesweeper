import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game
{
    private JFrame frame; 
    private JButton[][] grid;
    private int x;
    private int y;
    private int rows;
    private int cols;
    private String level;
    final int[][] bd;
    private int[][] seen;
    final int MYSTERY = -2;
    final int FLAG = -3;
    private ImageIcon flag;

    public Game(int r, int c, String l)
    {
        rows = r;
        cols = c;
        level = l;

        flag = new ImageIcon("flag.png");

        bd = Board.createBoard(rows,cols);

        seen = new int[rows][cols];
        for (int s = 0; s < rows; s++)
        {
            for (int t = 0; t < cols; t++)
            {
                seen[s][t] = MYSTERY;
            }
        }

        frame = new JFrame("Minesweeper");
        frame.setLayout(new GridLayout(rows,cols)); //set layout
        grid = new JButton[rows][cols]; //allocate the size of grid
        for(x=0; x<rows; x++){
            for(y=0; y<cols; y++){
                grid[x][y]=new JButton(); //creates new button
                //                 grid[x][y].addActionListener(new ActionListener()
                //                     {
                //                         public int f = x;
                //                         public int g = y;
                //                         public void actionPerformed(ActionEvent e)
                //                         {
                //                             //grid[c][d].setText(bd[c][d]+"");
                //                             //System.out.println(bd[c][d]);
                //                             //System.out.println("bd[f][g] is " + bd[f][g]);
                //                             //System.out.println("f is " + f);
                //                             //System.out.println("g is " + g);
                //                             if (bd[f][g]== -1)
                //                                 showBombs();
                //                             else if (bd[f][g] == 0)
                //                                 showMore(f, g);
                //                             else 
                //                             {
                //                                 //grid[f][g].setIcon(new ImageIcon(bd[f][g] + ".asdfjkl;"));
                //                                 grid[f][g].setText(bd[f][g]+"");
                //                             }
                //                             grid[f][g].setEnabled(false);
                //                         }
                //                     });
                grid[x][y].addMouseListener(new MouseAdapter(){
                        public int f = x;
                        public int g = y;
                        public void mouseClicked(MouseEvent e){
                            if(e.getButton() == 1)
                            {
                                //grid[c][d].setText(bd[f][g]+"");
                                //System.out.println(bd[f][g]);
                                //System.out.println("bd[f][g] is " + bd[f][g]);
                                //System.out.println("f is " + f);
                                //System.out.println("g is " + g);
                                if (seen[f][g] != FLAG && grid[f][g].isEnabled())
                                {
                                    if (bd[f][g]== -1)
                                        showBombs();
                                    else if (bd[f][g] == 0)
                                        showMore(f, g);
                                    else 
                                    {
                                        //grid[f][g].setIcon(new ImageIcon(bd[f][g] + ".asdfjkl;"));
                                        grid[f][g].setText(bd[f][g]+"");
                                    }
                                    grid[f][g].setEnabled(false);
                                }

                            }
                            else if(e.getButton() == 3)
                            {
                                if (seen[f][g] == FLAG)
                                {
                                    grid[f][g].setIcon(null);
                                    seen[f][g] = MYSTERY;
                                }
                                else
                                {
                                    grid[f][g].setIcon(flag);
                                    seen[f][g] = FLAG;
                                    //grid[f][g].setEnabled(false);
                                    //System.out.println("Right clicked!");
                                }
                            }

                        }
                    });
                frame.add(grid[x][y]); //adds button to grid
            }
        }
        ImageIcon img = new ImageIcon("minesweeperIcon.png");
        frame.setIconImage(img.getImage());

        //for (int rr = 0; rr < rows; rr++)
        //{
        //    for (int cc = 0; cc < cols; cc++)
        //    {
        //        System.out.print(bd[rr][cc] + "\t");
        //    }
        //    System.out.println("");
        //}

        JMenuBar dropdown = new JMenuBar();
        JMenu menu = new JMenu("File");
        dropdown.add(menu);

        JMenuItem startOver = new JMenuItem("New Game");
        menu.add(startOver);
        startOver.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    
                }
            });

        JMenuItem beg = new JMenuItem("Beginner");
        JMenuItem inter = new JMenuItem("Intermediate");
        JMenuItem exp = new JMenuItem("Expert");
        JMenu submenu = new JMenu("Change Level");
        submenu.add(beg);
        submenu.add(inter);
        submenu.add(exp);
        menu.add(submenu);

        frame.setJMenuBar(dropdown);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); //sets appropriate size for frame *****
        if (level.equals("beginner"))
        {
            frame.setSize(350,350);
        }
        else if (level.equals("intermediate"))
        {
            frame.setSize(700,700);
        }
        else if (level.equals("expert"))
        {

        }
        frame.setVisible(true); //makes frame visible
    }

    public void showMore(int xx, int yy) // The basis of the recursion loops was not my code.
    // However, I ran into problems with the basic recursion, hence all the print statements for debugging.
    {
        int minxx, minyy, maxxx, maxyy;
        int result = 0;

        //System.out.println("Inside showMore at point (" + xx + "," + yy + ")");

        if (xx <= 0) minxx = 0;
        else minxx = xx - 1;
        if (yy <= 0) minyy = 0;
        else minyy = yy - 1;
        if (xx >= rows - 1) maxxx = rows;
        else maxxx = xx + 2;
        if (yy >= cols - 1) maxyy = cols;
        else maxyy = yy + 2;

        //System.out.println("minxx is " + minxx);
        //System.out.println("minyy is " + minyy);
        //System.out.println("maxxx is " + maxxx);
        //System.out.println("maxyy is " + maxyy);

        for (int k = minxx; k < maxxx; k++)
        {
            //System.out.println("Inside first loop, k is " + k);
            for (int l = minyy; l < maxyy; l++)
            {
                //System.out.println("Inside second loop, l is " + l);
                if (bd[k][l] != -1 && seen[k][l] == MYSTERY)
                {
                    //System.out.println("Got into first if");
                    //System.out.println("k is " + k);
                    //System.out.println("l is " + l);
                    if (bd[k][l] != 0)
                        grid[k][l].setText(bd[k][l]+"");
                    grid[k][l].setEnabled(false);
                    seen[k][l] = 1;
                    if (bd[k][l] == 0)
                    {
                        //System.out.println("Got into second if");
                        showMore(k, l);
                    }
                }
            }
        }
    }

    public void showBombs()
    {
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                if (bd[r][c] == -1 && seen[r][c] != FLAG)
                {
                    //grid[r][c].setText(bd[r][c]+"");
                    grid[r][c].setIcon(new ImageIcon("minesweeperIcon.png"));

                }
                grid[r][c].setEnabled(false);
            }
        }
        //JFrame loss = new JFrame("Game Over");
        //JLabel x=new JLabel("Sorry, you lost.");
        //loss.add(x);
        //loss.setSize(150,150);
        //loss.setVisible(true);
    }
}

