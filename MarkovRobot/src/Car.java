import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Car extends JPanel {
	private static final long serialVersionUID = 1L;
	private Point position;
	private double phi;
	private double axisLength = 20.0;
	private double v1 = 0.0;
	private double v2 = 0.0;
	private Control control;
	private Sensor sensor1;
	private Sensor sensor2;
	int sensorValue1 = 0;
	int sensorValue2 = 0;
	private Point sensorPosition1 = new Point(10, 0);
	private Point sensorPosition2 = new Point(-10, 0);
	private Point b1 = new Point(10, -10);
	private Point b2 = new Point(-10, -10);
	private Point b3 = new Point(-10, 16);
	private Point b4 = new Point(10, 16);
	private int stepCounter = 0;
	
	public Car(Point position, double phi) {
		this.position = position;
		this.phi = phi;
	}

	public void setSensors(Sensor sensor1, Sensor sensor2) {
		this.sensor1 = sensor1;
		this.sensor2 = sensor2;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	protected void paintComponent(Graphics g) {
		//super.paintComponent(g); // Paint background, border
		Point br1 = position.add(b1.rotate(phi));
		Point br2 = position.add(b2.rotate(phi));
		Point br3 = position.add(b3.rotate(phi));
		Point br4 = position.add(b4.rotate(phi));
		int xPoints[] = { (int)br1.getx(), (int)br2.getx(), (int)br3.getx(), (int)br4.getx() };
		int yPoints[] = { (int)br1.gety(), (int)br2.gety(), (int)br3.gety(), (int)br4.gety() };
		g.setColor(Color.blue);
		g.fillPolygon(xPoints, yPoints, 4);
	}

	public void dump() {
		System.out.printf("car ");
		position.dump();
		System.out.printf(" s1: %1$d s2: %2$d", sensorValue1, sensorValue2);
		System.out.printf("\n");
	}

	public void step(double deltat) {
		Point delta = new Point((v1 + v2) / 2 * Math.cos(phi) * deltat, (v1 + v2) / 2 * Math.sin(phi) * deltat);
		position = position.add(delta);
		phi = phi + (v1 - v2) / axisLength;
		stepCounter++;
		if (stepCounter == 5) {
			sensorValue1 = sensor1.read(position.add(sensorPosition1.rotate(phi)));
			sensorValue2 = sensor2.read(position.add(sensorPosition2.rotate(phi)));
			ControlPoint cp = control.getControlPoint(sensorValue1, sensorValue2);
			v1 = cp.v1;
			v2 = cp.v2;
			stepCounter = 0;
		}
		dump();
	}
	
}
