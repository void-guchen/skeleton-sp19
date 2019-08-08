public class Body {
    public static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    //The name of the file that corresponds to the image that depicts the body  i.e. juputer.gif
    public String imgFileName;
    
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = b.xxPos - xxPos;
        double dy = b.yyPos - yyPos;
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));   
    }

    public double calcForceExertedBy(Body b) {
        double dis = calcDistance(b);
        return (G * mass * b.mass) / Math.pow(dis, 2);
    }

    public double calcForceExertedByX(Body b) {
        double dx = b.xxPos - xxPos;
        return calcForceExertedBy(b) * dx / calcDistance(b);
    }

    public double calcForceExertedByY(Body b) {
        double dy = b.yyPos - yyPos;
        return calcForceExertedBy(b) * dy / calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] bodys) {
        double fx = 0.0;
        for(Body b: bodys) {
            if(!b.equals(this))
                fx += calcForceExertedByX(b);
        }
        return fx;
    }
    public double calcNetForceExertedByY(Body[] bodys) {
        double fy = 0.0;
        for(Body b: bodys) {
            if(!b.equals(this))
                fy += calcForceExertedByY(b);
        }
        return fy; 
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel = xxVel + aX * dt;
        yyVel = yyVel + aY * dt;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt; 
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}