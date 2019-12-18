package com.activity3b;

import java.util.*;

public class KelvinTempSensor
{ 

   int currentReading = 29315;
   boolean increasing = true;
   
   Random rand = new Random();
   
   public int reading() {
      if (rand.nextDouble() > 0.8D) {
         this.increasing = !this.increasing;
      }
      
      int i = rand.nextInt(100) + 200;
      this.currentReading += i * (this.increasing ? 1 : -1);
      if (this.currentReading >= 32315) {
         this.currentReading = 32315;
         this.increasing = false;
      } else if (this.currentReading <= 23315) {
         this.currentReading = 23315;
         this.increasing = true;
      }  
      return this.currentReading;
   }
}