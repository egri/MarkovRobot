package hu.meditations.markovrobot;

public class ControlPoint {

	public int sensor1;
	public int sensor2;
	public double v1;
	public double v2;

	public ControlPoint(int sensor1, int sensor2, double v1, double v2) throws Exception {
		this.sensor1 = sensor1;
		this.sensor2 = sensor2;
		this.v1 = v1;
		this.v2 = v2;
		check();
	}

	public ControlPoint(String[] data) throws Exception {
		this.sensor1 = Integer.valueOf(data[0]);
		this.sensor2 = Integer.valueOf(data[1]);
		this.v1 = Double.valueOf(data[2]);
		this.v2 = Double.valueOf(data[3]);
		check();
	}
	
	private void check() throws Exception {
		if ((Math.abs(v1) > 100.0) || (Math.abs(v2) > 100.0))
			throw(new Exception("Velocity must be between -100 and 100"));
	}

}
