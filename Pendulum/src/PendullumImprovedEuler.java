/**
 * Created by Alex Fedosenko on 12.04.2017.
 */
import javax.swing.*;
import java.awt.*;

public class PendullumImprovedEuler extends JPanel implements Runnable {
    private static double angle = 0;
    public static double angleIm = 0;
    public int length=0;
    public static int mass=50;
    static double g = -9.81;
    static boolean bulba=false;
    static double distance=0;
    static double totalEnergy;

    public PendullumImprovedEuler(int length, double angle2) {
        this.length = length;
        this.angle = angle2;
        setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLUE);
        int anchorX = getWidth() / 2, anchorY = getHeight() / 4;
        int ballX = anchorX + (int) (Math.sin(angle) * length);
        int ballY = anchorY + (int) (Math.cos(angle) * length);
        g.drawLine(anchorX, anchorY, ballX, ballY);
        g.fillOval(anchorX - 3, anchorY - 4, 7, 7);//top ball x y
        g.fillOval(ballX - 7, ballY - 7, 14, 14);//bottom ball x y
        double bulbakek = 90-angle;
        distance= length*(1-Math.cos(angle));
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(2 * length + 50, length / 2 * 3);
    }
    double value;
    public void run() {
        double angularAccel,angleVelocityIm = 0, angleVelocity = 0, dt = 0.1;
        int i=0;
        while (true) {
            double bulba;
            i++;
            angularAccel = (g/length) * Math.sin(angle);//g/l*sin(a) or simply e
            angleVelocityIm += angularAccel *(dt/2);
            bulba=mass*angleVelocity*angleVelocity*length*length/2;

            value=Math.abs(g)*distance*mass;
            angle += angleVelocityIm*(dt/2);
            repaint();
            int kek =(int)(dt/2)*50;
            try { Thread.sleep(5); } catch (InterruptedException ex) {};
            totalEnergy = bulba - value;
        }
    }
}



