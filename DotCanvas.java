import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class DotCanvas extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    private List<Dot> dots = new ArrayList<>();
    private Point lastClickedPoint;
    private Color currentColor = Color.BLACK;

    public DotCanvas() {
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setBackground(Color.WHITE);
    }

    public DotCanvas(int initialDotCount) {
        this();
        for (int i = 0; i < initialDotCount; i++) {
            int x = (int) (Math.random() * getWidth());
            int y = (int) (Math.random() * getHeight());
            Dot dot = new Dot(x, y, currentColor);
            dots.add(dot);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        lastClickedPoint = new Point(x, y);
        Dot newDot = new Dot(x, y, currentColor);
        dots.add(newDot);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        lastClickedPoint = new Point(x, y);
        Dot newDot = new Dot(x, y, currentColor);
        dots.add(newDot);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char keyPressed = e.getKeyChar();
        switch (keyPressed) {
            case 'r':
                currentColor = Color.RED;
                break;
            case 'b':
                currentColor = Color.BLUE;
                break;
            case 'g':
                currentColor = Color.GREEN;
                break;
            case 'l':
                currentColor = Color.BLACK;
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Implementing the keyPressed method is required, but we don't need to do anything in this case.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Implementing the keyReleased method is required, but we don't need to do anything in this case.
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        for (Dot dot : dots) {
            int x = dot.x;
            int y = dot.y;
            Color dotColor = dot.color;

            g2d.setColor(dotColor);
            g2d.fill(new Ellipse2D.Double(x - 5, y - 5, 10, 10));
        }

        if (lastClickedPoint != null) {
            int x = lastClickedPoint.x;
            int y = lastClickedPoint.y;

            g2d.setColor(Color.BLACK);
            for (Dot dot : dots) {
                int dotX = dot.x;
                int dotY = dot.y;
                g2d.setColor(dot.color);
                g2d.drawLine(dotX, dotY, x, y);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dot Canvas");
        DotCanvas canvas = new DotCanvas(10); // Create with 10 initial dots
        frame.add(canvas);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class Dot {
        int x, y;
        Color color;

        Dot(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}
