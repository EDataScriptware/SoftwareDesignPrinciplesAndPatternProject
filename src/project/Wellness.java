

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;

public class Wellness
{
   //list of variables (if needed)
   private FileManager fm = new FileManager();
   private FoodManager foodManager = new FoodManager();
   private LogManager logManager = new LogManager();
   private ExerciseManager exerciseManager = new ExerciseManager();
   private Boolean loadedDataConnection = false;

   //constructor
   public Wellness()
   {
      // Load LogData and FoodData
      if (!loadedDataConnection)
      {
         loadedDataConnection = true;
      }
   }

   //call the Food Controller
   public FoodManager getFoodManager()
   {
       return foodManager;
   }

   //call the Log Controller
   public LogManager getLogManager()
   {
       return logManager;
   }

   //call the Exercise Controller
   public ExerciseManager getExerciseManager()
   {
       return exerciseManager;
   }
}