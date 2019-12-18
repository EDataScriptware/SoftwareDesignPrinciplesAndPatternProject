/*
 * Initial Author
 *      Michael J. Lutz
 *
 * Other Contributers
 *
 * Acknowledgements
 */

/*
 * The TextUI class is an observer of the WeatherStation that,
 * when it receives an update message, prints the temperature
 * in Celsius and Kelvin.
 *
 * The main method for the text based monitoring application
 * is here as well.
 */
package com.activity3b; 
 
import java.util.Observer ;
import java.util.Observable;

public class TextUI implements Observer {
   private final WeatherStation station ;
      
   /*
    * Remember the station we're attached to and
    * add ourselves as an observer.
    */
   public TextUI(WeatherStation station) {
      this.station = station ;
      this.station.addObserver(this) ;
   }

   /*
    * Called when WeatherStation gets another reading.
    * The Observable should be the station; the Object
    * argument is ignored.
    */
   public void update(Observable obs, Object ignore) {
      /*
       * Check for spurious updates from unrelated objects.
       */
      if( station != obs ) {
         return ;
      }
      /*
       * Retrieve and print the temperatures & pressures.
       */
      System.out.printf(
             "Temperature:%6.2f C %6.2f F %6.2f K%nPressure: %6.2f inches %6.2f mbar%n%n",
             station.showCelsius(), station.showFahrenheit(), station.showKelvin(), station.showInches(), station.showMillibars()) ;
             
   }

   /*
    * Start the application.
    */
   public static void main(String[] args) {
   
      Barometer baro = new Barometer();
      KelvinTempSensor kts = new KelvinTempSensor();
      KelvinTempSensorAdapter adpt = new KelvinTempSensorAdapter(kts);
      WeatherStation ws = new WeatherStation(baro, adpt) ;
      Thread thread = new Thread(ws) ;
      TextUI ui = new TextUI(ws) ;
   
      thread.start();
   }
}
