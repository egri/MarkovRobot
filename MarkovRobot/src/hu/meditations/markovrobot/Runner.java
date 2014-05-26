package hu.meditations.markovrobot;

import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

public class Runner extends JPanel {
	private static final long serialVersionUID = 1L;
	private Path path = new Path("data/path.dat");
	private Control control = new Control("data/control.dat");
	private Car car = new Car(path.getStartPosition(), path.getStartPhi());
	private Sensor sensor1 = new Sensor(path, 6.0);
	private Sensor sensor2 = new Sensor(path, 6.0);
	private boolean isRunning;
	private double time; 

	public Runner() {
		car.setSensors(sensor1, sensor2);
		car.setControl(control);
		this.reset();
		//frame.getContentPane().add(new JScrollPane(path));
		//frame.getContentPane().add(car);
	}
	
	protected void paintComponent(Graphics g) {
		path.paintComponent(g);
		car.paintComponent(g);
	}
	
	public void run() {
		double deltat = 0.01;
		while (isRunning && time < 5.0) {
			car.step(deltat);
			time = time + deltat;
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
//			this.paint();
		}
	}

	public void reset() {
		isRunning = false;
		time = 0.0;
	}

	public void start() {
		isRunning = true;
	}

	public void stop() {
		isRunning = false;
	}
	
}
