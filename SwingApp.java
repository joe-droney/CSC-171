import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SwingApp extends JFrame {
    private JPanel controlPanel;
    private DisplayPanel displayPanel;
    private JButton mode1Button, mode2Button, mode3Button;
    private JSlider slider;
    private Timer timer;
    private int x, y, dx, dy;
    private double theta;
    private boolean isMode1 = false;
    private boolean isMode2 = false;
    private boolean isMode3 = false;
    private List<Line> lines = new ArrayList<>(); // Store lines for Mode 3
    private int numLines;

    public SwingApp() {
        setTitle("Bouncing Shape Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mode1Button = new JButton("Mode 1");
        mode2Button = new JButton("Mode 2");
        mode3Button = new JButton("Mode 3");

        slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        controlPanel.add(mode1Button);
        controlPanel.add(mode2Button);
        controlPanel.add(mode3Button);
        controlPanel.add(slider);

        mode1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startBouncingAnimation();
            }
        });

        mode2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAnimation();
                startCircularAnimation();
            }
        });

        mode3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAnimation();
                startScreensaverAnimation();
            }
        });

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int speed = slider.getValue();
                timer.setDelay(10 / speed);
                updateDisplay("Speed adjusted: " + speed);
            }
        });

        displayPanel = new DisplayPanel();
        displayPanel.setLayout(new BorderLayout());

        add(controlPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);

        x = 50;
        y = 50;
        dx = 3;
        dy = 3;
        theta = 0;

        timer = new Timer(1000 / slider.getValue(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePosition();
                updateCircularPosition();
                displayPanel.repaint();
            }
        });

        setVisible(true);
    }

    private void startCircularAnimation() {
        stopAnimation();
        isMode2 = true;
        isMode1 = false;
        timer.start();
        updateDisplay("Mode 2 selected - Circular Animation started");
    }

    private void startBouncingAnimation() {
        stopAnimation();
        isMode1 = true;
        isMode2 = false;
        timer.start();
        updateDisplay("Mode 1 selected - Bouncing Animation started");
    }

    private void stopAnimation() {
        timer.stop();
    }

    private void updatePosition() {
        if (isMode1) {
            x += dx;

            if (x <= 0 || x >= displayPanel.getWidth() - 30) {
                dx = -dx;
            }
        }
    }

    private void updateCircularPosition() {
        if (isMode2) {
            int radius = 50;
            int centerX = displayPanel.getWidth() / 2;
            int centerY = displayPanel.getHeight() / 2;

            x = (int) (centerX + radius * Math.cos(theta));
            y = (int) (centerY + radius * Math.sin(theta));

            theta += 0.1;
        }
    }

    private void updateDisplay(String message) {
        JLabel label = new JLabel(message);
        displayPanel.removeAll();
        displayPanel.add(label, BorderLayout.CENTER);
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    private void startScreensaverAnimation() {
        stopAnimation();
        isMode3 = true;
        isMode1 = false;
        isMode2 = false;
        numLines = slider.getValue();
        lines.clear();
        timer.start();
        updateDisplay("Mode 3 selected - Screensaver Animation started");
    }

    private void updateScreensaverPosition() {
        if (isMode3) {
            Random rand = new Random();

            for (int i = 0; i < numLines; i++) {
                int x1 = rand.nextInt(displayPanel.getWidth());
                int y1 = rand.nextInt(displayPanel.getHeight());
                int x2 = rand.nextInt(displayPanel.getWidth());
                int y2 = rand.nextInt(displayPanel.getHeight());

                Color randomColor = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
                Line line = new Line(x1, y1, x2, y2, randomColor);
                lines.add(line);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingApp();
            }
        });
    }

    private class DisplayPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (isMode1) {
                g.setColor(Color.BLUE);
                g.fillOval(x, y, 30, 30);
            } else if (isMode2) {
                g.setColor(Color.RED);
                g.fillOval(x, y, 30, 30);
            } else if (isMode3) {
                updateScreensaverPosition();
                for (Line line : lines) {
                    g.setColor(line.color);
                    g.drawLine(line.x1, line.y1, line.x2, line.y2);
                }
            }
        }
    }

    private class Line {
        int x1, y1, x2, y2;
        Color color;

        public Line(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
    }
}
