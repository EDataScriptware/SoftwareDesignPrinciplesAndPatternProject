
// A Recipe class is a composite class which to compose both a single recipe and a food with the abstract/interface of IFood
// Responsible to add and remove food and or recipe from the array
import java.util.*;

class Recipe extends Food
{
   // initialized variables
   private String display;
   private String letter;
   private String name;
   private float calories;
   private float fat;
   private float carb;
   private float protein;
   private String title;
   private float serving;
   private ArrayList<Food> recipes;

   // defaulted constructor

   public Recipe(String _letter, String _title)
   {
      this.setLetter(_letter);
      this.setTitle(_title);
      this.recipes  = new ArrayList<Food>();
   }

   public Recipe(String _name, float _serving)
   {
      this.setName(_name);
      this.setServing(_serving);
   }

   public Recipe(String letter, String name, float calories, float fat, float carbs, float protein)
   {
      this.setLetter(letter);
      this.setName(name);
      this.setCalories(calories);
      this.setFat(fat);
      this.setCarb(carbs);
      this.setProtein(protein);
   }

   public Recipe()
   {
      this.recipes = new ArrayList<Food>();
   }

   // returns data in a string
   public String getLetter()
   {
      return letter;
   }

   void setLetter(String _letter)
   {
      this.letter = _letter;
   }

   public String getDisplay()
   {
      return display;
   }

   void setDisplay(String _display)
   {
      this.display = _display;
   }

   void setName(String name) {
      this.name = name;
   }

   String getName()
   {
      return name;
   }

   void setServing(float _serving)
   {
      this.serving = _serving;
   }

   float getServing()
   {
      return serving;
   }

   //return the value of calories
   float getCalories(){
      return calories;}
   //set the values of calories
   void setCalories(float _calories)
   {
      if(_calories == 0.0f)
      {
         calories = 2000;
      }
      else
      {
         calories = _calories;
      }
   }

   ArrayList<Food> getRecipe()
   {
      return recipes;
   }

   void setTitle(String _title)
   {
      this.title = _title;
   }

   public String getTitle()
   {
      return title;
   }

   //return the values of fat
   float getFat(){
      return fat;}

   //set the values of fat
   void setFat(float _fat){fat = _fat;}

   //return the values of carb
   float getCarb(){
      return carb;}

   //set the values of carb
   void setCarb(float _carb){carb = _carb;}

   //return the values of protein
   float getProtein(){
      return protein;}

   //set the values of protein
   void setProtein(float _protein){protein = _protein;}

   public void Add(Food _recipe)
   {
      recipes.add(_recipe);
   }

   public void Remove(Food _recipe)
   {
      recipes.remove(_recipe);
   }



   public String toString(String signal)
   {
      if(signal.equals("b"))
      {
         return getLetter()+","+getName()+","+getCalories()+","+getFat()+","+getCarb()+","+getProtein()+"\n";
      }
      else if(signal.equals("h"))
      {
         return getLetter()+","+getTitle();
      }
      else if(signal.equals("s"))
      {
         return ","+getName()+","+getServing();
      }
      return "";
   }
}