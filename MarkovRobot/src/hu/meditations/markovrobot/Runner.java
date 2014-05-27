package hu.meditations.markovrobot;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Runner extends JPanel {
	private static final long serialVersionUID = 1L;
	private Path path = new Path("data/path.dat");
	private Control control = new Control("data/control.dat");
	private Car car = new Car(path.getStartPosition(), path.getStartPhi());
	private Sensor sensor1 = new Sensor(path, 6.0);
	private Sensor sensor2 = new Sensor(path, 6.0);
	private boolean isRunning;
	private double time; 
	private double deltat = 0.01;

	private int screenOneStep = 10;    // Number of deltat steps in one animation step
	private int screenInterval  = 35;  // Milliseconds between screen updates
    private Timer screenTimer;         // Timer that fires to animation step

    public Runner() {
		car.setSensors(sensor1, sensor2);
		car.setControl(control);
		this.reset();
		screenTimer = new Timer(screenInterval, new ScreenTimerAction());
	}
    
	public void run() {
		while (isRunning && time < 5.0) {
			car.step(deltat);
			time = time + deltat;
		}
	}
	
	public void start() {
		isRunning = true;
		screenTimer.start();
	}

	public void stop() {
		isRunning = false;
		screenTimer.stop();
	}
	
	public void reset() {
		isRunning = false;
//		screenTimer.stop();
		time = 0.0;
		car.setPosition(path.getStartPosition(), path.getStartPhi());
	}

	protected void paintComponent(Graphics g) {
		path.paintComponent(g);
		car.paintComponent(g);
	}

    class ScreenTimerAction implements ActionListener {
 
    	public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < screenOneStep; i++) {
				car.step(deltat);
				time = time + deltat;
			}
			repaint();
    	}
 
    }

}
