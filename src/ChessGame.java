package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGame {

    public static void main(String[] args) {
        JFrame f = new JFrame("Chinese Chess Game");//title

        f.setSize(800,800);
        f.setLocationRelativeTo(null);

        //set background
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("image/background.jpg")));
        f.setLayout(new FlowLayout());

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Canvas c=  new Canvas();
        f.add(c);

        //create buttons
        JButton newStartGame = new JButton("New Game");
        JButton ExitGame = new JButton("Exit");

        ExitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int j=JOptionPane.showConfirmDialog(null,"Are you sure? ","Exit",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(j==JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });


        //add buttons
        f.add(newStartGame);
        f.add(ExitGame);
        
        //new game
        newStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            }
        });

        f.setVisible(true);
    }
}
