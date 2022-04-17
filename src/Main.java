package src;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ChessBoard ui = new ChessBoard();
        ui.initUI();

    }
}