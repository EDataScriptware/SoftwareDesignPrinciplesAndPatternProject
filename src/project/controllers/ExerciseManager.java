import java.util.ArrayList;

public class ExerciseManager
{
    private FileManager fm = new FileManager();
   private Exercise exercise = new Exercise();

    // defaulted constructor
    public ExerciseManager()
    {
        loadExerciseData();
    }

    // overloaded constructor


    // loading exercise information
    public void loadExerciseData()
    {

        ArrayList<ArrayList<String>> inData = fm.read( "exercise" );

        for( var in: inData )
        {
            exercise.Add( new Exercise( in.get(0), in.get(1), Float.parseFloat( in.get(2) ) ) );
        }
    }

    public void addExercise(Exercise _exercise)
    {
        exercise.Add(_exercise);
    }

    // update exercise file
    public void writeExerciseData(){
        if( !exercise.getExercise().isEmpty())
        {
            ArrayList<String> data = new ArrayList<String>();
            for( var dat: exercise.getExercise())
            {
                data.add( dat.toString() );
            }
            fm.write( data, "exercise" );
        }
    }

    // Return the total amount of calories expended or burned
    public float totalCalorieBurn(Float _totalCalories, Double _totalMinutes)
    {
        // Calories burned per minute
        double burn = 2;
        double burned = burn * _totalMinutes;

        double finalTotalCalories = _totalCalories - burned;


        return ((float)finalTotalCalories);
    }

    // returns all exercises
    public ArrayList<Exercise> getExerciseLog(){
        return exercise.getExercise();
    }
}
