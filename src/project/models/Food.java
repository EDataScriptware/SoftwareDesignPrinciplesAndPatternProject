
public abstract class Food
{
   abstract String getLetter();

   abstract void setLetter(String _letter);
   //asbtract method for return the values of name;
   abstract String getName();

   void setTitle(String _title){};

   String getTitle(){return null;}

   //asbtract method for set the values of name;
   abstract void setName(String _name);

   //abstract method for return the values of calories
   abstract float getCalories();

   //abstract method for set the values of calories
   abstract void setCalories(float _calories);

   //abstract method for return the values of fat
   abstract float getFat();

   //abstract method for set the values of fat
   abstract void setFat(float _fat);


   //abstract method for return the values of carb
   abstract float getCarb();

   //abstract method for set the values of carb
   abstract void setCarb(float _carb);

   //abstract method for return the values of protein
   abstract float getProtein();

   //abstract method for set the values of protein
   abstract void setProtein(float _protein);
}