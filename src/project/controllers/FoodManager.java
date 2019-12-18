import java.util.ArrayList;

public class FoodManager
{

    public FoodManager()
    {
        loadFoodData();
        loadRecipeData();
    }

    private FileManager fm = new FileManager();
    private Recipe collection = new Recipe();
    private Recipe headRec = new Recipe();
    private Recipe subRec = new Recipe();


    public void AddFood(Food _food)
    {
        collection.Add(_food);

        for(var b :collection.getRecipe())
        {
            if(b instanceof  BasicFood)
            {
                System.out.println(b.toString());
            }
        }
    }

    //for checking to see if food already exist inside before adding food to prevent duplication.
    public boolean isFoodExists(String name)
    {
        try
        {
            for(var f : collection.getRecipe())
            {
                if(f instanceof BasicFood)
                {
                    if(f.getName().equals(name))
                    {
                        return true;
                    }
                }
                else if( f instanceof Recipe ){
                    if( f.getTitle().equalsIgnoreCase( name ) ){
                        return true;
                    }
                }
            }
            return false;
        }
        catch ( Exception e ){
            System.out.println("Error: " + e + " = Food is not found => " + name );
            return false;
        }
    }

    public ArrayList<String> getBasicFood()
    {
        ArrayList<String> data = new ArrayList<String>();
        for(var b : collection.getRecipe())
        {
            if(b instanceof  BasicFood)
            {
                data.add(b.getName());
            }
        }

        return data;
    }

    //check to see if food existed & if it does, then will display information for it
    public Food findFood(String name)
    {
        for(var f: collection.getRecipe())
        {
            if(f instanceof BasicFood)
            {
                if(f.getName().equals(name))
                {
                    return f;
                }
            }
            else if( f instanceof Recipe)
            {
                if(f.getLetter().equals("r") && f.getTitle().equals(name))
                {
                    return f;
                }
            }
        }

        return null;
    }

    public void removeFoodRecipe(Food _recipe, Food _food)
    {
        int index = collection.getRecipe().indexOf(_recipe);

        Recipe rec = (Recipe) collection.getRecipe().get(index);

        for(Food r : rec.getRecipe())
        {
            if(((Recipe)r).getName().equals(_food.getName()))
            {
                rec.getRecipe().remove(r);
                System.out.println("Removal Operation Successful");
                break;
            }
        }

    }

    public void addFoodRecipe(Food _recipe, Food _food, float serves)
    {
        int index = collection.getRecipe().indexOf(_recipe);

        Recipe rec = (Recipe) collection.getRecipe().get(index);

        rec.Add(new Recipe(_food.getName(),serves));
    }

    public void swapFoodServing(Food _recipe,Food _food, float serves)
    {
        int index = collection.getRecipe().indexOf(_recipe);

        Recipe rec = (Recipe)collection.getRecipe().get(index);

        for(var r : rec.getRecipe())
        {
            Recipe recs = (Recipe)r;

            if(recs.getName().equals(_food.getName()))
            {
                recs.setServing(serves);

                System.out.println("Changes successfully");
                break;
            }
        }
    }

    public Food findRecipe(String name)
    {
        for(var r : collection.getRecipe())
        {
            if( r instanceof Recipe)
            {
                if(r.getLetter().equals("r"))
                {
                    if(r.getTitle().equals(name))
                    {
                        return r;
                    }
                }
            }
        }
        return null;
    }

    public void replaceBasicFood(Food _food, String input, String changes)
    {
       int index =  collection.getRecipe().indexOf(_food);

       Food changeFood = collection.getRecipe().get(index);

                switch (input)
                {
                    case"1":
                        changeFood.setName(changes);
                        break;
                    case"2":
                        changeFood.setCalories(Float.parseFloat(changes));
                        break;
                    case"3":
                        changeFood.setFat(Float.parseFloat(changes));
                        break;
                    case"4":
                        changeFood.setCarb(Float.parseFloat(changes));
                        break;
                    case"5":
                        changeFood.setProtein(Float.parseFloat(changes));
                        break;
                }
                collection.getRecipe().set(index, changeFood);
    }

