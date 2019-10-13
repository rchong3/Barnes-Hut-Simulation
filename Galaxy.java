import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Galaxy {

	static final int SIZE = 1000;
	
	public static void main(String[] args) throws IOException { //Just for testing and playing around
		LinkedList<Star> stars = new LinkedList<>();
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println("Ms per frame (1 frame = 1000 seconds real-time):");
		int msPerFrame = Integer.parseInt(br.readLine());
//		System.out.println("Enter 1 if imaginary example");
//		System.out.println("and enter 2 if real-life star example");
		String type = "1";//br.readLine();
		System.out.println("Please enter the system file name.");
		System.out.println("Remember to include the file extension.");
		String file = br.readLine();
		if (type.equals("1")) {
			isr.close();
			br = new BufferedReader(new FileReader(file));
			int n = Integer.parseInt(br.readLine());
			double scale = Double.parseDouble(br.readLine());
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				double xv = Double.parseDouble(st.nextToken());
				double yv = Double.parseDouble(st.nextToken());
				double mass = Double.parseDouble(st.nextToken());
				stars.add(new Star(x, y, xv, yv, mass));
			}
			br.close();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JFrame f = new JFrame();
					Plot p = new Plot(scale, msPerFrame);
					for (Star star: stars) {
						p.addStar(star);
					}
					f.setSize(SIZE, SIZE);
					f.add(p);
					f.setTitle("Plot");
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.setVisible(true);
				}
			});
		}
//Unable to complete simulation of galaxy due to lack of data
//		else if (type.equals("2")) {
//			double conversionFactor = 3.086e+16;
//			System.out.println("How many stars are there?");
//			int n = Integer.parseInt(br.readLine());
//			isr.close();
//			br = new BufferedReader(new FileReader(file));
//			br.readLine();
//			double centerX = 0;
//			double centerY = 0;
//			double[][] starStats = new double[n][3]; //{x, y, v}
//			for (int i = 0; i < n; i++) {
//				StringTokenizer st = new StringTokenizer(br.readLine());
//				st.nextToken();
//				st.nextToken();
//				starStats[i][0] = conversionFactor * Double.parseDouble(st.nextToken());
//				starStats[i][1] = conversionFactor * Double.parseDouble(st.nextToken());
//				centerX += starStats[i][0];
//				centerY += starStats[i][1];
//				st.nextToken();
//				st.nextToken();
//				starStats[i][2] = Double.parseDouble(st.nextToken());
//			}
//			centerX /= n;
//			centerY /= n;
//			double minX = starStats[0][0];
//			double maxX = starStats[0][0];
//			double minY = starStats[0][1];
//			double maxY = starStats[0][1];
//			for (double[] star: starStats) {
//				double x = star[0] - centerX;
//				double y = star[1] - centerY;
//				double d = Math.sqrt(x * x + y * y);
//				double xv = -star[2] * y / d * 1000000;
//				double yv = star[2] * x / d * 1000000;
//				if (x < minX) {
//					minX = x;
//				} else if (x > maxX) {
//					maxX = x;
//				}
//				if (y < minY) {
//					minY = y;
//				} else if (y > maxY) {
//					maxY = y;
//				}
//				stars.add(new Star(x, y, xv, yv, 1));
//			}
//			stars.add(new Star(0, 0, 0, 0, 1e+10));
//			double scale = Math.max(Math.max(maxX, minX), Math.max(maxY, minY));
//			br.close();
//			SwingUtilities.invokeLater(new Runnable() {
//				public void run() {
//					JFrame f = new JFrame();
//					Plot p = new Plot(scale, msPerFrame);
//					for (Star star: stars) {
//						p.addStar(star);
//					}
//					f.setSize(SIZE, SIZE);
//					f.add(p);
//					f.setTitle("Plot");
//					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//					f.setVisible(true);
//				}
//			});
//		}
		else {
			System.exit(1);
		}
	}
	
}