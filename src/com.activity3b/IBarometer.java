// Java Interface, IBarometer, to allows any concrete Barometer to implement object here
// @author      Aaron Kelly
package com.activity3b;

public interface IBarometer{        // concrete Baromemter class
   final double MIN = 27.0 ;       // minimum reading
   final double MAX = 32.0 ;       // maximum reading
   final double DEFAULT = 29.92 ;  // default reading.
   final double INCHTOMILL = 33.864D; //Inches to Millibars conversion      

   public double pressure();
   public double getInches();
   public double getMillibars();
}