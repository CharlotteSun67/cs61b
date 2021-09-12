

public class Planet {
   
      public double xxPos;
      public double yyPos;
      public double xxVel;
      public double yyVel;
      public double mass;
      public String imgFileName;
      public static final double G = 6.67e-11;



      public Planet (double xP, double yP, double xV, double yV, double m, String img) {
         xxPos = xP;
         yyPos = yP;
         xxVel = xV;
         yyVel = yV;
         mass = m;
         imgFileName = img;
      }

      public Planet (Planet p) {
         xxPos = p.xxPos;
         yyPos = p.yyPos;
         xxVel = p.xxVel;
         yyVel = p.yyVel;
         mass = p.mass;
         imgFileName = p.imgFileName;

      }

      public double calcDistance (Planet anotherPlanet) {
         double dx = anotherPlanet.xxPos - this.xxPos;
         double dy = anotherPlanet.yyPos - this.yyPos;
         double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

         return r;

      }

      public double calcForceExertedBy (Planet anotherPlanet) {
   
         double Force = (this.G * anotherPlanet.mass * this.mass)/(Math.pow(this.calcDistance(anotherPlanet),2));
         return Force;
      }

      public double calcForceExertedByX (Planet anotherPlanet) {

         double dx = anotherPlanet.xxPos - this.xxPos;
         double ForceByX = this.calcForceExertedBy(anotherPlanet) * dx/this.calcDistance(anotherPlanet);

         return ForceByX;
      }

      public double calcForceExertedByY (Planet anotherPlanet) {

         double dy = anotherPlanet.yyPos - this.yyPos;
         double ForceByY = this.calcForceExertedBy(anotherPlanet) * dy/this.calcDistance(anotherPlanet);

         return ForceByY;
      }
      
        public double calcNetForceExertedByX  (Planet[] allPlanets) {

         double netForceByX = 0;

         for (Planet p : allPlanets) {
            if (p.equals(this)) {
               continue;
            }

            netForceByX = netForceByX + this.calcForceExertedByX(p);

            }
         return netForceByX;

      }
      
        public double calcNetForceExertedByY  (Planet[] allPlanets) {

         double netForceByY = 0;

         for (Planet p : allPlanets) {
            if (p.equals(this)) {
               continue;
            }

            netForceByY = netForceByY - this.calcForceExertedByY(p);

         }
         return netForceByY;
     
      }

      public void update (double dt, double fx, double fy) {

         double a_x = fx/this.mass;
         double a_y = fy/this.mass;
         this.xxVel = this.xxVel + dt * a_x;
         this.yyVel = this.yyVel + dt * a_y;
         this.xxPos = this.xxPos + dt * this.xxVel;
         this.yyPos = this.yyPos + dt * this.yyVel;

      }

      public void draw() {

         StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);


      }
      

      
}



