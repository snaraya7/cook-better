package com.bot.cookbetter.version2;

import ch.qos.logback.core.db.dialect.SybaseSqlAnywhereDialect;
import com.bot.cookbetter.version2.Ingredient;
import java.sql.ResultSet;
import java.util.Set;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.util.HashSet;

/**
 * @author snaraya7 Shrikanth N C
 */
public class Util {

    public static void main(String[] args) {
        Recipe rec;
        rec = getRecipe(2);

        //Get Recipe ID
        System.out.println(rec.getID());
        //Get Recipe Name
        System.out.println(rec.getName());
        //Get Recipe Ingredients
        for (Ingredient in : rec.getIngredients()) {
            System.out.print(in.getName());
            System.out.print("\t");
        }
    }
    //@kashyap
    public static String extractIngredients(String naturalQuery){

        // return csv : apple,milk,orange
        return null;
    }

    //@kashyap
    public  static Set<Ingredient> constructIngredients(String csvIngredients){
        return null;
    }


    //@charan
    public static Recipe getRecipe(int recipeID) {

        try {
            // Database connection
            Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://mydbinstance.ckzbitlijtbu.us-west-2.rds.amazonaws.com:3306/cookbetter?useUnicode=true&characterEncoding=UTF-8&user=cookbetter&password=cookbetter";
            Connection conn = DriverManager.getConnection(connectionUrl);
            //System.out.println("Conn established");
            String query = "select * from data where id = '"+recipeID+"';";

            ResultSet rs = conn.prepareStatement(query).executeQuery();

            //String result = "";
            Recipe recipe = new Recipe();
            while(rs.next()) {
                //System.out.println("id:" + rs.getInt(1));
                int ID = rs.getInt(1);
                //System.out.println("title:" + rs.getString(2));
                String name = rs.getString(2);
                recipe.setID(ID);
                recipe.setName(name);

                ResultSetMetaData rsMetaData = rs.getMetaData();
                int numberOfColumns = rsMetaData.getColumnCount();

                Set<Ingredient> ingredients = new HashSet<Ingredient>();
                for (int idx = 14; idx < numberOfColumns + 1; idx++) {
                    // Get the name of the column's name
                    if (rs.getInt(idx) == 1)
                        ingredients.add(new Ingredient(rsMetaData.getColumnName(idx)));
                }
                recipe.setIngredients(ingredients);

                //result+="<";
                //String link = "https://www.epicurious.com/search/";
                //String modTitle = name.replaceAll(" ", "%20");
                //link+=modTitle+"%20";
                //result+= link + "|"+name+"> \n";
            }
            return  recipe;
        }
        catch(Exception e) {
            //System.err.println(e);
            e.printStackTrace();
        }
        return null;
    }

    //@karthik
    public static Set<Ingredient> getIngredients(int recipeID){
        return null;
    }

    //@karthik
    public static Set<Ingredient> getAllIngredients(){
        return  null;
    }
}