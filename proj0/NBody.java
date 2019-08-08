public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        return in.readDouble();
    }

    public static int readBodyNum(String filename) {
        In in = new In(filename);
        return in.readInt();
    }

    public static Body[] readBodies(String filename) {
        int index = 0;
        In in = new In(filename);
        int num = in.readInt();
        Body[] bodies = new Body[num];
        in.readDouble();
        while(!in.isEmpty() && index < num) {
            bodies[index] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
            index++;
        }
        return bodies;
    }

    public static void main(String[] args) {
        //read data
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        int num = readBodyNum(filename);
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        String background = "images/starfield.jpg";
        
        //create animation
        StdDraw.enableDoubleBuffering();
        double time = 0.0;
        while(time < T) {
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for(int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            //update bodies state
            for(int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            //draw backgroud
            StdDraw.setScale(-radius,radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, background);
            //draw bodys
            for(Body b : bodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
    
    }
}