package hu.meditations.markovrobot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Path extends JPanel {

	private static final long serialVersionUID = 1L;
	private Point startPosition = new Point (100, 100);
	private double startPhi = 0.0;
	private ArrayList<ISegment> segments = new ArrayList<ISegment>();
	public BufferedImage image;
	private int canvasWidth = 1000;
	private int canvasHeight = 500;
	
	public Path(String filename) {
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
			String s[] = line.split("\\s+");
			if (s[0].contentEquals("Start")) {
				startPosition = new Point(Double.valueOf(s[1]), Double.valueOf(s[2]));
				startPhi = Double.valueOf(s[3]);
			} else {
				try {
					ISegment segment = SegmentFactory.createSegment(line.split("\\s+"));
					segments.add(segment);
					//segment.dump();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		scanner.close();
		createCanvas();
	}

	public Point getStartPosition() {
		return startPosition;
	}
	
	public double getStartPhi() {
		return startPhi;
	}

	protected void paintComponent(Graphics g) {
		// Center image in this component.
		//int x = (getWidth() - canvasWidth)/2;
        //int y = (getHeight() - canvasHeight)/2;
        g.drawImage(image, 0, 0, this);
	}
    
	private void createCanvas() {
		image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage. TYPE_INT_RGB);
		Graphics im3d = image.getGraphics();
		Graphics2D im = (Graphics2D)im3d;
		im.fillRect(0, 0, canvasWidth, canvasHeight);
		im.setColor(Color.black);
		for(ISegment segment : segments) {
			segment.render(im);
		}
		try {
			ImageIO.write(image,"jpg",new File("data/canvas.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
