/**
 * Visualizer for Time class
 * @author Bryan Zhang
 * @version Sept 2019
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TimeVisualizer {
    static Time[] timers;
    static int[] seconds;
    static int index;
    static JFrame frame = new JFrame();
    static int maxX;
    static int maxY;
    static boolean timeView;

    public static void main (String[] args) {
        seconds = new int[2];
        timers = new Time[2];
        timers[0] = new Time();
        timers[1] = new Time();

        frame = new JFrame("Time Visualizer");
        maxX = Toolkit.getDefaultToolkit().getScreenSize().width/2;
        maxY = Toolkit.getDefaultToolkit().getScreenSize().height/2;

        DrawPanel worldPanel = new DrawPanel();
        MyKeyListener keyListener = new MyKeyListener();

        frame.addKeyListener(keyListener);
        frame.getContentPane().add(BorderLayout.CENTER, worldPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(maxX,maxY);
        frame.setVisible(true);
        
        System.out.println("Controls: ");
        System.out.println("Up Arrow - Increment");
        System.out.println("Down Arrow - Decrement");
        System.out.println("Left Arrow - Switch to time clock");
        System.out.println("Right Arrow - Switch to seconds clock");
        System.out.println("Space - Multiply by 5");
        System.out.println("W - Select top timer");
        System.out.println("S - Select bottom timer");
        System.out.println("Q - Subtract other time from selected time");
        System.out.println("A - Add other time to selected time");
        System.out.println("ESC - Exit visualizer");
    }

    private static class DrawPanel extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, maxX, maxY);
            g.setColor(Color.WHITE);
            if (timeView) {
                g.fillRect(0, 0, maxX/2, maxY);
            } else {
                g.fillRect(maxX/2, 0, maxX/2, maxY);
            }

            g.setColor(new Color(128, 128, 255, 64));
            if (index == 0) {
                g.fillRect(0, 0, maxX, maxY/2);
            } else {
                g.fillRect(0, maxY/2, maxX, maxY/2);
            }

            g.setFont(new Font("Arial", Font.PLAIN, 65));
            for (int i = 0; i < timers.length; i++) {
                if (timers[i].isValid()) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.RED);
                }
                g.drawString(timers[i].getTimeInfo(), maxX/10, maxY/4 + i*maxY/2);
                g.setColor(Color.GREEN);
                g.drawString(Integer.toString(seconds[i]), maxX/2 + maxX/10, maxY/4 + i*maxY/2);
            }
            this.repaint();
        }
    }

    private static class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (timeView) {
                    timers[index].increment();
                } else {
                    seconds[index] = seconds[index] + 1;
                }

            } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
                if (timeView) {
                    timers[index].decrement();
                } else {
                    seconds[index] = seconds[index] - 1;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (timeView) {
                    timers[index].multiply(5);
                } else {
                    seconds[index] = seconds[index] * 5;
                }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                timeView = true;
                timers[index].convertFromInt(seconds[index]);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                timeView = false;
                seconds[index] = timers[index].convertToInt();
            }

            if (KeyEvent.getKeyText(e.getKeyCode()).equals("W")) {
                index = 0;
            } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("S")) {
                index = 1;
            }

            if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {
                if (index == 0) {
                    timers[index].addTime(timers[1]);
                } else {
                    timers[index].addTime(timers[0]);
                }
            } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Q")) {
                if (index == 0) {
                    timers[index].subtractTime(timers[1]);
                } else {
                    timers[index].subtractTime(timers[0]);
                }
            }
            
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    }
}
