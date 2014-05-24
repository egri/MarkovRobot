import java.awt.Color;

public class Sensor {

	private double r;
	private Path path;
 	
	public Sensor(Path path, double r) {
		this.path = path;
		this.r = r;
	}

	public int read(Point position) {
		double x = position.getx();
		double y = position.gety();
		int all = 0;
		int sum = 0;
		
		for (int i = (int)(x - r - 1); i < (int)(x + r + 1); i++) {
			for (int j = (int)(y - r - 1); j < (int)(y + r + 1); j++) {
				if (((double)i - x) * ((double)i - x) + ((double)j - y) * ((double)j - y) < r * r) {
					Color c = new Color(path.image.getRGB(i, j));
					all++;
					if (c.getRed() == 0) {
						sum++;
					}
				}
			}
		}
		return (int)((double)sum / (double)all * 100.0);
	}

}
