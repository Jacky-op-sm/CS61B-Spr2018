public class NBody {
	public static double readRadius(String file) {
		In in = new In(file);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String file) {
		In in = new In(file);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] allplanets = new Planet[num];
		for(int i = 0; i < num; i += 1) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVal = in.readDouble();
			double yyVal = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			Planet b = new Planet(xxPos, yyPos, xxVal, yyVal, mass, img);
			allplanets[i] = b;
		}
		return allplanets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] allplanets = readPlanets(filename);
		int num = allplanets.length;
		String imageToDraw = "images/starfield.jpg";
		/** draw the initial location. */
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);
		for(Planet b : allplanets) {
			b.draw();
		}
		StdDraw.show();
		double t = 0;
		/** animate the movement. */
		while (t <= T) {
			double[] xForces = new double[num];
			double[] yForces = new double[num];
			for(int i = 0; i < num; i += 1) {
				xForces[i] = allplanets[i].calcNetForceExertedByX(allplanets);
				yForces[i] = allplanets[i].calcNetForceExertedByY(allplanets);
			}
			for(int i = 0; i < num; i += 1) {
				allplanets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, imageToDraw);
			for(Planet b : allplanets) {
			b.draw();
		}
		StdDraw.show();
		StdDraw.pause(10);
		t += dt;
		}
		/** print out the universe at the T moment*/
		StdOut.printf("%d\n", num);
		StdOut.printf("%.2e\n", radius);
		for (Planet b: allplanets) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
               	b.xxPos, b.yyPos, b.xxVel,
                b.yyVel, b.mass, b.imgFileName);   
}
	}
}
