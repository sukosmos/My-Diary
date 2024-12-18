package com.example.myapplicationn;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 메모 데이터베이스
 */
public class NoteDatabase {
	public static NoteDatabase mDatabase = null;  // 전역 인스턴스

	private static final String TAG = "NoteDatabase";

	/**
	 * 싱글톤 인스턴스
	 */
	private static NoteDatabase database;

	/**
	 * table name for MEMO
	 */
	public static String TABLE_NOTE = "NOTE";

	/**
	 * version
	 */
	public static int DATABASE_VERSION = 1;

	/**
	 * Helper class defined
	 */
	private DatabaseHelper dbHelper;

	/**
	 * SQLiteDatabase 인스턴스
	 */
	private SQLiteDatabase db;

	/**
	 * 컨텍스트 객체
	 */
	private Context context;

	/**
	 * 생성자
	 */
	private NoteDatabase(Context context) {
		this.context = context;
	}

	/**
	 * 인스턴스 가져오기
	 */
	public static NoteDatabase getInstance(Context context) {
		if (database == null) {
			database = new NoteDatabase(context);
		}

		return database;
	}

	/**
	 * 데이터베이스 열기
	 */
	public boolean open() {
		try {
			Log.d(TAG, "Opening database [" + AppConstants.DATABASE_NAME + "].");

			dbHelper = new DatabaseHelper(context);
			db = dbHelper.getWritableDatabase();

			return true;
		} catch (Exception e) {
			Log.e(TAG, "Error while opening database", e);
			return false;
		}
	}

	/**
	 * 데이터베이스 닫기
	 */
	public void close() {
		if (db != null && db.isOpen()) {
			db.close();
			db = null;
			Log.d(TAG, "Database closed.");
		}

		database = null;
	}

	/**
	 * execute raw query using the input SQL
	 * close the cursor after fetching any result
	 *
	 * @param SQL
	 * @return
	 */
	public Cursor rawQuery(String SQL) {
		if (db == null) {
			Log.e(TAG, "Database is not open. Call open() before executing queries.");
			return null;
		}

		Log.d(TAG, "Executing raw query: " + SQL);
		Cursor cursor = null;

		try {
			cursor = db.rawQuery(SQL, null);
			Log.d(TAG, "Query executed. Cursor count: " + (cursor != null ? cursor.getCount() : 0));
		} catch (Exception e) {
			Log.e(TAG, "Exception in executeQuery", e);
		}

		return cursor;
	}

	public boolean execSQL(String SQL) {
		if (db == null) {
			Log.e(TAG, "Database is not open. Call open() before executing queries.");
			return false;
		}

		try {
			Log.d(TAG, "Executing SQL: " + SQL);
			db.execSQL(SQL);
			return true;
		} catch (Exception e) {
			Log.e(TAG, "Exception in executeQuery", e);
			return false;
		}
	}

	/**
	 * Database Helper inner class
	 */
	private class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, AppConstants.DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.d(TAG, "Creating database [" + AppConstants.DATABASE_NAME + "].");

			// Create TABLE_NOTE
			Log.d(TAG, "Creating table [" + TABLE_NOTE + "].");

			// Drop existing table if exists
			String DROP_SQL = "DROP TABLE IF EXISTS " + TABLE_NOTE;
			try {
				db.execSQL(DROP_SQL);
			} catch (Exception e) {
				Log.e(TAG, "Exception in DROP_SQL", e);
			}

			// Create table
			String CREATE_SQL = "CREATE TABLE " + TABLE_NOTE + " (" +
					"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"WEATHER TEXT DEFAULT '', " +
					"ADDRESS TEXT DEFAULT '', " +
					"LOCATION_X TEXT DEFAULT '', " +
					"LOCATION_Y TEXT DEFAULT '', " +
					"CONTENTS TEXT DEFAULT '', " +
					"MOOD TEXT, " +
					"PICTURE TEXT DEFAULT '', " +
					"CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
					"MODIFY_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
					")";
			try {
				db.execSQL(CREATE_SQL);
			} catch (Exception e) {
				Log.e(TAG, "Exception in CREATE_SQL", e);
			}

			// Create index
			String CREATE_INDEX_SQL = "CREATE INDEX IF NOT EXISTS " + TABLE_NOTE + "_IDX ON " + TABLE_NOTE + "(CREATE_DATE)";
			try {
				db.execSQL(CREATE_INDEX_SQL);
			} catch (Exception e) {
				Log.e(TAG, "Exception in CREATE_INDEX_SQL", e);
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.d(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ".");
		}

		@Override
		public void onOpen(SQLiteDatabase db) {
			Log.d(TAG, "Opened database [" + AppConstants.DATABASE_NAME + "].");
		}
	}
}
