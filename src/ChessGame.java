package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGame {
    //global variables
    private static JFrame f;

    public static void main(String[] args) {
        // Frame Creation Things vv
        f = new JFrame("Chinese Chess Game");//title
        f.setSize(800,800); // window size
        f.setLocationRelativeTo(null); // null == center of screen
        f.setLayout(new BorderLayout()); // boarder on frame
        f.setContentPane(new JLabel(new ImageIcon("image/background.jpg"))); //set background
        f.setLayout(new FlowLayout()); // how things sit on the frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit button top right
        final Canvas c=  new Canvas(); // canvas is what the user interacts with
        f.add(c); // add canvas (interaction) to the frame

        //create buttons
        JButton newStartGame = new JButton("New Game");
        JButton ExitGame = new JButton("Exit");
        //add buttons to frame
        f.add(newStartGame);
        f.add(ExitGame);

        // exitGame Listener
        ExitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int j=JOptionPane.showConfirmDialog(null,"Are you sure? ","Exit",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(j==JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        // newStartGame Listener
        newStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // make game?


            }
        });

        f.setVisible(true); // make frame active
    }
}
