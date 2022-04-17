package src;

import javax.swing.*;
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand() == "Start"){
            System.out.println("Game start");
            renew(); // redraws everything
            ui.repaint();
        }else if(e.getActionCommand() == "Restart"){
            System.out.println("Game restarts");
            renew(); // redraws everything
            ui.repaint();
        }else if(e.getActionCommand() == "Exit"){
            ChessBoard.jf.dispose();
        }
    }

    public void renew(){
        pieces = new int[][]{
                //red
                {1,2,3,4,5,4,3,2,1}, {0,0,0,0,0,0,0,0,0},{0,6,0,0,0,0,0,6,0}, {7,0,7,0,7,0,7,0,7},
                //center
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}
                //black
                ,{77,0,77,0,77,0,77,0,77}, {0,66,0,0,0,0,0,66,0},{0,0,0,0,0,0,0,0,0},{11,22,33,44,55,44,33,22,11}
        };//renew pieces

        r = -1;
        x1=0;y1=0;x2=0;y2=0;
        chessflag = 1;
        curPiece = new int[3];
        beforePiece = new int[3];
    }

    //from start point to end point (车 炮 go straight)
    public int findPiece(int r1, int c1, int r2, int c2){
        int stone = 0;

        if(r1 == r2){
            for(int i = Math.min(c1, c2) + 1; i < Math.max(c1, c2); i++){
                if(pieces[r1][i] > 0){
                    stone++;
                }
            }
        }else if(c1 == c2){
            for(int i = Math.min(r1, r2) + 1; i < Math.max(r1, r2); i++){
                if(pieces[i][c1] > 0){
                    stone++;
                }
            }
        }
        return stone;
    }

    public int canMove(int movingPiece){

        int flag = 0;

        if(movingPiece == 5){
            if(r < 3 && c < 6 && c >2){
                if(beforePiece[0] == curPiece[0] && Math.abs(beforePiece[1] - curPiece[1]) == 1
                        || beforePiece[1] == curPiece[1] && Math.abs(beforePiece[0]- curPiece[0]) == 1){
                    flag = 1;
                }
            }
        } //将 commander
        else if(movingPiece == 55){
            if(r > 6 && c < 6 && c > 2){
                if(beforePiece[0] == curPiece[0] && Math.abs(beforePiece[1] - curPiece[1]) == 1
                        || beforePiece[1] == curPiece[1] && Math.abs(beforePiece[0] - curPiece[0]) == 1){
                    flag = 1;
                }
            }
        }//帅 commander
        else if(movingPiece == 1 || movingPiece == 11){
            if(beforePiece[0] == curPiece[0] | beforePiece[1] == curPiece[1]){
                if (findPiece(beforePiece[0], beforePiece[1], curPiece[0], curPiece[1]) == 0) {
                    flag = 1;
                }
            }
        }//车 carriage
        else if(movingPiece == 6 || movingPiece == 66){
            if(beforePiece[0] == curPiece[0] || beforePiece[1] == curPiece[1]){
                if (findPiece(beforePiece[0], beforePiece[1], curPiece[0], curPiece[1]) == 1 &&
                        curPiece[2]!=0 || findPiece(beforePiece[0], beforePiece[1], curPiece[0], curPiece[1]) == 0
                        && curPiece[2]==0) {
                    flag= 1;
                }
            }
        }//炮 gun
        else if(movingPiece == 77){
            if(beforePiece[0] > 4 && beforePiece[1] == curPiece[1] && beforePiece[0]-curPiece[0]==1
                    ||beforePiece[0] < 5 && beforePiece[1] == curPiece[1]
                    &&beforePiece[0] - curPiece[0] == 1 || beforePiece[0]<5
                    &&beforePiece[0] == curPiece[0]
                    &&Math.abs(beforePiece[1] - curPiece[1])==1) {
                flag = 1;
            }
        }//兵 soldier
        else if(movingPiece == 7){
            if(beforePiece[0]<5 && beforePiece[1] == curPiece[1] && beforePiece[0] - curPiece[0] == -1
                    || beforePiece[0]>4 && beforePiece[1] == curPiece[1]
                    && beforePiece[0] - curPiece[0]==-1 || beforePiece[0]>4
                    && beforePiece[0] == curPiece[0]
                    && Math.abs(beforePiece[1]-curPiece[1])==1) {
                flag= 1;
            }
        }//卒 soldier
        else if(movingPiece == 4){
            if (r < 3 & c < 6 & c > 2) {
                if(Math.abs(beforePiece[1] - curPiece[1])==1
                        && Math.abs(beforePiece[0] - curPiece[0])==1) {
                    flag = 1;
                }
            }
        }//士 minister
        else if(movingPiece == 44){
            if (r > 6 & c < 6 & c > 2) {
                if(Math.abs(beforePiece[1] - curPiece[1])==1
                        && Math.abs(beforePiece[0] - curPiece[0])==1) {
                    flag = 1;
                }
            }
        }//仕 minister
        else if(movingPiece == 2 || movingPiece == 22){
            if(beforePiece[0] > 0) {
                if (beforePiece[0] - curPiece[0] == 2 && Math.abs(beforePiece[1] - curPiece[1]) == 1
                        && pieces[beforePiece[0] - 1][beforePiece[1]] == 0) {
                    flag = 1;// topward
                }
            }
            if(beforePiece[0] < 9) {
                if (beforePiece[0] - curPiece[0] == -2 && Math.abs(beforePiece[1] - curPiece[1]) == 1
                        && pieces[beforePiece[0] + 1][beforePiece[1]] == 0) {
                    flag = 1;// downward
                }
            }
            if(beforePiece[1] < 8) {
                if (beforePiece[1] - curPiece[1] == -2 && Math.abs(beforePiece[0] - curPiece[0]) == 1
                        && pieces[beforePiece[0]][beforePiece[1] + 1] == 0) {
                    flag = 1;// right
                }
            }
            if(beforePiece[1] > 0) {
                if (beforePiece[1] - curPiece[1] == 2 && Math.abs(beforePiece[0] - curPiece[0]) == 1
                        && pieces[beforePiece[0]][beforePiece[1] - 1] == 0) {
                    flag = 1;// left
                }
            }
        }//马 horse
        else if(movingPiece == 3 || movingPiece == 33){
            if(beforePiece[0] > 0 && beforePiece[1] > 0) {
                if (beforePiece[0] - curPiece[0] == 2 && beforePiece[1] - curPiece[1] == 2
                        && pieces[beforePiece[0] - 1][beforePiece[1] - 1] == 0) {
                    flag = 1;// left top
                }
            }
            if(beforePiece[0] < 9&beforePiece[1]  > 0) {
                if (beforePiece[0] - curPiece[0] == -2 && beforePiece[1] - curPiece[1] == 2
                        && pieces[beforePiece[0] + 1][beforePiece[1] - 1] == 0) {
                    flag = 1;// left bottom
                }
            }
            if(beforePiece[0] > 0&beforePiece[1] < 8) {
                if (beforePiece[0] - curPiece[0] == 2 && beforePiece[1] - curPiece[1] == -2
                        && pieces[beforePiece[0] - 1][beforePiece[1] + 1] == 0) {
                    flag = 1;// right top
                }
            }
            if(beforePiece[0] < 9&beforePiece[1] < 8) {
                if (beforePiece[0] - curPiece[0] == -2 && beforePiece[1] - curPiece[1] == -2
                        && pieces[beforePiece[0] + 1][beforePiece[1] + 1] == 0) {
                    flag = 1;// right bottom
                }
            }
        }//象 elephant

        return flag;
    }

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
        System.out.println(c +" "+ r + " " + beforePiece[2]);
        pieces[r][c] = beforePiece[2];
        pieces[beforePiece[0]][beforePiece[1]] = 0;
        curPiece = new int[3];
        beforePiece = new int[3];

        c = -1;
        r = -1;
        setChessflag();//switch users
        ui.repaint();
    }

    public boolean checkREDWinner(){
        boolean flag = true;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 9; j++){
                if(pieces[i][j] == 5){
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }
    public boolean checkBLACKWinner(){
        boolean flag = true;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 9; j++){
                if(pieces[i][j] == 55){
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    public void mouseClicked(MouseEvent e){
        //System.out.println("click test");
        x1 = e.getX();
        y1 = e.getY();

        if (x1 > init.x0 - init.size / 2 && y1 > init.y0 - init.size / 2
                && x1 < init.x0 + init.size / 2 + init.column * init.size
                && y1 < init.y0 + init.row * init.size + init.size / 2) {
            x2 = ((x1 - init.x0 + init.size / 2) / init.size) * init.size + init.x0;
            y2 = ((y1 - init.y0 + init.size / 2) / init.size) * init.size + init.y0;

            //get current clicking location
            getCurrentClick();

            //update
            updateBeforePiece();
            ui.repaint();//selected piece
            updateCurPiece();

            if(r != -1){
                if(curPiece[2] == 0 && chessflag == 1 && beforePiece[2] > 10 && canMove(beforePiece[2]) == 1//red
                        || curPiece[2] == 0  && chessflag == 2 && beforePiece[2] < 10 && canMove(beforePiece[2]) == 1){//black
                    System.out.println("moved to an empty spot");
                    move();
                }else if(beforePiece[2] > 10 & curPiece[2] < 10 & chessflag == 1 & pieces[r][c] < 10 & canMove(beforePiece[2]) == 1) {
                    if (curPiece[2] != 0) {
                        System.out.println("red->black");
                        move();
                        if( checkREDWinner() ){
                            System.out.println("Red Won!");
                            JOptionPane.showMessageDialog(ChessBoard.jf,"Red Won!");
                        }
                    }
                } else if (beforePiece[2] < 10 & curPiece[2] > 10 & beforePiece[2] > 0 & chessflag == 2 & pieces[r][c] > 10 & canMove(beforePiece[2]) == 1) {
                    if (curPiece[2] != 0) {
                        System.out.println("black->red");
                        move();
                        if( checkBLACKWinner() ){
                            System.out.println("Black Won!");
                            JOptionPane.showMessageDialog(ChessBoard.jf,"Black Won!");
                        }
                    }
                }
            }
        }
    }
}
