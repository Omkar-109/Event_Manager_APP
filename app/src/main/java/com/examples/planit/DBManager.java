package com.examples.planit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.examples.planit.internals.Budget;
import com.examples.planit.internals.Event;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "event_manager.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_EVENT = "Event";
    private static final String TABLE_BUDGET = "Budget";
    private static final String TABLE_GUEST = "Guest";
    private static final String TABLE_VENDOR = "Vendor";
    private static final String TABLE_PAYMENT = "Payment";
    private static final String TABLE_BUDGET_PAYMENTS = "BudgetPayments";
    private static final String TABLE_EVENT_GUESTS = "EventGuests";
    private static final String TABLE_EVENT_VENDORS = "EventVendors";

    // Event Table Columns
    private static final String COL_EVENT_ID = "eventUID";
    private static final String COL_EVENT_NAME = "eventName";
    private static final String COL_EVENT_DATE = "startDate";
    private static final String COL_EVENT_LOCATION = "location";
    private static final String COL_EVENT_EVENT_STATUS = "eventStatus";
    private static final String COL_EVENT_BUDGET_UID = "budgetUID";

    // Budget Table Columns
    private static final String COL_BUDGET_ID = "budgetUID";
    private static final String COL_BUDGET_TOTAL_AMOUNT = "totalAmount";
    private static final String COL_BUDGET_ALLOCATED_AMOUNT = "allocatedAmount";

    // Guest Table Columns
    private static final String COL_GUEST_ID = "guestUID";
    private static final String COL_GUEST_NAME = "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_PHONE = "phone";
    private static final String COL_GUEST_STATUS = "status";

    // Vendor Table Columns
    private static final String COL_VENDOR_ID = "vendorUID";
    private static final String COL_VENDOR_NAME = "name";
    private static final String COL_SERVICE_TYPE = "serviceType";
    private static final String COL_CONTACT_INFO = "contactInfo";

    // Payment Table Columns
    private static final String COL_PAYMENT_ID = "paymentUID";
    private static final String COL_AMOUNT = "amount";
    private static final String COL_DATE = "date";
    private static final String COL_PAY_TO = "payTo";
    private static final String COL_STATUS = "status";

    // BudgetPayments Table Columns
    private static final String COL_BUDGET_PAY_UID = "budgetUID";
    private static final String COL_PAYMENT_UID = "paymentUID";

    // EventGuests Table Columns
    private static final String COL_EVENT_GUEST_ID = "eventUID";
    private static final String COL_GUEST_UID = "guestUID";

    // EventVendors Table Columns
    private static final String COL_EVENT_VENDOR_ID = "eventUID";
    private static final String COL_VENDOR_UID = "vendorUID";

    // Constructor
    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        // Create tables
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_EVENT + " ("
                + COL_EVENT_ID + " TEXT PRIMARY KEY,"
                + COL_EVENT_NAME + " TEXT NOT NULL,"
                + COL_EVENT_DATE + " TEXT NOT NULL,"
                + COL_EVENT_LOCATION + " TEXT NOT NULL,"
                + COL_EVENT_EVENT_STATUS + " TEXT NOT NULL,"
                + COL_EVENT_BUDGET_UID + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_BUDGET + " ("
                + COL_BUDGET_ID + " TEXT PRIMARY KEY,"
                + COL_BUDGET_TOTAL_AMOUNT + " REAL NOT NULL,"
                + COL_BUDGET_ALLOCATED_AMOUNT + " REAL NOT NULL);");

     /*   db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_GUEST + " ("
                + COL_GUEST_ID + " TEXT PRIMARY KEY,"
                + COL_GUEST_NAME + " TEXT NOT NULL,"
                + COL_EMAIL + " TEXT NOT NULL,"
                + COL_PHONE + " TEXT NOT NULL,"
                + COL_GUEST_STATUS + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_VENDOR + " ("
                + COL_VENDOR_ID + " TEXT PRIMARY KEY,"
                + COL_VENDOR_NAME + " TEXT NOT NULL,"
                + COL_SERVICE_TYPE + " TEXT NOT NULL,"
                + COL_CONTACT_INFO + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PAYMENT + " ("
                + COL_PAYMENT_ID + " TEXT PRIMARY KEY,"
                + COL_AMOUNT + " REAL NOT NULL,"
                + COL_DATE + " TEXT NOT NULL,"
                + COL_PAY_TO + " INTEGER NOT NULL,"
                + COL_STATUS + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_BUDGET_PAYMENTS + " ("
                + COL_BUDGET_PAY_UID + " TEXT NOT NULL,"
                + COL_PAYMENT_UID + " TEXT NOT NULL,"
                + "PRIMARY KEY (" + COL_BUDGET_PAY_UID + ", " + COL_PAYMENT_UID + "));");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_EVENT_GUESTS + " ("
                + COL_EVENT_GUEST_ID + " TEXT NOT NULL,"
                + COL_GUEST_UID + " TEXT NOT NULL,"
                + "PRIMARY KEY (" + COL_EVENT_GUEST_ID + ", " + COL_GUEST_UID + "));");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_EVENT_VENDORS + " ("
                + COL_EVENT_VENDOR_ID + " TEXT NOT NULL,"
                + COL_VENDOR_UID + " TEXT NOT NULL,"
                + "PRIMARY KEY (" + COL_EVENT_VENDOR_ID + ", " + COL_VENDOR_UID + "));");*/
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET);
        /*db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENDOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET_PAYMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT_GUESTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT_VENDORS);*/
        onCreate(db);
    }

    @Override
    public void onDowngrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET);
        /*db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENDOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET_PAYMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT_GUESTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT_VENDORS);*/
        onCreate(db);
    }

    /**
     * @noinspection UnusedReturnValue
     */
    public boolean addEvent(@NonNull Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EVENT_ID, event.getUID().toString());
        contentValues.put(COL_EVENT_NAME, event.getName());
        contentValues.put(COL_EVENT_DATE, event.getFormattedStartDate());
        contentValues.put(COL_EVENT_LOCATION, event.getLocation());
        contentValues.put(COL_EVENT_EVENT_STATUS, event.getEventStatus().getStatus());

        contentValues.put(COL_EVENT_BUDGET_UID, event.getBudget().getUID().toString());
        long result = db.insert(TABLE_EVENT, null, contentValues);

        if (result == -1) {
            Log.e("DB_INSERT", "Failed to insert event");
        } else {
            Log.d("DB_INSERT", "Event inserted with row ID: " + result);
        }

        return result != -1;
    }

    public ArrayList<Event> getAllEvents() {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Event> events = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EVENT, null);

        if (cursor.moveToFirst()) {
            do {
                String uid = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_NAME));
                String startDate = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_DATE));
                String location = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_LOCATION));
                String eventStatus = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_EVENT_STATUS));

                String budgetUID = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_BUDGET_UID));
                Budget budget = getBudgetById(budgetUID);

                events.add(new Event(uid, name, startDate, location, budget, eventStatus));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return events;
    }

    public boolean updateEvent(@NonNull Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EVENT_NAME, event.getName());
        contentValues.put(COL_EVENT_DATE, event.getFormattedStartDate());
        contentValues.put(COL_EVENT_LOCATION, event.getLocation());
        int result = db.update(TABLE_EVENT, contentValues, COL_EVENT_ID + " = ?", new String[]{event.getUID().toString()});
        return result > 0;
    }

    // TODO: When Deleting event we have to delete everything related to that event
    public Integer deleteEvent(@NonNull Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_EVENT, COL_EVENT_ID + " = ?", new String[]{event.getUID().toString()});
        if (res > 0) {
            res = deleteBudget(event.getBudget());
        }
        return res;
    }

    public Event getEventById(String eventUID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EVENT + " WHERE " + COL_EVENT_ID + " = ?", new String[]{eventUID});
        Event event;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String uid = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_NAME));
            String startDate = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_DATE));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_LOCATION));
            String eventStatus = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_EVENT_STATUS));

            String budgetUID = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_BUDGET_UID));
            Budget budget = getBudgetById(budgetUID);

            event = new Event(uid, name, startDate, location, budget, eventStatus);

        } else {
            event = new Event();
        }

        cursor.close();
        return event;
    }


    // Budget

    /**
     * @noinspection UnusedReturnValue
     */
    public boolean addBudget(@NonNull Budget budget) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_BUDGET_ID, budget.getUID().toString());
        contentValues.put(COL_BUDGET_TOTAL_AMOUNT, budget.getTotalBudget());
        contentValues.put(COL_BUDGET_ALLOCATED_AMOUNT, budget.getAllocated());

        long result = db.insert(TABLE_BUDGET, null, contentValues);

        if (result == -1) {
            Log.e("DB_INSERT", "Failed to insert budget");
        } else {
            Log.d("DB_INSERT", "Event inserted with row ID: " + result);
        }
        return result != -1;
    }

    public ArrayList<Budget> getAllBudgets() {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Budget> budgets = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BUDGET, null);

        if (cursor.moveToFirst()) {
            do {
                String budgetUID = cursor.getString(cursor.getColumnIndexOrThrow(COL_BUDGET_ID));
                double total = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_BUDGET_TOTAL_AMOUNT));
                double allocated = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_BUDGET_ALLOCATED_AMOUNT));

                budgets.add(new Budget(budgetUID, total, allocated));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return budgets;
    }

    public boolean updateBudget(@NonNull Budget budget) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_BUDGET_TOTAL_AMOUNT, budget.getTotalBudget());
        contentValues.put(COL_BUDGET_ALLOCATED_AMOUNT, budget.getAllocated());
        int result = db.update(TABLE_BUDGET, contentValues, COL_BUDGET_ID + " = ?", new String[]{budget.getUID().toString()});
        return result > 0;
    }

    // TODO: Delete Budget and delete reference in BudgetPayments Table
    public Integer deleteBudget(@NonNull Budget budget) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_BUDGET, COL_BUDGET_ID + " = ?", new String[]{budget.getUID().toString()});
    }

    // Get Budget by ID
    public Budget getBudgetById(String budgetUID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BUDGET + " WHERE " + COL_BUDGET_ID + " = ?", new String[]{budgetUID});
        Budget budget;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            double total = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_BUDGET_TOTAL_AMOUNT));
            double allocated = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_BUDGET_ALLOCATED_AMOUNT));
            budget = new Budget(budgetUID, total, allocated);
        } else {
            budget = new Budget(0);
        }
        cursor.close();
        return budget;
    }

}
