package com.kuliah.rumahumkm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DBNAME = "RumahUMKM.db";

    public DBHelper(Context context) {
        super(context, "RumahUMKM.db", null , 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table user(username TEXT primary key, password TEXT, email TEXT)");
        MyDB.execSQL("create Table kelas(id INTEGER primary key AUTOINCREMENT, nama_kelas TEXT, kategori_kelas TEXT)");
        MyDB.execSQL("create Table seminar(id INTEGER primary key AUTOINCREMENT, nama_seminar TEXT, kategori_seminar TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists user");
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where username = ? ", new String[] {username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where username = ? and password = ?", new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean insertData(String username, String password, String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        long result = MyDB.insert("user", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public void deleteAll() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("DELETE FROM kelas");
        MyDB.execSQL("DELETE FROM seminar");
    }

    public void addKelas() {
        String nama1 = "Tips Berjualan di E-Commerce";
        String kategori1 = "Pemasaran";
        String nama2 = "Sukses Bisnis Online Shop";
        String kategori2 = "Pemasaran";
        String nama3 = "Pahami Pajak UMKM";
        String kategori3 = "Pemasaran";

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        ContentValues contentValues2 = new ContentValues();
        ContentValues contentValues3 = new ContentValues();
        contentValues1.put("nama_kelas", nama1);
        contentValues1.put("kategori_kelas", kategori1);
        MyDB.insert("kelas", null, contentValues1);
        contentValues2.put("nama_kelas", nama2);
        contentValues2.put("kategori_kelas", kategori2);
        MyDB.insert("kelas", null, contentValues2);
        contentValues3.put("nama_kelas", nama3);
        contentValues3.put("kategori_kelas", kategori3);
        MyDB.insert("kelas", null, contentValues3);
    }

    public void addSeminar() {
        String nama1 = "Manfaat PEN Bagi UMKM";
        String kategori1 = "Pemasaran";
        String nama2 = "Inovasi UMKM Go DIgital";
        String kategori2 = "Branding";
        String nama3 = "Membangun Brand di Sosmed";
        String kategori3 = "Branding";

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        ContentValues contentValues2 = new ContentValues();
        ContentValues contentValues3 = new ContentValues();
        contentValues1.put("nama_seminar", nama1);
        contentValues1.put("kategori_seminar", kategori1);
        MyDB.insert("seminar", null, contentValues1);
        contentValues2.put("nama_seminar", nama2);
        contentValues2.put("kategori_seminar", kategori2);
        MyDB.insert("seminar", null, contentValues2);
        contentValues3.put("nama_seminar", nama3);
        contentValues3.put("kategori_seminar", kategori3);
        MyDB.insert("seminar", null, contentValues3);
    }

    Cursor readAllDataKelas() {
        String query = "SELECT * FROM kelas";
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = null;
        if (MyDB != null) {
            cursor = MyDB.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDataSeminar() {
        String query = "SELECT * FROM seminar";
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = null;
        if (MyDB != null) {
            cursor = MyDB.rawQuery(query, null);
        }
        return cursor;
    }
}
