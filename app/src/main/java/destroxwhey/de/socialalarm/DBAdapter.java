// ------------------------------------ DBADapter.java ---------------------------------------------

// TODO: Change the package to match your project.
package destroxwhey.de.socialalarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


// TO USE:
// Change the package (at top) to match your project.
// Search for "TODO", and make the appropriate changes.
public class DBAdapter {

	/////////////////////////////////////////////////////////////////////
	//	Constants & Data
	/////////////////////////////////////////////////////////////////////
	// For logging:
	private static final String TAG = "DBAdapter";
	
	// DB Fields
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	/*
	 * CHANGE 1:
	 */
	// TODO: Setup your fields here:
	public static final String KEY_NAME = "name";
	public static final String KEY_HOUROFDAY = "hourofday";
    public static final String KEY_MINUTE = "minute";
    public static final String KEY_REQUESTCODE = "requestcode";
	public static final String KEY_MONDAY = "monday";
    public static final String KEY_TUESDAY = "tuesday";
    public static final String KEY_WEDNESDAY = "wednesday";
    public static final String KEY_THURSDAY = "thursday";
    public static final String KEY_FRIDAY = "friday";
    public static final String KEY_SATURDAY = "saturday";
    public static final String KEY_SUNDAY = "sunday";
	
	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
    public static final int COL_NAME = 1;
    public static final int COL_HOUROFDAY = 2;
    public static final int COL_MINUTE = 3;
    public static final int COL_REQUESTCODE = 4;
    public static final int COL_MONDAY = 5;
    public static final int COL_TUESDAY = 6;
    public static final int COL_WEDNESDAY = 7;
    public static final int COL_THURSDAY = 8;
    public static final int COL_FRIDAY = 9;
    public static final int COL_SATURDAY = 10;
    public static final int COL_SUNDAY = 11;

	
	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_NAME, KEY_HOUROFDAY, KEY_MINUTE, KEY_REQUESTCODE, KEY_MONDAY, KEY_TUESDAY, KEY_WEDNESDAY, KEY_THURSDAY, KEY_FRIDAY, KEY_SATURDAY, KEY_SUNDAY};
	
	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "MyDb";
	public static final String DATABASE_TABLE = "alarmTable";
	// Track DB version if a new version of your app changes the format.
	public static final int DATABASE_VERSION = 6;
	
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			
			/*
			 * CHANGE 2:
			 */
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			//	- Key is the column name you created above.
			//	- {type} is one of: text, integer, real, blob
			//		(http://www.sqlite.org/datatype3.html)
			//  - "not null" means it is a required field (must be given a value).
			// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
			+ KEY_NAME + " text not null, "
			+ KEY_HOUROFDAY + " integer not null, "
            + KEY_MINUTE + " integer not null, "
            + KEY_REQUESTCODE + " integer not null, "
            + KEY_MONDAY + " integer not null, "
            + KEY_TUESDAY + " integer not null, "
            + KEY_WEDNESDAY + " integer not null, "
            + KEY_THURSDAY + " integer not null, "
            + KEY_FRIDAY + " integer not null, "
            + KEY_SATURDAY + " integer not null, "
            + KEY_SUNDAY + " integer not null "
			
			// Rest  of creation:
			+ ");";
	
	// Context of application who uses us.
	private final Context context;
	
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;

	/////////////////////////////////////////////////////////////////////
	//	Public methods:
	/////////////////////////////////////////////////////////////////////
	
	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}
	
	// Open the database connection.
	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}
	
	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}
	
	// Add a new set of values to the database.
	public long insertRow(String name, int hourofday, int minute, int requestcode, int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_HOUROFDAY, hourofday);
        initialValues.put(KEY_MINUTE, minute);
        initialValues.put(KEY_REQUESTCODE, requestcode);
		initialValues.put(KEY_MONDAY, monday);
        initialValues.put(KEY_TUESDAY, tuesday);
        initialValues.put(KEY_WEDNESDAY, wednesday);
        initialValues.put(KEY_THURSDAY, thursday);
        initialValues.put(KEY_FRIDAY, friday);
        initialValues.put(KEY_SATURDAY, saturday);
        initialValues.put(KEY_SUNDAY, sunday);
		
		// Insert it into the database.
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	// Return all data in the database.
	public Cursor getAllRows() {
        String where = null;
        Cursor c = db.query(true, DATABASE_TABLE, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	// Change an existing row to be equal to new data.
	public boolean updateRow(long rowId, String name, int hourofday, int minute, int requestcode, int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday) {
		String where = KEY_ROWID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
        newValues.put(KEY_NAME, name);
        newValues.put(KEY_HOUROFDAY, hourofday);
        newValues.put(KEY_MINUTE, minute);
        newValues.put(KEY_REQUESTCODE, requestcode);
        newValues.put(KEY_MONDAY, monday);
        newValues.put(KEY_TUESDAY, tuesday);
        newValues.put(KEY_WEDNESDAY, wednesday);
        newValues.put(KEY_THURSDAY, thursday);
        newValues.put(KEY_FRIDAY, friday);
        newValues.put(KEY_SATURDAY, saturday);
        newValues.put(KEY_SUNDAY, sunday);
		
		// Insert it into the database.
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}
	
	
	
	/////////////////////////////////////////////////////////////////////
	//	Private Helper Classes:
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * Private class which handles database creation and upgrading.
	 * Used to handle low-level database access.
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DATABASE_CREATE_SQL);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			
			// Recreate new database:
			onCreate(_db);
		}
	}
}
