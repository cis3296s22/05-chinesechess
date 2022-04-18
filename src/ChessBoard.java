package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChessBoard extends JPanel{

    Pieces pic = new Pieces();
    static JFrame jf = new JFrame();

    public void initUI() throws IOException {

        jf.setSize(1240,860);
        jf.setTitle("中国象棋/Chinese Chess");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Close the windows and kill the application
        jf.getContentPane().setBackground(Color.white);//background color white
        jf.setLocationRelativeTo(null);//windows
        jf.setResizable(false);//cannot resize

        this.setBackground(Color.gray);
        jf.add(this);
        jf.setVisible(true);

        jf.addMouseListener(pic);
        Graphics g = jf.getGraphics();
        pic.setG(g);
        pic.setChessBoard(this);

        // add new panel on the right and set the size
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(450,1));
        jp.setBackground(Color.gray);
        jp.setLayout(null);
        jf.add(jp, BorderLayout.EAST);

        //add logo
        JLabel logo = new JLabel("Chinese Chess");
        logo.setFont(new Font("Serif", Font.PLAIN, 40));
        logo.setBounds(100,10,250,100);
        jp.add(logo);

        //add buttons
        JButton startButton = new JButton("Start");
        startButton.setBounds(100, 150, 250,100);
        startButton.setFont(new Font("Serif", Font.PLAIN, 40));
        startButton.setBackground(Color.PINK);
        startButton.addActionListener(pic);
        jp.add(startButton);

        JButton restartButton = new JButton("Restart");
        restartButton.setBounds(100, 300, 250,100);
        restartButton.setFont(new Font("Serif", Font.PLAIN, 40));
        restartButton.setBackground(Color.PINK);
        restartButton.addActionListener(pic);
        jp.add(restartButton);

        JButton pvpButton = new JButton("PvP");
        pvpButton.setBounds(100, 450, 250,100);
        pvpButton.setFont(new Font("Serif", Font.PLAIN, 40));
        pvpButton.setBackground(Color.PINK);
        pvpButton.addActionListener(pic);
        jp.add(pvpButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(100, 600, 250,100);
        exitButton.setFont(new Font("Serif", Font.PLAIN, 40));
        exitButton.setBackground(Color.PINK);
        exitButton.addActionListener(pic);
        jp.add(exitButton);
    }

    public void paint(Graphics g){
        super.paint(g);

        try {
            //paint chessboard
            final BufferedImage chessboard = ImageIO.read(new File("img/chessboard.png"));
            g.drawImage(chessboard, 90, 60, 625, 700, this);

            //paint pieces
            for(int i = 0; i < init.row; i++){
                for(int j = 0; j < init.column; j++){
                    if(pic.pieces[i][j] > 0){
                        BufferedImage drawPic = ImageIO.read(new File("img/"+(pic.pieces[i][j]) +".png"));
                        g.drawImage(drawPic, init.y0 + j * init.size - init.chesssize / 2,
                                init.x00 + i * init.size - init.chesssize / 2,init.chesssize, init.chesssize, this);
                    }
                }
            }

            //Zoom in the selected piece
            if(pic.r != -1){
                if(pic.pieces[pic.r][pic.c] > 0){
                    if(pic.chessflag == 1 && pic.pieces[pic.r][pic.c] > 10
                            || pic.chessflag == 2 && pic.pieces[pic.r][pic.c] < 10) {
                        int newexsize = 13;
                        BufferedImage drawPicece = ImageIO.read(new File("img/"+pic.pieces[pic.r][pic.c]+".png"));
                        g.drawImage(drawPicece, init.y0 + pic.c * init.size - (init.chesssize+newexsize) / 2,init.x00 + pic.r * init.size - (init.chesssize+newexsize) / 2,init.chesssize+newexsize, init.chesssize+newexsize, this);
                        // TODO send message between players?
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Player {
        public String name;
        public int side; // 1 is red, 2 is black
        public int bottom_or_top; //1 is bottom side, 2 is top side

        public Player(String n, int s, int b_o_t ){
            // constructor
            name = n;
            side = s;
            bottom_or_top = b_o_t;
        }
    }
}