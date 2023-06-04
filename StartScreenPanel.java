import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StartScreenPanel extends JPanel {
    private static final int DEFAULT_VERTICAL_PADDING = 20;
    private static final Color activeButtonColor = Color.GREEN;
    private static final Color inactiveButtonColor = Color.WHITE;

    private JButton onePlayerButton;
    private JButton twoPlayersButton;
    private JButton quitButton;
    private ImagePanel imagePanel;

    private int selectedOptionIndex;

    public StartScreenPanel(Window window) {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        JLabel titleLabel = new JLabel("TETRIS");
        titleLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        
        

        imagePanel = new ImagePanel("D:/Minh Dũng/1. VinUNI/2. Spring 2023/13. JAVA/Project/tetris-multiplayer-master/tetris-multiplayer-master/sources/backgroundN.png");
        
        

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        onePlayerButton = new JButton("1 PLAYER");
        onePlayerButton.addActionListener(window);
        onePlayerButton.setActionCommand("1P");;
        imagePanel.add(onePlayerButton, constraints);

        twoPlayersButton = new JButton("2 PLAYERS");
        twoPlayersButton.addActionListener(window);
        twoPlayersButton.setActionCommand("2P");

        imagePanel.add(twoPlayersButton, constraints);

        quitButton = new JButton("QUIT");
        quitButton.addActionListener(window);
        quitButton.setActionCommand("QUIT");

        imagePanel.add(quitButton);
        
        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        selectedOptionIndex = 0;

        // Add key listener to change options using UP and DOWN keys
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
                    switchSelectedOption(keyCode);
                }
            }
        });

        setFocusable(true);
    }

    private void switchSelectedOption(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            selectedOptionIndex--;
            if (selectedOptionIndex < 0) {
                selectedOptionIndex = 1;
            }
        } else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_RIGHT) {
            selectedOptionIndex++;
            if (selectedOptionIndex > 1) {
                selectedOptionIndex = 0;
            }
        }

        updateButtonColors();
    }

    private void updateButtonColors() {
        if (selectedOptionIndex == 0) {
            onePlayerButton.setBackground(activeButtonColor);
            twoPlayersButton.setBackground(inactiveButtonColor);
        } else {
            onePlayerButton.setBackground(inactiveButtonColor);
            twoPlayersButton.setBackground(activeButtonColor);
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow(); // Set focus to the panel to enable key events
    }

    private class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(String imagePath) {
            super();
            backgroundImage = Toolkit.getDefaultToolkit().getImage(imagePath);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
