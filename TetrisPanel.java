import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TetrisPanel extends Panel implements KeyListener {
	
	private ImageIcon imageIcon;
	private Image backgroundImage;
	JLabel imageLabel;


	private static final long serialVersionUID = -8444879183679955468L;

	// variables for double buffered display
	private BufferedImage bi;
	private Graphics gi;

	// dimensions of the frame
	private Dimension dim;

	// constants for panel
	private final Color background = new Color(100,100,100);

	// Variable representing the number of players
	private int numOfPlayers;
	
	// the left and right portions of the panel
	Tetris[] screens;
	
	// nhập liệu bàn phím 6 phím chơi cho mỗi người 
	private BufferedReader br;
	private int[][] key;
	TetrisPanel (int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
		key = new int[numOfPlayers][6];
		screens = new Tetris[numOfPlayers];
		try {
			br = new BufferedReader(new FileReader("D:/Minh Dũng/1. VinUNI/2. Spring 2023/13. JAVA/Project/tetris-multiplayer-master/tetris-multiplayer-master/INPUT"));
			for (int i = 0; i < numOfPlayers; i++)
				for (int j = 0; j < 6; j++) {
					key[i][j] = Integer.parseInt(br.readLine().trim());
					System.out.println(key[i][j]+" "+(char)(key[i][j]));
				}
		} catch (IOException ie) {
			System.out.println("INVALID INPUT SEQUENCE");
			System.exit(0);
		} 
		addKeyListener(this);
	//	imageIcon = new ImageIcon("D:/Minh Dũng/1. VinUNI/2. Spring 2023/13. JAVA/Project/tetris-multiplayer-master/tetris-multiplayer-master/sources/desktop-wallpaper-hinh-nền-allen-walker-1920x1080-anime-alan-walker.jpg");
		imageIcon = new ImageIcon("D:/Minh Dũng/1. VinUNI/2. Spring 2023/13. JAVA/Project/tetris-multiplayer-master/tetris-multiplayer-master/sources/resized.png");

		
		backgroundImage = imageIcon.getImage();
		imageLabel = new JLabel(imageIcon);

		
		// tạo tetris panel
		for (int i = 0; i < numOfPlayers; i++)
			screens[i] = new Tetris(400*i, 0, this, i);
	}
	
	//khởi tạo graphic
	public void paint (Graphics g) {
		dim = getSize();
		bi = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
		gi = bi.getGraphics();
		update(g);
	}
	
	//update graphic
	public void update (Graphics g) {
		gi.setColor(Color.blue);
		
		// vẽ 1 hình chữ nhật fill trên 1 đối tượng graphic
		gi.drawImage(backgroundImage, 0, 0, null);

		for (int i = 0; i < numOfPlayers; i++) {
			if (screens[i] == null)
				continue;
			
			// 3 method của tetris, lát đọc r chuyển qua sau
			screens[i].displayGrid(gi);
			screens[i].displayPieces(gi);
			screens[i].displayUI(gi);
		}
		//vẽ image
		g.drawImage(bi, 0, 0, this);
	}

	
	//thả rơi đồ xuống với các phím
	@Override
	public void keyTyped (KeyEvent e) {}
	@Override
	public void keyReleased (KeyEvent e) {
		for (int i = 0; i < numOfPlayers; i++) {
			for (int j = 0; j < 6; j++) {
				if (e.getKeyCode() == key[i][j]) {
					if (screens[i].curr == null)
						break;
					if (j == 3)
						screens[i].delay = (screens[i].level >= 20 ? Tetris.GLOBAL_DELAY[19] : Tetris.GLOBAL_DELAY[screens[i].level]);
				}
			}
		}
	}
	
	@Override
	public void keyPressed (KeyEvent e) {
		// user input
		// three cases that handle when the user adjusts the game states (ACTIVE, PAUSED, CLOSEd)
		if (e.getKeyCode() == KeyEvent.VK_P) {
			boolean currentState = screens[0].isPaused;
			for (int i = 0; i < numOfPlayers; i++)
				screens[i].isPaused = !currentState;
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_Q) {
			System.exit(0);
			
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			for (int i = 0; i < numOfPlayers; i++)
				screens[i].restart();
			repaint();
			return;
		}
		
		if (screens[0].isPaused || screens[0].isGameOver)
			return;
		int keyCode = e.getKeyCode();
		for (int i = 0; i < numOfPlayers; i++) {
			for (int j = 0; j < 6; j++) {
				if (keyCode == key[i][j]) {
					if (screens[i].curr == null)
						break;
					switch (j) {
						case 0:
							screens[i].movePiece(0, -1);
							repaint();
							break;
						case 1:
							screens[i].movePiece(0, 1);
							repaint();
							break;
						case 2:
							screens[i].rotateRight();
							break;	
						case 3:
							screens[i].delay = (screens[i].level >= 20 ? Tetris.GLOBAL_DELAY[19] : Tetris.GLOBAL_DELAY[screens[i].level])/8;
							break;

						case 4:
							if (screens[i].isHolding)
								break;
							if (screens[i].holdId == 0) {
								screens[i].holdId = screens[i].curr.id;
								screens[i].curr = null;
							} else {
								int temp = screens[i].holdId;
								screens[i].holdId = screens[i].curr.id;
								screens[i].curr = screens[i].p.getActive(temp-1);
							}
							screens[i].isHolding = true;
							screens[i].time = 1 << 30;
							break;
						case 5:
							screens[i].time = 1 << 30;
							screens[i].lockTime = 1 << 30;
							while(screens[i].movePiece(1, 0));
							
							PhatNhac musicPlayer = new PhatNhac();
					        musicPlayer.play("D:/Minh Dũng/1. VinUNI/2. Spring 2023/13. JAVA/Project/tetris-multiplayer-master/tetris-multiplayer-master/sources/drop cột.wav");
					        
					        
					        
					        
					        
					        
					        
					        
					        
					        
					        
					        
					        
					        
					        
					        
							break;
					}
				}
			}
		}
		repaint();
	}
	
	//game over
	protected void setGameOver () {
		for (int i = 0; i < numOfPlayers; i++)
			screens[i].isGameOver = true;
	}
	
	//send garbage
	protected void sendGarbage (int id, int send) {
		if (numOfPlayers == 1)
			return;
		int rand = (int)(Math.random()*(numOfPlayers-1));
		if (rand >= id)
			rand++;
		screens[rand].addGarbage(send);

		

//		System.out.println("SENT " + send);
	}
}
