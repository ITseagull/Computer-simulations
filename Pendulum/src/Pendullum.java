
import org.jfree.data.time.Millisecond;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Pendullum extends JPanel implements Runnable {
    private static double angle = 0;
    private static double angleIm = 0;
    private int length=0;
    private static int mass=50;
    static double g = -9.81;
    static Graphs demo;
    static Graphs plot;
    static Graphs graph;
    static boolean bulba=false;
    static double distance=0;
    double totalEnergy2 = 260;

    public Pendullum(int length) {
        this.length = length;
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


    public static void main(String[] args) {
        System.out.println("Input length");
        Scanner sc = new Scanner(System.in);
        String kek = sc.nextLine();
        int topkek=Integer.parseInt(kek);
        System.out.println("Input angle");
        String kek1 = sc.nextLine();
        angle =Math.PI / Integer.parseInt(kek1);
        JFrame f = new JFrame("Pendulum");
        Pendullum p = new Pendullum(topkek);
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
        JFrame IM = new JFrame("Pendulum Improved");
        PendullumImprovedEuler I = new PendullumImprovedEuler(topkek, angle);
        IM.add(I);
        IM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        IM.pack();

        IM.setVisible(true);
        new Thread(p).start();
        new Thread(I).start();

        demo = new Graphs("Kinetic energy and potential energy of improved Euler");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

        plot = new Graphs("Potential energy");
        plot.pack();
        RefineryUtilities.centerFrameOnScreen(plot);
        plot.setVisible(true);

        graph = new Graphs("Kinetic and potential energy");
        graph.pack();
        RefineryUtilities.centerFrameOnScreen(graph);
        graph.setVisible(true);
        bulba=true;

    }

    public void run() {
        double angularAccel,angleVelocityIm = 0, angleVelocity = 0, dt = 0.1;
        int i=0;
        while (true) {
            i++;
            angularAccel = (g/length) * Math.sin(angle);//g/l*sin(a) or simply e
            angleVelocity += (angularAccel) * dt;//w
            angle += angleVelocity * dt;//a

            // angleVelocityIm += angularAccel *dt/2;
            // angleIm += +angleVelocityIm*dt;

            repaint();

            if (bulba&&i%2==0){
                double bulbakek1 = mass*angleVelocity*angleVelocity*length*length/2;
                double bulbakek=Math.abs(g)*distance*mass;
                plot.lastValue=bulbakek;
                plot.series.add(new Millisecond(), plot.lastValue);

                double graph1 = (bulbakek1 + bulbakek);
                //double graph1 = mass*9.81*length*(1 - Math.cos(angle));
                        graph.lastValue=graph1;

                totalEnergy2=totalEnergy2 + 0.05;
                demo.lastValue=totalEnergy2;
                demo.series.add(new Millisecond(), demo.lastValue);

                graph.series.add(new Millisecond(), graph.lastValue);
            }

            try { Thread.sleep(10); } catch (InterruptedException ex) {}
        }
    }
}

