package com.example.notandi.hanastel.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.notandi.hanastel.domain.Cocktail;
import com.example.notandi.hanastel.domain.Favorite;
import com.example.notandi.hanastel.domain.IngredientInDrink;
import com.example.notandi.hanastel.domain.IngredientRaw;
import com.example.notandi.hanastel.domain.MyBarIngredient;
import com.example.notandi.hanastel.product.CocktailRecipe;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Notandi on 29-Oct-15.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static String DB_PATH;
    //"data/data/com.example.notandi.hanastel/databases/";


    private static String DB_NAME = "mydatabase.sqlite";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    int index = 0;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
    }

    public void createDataBase() throws IOException {
        //THIS MAY NOT WORK, IF ENCOUNTING ERROR WITH DB, CHECK IF THIS IS RIGHT
        boolean dbExist = false; //checkDataBase();
        if(dbExist){
            //do nothing - database already exist
        }
        else {
            this.getWritableDatabase();
            try {
                copyDataBase();
            }
            catch (IOException e){
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e){
            //database does't exist yet.
        }
    if(checkDB != null){
        checkDB.close();
    }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws  IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        if(myInput == null){
            throw new IOException("Database file not found");
        }

        //"data/data/com.example.notandi.hanastel/databases/mydatabase.sqlite
        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized  void close(){
        if(myDataBase != null){
            myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private Cursor selectAllFrom(String table){
        String selectQuery = "SELECT * FROM " + table + ";";
        return myDataBase.rawQuery(selectQuery, null);
    }

    public ArrayList<Cocktail> getDomainCocktails(){
        ArrayList<Cocktail> cts = new ArrayList<>();

        Cursor c = selectAllFrom("Cocktails");

        if(c.moveToFirst()){
            do{
                Cocktail ct = new Cocktail();
                ct.set_id(c.getInt(0));
                ct.setName(c.getString(1));
                ct.setPicture(c.getString(2));
                ct.setDescription(c.getString(3));

                cts.add(ct);
            }while(c.moveToNext());
        }
        c.close();

        return cts;
    }

    public ArrayList<Favorite> getDomainFavorites(){
        ArrayList<Favorite> fvs = new ArrayList<>();

        Cursor c = selectAllFrom("Favorites");

        if(c.moveToFirst()){
            do{
                Favorite fv = new Favorite();
                fv.set_id(c.getInt(0));
                fv.setCocktailId(c.getInt(1));

                fvs.add(fv);
            }while(c.moveToNext());
        }
        c.close();

        return fvs;
    }

    public ArrayList<IngredientInDrink> getDomainIngredientsInDrinks(){
        ArrayList<IngredientInDrink> ingrs = new ArrayList<>();
        Cursor c = selectAllFrom("IngredientsInDrink");

        if(c.moveToFirst()){
            do{
                IngredientInDrink ingr = new IngredientInDrink();
                ingr.set_id(c.getInt(0));
                ingr.setCocktailId(c.getInt(1));
                ingr.setIngredientId(c.getInt(2));
                ingr.setQuantity(c.getString(3));

                ingrs.add(ingr);
            }while(c.moveToNext());
        }
        c.close();
        return ingrs;
    }

    public ArrayList<IngredientRaw> getDomainIngredientRaw(){
        ArrayList<IngredientRaw> irs = new ArrayList<>();
        Cursor c = selectAllFrom("Ingredients");

        if(c.moveToFirst()){
            do{
                IngredientRaw ir = new IngredientRaw();
                ir.set_id(c.getInt(0));
                ir.setName(c.getString(1));

                irs.add(ir);
            } while(c.moveToNext());
        }
        c.close();
        return irs;
    }

    public ArrayList<MyBarIngredient> getDomainMyBarIngredient(){
        ArrayList<MyBarIngredient> mbis = new ArrayList<>();
        Cursor c = selectAllFrom("MyBar");

        if(c.moveToFirst()){
            do{
                MyBarIngredient mb = new MyBarIngredient();
                mb.set_id(c.getInt(0));
                mb.setIngredientId(c.getInt(1));

                mbis.add(mb);
            }while(c.moveToNext());
        }
        c.close();
        return mbis;
    }

    public Cursor showAllTables(){
        String mySql = "SELECT name FROM sqlite_master WHERE type='table' ORDER BY name;";
        return myDataBase.rawQuery(mySql, null);
    }

    public boolean searchForFavorite(CocktailRecipe favRecipie) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT COUNT(*) FROM Favorites WHERE CocktailId = \'" + favRecipie.getCocktailId() + "\'", null);

        Log.d("------> Search Fav: ", "c-> " + c.getCount());

        if (c.getCount() > 0) {
            Log.d("------> Search Fav: ", "TRUE");
            return true;
        } else {
            Log.d("------> Search Fav: ", "FALSE");
            return false;
        }
    }
    public void addFavorite(CocktailRecipe favRecipie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        index++;
        values.put("_id", index);
        values.put("CocktailId", favRecipie.getCocktailId());

        db.insert("Favorites", null, values);

        Log.d("Add DB: ", "Successfully Added to DB" + favRecipie.getCocktailId());
    }

    public void deleteFavorite(CocktailRecipe favRecipie){
        Log.d("Delete from DB: ", "inside delete");

        SQLiteDatabase db = this.getWritableDatabase();

        //db.rawQuery("DELETE FROM Favorites WHERE CocktailId = \'" + favRecipie.getCocktailId() + "\'", null);

        db.delete("Favorites", "CocktailId = '" + favRecipie.getCocktailId() + "'", null);

        Log.d("Delete from DB: ", "Successfully deleted");
    }
}
