package sg.edu.nus.iss.se.ft05.medipal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ethi on 10/03/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    protected static final String TABLE_CATEGORY = "categories";
    protected static final String TABLE_MEDICINE = "medicines";
    protected static final String TABLE_REMINDER = "reminders";
    protected static final String TABLE_MEASUREMENT = "measurement";
    private static final String DATABASE_NAME = "medipal";

    private static final int DATABASE_VERSION = 1;
    public static final String CATEGORY_KEY_ID = "id";
    public static final String CATEGORY_KEY_CATEGORY = "category";
    public static final String CATEGORY_KEY_CODE = "code";
    public static final String CATEGORY_KEY_DESCRIPTION = "description";
    public static final String CATEGORY_KEY_REMIND = "remind";
    public static final String MEDICINE_KEY_ID = "id";
    public static final String MEDICINE_KEY_MEDICINE = "medicine";
    public static final String MEDICINE_KEY_DESCRIPTION = "description";
    public static final String MEDICINE_KEY_CATID = "catId";
    public static final String MEDICINE_KEY_REMINDERID = "reminderId";
    public static final String MEDICINE_KEY_REMIND = "remind";
    public static final String MEDICINE_KEY_QUANTITY = "quantity";
    public static final String MEDICINE_KEY_DOSAGE = "dosage";
    public static final String MEDICINE_KEY_CONSUME_QUALITY = "consumeQuantity";
    public static final String MEDICINE_KEY_THRESHOLD = "threshold";
    public static final String MEDICINE_KEY_DATE_ISSUED = "dateIssued";
    public static final String MEDICINE_KEY_EXPIRE_FACTOR = "expireFactor";
    public static final String REMINDER_KEY_ID = "id";
    public static final String REMINDER_KEY_STARTTIME = "startTime";
    public static final String REMINDER_KEY_FREQUENCY = "frequency";

    public static final String MEASUREMENT_KEY_ID = "id";
    public static final String MEASUREMENT_KEY_SYSTOLIC = "systolic";
    public static final String MEASUREMENT_KEY_DIASTOLIC = "diastolic";
    public static final String MEASUREMENT_KEY_PULSE = "pulse";
    public static final String MEASUREMENT_KEY_TEMPERATURE = "temperature";
    public static final String MEASUREMENT_KEY_WEIGHT = "weight";
    public static final String MEASUREMENT_KEY_MEASURED_ON = "measuredOn";


    public static final String REMINDER_KEY_INTERVAL = "interval";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
            + TABLE_CATEGORY + "(" + CATEGORY_KEY_ID + " INTEGER PRIMARY KEY," + CATEGORY_KEY_CATEGORY
            + " TEXT," + CATEGORY_KEY_CODE + " TEXT," + CATEGORY_KEY_DESCRIPTION
            + " TEXT," + CATEGORY_KEY_REMIND + " INTEGER DEFAULT 0"+")";
    private static final String CREATE_TABLE_MEDICINE = "CREATE TABLE "
            + TABLE_MEDICINE + "(" + MEDICINE_KEY_ID + " INTEGER PRIMARY KEY," + MEDICINE_KEY_MEDICINE
            + " TEXT," + MEDICINE_KEY_DESCRIPTION + " TEXT," + MEDICINE_KEY_CATID + " INTEGER,"
            + MEDICINE_KEY_REMINDERID + " INTEGER," + MEDICINE_KEY_REMIND
            + " INTEGER DEFAULT 0," + MEDICINE_KEY_QUANTITY + " INTEGER,"+ MEDICINE_KEY_DOSAGE+ " INTEGER,"
            + MEDICINE_KEY_CONSUME_QUALITY+ " INTEGER," + MEDICINE_KEY_THRESHOLD+ " INTEGER,"+ MEDICINE_KEY_DATE_ISSUED+ " TEXT," +
            MEDICINE_KEY_EXPIRE_FACTOR + " INTEGER" + ")";
    private static final String CREATE_TABLE_REMINDER = "CREATE TABLE "
            + TABLE_REMINDER + "(" + REMINDER_KEY_ID + " INTEGER PRIMARY KEY," + REMINDER_KEY_FREQUENCY
            + " INTEGER," + REMINDER_KEY_STARTTIME + " TEXT," + REMINDER_KEY_INTERVAL
            + " INTEGER" + ")";
    private static final String CREATE_TABLE_MEASUREMENT = "CREATE TABLE "
            + TABLE_MEASUREMENT + "(" + MEASUREMENT_KEY_ID + " INTEGER PRIMARY KEY," + MEASUREMENT_KEY_SYSTOLIC
            + " INTEGER," + MEASUREMENT_KEY_DIASTOLIC + " INTEGER," + MEASUREMENT_KEY_PULSE + " INTEGER,"
            + MEASUREMENT_KEY_TEMPERATURE + " INTEGER," + MEASUREMENT_KEY_WEIGHT + " INTEGER,"
            + MEASUREMENT_KEY_MEASURED_ON + " TEXT" + ")";
    Connection connection = null;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_MEDICINE);
        db.execSQL(CREATE_TABLE_REMINDER);
        db.execSQL(CREATE_TABLE_MEASUREMENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEASUREMENT);
        onCreate(db);
    }

}