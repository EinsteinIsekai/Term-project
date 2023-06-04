

import javax.sound.sampled.*;
import java.io.*;

class PhatNhac {
    private Clip clip;

    public void play(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}

public class Sound {
    public static void main(String[] args) {
        PhatNhac musicPlayer = new PhatNhac();
        musicPlayer.play("C:/Users/dungc/eclipse-workspace/Tetris/src/tetris3/source/y2mate.com - Rick Astley  Never Gonna Give You Up Official Music Video.wav/");

        // Đợi một khoảng thời gian trước khi dừng phát nhạc
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        musicPlayer.stop();
    }
}

