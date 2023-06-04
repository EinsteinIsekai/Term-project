import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends Frame implements ActionListener {
    private static final long serialVersionUID = -1324363758675184283L;
    private int numOfPlayers;
    private StartScreenPanel startScreenPanel;

    public Window() throws IOException {
        numOfPlayers = 0;
        
        
        setTitle("Tetris");
        setSize(400, 600);
        setLocation(100, 100);
        setResizable(false);
        setLayout(new BorderLayout());

        startScreenPanel = new StartScreenPanel(this);
        add(startScreenPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("1P")) {
            numOfPlayers = 1;
            startGame();
        } else if (command.equals("2P")) {
            numOfPlayers = 2;
            startGame();
        } else if (command.equals("QUIT")) {
        	System.exit(0);
        }
    }

    private void startGame() {
        setSize(400 * numOfPlayers, 600);
        remove(startScreenPanel);
        add(new TetrisPanel(numOfPlayers), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) throws IOException {
        new Window();
    }
}
