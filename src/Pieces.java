package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pieces extends MouseAdapter implements ActionListener {

    Graphics g;
    ChessBoard ui;

    int chessflag = 1;//1-> red 2->black

    int x1, y1, x2, y2;
    int c = -1, r = -1;

    int[][] pieces = new int[][]{
            //red
            {1,2,3,4,5,4,3,2,1}, {0,0,0,0,0,0,0,0,0},{0,6,0,0,0,0,0,6,0}, {7,0,7,0,7,0,7,0,7},
            //center
            {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}
            //black
            ,{77,0,77,0,77,0,77,0,77}, {0,66,0,0,0,0,0,66,0},{0,0,0,0,0,0,0,0,0},{11,22,33,44,55,44,33,22,11}
    };//make pieces

    int[] curPiece = new int[3];
    int[] beforePiece = new int[3];

    public void setG(Graphics g){
        this.g = g;
    }

    public void setChessBoard(ChessBoard ui){
        this.ui = ui;
    }

    public void actionPerformed(ActionEvent e) {}

    public void updateCurPiece(){
        if(r != -1){
            curPiece[0] = r;
            curPiece[1] = c;
            curPiece[2] = pieces[r][c];
        }
    }

    public void updateBeforePiece(){
        beforePiece[0] = curPiece[0];
        beforePiece[1] = curPiece[1];
        beforePiece[2] = curPiece[2];
    }

    public void getCurrentClick(){
        x2 = ((x1 - init.x0 + init.size / 2) / init.size) * init.size + init.x0;
        y2 = ((y1 - init.y0 + init.size / 2) / init.size) * init.size + init.y0;
        //get point axis
        c = (x2 - init.x0) / init.size;
        r = (y2 - init.y0) / init.size;
    }

    public void setChessflag(){
        if(chessflag == 1){
            chessflag = 2;
        }else if(chessflag == 2){
            chessflag = 1;
        }
    }

    public void move(){
        pieces[c][r] = beforePiece[2];
        pieces[beforePiece[0]][beforePiece[1]] = 0;
        curPiece = new int[3];
        beforePiece = new int[3];

        c = -1;
        r = -1;
        setChessflag();
        ui.repaint();
    }

    public void mouseClicked(MouseEvent e){
        System.out.println("click test");
        x1 = e.getX();
        y1 = e.getY();

        if (x1 > init.x0 - init.size / 2 && y1 > init.y0 - init.size / 2
                && x1 < init.x0 + init.size / 2 + init.column * init.size
                && y1 < init.y0 + init.row * init.size + init.size / 2) {
            x2 = ((x1 - init.x0 + init.size / 2) / init.size) * init.size + init.x0;
            y2 = ((y1 - init.y0 + init.size / 2) / init.size) * init.size + init.y0;
            getCurrentClick();
            updateBeforePiece();
            ui.repaint();
            updateCurPiece();

            if(r != -1){
                if(curPiece[2] == 0 && chessflag == 1 && beforePiece[2] > 10
                        || curPiece[2] == 0  && chessflag == 2 && beforePiece[2] < 10){
                    System.out.println("move");
                    move();
                }
            }
        }
    }
}
