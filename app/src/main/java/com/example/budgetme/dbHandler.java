package com.example.budgetme;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.budgetme.addexpense.AddexpnseModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOME on 3/4/2018.
 */
public class dbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 10;

    private static final String DATABASE_NAME = "myDatabase";
    private static final String CATEGORY_TABLE = "categories";
    public static final String EXPENSE_TABLE = "expenses";
    public static final String BUDGET_TABLE = "budget";
    public static final String _ID = "id";
    public static final String CAT_NAME = "catName";
    public static final String EXPENSE_CATEGORY = "category";
    public static final String DAY = "day";
    public static final String MONTH = "month";
    public static final String YEAR = "year";
    public static final String AMOUNT = "amount";
    public static final String DESCRIPTION = "description";
    public static final String DATE = "date";

    public dbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ////////////////// Category table create query //////////////////////

        String CREATE_CATEGORIES_TABLE = "CREATE TABLE IF NOT EXISTS " + CATEGORY_TABLE + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CAT_NAME + " TEXT UNIQUE)";
        db.execSQL(CREATE_CATEGORIES_TABLE);
        String CREATE_EXPENSE_TABLE = "CREATE TABLE IF NOT EXISTS " + EXPENSE_TABLE + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                " category TEXT , " + AMOUNT + " REAL , " + DAY + " INTEGER , " + MONTH + " INTEGER , " + YEAR + " INTEGER, "
                + DESCRIPTION + " TEXT," +DATE + " TEXT)";
        db.execSQL(CREATE_EXPENSE_TABLE);
        String CREATE_BUDGET_TABLE = "CREATE TABLE IF NOT EXISTS " + BUDGET_TABLE + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AMOUNT + " REAL, " + MONTH + " INTEGER, " + YEAR + " INTEGER , CONSTRAINT monthYear_unique UNIQUE(" + MONTH + "," + YEAR + "))";
        db.execSQL(CREATE_BUDGET_TABLE);
        db.execSQL("INSERT INTO categories (catName) VALUES ('FOOD'),('TRANSPORT'),('MEDICAL'),('EDUCATION'),('ENTERTAINMENT'),('CLOTHES')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BUDGET_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EXPENSE_TABLE);
        // Create tables again
        onCreate(db);
    }

    /////////////////////////////////// Inserting BUDGET into budget table
/////////////////////////
    public long insertBudget(String table_name, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        long newRowId = db.replace(table_name, null, values);
        return newRowId;
    }
    public Float getBudget(String table_name, ContentValues values) {
        List<String> categoriesList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT amount FROM "+table_name+" where month="+values.get("month")+" AND year=" +values.get("year");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
               return cursor.getFloat(0);

            } while (cursor.moveToNext());
        }
        // closing connection

        cursor.close();
        db.close();
        return 0f;
    }

    /////////////////////////////////// Inserting EXPENSES into expenses table
/////////////////////////
    public long insertExpense(String table_name, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        long newRowId = db.insert(table_name, null, values);
        return newRowId;
    }

    public Cursor executeRawQuery(String rawQuery) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(rawQuery, null);
        return c;
    }

    public void deleteExpense(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String del = "DELETE FROM expenses WHERE id=" + id;
        db.execSQL(del);
    }

    ///////////////////////////////////////Inserting new categories into categories table
//////////////////////////
    public void insertCategory(String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAT_NAME, category);

        // Inserting Row
        db.insert(CATEGORY_TABLE, null, values);
        // Closing database connection
        db.close();
    }

    //////////////////////Getting all categories returns list of categories
    public List<String> getAllCategories() {
        List<String> categoriesList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + CATEGORY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                categoriesList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // closing connection

        cursor.close();
        db.close();
        // returning lables
        return categoriesList;
    }
    public List<AddexpnseModel> getAllExpense(String tableName) {
        List<AddexpnseModel> categoriesList = new ArrayList<AddexpnseModel>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddexpnseModel addexpnseModel = new AddexpnseModel();
                addexpnseModel.setId(cursor.getInt(0));
                addexpnseModel.setPrice(cursor.getFloat(2));
                addexpnseModel.setCategory(cursor.getString(1));
                addexpnseModel.setDate(cursor.getString(7));
                addexpnseModel.setDescription(cursor.getString(6));
                categoriesList.add(addexpnseModel);

            } while (cursor.moveToNext());
        }
        // closing connection

        cursor.close();
        db.close();
        // returning lables
        return categoriesList;
    }

    //////////////////////////////////// Deleting categories /////////////////////////////////////////
    void delete(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM categories WHERE catName = '" + name +
                "'");
    }

    /////////////////////////////// AndroidDatabaseManagerFunctions
////////////////////////////////////////////
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();

        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);
        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);
            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});
            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {
                alc.set(0, c);
                c.moveToFirst();
                return alc;
            }
            return alc;
        } catch (Exception ex) {
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }
    /////////////////////////////// AndroidDatabaseManagerFunctions
////////////////////////////////////////////
}