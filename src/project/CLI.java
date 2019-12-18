

import java.lang.reflect.Array;
import java.util.*;
import java.time.*;
import java.time.format.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CLI
{
   // Sets up a class "cli" for Command Line Interface
   public static void main(String [] args)
   {
      Scanner scan = new Scanner(System.in);

      LocalDateTime myDateObj = LocalDateTime.now();
      String[] foodChoice = new String[6];
      String[] logChoice = new String[6];
      Wellness wellness = new Wellness();
      int year,month,day;
      float weight, calories;
      double minutes;
      View view = new View();

      DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");

      String formattedDate = myDateObj.format(myFormatObj);

      System.out.println(formattedDate);
      while(true)
      {

         System.out.println("Please select: \n1. Food\n2. Log of Food\n3. Find Food\n4. Exit\n5. Exercise Under Construction\n6. Log Weight\n7. Log Calorie\n8. Remove Food Log\n9. Total Calories Expended\n10. Total Calories\n11. Net Calories");

         switch (scan.nextLine()) {
            case "1":
               foodChoice[0] = "b";
               System.out.println("Name of food");
               foodChoice[1] = scan.nextLine();
               System.out.println("Amount of calories");
               foodChoice[2] = scan.nextLine();
               System.out.println(("Amount of fat"));
               foodChoice[3] = scan.nextLine();
               System.out.println("Amount of carbs");
               foodChoice[4] = scan.nextLine();
               System.out.println("Amount of protein");
               foodChoice[5] = scan.nextLine();
               wellness.getFoodManager().AddFood(new BasicFood(foodChoice[0], foodChoice[1], Float.parseFloat(foodChoice[2]), Float.parseFloat(foodChoice[3]), Float.parseFloat(foodChoice[4]), Float.parseFloat(foodChoice[5])));
               String findFood = foodChoice[1];
               view.setDisplay(wellness.getFoodManager().findFood(findFood));
               System.out.println("Food is added! ");
               view.getDisplay();
               break;
            case "2":
               String[] splits = formattedDate.split("/");
               logChoice[0] = splits[2];
               logChoice[1] = splits[1];
               logChoice[2] = splits[0];
               logChoice[3] = "f";

               System.out.println("Please select: \n1. Add a new log\n2. Add a serving of a existing food");
               // Add serving of existing log or add a new one
               switch (scan.nextLine())
               {
                  case "1":
                     System.out.println("Enter food name");
                     logChoice[4] = scan.nextLine();
                     System.out.println("Enter the ounces in float");
                     logChoice[5] = scan.nextLine();
                     wellness.getLogManager().Add(new DailyLog(Integer.parseInt(logChoice[0]), Integer.parseInt(logChoice[1]), Integer.parseInt(logChoice[2]), logChoice[3], logChoice[4], Float.parseFloat(logChoice[5])));
                     break;
                  case "2":
                     boolean matchFound = false;
                     ArrayList<String> collection = wellness.getFoodManager().getFoodList();
                     System.out.println("List of Food: ");
                     for(int i = 0; i < collection.size(); i++)
                     {
                        if(collection.get(i) != null)
                        {
                           System.out.println(collection.get(i));
                        }
                     }
                     System.out.println("Select a food:");
                     String input = scan.nextLine();

                     String selectedFood = null;
                     //check for any matches
                     for(int i = 0; i < collection.size(); i++)
                     {
                        if(input.equals(collection.get(i)))
                        {
                           matchFound = true;
                           selectedFood = collection.get(i);
                           System.out.println("MATCH FOUND!");
                        }
                     }

                     if(matchFound)
                     {
                        logChoice[4] = selectedFood;
                        System.out.println("Enter the ounces in float");
                        logChoice[5] = scan.nextLine();

                        wellness.getLogManager().Add(new DailyLog(Integer.parseInt(logChoice[0]), Integer.parseInt(logChoice[1]), Integer.parseInt(logChoice[2]), logChoice[3], logChoice[4], Float.parseFloat(logChoice[5])));

                        System.out.println("Successfully added a serving to dailylog");
                     }
                     else
                     {
                        System.out.println("MATCH HAS NOT BEEN FOUND!\n");
                     }


               }


               break;
            case"3":
               System.out.println("Enter the name of food you want to find.");
               String findFood2 = scan.nextLine();
               if(wellness.getFoodManager().isFoodExists(findFood2))
               {
                  view.setDisplay(wellness.getFoodManager().findFood(findFood2));
                  System.out.println(view.getDisplay());
               }
               else
               {
                  System.out.println("Food not found!");
               }
               break;
            case "4":
               wellness.getFoodManager().writeFoodData();
               wellness.getLogManager().writeLogData();
               System.exit(0);
               break;
            case"5":
               wellness.getExerciseManager();
               break;
            case "6":
               System.out.println("Enter your year: ");
               year = Integer.parseInt(scan.nextLine());
               System.out.println("Enter your month: ");
               month = Integer.parseInt(scan.nextLine());
               System.out.println("Enter your day: ");
               day = Integer.parseInt(scan.nextLine());
               System.out.println("Enter your weight: ");
               weight = Float.parseFloat(scan.nextLine());
               wellness.getLogManager().Add(new DailyLog(year, month, day, "w", weight));
               break;
            case "7":
               System.out.println("Enter your year: ");
               year = Integer.parseInt(scan.nextLine());
               System.out.println("Enter your month: ");
               month = Integer.parseInt(scan.nextLine());
               System.out.println("Enter your day: ");
               day = Integer.parseInt(scan.nextLine());
               System.out.println("Enter your calorie limit: ");
               calories = Float.parseFloat(scan.nextLine());
               wellness.getLogManager().Add(new DailyLog(year, month, day, "c", calories));
               break;
            case "8":
               System.out.println("---------------------------------------------------");
               wellness.getLogManager().displayLogData();
               System.out.println("Select the number that you wanted to remove:");
               int num = Integer.parseInt(scan.nextLine());
               //wellness.getLogManager().Remove(num);
               break;
            case "9":// display the total caloreis expend from exercise
               //System.out.println("\nTotal Calories Expended: " + wellness.getExerciseManager().totalCalorieBurn() + "\n");
               break;
            case "10":// display the total calories
               ArrayList<Food> data = wellness.getFoodManager().showRecipe();
               System.out.println("\nTotal Calories: " + wellness.getLogManager().totalCalories(data) + "\n");
               break;
            case "11":// display the net calories
               ArrayList<Food> data3 = wellness.getFoodManager().showRecipe();
               float totalCalories = wellness.getLogManager().totalCalories(data3);

               //float totalExpend = wellness.getExerciseManager().totalCalorieBurn();
               //System.out.println( "Current calories:\nTotal: " + totalCalories + "\nExpended from Exercise: " + totalExpend );
               //System.out.println( "\nNet Calories: (consumed - expended)" + (totalCalories - totalExpend) + "\n" );
               break;
            case "12": // all foods along with calories and number of serving
               ArrayList<Food> data2 = wellness.getFoodManager().showRecipe();
               System.out.println("Log food detail: \n" + wellness.getLogManager().getFoodIndividual(data2));

               break;
            case "13": // display all food logs
               ArrayList<DailyLog> getLog = wellness.getLogManager().getConsumedAll();
               ArrayList<Food> result = new ArrayList<Food>();

               for( DailyLog log: getLog ){
                  result.add( wellness.getFoodManager().findFood( log.getFood() ));
               }
               System.out.println( result );
               break;

            case "14":// display the exercise log along with number of calories burned
               ArrayList<Exercise> inp = wellness.getExerciseManager().getExerciseLog();
               for( Exercise et : inp ){
                  System.out.println( et.getExerciseName() + " = Calories expended => " + et.getCaloriesBurned() );
               }
               break;

            case "15":     // add exercise to log
               System.out.println("Enter your year: ");
               year = Integer.parseInt(scan.nextLine());
               System.out.println("Enter your month: ");
               month = Integer.parseInt(scan.nextLine());
               System.out.println("Enter your day: ");
               day = Integer.parseInt(scan.nextLine());
               System.out.println("Enter your exercise name: ");
               String exericse = scan.nextLine();
               System.out.println("Enter your minutes: ");
               minutes = Double.parseDouble(scan.nextLine());
               wellness.getLogManager().Add(new DailyLog(year, month, day, "e", exericse, minutes));
               break;
            case "16":
               ArrayList<Food> data5 = wellness.getFoodManager().showRecipe();
               System.out.println( wellness.getLogManager().getAllNutriFact(data5) );
               break;
         }
      }
   }
}