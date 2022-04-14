package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChessBoard extends JPanel{

    Pieces pic = new Pieces();

    public void initUI() throws IOException {
        JFrame jf = new JFrame();
        jf.setSize(1240,860);
        jf.setTitle("中国象棋/Chinese Chess");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Close the windows and kill the application
        jf.getContentPane().setBackground(Color.white);//background color white
        jf.setLocationRelativeTo(null);//windows
        jf.setResizable(false);//cannot resize

        this.setBackground(Color.white);
        jf.add(this);
        jf.setVisible(true);

        jf.addMouseListener(pic);
        Graphics g = jf.getGraphics();
        pic.setG(g);
        pic.setChessBoard(this);

//        add new panel on the right and set the size
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(450,1));
        jp.setBackground(Color.cyan);
        jp.setLayout(null);
        jf.add(jp, BorderLayout.EAST);


        //add logo
        //            JLabel logo = new JLabel("Chinese Chess"){
//                BufferedImage lg = ImageIO.read(new File("img/logo.png"));
//
//                public void paint(Graphics g){
//                    g.drawImage(lg, 60,40,150,150,null);
//                }
//            };
//            logo.setBounds(0,0,400,204);
//            jp.add(logo);

        //add startButton

        JButton startButton = new JButton("Start"){
            BufferedImage startPic = ImageIO.read(new File("img/start.jpg"));

            public void paint(Graphics g){
                g.drawImage(startPic, 60, 400, 150, 90, null);
            }
        };

        startButton.setBounds(100, 450, 250,100);
        startButton.addActionListener(pic);
        jp.add(startButton);


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
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