    public void displayRecipe()
    {
        for(var s: collection.getRecipe())
        {
            if(s instanceof  Recipe);
            {
                if(s.getLetter().equals("r"))
                {
                    Recipe rec = (Recipe)s;

                    System.out.println(rec.getLetter()+" "+rec.getTitle());
                    for(var r: rec.getRecipe())
                    {
                        if(r instanceof Recipe)
                        {
                            Recipe showR = (Recipe)r;
                            System.out.println(showR.getName()+" "+showR.getServing());
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Food> showRecipe()
    {
        return collection.getRecipe();
    }

    public void listFood()
    {
        for(var f : collection.getRecipe())
        {
            if(f instanceof  BasicFood)
            {
                System.out.println(f.toString());
            }
        }
    }

    public ArrayList<String> getFoodList(){
        ArrayList<String> foodList = new ArrayList<String>();
        for (var f : collection.getRecipe()){
            if (f instanceof BasicFood){
                foodList.add(f.getName());
            } else if (f instanceof Recipe) {
                if(f.getLetter().equals("r"))
                {
                    foodList.add(f.getTitle());
                } else
                {
                    if(!isFoodExists(f.getName()))
                    {
                        foodList.add(f.getName());
                    }
                }
            }
        }
        return foodList;
    }

    private void loadFoodData()
    {
        ArrayList<ArrayList<String>> datas = fm.read("basicfoods");

        for(var data: datas)
        {
            collection.Add(new BasicFood(data.get(0), data.get(1),Float.parseFloat(data.get(2)),Float.parseFloat(data.get(3)),Float.parseFloat(data.get(4)),Float.parseFloat(data.get(5))));
        }
        /*Testing purpose*/
//        for(var f : collection.getRecipe())
//        {
//            System.out.println(f.toString());
//        }
    }

    private void loadRecipeData()
    {

        ArrayList<ArrayList<String>> datas = fm.read("recipefoods");

        for (var data : datas){
            if (data.get(0).equals("b"))
            {
                collection.Add(new Recipe(data.get(0),data.get(1),Float.parseFloat(data.get(2)),Float.parseFloat(data.get(3)),Float.parseFloat(data.get(4)),Float.parseFloat(data.get(5))));
            }
            else if(data.get(0).equals("r"))
            {
                Recipe rec = new Recipe(data.get(0), data.get(1));


                int i = 2;

                while(i < data.size())
                {
                    rec.Add(new Recipe(data.get(i), Float.parseFloat(data.get(++i))));
                    i++;
                }

                collection.Add(rec);
            }
        }
        /*Testing purpose*/
        System.out.println("======== AFTER loading Recipe Data");
        for(var f : collection.getRecipe())
        {
            if(f instanceof Recipe)
            {
                if(f.getLetter().equals("r"))
                {
                    String msg ="";
                    //System.out.println(((Recipe) f).toString("h"));
                    msg = ((Recipe) f).toString("h");
                    Recipe rec = (Recipe)f;

                    for(var rs : rec.getRecipe())
                    {
                        Recipe recs = (Recipe)rs;
                        //System.out.println(recs.toString("s"));

                        msg += recs.toString("s");
                    }

                    System.out.println(msg);
                }
                else
                {

                    System.out.println(((Recipe)f).toString("b"));
                }
            }
        }
        System.out.println("======== END OF loading Recipe Data");

    }

    public void writeRecipeData()
    {
        ArrayList<String> writeObj = new ArrayList<String>();

        for(var r : collection.getRecipe())
        {
            if(r instanceof Recipe)
            {
                if(r.getLetter().equals("b"))
                {
                    writeObj.add(((Recipe) r).toString("b"));
                }
                else if(r.getLetter().equals("r"))
                {
                    String message = ((Recipe) r).toString("h");
                    for(var s :((Recipe) r).getRecipe())
                    {
                        message += ((Recipe) s).toString("s");
                    }
                    message += "\n";
                    writeObj.add(message);
                }
            }
        }
        fm.write(writeObj, "recipefoods");
    }


    public void writeFoodData()
    {
        /*Testing purpose*/
        //collection.add(new BasicFood("b","Chocolate",180.0f,15.0f,1.0f,12.0f));

        ArrayList<String> writeObj = new ArrayList<String>();

        for(var b : collection.getRecipe())
        {
            if(b instanceof  BasicFood)
            {
                writeObj.add(b.toString());
            }
        }

        fm.write(writeObj, "basicfoods");
    }
}
