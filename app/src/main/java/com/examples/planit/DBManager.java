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
import com.examples.planit.internals.Guest;
import com.examples.planit.internals.Vendor;

import java.util.ArrayList;

/**
 * Database manager for the Event Management application.
 * This class is responsible for handling the creation, upgrade, and management of the database, including
 * CRUD operations for Event, Budget, Guest, and Vendor entities.
 *
 * @author Ram Amoncar
 * @author Omkar Mhamal
 * @version 2.0
 */
public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "event_manager.db";
    private static final int DATABASE_VERSION = 2;

    // Table Names
    private static final String TABLE_EVENT = "Event";
    private static final String TABLE_BUDGET = "Budget";
    private static final String TABLE_GUEST = "Guest";
    private static final String TABLE_VENDOR = "Vendor";
    private static final String TABLE_EVENT_GUESTS = "EventGuests";
    private static final String TABLE_EVENT_VENDORS = "EventVendors";
    private static final String TABLE_PAYMENT = "Payment";
    private static final String TABLE_BUDGET_PAYMENTS = "BudgetPayments";

    // Event Table Columns
    private static final String COL_EVENT_ID = "eventUID";
    private static final String COL_EVENT_NAME = "eventName";
    private static final String COL_EVENT_DATE = "startDate";
    private static final String COL_EVENT_LOCATION = "location";
    private static final String COL_EVENT_BUDGET_UID = "budgetUID";

    // Budget Table Columns
    private static final String COL_BUDGET_ID = "budgetUID";
    private static final String COL_BUDGET_TOTAL_AMOUNT = "totalAmount";
    private static final String COL_BUDGET_ALLOCATED_AMOUNT = "allocatedAmount";

    // Guest Table Columns
    private static final String COL_GUEST_ID = "guestUID";
    private static final String COL_GUEST_NAME = "guest_name";
    private static final String COL_EMAIL = "guest_email";
    private static final String COL_PHONE = "guest_phone";
    private static final String COL_GUEST_STATUS = "guest_status";

    // Vendor Table Columns
    private static final String COL_VENDOR_ID = "vendorUID";
    private static final String COL_VENDOR_NAME = "vendor_name";
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
    public void onCreate(SQLiteDatabase db) {
        // Create tables

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_EVENT + " ("
                + COL_EVENT_ID + " TEXT PRIMARY KEY,"
                + COL_EVENT_NAME + " TEXT NOT NULL,"
                + COL_EVENT_DATE + " TEXT NOT NULL,"
                + COL_EVENT_LOCATION + " TEXT NOT NULL,"
                + COL_EVENT_BUDGET_UID + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_BUDGET + " ("
                + COL_BUDGET_ID + " TEXT PRIMARY KEY,"
                + COL_BUDGET_TOTAL_AMOUNT + " REAL NOT NULL,"
                + COL_BUDGET_ALLOCATED_AMOUNT + " REAL NOT NULL);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_GUEST + " ("
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

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_EVENT_GUESTS + " ("
                + COL_EVENT_GUEST_ID + " TEXT NOT NULL,"
                + COL_GUEST_UID + " TEXT NOT NULL,"
                + "PRIMARY KEY (" + COL_EVENT_GUEST_ID + ", " + COL_GUEST_UID + "));");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_EVENT_VENDORS + " ("
                + COL_EVENT_VENDOR_ID + " TEXT NOT NULL,"
                + COL_VENDOR_UID + " TEXT NOT NULL,"
                + "PRIMARY KEY (" + COL_EVENT_VENDOR_ID + ", " + COL_VENDOR_UID + "));");
        /*
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
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENDOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT_GUESTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT_VENDORS);

        /*
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET_PAYMENTS);
        */
        onCreate(db);
    }

    /**
     * Adds a new event to the database.
     *
     * @param event The event to add. Must not be null.
     * @return true if the event was added successfully, false otherwise.
     * @noinspection UnusedReturnValue
     */
    public boolean addEvent(@NonNull Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EVENT_ID, event.getUID().toString());
        contentValues.put(COL_EVENT_NAME, event.getName());
        contentValues.put(COL_EVENT_DATE, event.getFormattedStartDate());
        contentValues.put(COL_EVENT_LOCATION, event.getLocation());

        contentValues.put(COL_EVENT_BUDGET_UID, event.getBudget().getUID().toString());
        long result = db.insert(TABLE_EVENT, null, contentValues);

        if (result == -1) {
            Log.e("DB_INSERT", "Failed to insert event");
        } else {
            Log.d("DB_INSERT", "Event inserted with row ID: " + result);
        }

        return result != -1;
    }

    /**
     * Retrieves all events from the database.
     *
     * @return A list of all events. The list may be empty if no events are found.
     */
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

                String budgetUID = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_BUDGET_UID));
                Budget budget = getBudgetById(budgetUID);

                events.add(new Event(uid, name, startDate, location, budget));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return events;
    }

    public ArrayList<String> getAllEventNames() {
        ArrayList<String> eventNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EVENT, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_NAME));
                eventNames.add(name); // Get the event name
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return eventNames;
    }


    /**
     * Updates an existing event in the database.
     *
     * @param event The event to update. Must not be null.
     * @return true if the event was updated successfully, false otherwise.
     */
    public boolean updateEvent(@NonNull Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EVENT_NAME, event.getName());
        contentValues.put(COL_EVENT_DATE, event.getFormattedStartDate());
        contentValues.put(COL_EVENT_LOCATION, event.getLocation());
        int result = db.update(TABLE_EVENT, contentValues, COL_EVENT_ID + " = ?", new String[]{event.getUID().toString()});
        return result > 0;
    }

    /**
     * Deletes an event from the database, including its associated guests and vendors.
     *
     * @param event The event to delete. Must not be null.
     * @return The number of rows affected, which can be zero if no event was found to delete.
     */
    public Integer deleteEvent(@NonNull Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        String eventUID = event.getUID().toString();

        // Delete associated guests
        db.delete(TABLE_EVENT_GUESTS, COL_EVENT_GUEST_ID + " = ?", new String[]{eventUID});
        db.delete(TABLE_GUEST, COL_GUEST_ID + " IN (SELECT " + COL_GUEST_UID + " FROM " + TABLE_EVENT_GUESTS + " WHERE " + COL_EVENT_GUEST_ID + " = ?)", new String[]{eventUID});

        // Delete associated vendors
        db.delete(TABLE_EVENT_VENDORS, COL_EVENT_VENDOR_ID + " = ?", new String[]{eventUID});
        db.delete(TABLE_VENDOR, COL_VENDOR_ID + " IN (SELECT " + COL_VENDOR_UID + " FROM " + TABLE_EVENT_VENDORS + " WHERE " + COL_EVENT_VENDOR_ID + " = ?)", new String[]{eventUID});

        // Delete the event itself
        int result = db.delete(TABLE_EVENT, COL_EVENT_ID + " = ?", new String[]{eventUID});

        if (result > 0) {
            result = deleteBudget(event.getBudget());
        }
        return result;
    }


    /**
     * Retrieves an event from the database by its unique identifier.
     *
     * @param eventUID The unique identifier of the event. Must not be null.
     * @return The event corresponding to the given UID, or a new Event if no match is found.
     */
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

            String budgetUID = cursor.getString(cursor.getColumnIndexOrThrow(COL_EVENT_BUDGET_UID));
            Budget budget = getBudgetById(budgetUID);

            event = new Event(uid, name, startDate, location, budget);
        } else {
            event = new Event();
        }

        cursor.close();
        return event;
    }


    // Budget

    /**
     * Adds a new budget to the database.
     *
     * @param budget The budget to add. Must not be null.
     * @return true if the budget was added successfully, false otherwise.
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

    /**
     * Retrieves all budgets from the database.
     *
     * @return A list of all budgets. The list may be empty if no budgets are found.
     */
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

    /**
     * Updates an existing budget in the database.
     *
     * @param budget The budget to update. Must not be null.
     * @return true if the budget was updated successfully, false otherwise.
     */
    public boolean updateBudget(@NonNull Budget budget) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_BUDGET_TOTAL_AMOUNT, budget.getTotalBudget());
        contentValues.put(COL_BUDGET_ALLOCATED_AMOUNT, budget.getAllocated());
        int result = db.update(TABLE_BUDGET, contentValues, COL_BUDGET_ID + " = ?", new String[]{budget.getUID().toString()});
        return result > 0;
    }

    // TODO: Delete Budget and delete reference in BudgetPayments Table

    /**
     * Deletes a budget from the database.
     *
     * @param budget The budget to delete. Must not be null.
     * @return The number of rows affected, which can be zero if no budget was found to delete.
     */
    public Integer deleteBudget(@NonNull Budget budget) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_BUDGET, COL_BUDGET_ID + " = ?", new String[]{budget.getUID().toString()});
    }

    /**
     * Retrieves a budget from the database by its unique identifier.
     *
     * @param budgetUID The unique identifier of the budget. Must not be null.
     * @return The budget corresponding to the given UID, or a new Budget with zero values if no match is found.
     */
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

    // Guest

    /**
     * Adds a new guest to the database.
     *
     * @param guest The guest to add. Must not be null.
     * @return true if the guest was added successfully, false otherwise.
     */
    public boolean addGuest(@NonNull Guest guest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_GUEST_ID, guest.getUID().toString());
        contentValues.put(COL_GUEST_NAME, guest.getName());
        contentValues.put(COL_EMAIL, guest.getEmail());
        contentValues.put(COL_PHONE, guest.getPhone());
        contentValues.put(COL_GUEST_STATUS, guest.getStatus().toString());

        long result = db.insert(TABLE_GUEST, null, contentValues);
        return result != -1;
    }

    /**
     * Retrieves all guests from the database.
     *
     * @return A list of all guests. The list may be empty if no guests are found.
     */
    public ArrayList<Guest> getAllGuests() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Guest> guests = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GUEST, null);

        if (cursor.moveToFirst()) {
            do {
                String uid = cursor.getString(cursor.getColumnIndexOrThrow(COL_GUEST_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_GUEST_NAME));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(COL_GUEST_STATUS));

                guests.add(new Guest(uid, name, email, phone, status));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return guests;
    }

    /**
     * Updates an existing guest in the database.
     *
     * @param guest The guest to update. Must not be null.
     * @return true if the guest was updated successfully, false otherwise.
     */
    public boolean updateGuest(@NonNull Guest guest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_GUEST_NAME, guest.getName());
        contentValues.put(COL_EMAIL, guest.getEmail());
        contentValues.put(COL_PHONE, guest.getPhone());
        contentValues.put(COL_GUEST_STATUS, guest.getStatus().toString());

        int result = db.update(TABLE_GUEST, contentValues, COL_GUEST_ID + " = ?", new String[]{guest.getUID().toString()});
        return result > 0;
    }

    /**
     * Deletes a guest from the database.
     *
     * @param guest The guest to delete. Must not be null.
     * @return The number of rows affected, which can be zero if no guest was found to delete.
     */
    public Integer deleteGuest(@NonNull Guest guest) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_GUEST, COL_GUEST_ID + " = ?", new String[]{guest.getUID().toString()});
    }

    // Vendor

    /**
     * Adds a new vendor to the database.
     *
     * @param vendor The vendor to add. Must not be null.
     * @return true if the vendor was added successfully, false otherwise.
     */
    public boolean addVendor(@NonNull Vendor vendor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_VENDOR_ID, vendor.getUID().toString());
        contentValues.put(COL_VENDOR_NAME, vendor.getName());
        contentValues.put(COL_SERVICE_TYPE, vendor.getServiceType());
        contentValues.put(COL_CONTACT_INFO, vendor.getContactInfo());

        long result = db.insert(TABLE_VENDOR, null, contentValues);
        return result != -1;
    }

    /**
     * Retrieves all vendors from the database.
     *
     * @return A list of all vendors. The list may be empty if no vendors are found.
     */
    public ArrayList<Vendor> getAllVendors() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Vendor> vendors = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_VENDOR, null);

        if (cursor.moveToFirst()) {
            do {
                String uid = cursor.getString(cursor.getColumnIndexOrThrow(COL_VENDOR_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_VENDOR_NAME));
                String serviceType = cursor.getString(cursor.getColumnIndexOrThrow(COL_SERVICE_TYPE));
                String contactInfo = cursor.getString(cursor.getColumnIndexOrThrow(COL_CONTACT_INFO));

                vendors.add(new Vendor(uid, name, serviceType, contactInfo));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return vendors;
    }

    /**
     * Updates an existing vendor in the database.
     *
     * @param vendor The vendor to update. Must not be null.
     * @return true if the vendor was updated successfully, false otherwise.
     */
    public boolean updateVendor(@NonNull Vendor vendor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_VENDOR_NAME, vendor.getName());
        contentValues.put(COL_SERVICE_TYPE, vendor.getServiceType());
        contentValues.put(COL_CONTACT_INFO, vendor.getContactInfo());

        int result = db.update(TABLE_VENDOR, contentValues, COL_VENDOR_ID + " = ?", new String[]{vendor.getUID().toString()});
        return result > 0;
    }


    /**
     * Deletes a vendor from the database.
     *
     * @param vendor The vendor to delete. Must not be null.
     * @return The number of rows affected, which can be zero if no vendor was found to delete.
     */
    public Integer deleteVendor(@NonNull Vendor vendor) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_VENDOR, COL_VENDOR_ID + " = ?", new String[]{vendor.getUID().toString()});
    }

    // Event's Guests

    /**
     * Adds a guest to a specific event in the database.
     *
     * @param eventUID The unique identifier of the event. Must not be null.
     * @param guestUID The unique identifier of the guest. Must not be null.
     * @return true if the guest was added successfully to the event, false otherwise.
     */
    public boolean addEventGuest(String eventUID, String guestUID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EVENT_GUEST_ID, eventUID);
        contentValues.put(COL_GUEST_UID, guestUID);

        long result = db.insert(TABLE_EVENT_GUESTS, null, contentValues);
        return result != -1;
    }

    /**
     * Retrieves a list of guests associated with a specific event.
     *
     * @param eventUID The unique identifier of the event. Must not be null.
     * @return A list of guests associated with the event. The list may be empty if no guests are found.
     */
    public ArrayList<Guest> getGuestsForEvent(String eventUID) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Guest> guests = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GUEST +
                " INNER JOIN " + TABLE_EVENT_GUESTS +
                " ON " + TABLE_GUEST + "." + COL_GUEST_ID + " = " + TABLE_EVENT_GUESTS + "." + COL_GUEST_UID +
                " WHERE " + TABLE_EVENT_GUESTS + "." + COL_EVENT_GUEST_ID + " = ?", new String[]{eventUID});

        if (cursor.moveToFirst()) {
            do {
                String uid = cursor.getString(cursor.getColumnIndexOrThrow(COL_GUEST_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_GUEST_NAME));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(COL_GUEST_STATUS));

                guests.add(new Guest(uid, name, email, phone, status));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return guests;
    }

    /**
     * Deletes a guest from a specific event.
     *
     * @param eventUID The unique identifier of the event. Must not be null.
     * @param guestUID The unique identifier of the guest. Must not be null.
     * @return The number of rows affected, which can be zero if no guest was found for the event to delete.
     */
    public Integer deleteEventGuest(String eventUID, String guestUID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_EVENT_GUESTS, COL_EVENT_GUEST_ID + " = ? AND " + COL_GUEST_UID + " = ?", new String[]{eventUID, guestUID});
    }


    // Event's Vendors

    /**
     * Adds a vendor to a specific event in the database.
     *
     * @param eventUID  The unique identifier of the event. Must not be null.
     * @param vendorUID The unique identifier of the vendor. Must not be null.
     * @return true if the vendor was added successfully to the event, false otherwise.
     */
    public boolean addEventVendor(String eventUID, String vendorUID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EVENT_VENDOR_ID, eventUID);
        contentValues.put(COL_VENDOR_UID, vendorUID);

        long result = db.insert(TABLE_EVENT_VENDORS, null, contentValues);
        return result != -1;
    }

    /**
     * Retrieves a list of vendors associated with a specific event.
     *
     * @param eventUID The unique identifier of the event. Must not be null.
     * @return A list of vendors associated with the event. The list may be empty if no vendors are found.
     */
    public ArrayList<Vendor> getVendorsForEvent(String eventUID) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Vendor> vendors = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_VENDOR +
                " INNER JOIN " + TABLE_EVENT_VENDORS +
                " ON " + TABLE_VENDOR + "." + COL_VENDOR_ID + " = " + TABLE_EVENT_VENDORS + "." + COL_VENDOR_UID +
                " WHERE " + TABLE_EVENT_VENDORS + "." + COL_EVENT_VENDOR_ID + " = ?", new String[]{eventUID});

        if (cursor.moveToFirst()) {
            do {
                String uid = cursor.getString(cursor.getColumnIndexOrThrow(COL_VENDOR_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_VENDOR_NAME));
                String serviceType = cursor.getString(cursor.getColumnIndexOrThrow(COL_SERVICE_TYPE));
                String contactInfo = cursor.getString(cursor.getColumnIndexOrThrow(COL_CONTACT_INFO));

                vendors.add(new Vendor(uid, name, serviceType, contactInfo));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return vendors;
    }

    /**
     * Deletes a vendor from a specific event.
     *
     * @param eventUID  The unique identifier of the event. Must not be null.
     * @param vendorUID The unique identifier of the vendor. Must not be null.
     * @return The number of rows affected, which can be zero if no vendor was found for the event to delete.
     */
    public Integer deleteEventVendor(String eventUID, String vendorUID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_EVENT_VENDORS, COL_EVENT_VENDOR_ID + " = ? AND " + COL_VENDOR_UID + " = ?", new String[]{eventUID, vendorUID});
    }

    public String getEarliestEventName() {
        String earliestEventName = "Event Name";

        return earliestEventName;
    }
}
