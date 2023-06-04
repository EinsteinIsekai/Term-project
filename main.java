import java.io.IOException;



public class main {
	public static void main (String[] args) throws IOException {
		new Window();
		PhatNhac musicPlayer = new PhatNhac();
//        musicPlayer.play("D:/Minh Dũng/1. VinUNI/2. Spring 2023/13. JAVA/Project/tetris-multiplayer-master/tetris-multiplayer-master/sources/nhạc nền.wav");

        // phat nhac
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}

