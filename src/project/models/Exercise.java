import java.util.*;

public class Exercise
{
    //private fields
    private String letter;
    private String exerciseName;
    private float caloriesBurned;
    private ArrayList<Exercise> exercises;

    //return the value of letter
    public String getLetter() {
        return letter;
    }
    //set the value of letter
    public void setLetter(String letter)
    {
        this.letter = letter;
    }

    //return the value of exercise
    public String getExerciseName()
    {
        return exerciseName;
    }

    //set the value exercise
    public void setExerciseName(String exerciseName)
    {
        this.exerciseName = exerciseName;
    }

    //return the value of caloriesburned
    public float getCaloriesBurned()
    {
        return caloriesBurned;
    }

    //set the value of calories burned
    public void setCaloriesBurned(float caloriesBurned)
    {
        this.caloriesBurned = caloriesBurned;
    }

    public ArrayList<Exercise> getExercise()
    {
        return exercises;
    }

    public void Add(Exercise exercise)
    {
        exercises.add(exercise);
    }

    public Exercise()
    {
        exercises = new ArrayList<Exercise>();
    }

    //constructor that accepts the values
    public Exercise(String letter, String exerciseName, float caloriesBurned)
    {
        this.setLetter(letter);
        this.setExerciseName(exerciseName);
        this.setCaloriesBurned(caloriesBurned);
    }

    //using this to write the exercise object to the file.
    @Override
    public String toString()
    {
        return this.getLetter()+","+this.getExerciseName()+","+this.getCaloriesBurned()+"\n";
    }
}
