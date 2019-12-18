/*
 * Initial Author
 *      Michael J. Lutz
 *
 * Other Contributers
 *
 * Acknowledgements
 */

/*
 * Class for a (simulated) barometer. We assume the "real" barometer
 * returns atmospheric pressure, in inches of mercury, as a double
 * precision number (average pressure = 29.92 inches).
 *
 * NOTE: Outside the U.S. and Canada atmospheric pressure is
 *       given in millibars, where average pressure = 1013.25 mbar.
 *       The conversion factor is 1 inch = 33.864 mbar.
 */
package com.activity3b;

import java.util.Random ;   // to simulate random fluctuations.

public class Barometer implements IBarometer{
   private double currentPressure ;     // current sensor reading
   private boolean increasing = true ;  // TRUE if pressure increasing
   private Random rand = new Random() ; // simulate random changes

   /*
    * Initialize the sensor to the DEFAULT value.
    */
   public Barometer() {
      currentPressure = DEFAULT ;
   }
   
   /*
    * Simulate a new pressure reading based on the last reading
    * and whether the trend is up or down.
    * We assume that the pressure has a 75% chance of continuing
    * its current trend.
    * We also constrain the value to a reasonable range.
    */
   public double pressure() 
   {
      final double CUTOFF = 0.75 ;    // 75% chance to continue trend
      final double MAXDELTA = 0.2 ;   // maximum pressure change
      double pressureChange ;         // absolute value pressure change.
   
      if ( rand.nextDouble() > CUTOFF ) {
         increasing = ! increasing ;         // switch direction
      }
   
      /*
       * Generate the new simulated pressure.
       */
      pressureChange = rand.nextDouble() * MAXDELTA ;
      currentPressure = currentPressure +
         pressureChange * (increasing ? 1 : -1) ;
   
      /*
       * Limit readings to the specified (simulated) range.
       */
      if( currentPressure >= MAX ) {
         currentPressure = MAX ;
         increasing = false ;
      } else if (currentPressure <= MIN ) {
         currentPressure = MIN ;
         increasing = true ;
      }
   
      return currentPressure ;
   }
   
      /*
    * Return the current reading in degrees celsius as a
    * double precision number.
    */
     /*
     * Return the current reading in pressures Inches as
     * double precision number.
     */
   public synchronized double getInches(){
      return this.currentPressure;
   }
    /*
     * Return the current reading in pressures Millibars as
     * double precision number.
     */
   public synchronized double getMillibars(){
      return (this.currentPressure * INCHTOMILL);
   }
}
