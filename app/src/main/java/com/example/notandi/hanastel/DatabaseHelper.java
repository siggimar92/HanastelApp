package com.example.notandi.hanastel;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Notandi on 29-Oct-15.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static String DB_PATH;
    //"data/data/com.example.notandi.hanastel/databases/";


    private static String DB_NAME = "mydatabase.sqlite";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
    }

    public void createDataBase() throws IOException {
        //THIS MAY NOT WORK, IF ENCOUNTING ERROR WITH DB, CHECK IF THIS IS RIGHT
        boolean dbExist = false;//checkDataBase();
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

    public ArrayList<String> getStuff() {
        String selectQuery = "SELECT * FROM Ingredients";
        Cursor c = myDataBase.rawQuery(selectQuery, null);

        ArrayList<String> itemArray = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                itemArray.add(c.getString(0));
                Log.d("db tag", c.getString(0));
                Log.d("db tag", c.getString(1));

            }while(c.moveToNext());
        }
        c.close();

        return itemArray;
    }

    public Cursor showAllTables(){
        String mySql = "SELECT name FROM sqlite_master WHERE type='table' ORDER BY name;";
        return myDataBase.rawQuery(mySql, null);
    }
}
