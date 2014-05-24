import javax.swing.JApplet;
import javax.swing.JFrame;

public class MarkovRobot extends JApplet {
	private static final long serialVersionUID = 1L;

	public MarkovRobot() {
        add(new MRPanel());
    }

	public static void main(final String[] args) {
		JFrame frame = new JFrame("MarkovRobot szimuláció");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MRPanel());
        frame.setSize(1200,600);
        frame.setLocation(50,50);
        //frame.pack();
        frame.setVisible(true);
	}

}
