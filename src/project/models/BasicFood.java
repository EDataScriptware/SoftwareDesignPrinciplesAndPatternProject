//Aaron Parker
//11/2/19
import java.util.*;

public class BasicFood extends Food
{
   private String name;
   private float calories;
   private float fat;
   private float carb;
   private float protein;
   private String letter;

   public BasicFood(String _letter, String _name, float _calories, float _fat, float _carb, float _protein)
   {
      this.setLetter(_letter);
      this.setName(_name);
      this.setCalories(_calories);
      this.setFat(_fat);
      this.setCarb(_carb);
      this.setProtein(_protein);
   }

   String getLetter()
   {
      return letter;
   }

   void setLetter(String _letter)
   {
      this.letter = _letter;
   }
   //return the values of name
   String getName()
   {
      return name;
   }

   void setName(String _name)
   {
      this.name = _name;
   }
   //return the values of calories
   float getCalories(){
      return calories;}
   //set the values of calories
   void setCalories(float _calories){this.calories = _calories;}

   //return the values of fat
   float getFat(){
      return fat;}

   //set the values of fat
   void setFat(float _fat){this.fat = _fat;}

   //return the values of carb
   float getCarb(){
      return carb;}

   //set the values of carb
   void setCarb(float _carb){this.carb = _carb;}

   //return the values of protein
   float getProtein(){
      return protein;}

   //set the values of protein
   void setProtein(float _protein){this.protein = _protein;}

   //tostring for writing to file
   @Override
   public String toString()
   {
      return getLetter()+","+getName()+","+getCalories()+","+getFat()+","+getCarb()+","+getProtein()+"\n";
   }


}