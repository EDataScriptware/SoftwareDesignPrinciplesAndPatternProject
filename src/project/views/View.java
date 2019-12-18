public class View
{
    private String display;

    public String getDisplay()
    {
        return display;
    }

    public void setDisplay(Food _food)
    {
        if(_food.getLetter().equals("b"))
        {
            this.display = "Food: "+_food.getName()+"\nCalories: "+_food.getCalories()+
                    "\nFat: "+_food.getFat()+"\nCarb: "+_food.getCarb()+"\nProtein: "+_food.getProtein();
        }
        else if(_food instanceof Recipe)
        {
            Recipe recipe = (Recipe)_food;

            this.display = "Recipe: "+recipe.getName();
        }
    }

    public void setDisplay(DailyLog _log)
    {

        this.display = "Date: "+_log.getMonth()+"/"+_log.getDay()+"/"+_log.getYear();
    }

    public void setDisplay(Exercise exercise)
    {
        this.display = "Exercise: "+exercise.getExerciseName()+"\nCalories burned: "+exercise.getCaloriesBurned();
    }
}
