package hu.meditations.markovrobot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Control {

	private ArrayList<ControlPoint> controlPoints = new ArrayList<ControlPoint>();
	
	public Control(String filename) {
		File file = new File(filename);
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(in);
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			try {
				controlPoints.add(new ControlPoint(line.split("\\s+")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		scanner.close();
		try {
			check();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void check() throws Exception {
		if (controlPoints.size() == 0) {
			throw new Exception("At least one controlpoint is needed!");
		}
	}
	
	public ControlPoint getControlPoint(int s1, int s2) {
		double dmin = Double.MAX_VALUE;
		ControlPoint cpClosest = null;

		for (ControlPoint cp : controlPoints) {
			double d = (cp.sensor1 - s1) * (cp.sensor1 - s1) + (cp.sensor2 - s2) * (cp.sensor2 - s2);
			if (d < dmin) {
				dmin = d;
				cpClosest = cp;
			}
		}
		return cpClosest;
	}

}
