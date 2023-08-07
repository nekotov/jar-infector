import java.io.IOException;
import javax.swing.*;
import java.awt.*;

public class template extends JPanel {
	private int x = 0;
    private int y = 0;
    private int xDelta = 2;
    private int yDelta = 2;
    private String text = "Michael";
    private Font font = new Font("Serif", Font.BOLD, 36);
    private int textWidth;
    private int textHeight;

    public template() {
        setPreferredSize(new Dimension(400, 300));
        setBackground(Color.WHITE);
        setFont(font);
        FontMetrics metrics = getFontMetrics(font);
        textWidth = metrics.stringWidth(text);
        textHeight = metrics.getHeight();
        Timer timer = new Timer(20, e -> moveText());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(text, x, y + textHeight);
    }

    private void moveText() {
        x += xDelta;
        y += yDelta;
        if (x < 0) {
            x = 0;
            xDelta = -xDelta;
        } else if (x + textWidth > getWidth()) {
            x = getWidth() - textWidth;
            xDelta = -xDelta;
        }
        if (y < 0) {
            y = 0;
            yDelta = -yDelta;
        } else if (y + textHeight > getHeight()) {
            y = getHeight() - textHeight;
            yDelta = -yDelta;
        }
        repaint();
    }
	public static void main(String[] args) {
		System.out.println("THE CODE HAS BEEN INJECTED!");
		
		SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My dear...");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new template());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

		
		
		/* --- TEMPLATE --- */
	}
}
