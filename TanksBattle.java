import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

public class TanksBattle extends JPanel {
    public static boolean move1 = false;
    public static boolean move2 = false;
    public static boolean move3 = false;
    public static boolean move4 = false;
    public static int angle1 = 0;
    public static int angle2 = 0;
    public static boolean shoot1 = false;
    public static boolean shoot2 = false;

    public TanksBattle() {
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    move1 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    move2 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    angle1 = 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    angle1 = 2;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    move3 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    move4 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    angle2 = 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    angle2 = 2;
                }
                if (e.getKeyCode() == KeyEvent.VK_M) {
                    shoot1 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_T) {
                    shoot2 = true;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    move1 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    move2 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    move3 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    move4 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    angle1 = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    angle1 = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    angle2 = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    angle2 = 0;
                }
            }
        };
        addKeyListener(listener);
        setFocusable(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        TanksBattle k = new TanksBattle();
        frame.add(k);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.add(new TanksBattle2());
        frame.setVisible(true);
    }

}

class TanksBattle2 extends JComponent {
    public static int x = 20;
    public static int y = 600;
    public static int x2 = 760;
    public static int y2 = 600;
    int angle1 = 45;
    int angle2 = 45;
    int xb1 = 20;
    int yb1 = 600;
    int xb2 = 760;
    int yb2 = 600;
    double yvel1 = 0;
    int xvel1 = 0;
    int power1 = 20;
    int xvel2 = 0;
    double yvel2 = 0;
    int score1 = 0;
    int score2 = 0;
    boolean restart = false;

    TanksBattle2() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (TanksBattle.move1 == true) {
                        if (x < 320) {
                            x++;
                        }
                        if (yb1 >= 610) {
                            xb1 = x;
                            yb1 = 610;
                        }
                    }
                    if (TanksBattle.move2 == true) {
                        x--;
                        if (yb1 >= 610) {
                            xb1 = x;
                            yb1 = 610;
                        }
                    }
                    if (TanksBattle.move3 == true) {
                        x2++;
                        if (yb2 >= 610) {
                            xb2 = x2;
                            yb2 = 610;
                        }
                    }
                    if (TanksBattle.move4 == true) {
                        if (x2 > 460) {
                            x2--;
                        }
                        if (yb2 >= 610) {
                            xb2 = x2;
                            yb2 = 610;
                        }
                    }
                    if (TanksBattle.angle1 == 1) {
                        angle1++;
                    } else if (TanksBattle.angle1 == 2) {
                        angle1--;
                    }
                    if (TanksBattle.angle2 == 1) {
                        angle2++;
                    } else if (TanksBattle.angle2 == 2) {
                        angle2--;
                    }
                    if (x < 0) {
                        x = 0;
                    }
                    if (x2 > 800) {
                        x2 = 800;
                    }
                    if (TanksBattle.shoot1 && yb1 >= 600) {
                        yvel1 = (int) (power1 * Math.sin(Math.toRadians(angle1)));
                        xvel1 = (int) (power1 * Math.cos(Math.toRadians(angle1)));
                        TanksBattle.shoot1 = false;
                    }
                    if (TanksBattle.shoot2 && yb2 >= 600) {
                        yvel2 = (int) (power1 * Math.sin(Math.toRadians(angle2)));
                        xvel2 = (int) (power1 * Math.cos(Math.toRadians(angle2)));
                        TanksBattle.shoot2 = false;
                    }
                    xb1 += xvel1;
                    yb1 -= yvel1;

                    xb2 -= xvel2;
                    yb2 -= yvel2;

                    if (yb1 < 610) {
                        yvel1 -= 0.6;
                    } else {
                        yvel1 = 0;
                        xvel1 = 0;
                        xb1 = x;
                        yb1 = 610;
                    }

                    if (yb2 < 610) {
                        yvel2 -= 0.6;
                    } else {
                        yvel2 = 0;
                        xvel2 = 0;
                        xb2 = x2;
                        yb2 = 610;
                    }
                    if ((xb2 < x + 20) && (xb2 > x) && (yb2 + 8 >= y)) {
                        score2++;
                        restart = true;
                    }
                    if ((xb2 + 10 > x) && (xb2 + 10 < x + 10) && (yb2 + 8 >= y)) {
                        score2++;
                        restart = true;
                    }

                    if ((xb1 < x2 + 20) && (xb1 > x2) && (yb1 + 8 >= y2)) {
                        score1++;
                        restart = true;
                    }
                    if ((xb1 + 10 > x2) && (xb1 + 10 < x2 + 10) && (yb1 + 8 >= y2)) {
                        score1++;
                        restart = true;
                    }
                    if (restart) {
                        x = 20;
                        y = 600;
                        x2 = 760;
                        y2 = 600;
                        angle1 = 45;
                        angle2 = 45;
                        xb1 = 20;
                        yb1 = 600;
                        xb2 = 760;
                        yb2 = 600;
                        yvel1 = 0;
                        xvel1 = 0;
                        xvel2 = 0;
                        yvel2 = 0;
                        restart = false;
                    }

                    repaint();
                    try {
                        Thread.sleep(15);
                    } catch (Exception e) {
                    }

                }
            }
        });

        t.start();
    }

    int maxheight = 0;
    int maxdistance = 0;
    int maxheight2 = 0;
    int maxdistance2 = 0;

    public void paint(Graphics g) {
        Graphics2D gg = (Graphics2D) g;

        // ground
        gg.setColor(Color.GREEN);
        gg.fillRect(0, 620, 800, 800);

        gg.fillRect(340, 590, 120, 100);

        // player 1
        gg.setColor(Color.BLUE);
        gg.fillRect(x, y, 20, 20);

        // score 1
        gg.drawString("Score: " + score1, 20, 20);

        // turret 1
        gg.fillRect(x + 10, y, 1, 1);
        maxheight = y - (int) (3 * Math.sin(Math.toRadians(angle1)));
        maxdistance = x + (int) (3 * Math.cos(Math.toRadians(angle1)));
        for (int i = 0; i < maxheight; i++) {
            gg.fillRect(x + 5 + (int) (i / 25 * Math.cos(Math.toRadians(angle1))),
                    y - (int) (i / 25 * Math.sin(Math.toRadians(angle1))), 3, 3);
        }

        // bullet 1
        gg.fillRect(xb1, yb1, 10, 10);

        // player 2
        gg.setColor(Color.RED);
        gg.fillRect(x2, y2, 20, 20);

        // score 2
        gg.drawString("Score: " + score2, 680, 20);

        // bullet 2
        gg.fillRect(xb2, yb2, 10, 10);

        // turret 2
        gg.fillRect(x2 + 10, y2, 1, 1);
        maxheight2 = y2 - (int) (3 * Math.sin(Math.toRadians(angle2)));
        maxdistance2 = x2 + (int) (3 * Math.cos(Math.toRadians(angle2)));
        for (int i = 0; i < maxheight; i++) {
            gg.fillRect(x2 + 5 - (int) (i / 25 * Math.cos(Math.toRadians(angle2))),
                    y2 - (int) (i / 25 * Math.sin(Math.toRadians(angle2))), 3, 3);
        }

    }

}
