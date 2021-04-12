package com.hfad.starbuzz;
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

 public class StarbuzzDatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "starbuzz"; // the name of our database
     private static final int DB_VERSION = 2; // the version of the databse
     StarbuzzDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabse(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
     updateMyDatabse(db, oldVersion, newVersion);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
         if (oldVersion ==3){

         }
    }
    private static void insertDrink(SQLiteDatabase db, String name, String description, int resourceId){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("DRINK", null, drinkValues);
    }
    private void updateMyDatabse(SQLiteDatabase db, int oldVersion, int newVersion){
         if (oldVersion < 1){
             db.execSQL("CREATE TABLE DRINK ("
                     + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                     + "NAME TEXT, "
                     + "DESCRIPTION TEXT, "
                     + "IMAGE_RESOURCE_ID INTEGER);");
             insertDrink(db, "Latte", "Expresso and steamed milk", R.drawable.latte);
             insertDrink(db, "Cappuccino", "Expresso, hot milk and steamed-milk foam", R.drawable.cappuccino);
             insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter);
         }
         if (oldVersion < 2){
             db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
         }
    }
}
