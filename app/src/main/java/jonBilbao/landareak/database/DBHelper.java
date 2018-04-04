package jonBilbao.landareak.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import jonBilbao.landareak.objects.Measure;
import jonBilbao.landareak.objects.Plant;


public class DBHelper extends SQLiteOpenHelper {
    public static final String PLANTS_COLUMN_NAME = "name";
    public static final String PLANTS_COLUMN_START_DATE = "start_date";
    public static final String PLANTS_COLUMN_IMAGE = "image";
    public static final String MEASURE_DATE = "measure_date";
    public static final String MEASURE_HUMIDITY = "humidity";
    public static final String MEASURE_TEMPERATURE = "temperature";

    public DBHelper(Context context){
        super(context, "GrowHelperDB", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS plants (id integer primary key AUTOINCREMENT NOT NULL, name text, start_date text, image text)");
        db.execSQL("CREATE TABLE IF NOT EXISTS log_entries (id integer primary key AUTOINCREMENT NOT NULL, plant_id integer, description text, notes text, date text, image text)");
        db.execSQL("CREATE TABLE IF NOT EXISTS measure (id integer primary key AUTOINCREMENT NOT NULL, name text, MEASURE_DATE text, MEASURE_HUMIDITY text, MEASURE_TEMPERATURE TEXT)");

    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {}

    public boolean insertPlant(String name, String startDate, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("start_date", startDate);
        contentValues.put("image", image);
        db.insert("plants", null, contentValues);
        db.close();
        return true;
    }

    public ArrayList<Plant> getAllPlants() {
        ArrayList<Plant> array_list = new ArrayList<Plant>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT id, name, start_date, image FROM plants", null );
        res.moveToFirst();

        while(res.isAfterLast() == false) {
            int id = res.getInt(res.getColumnIndex("id"));
            String name = res.getString(res.getColumnIndex(PLANTS_COLUMN_NAME));
            String startDate = res.getString(res.getColumnIndex(PLANTS_COLUMN_START_DATE));
            String image = res.getString(res.getColumnIndex(PLANTS_COLUMN_IMAGE));
            Plant p = new Plant(id, name, startDate, image);
            array_list.add(p);
            res.moveToNext();
        }
        db.close();
        return array_list;
    }

    public boolean insertMeasure(String name, String measureDate, String humidity, String temperature) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("measure_date", measureDate);
        contentValues.put("humidity", humidity);
        contentValues.put("temperature", temperature);
        db.insert("measure", null, contentValues);
        db.close();
        return true;
    }

    public ArrayList<Measure> getAllMeasures() {
        ArrayList<Measure> array_list = new ArrayList<Measure>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT id, name, measure_date, humidity, temperature FROM measure", null );
        res.moveToFirst();

        while(res.isAfterLast() == false) {
            int id = res.getInt(res.getColumnIndex("id"));
            String name = res.getString(res.getColumnIndex(PLANTS_COLUMN_NAME));
            String measureDate = res.getString(res.getColumnIndex(MEASURE_DATE));
            String humidity = res.getString(res.getColumnIndex(MEASURE_HUMIDITY));
            String temperature = res.getString(res.getColumnIndex(MEASURE_TEMPERATURE));
            Measure measur = new Measure(id, name, measureDate, humidity, temperature);
            array_list.add(measur);
            res.moveToNext();
        }
        db.close();
        return array_list;
    }
}