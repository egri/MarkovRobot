import java.awt.Graphics2D;

public class LineSegment implements ISegment { 
	
	private Point start;
	private Point end;
	private double width;

	public LineSegment(String[] data) {
		start = new Point(Double.valueOf(data[1]), Double.valueOf(data[2])); 
		end = new Point(Double.valueOf(data[3]), Double.valueOf(data[4]));
		width = Double.valueOf(data[5]);
	}

	public LineSegment(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	private double length() {
		return Math.sqrt( (end.getx() - start.getx()) * (end.getx() - start.getx()) + (end.gety() - start.gety()) * (end.gety() - start.gety()) ); 
	}

	@Override
	public void dump() {
		System.out.printf("LineSegment ");
		start.dump();
		System.out.printf(" ");
		end.dump();
		System.out.printf("\n");
	}

	@Override
	public void render(Graphics2D graphics) {
		Point v = end.add(start.minus()).scalar(width / 2 / length()).rotate(Math.PI / 2);
		Point s1 = start.add(v);
		Point s2 = start.add(v.minus());
		Point e1 = end.add(v);
		Point e2 = end.add(v.minus());
		int xPoints[] = { (int)s1.getx(), (int)s2.getx(), (int)e2.getx(), (int)e1.getx() };
		int yPoints[] = { (int)s1.gety(), (int)s2.gety(), (int)e2.gety(), (int)e1.gety() };
		graphics.fillPolygon(xPoints, yPoints, 4);
		graphics.fillOval((int)(end.getx() - width / 2), (int)(end.gety() - width / 2), (int)width, (int)width);
	}

}
