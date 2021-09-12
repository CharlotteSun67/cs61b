

public class NBody {

	public static String imageToDraw = "images/starfield.jpg";

	public static double readRadius(String name) {

		In in = new In(name);

		in.readInt();
		double radius = in.readDouble();

		return radius;

	}

	public static Planet[] readPlanets(String name) {

		In in = new In(name);

		int n = in.readInt();
		in.readDouble();

		Planet[] planets = new Planet[n];


		int i = 0;


		while (i < n) {

			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xP, yP, xV, yV, m, img);

			i = i + 1;

		}

		return planets;



	}

	public static void main(String[] args) {

		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet planets[] = readPlanets(filename);

		StdDraw.setScale(-radius, radius);

		StdDraw.enableDoubleBuffering();


		for (Planet p : planets) {

			p.draw();
		}

		double time = 0;
		while(time < T) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];

			for (int i = 0; i < planets.length; i++) {

				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);

			}

			for (int i = 0; i < planets.length; i++) {

				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, imageToDraw);

			for (Planet p : planets) {

				p.draw();

			}

			StdDraw.show();
			StdDraw.pause(10);

			time = time + dt;

		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}



	}






}