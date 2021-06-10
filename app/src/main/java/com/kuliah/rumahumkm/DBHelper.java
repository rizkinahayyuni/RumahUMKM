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
        MyDB.execSQL("create Table user(username TEXT primary key, password TEXT, email TEXT, no_hp TEXT, alamat TEXT, jenis_umkm TEXT)");
        MyDB.execSQL("create Table kelas(id INTEGER primary key AUTOINCREMENT, nama_kelas TEXT, kategori_kelas TEXT)");
        MyDB.execSQL("create Table detail_kelas(id INTEGER primary key AUTOINCREMENT, judul TEXT, isi_materi TEXT,id_kelas INTEGER," +
                "FOREIGN KEY(id_kelas) REFERENCES kelas(id))");
        MyDB.execSQL("create Table seminar(id INTEGER primary key AUTOINCREMENT, nama_seminar TEXT, kategori_seminar TEXT, " +
                "pembicara TEXT, pelaksana TEXT, tanggal TEXT, lokasi TEXT)");
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
        MyDB.execSQL("DELETE FROM detail_kelas");
    }

    public void addKelas() {
        String nama1 = "Tips Berjualan di E-Commerce";
        String kategori1 = "Pemasaran";
        String nama2 = "Sukses Bisnis Online Shop";
        String kategori2 = "Informasi";
        String nama3 = "Pahami Pajak UMKM";
        String kategori3 = "Perpajakan";
        String nama4 = "Desain Pakaian Formal";
        String kategori4 = "Fashion";
        String nama5 = "Memanfaatkan Sosial Media";
        String kategori5 = "Mikro";

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        ContentValues contentValues2 = new ContentValues();
        ContentValues contentValues3 = new ContentValues();
        ContentValues contentValues4 = new ContentValues();
        ContentValues contentValues5 = new ContentValues();
        contentValues1.put("id", "1");
        contentValues1.put("nama_kelas", nama1);
        contentValues1.put("kategori_kelas", kategori1);
        MyDB.insert("kelas", null, contentValues1);
        contentValues2.put("id", "2");
        contentValues2.put("nama_kelas", nama2);
        contentValues2.put("kategori_kelas", kategori2);
        MyDB.insert("kelas", null, contentValues2);
        contentValues3.put("id", "3");
        contentValues3.put("nama_kelas", nama3);
        contentValues3.put("kategori_kelas", kategori3);
        MyDB.insert("kelas", null, contentValues3);
        contentValues4.put("id", "4");
        contentValues4.put("nama_kelas", nama4);
        contentValues4.put("kategori_kelas", kategori4);
        MyDB.insert("kelas", null, contentValues4);
        contentValues5.put("id", "5");
        contentValues5.put("nama_kelas", nama5);
        contentValues5.put("kategori_kelas", kategori5);
        MyDB.insert("kelas", null, contentValues5);
    }

    public void addDetailKelas() {
        String judul1 = "Peluang Berjualan di E-Commerce";
        String isi_materi1 = "Lorem1 ipsum dolor sit amet, consectetur adipiscing elit. Donec vel ex ex. Nullam in molestie augue. " +
                "Curabitur eros purus, egestas et erat in, facilisis tincidunt dui. Nullam euismod, neque et efficitur viverra, " +
                "sem leo rutrum eros, nec semper felis nulla ac neque. Donec commodo sem eget mi maximus dignissim. Sed quam est, " +
                "ultrices imperdiet porttitor a, efficitur ultrices justo. Etiam nec lacinia augue. Morbi ornare, est luctus lobortis " +
                "venenatis, erat elit laoreet metus, id vestibulum nisi magna non ex. Donec semper ex a feugiat luctus. Nullam luctus " +
                "eros at lorem vestibulum, nec luctus est ornare. Suspendisse potenti. Sed egestas urna nec nunc tincidunt, eu " +
                "pulvinar arcu mattis. In viverra sem at nunc tristique elementum eget eu justo. Sed elit nulla, fermentum eu " +
                "felis a, euismod egestas elit. Sed in metus justo. Fusce semper libero arcu, pellentesque imperdiet leo finibus " +
                "vel. Sed mollis posuere enim sed malesuada. Donec sapien sapien, interdum at odio vel, accumsan consectetur urna. " +
                "Morbi libero mi, feugiat at est nec, mattis pretium ante. Mauris elementum facilisis congue. Lorem ipsum dolor " +
                "sit amet, consectetur adipiscing elit. Quisque imperdiet eleifend laoreet. Praesent consectetur malesuada tempor. " +
                "Vestibulum malesuada ipsum at tincidunt gravida. Nulla accumsan nisl a mi euismod, vitae elementum orci porta. " +
                "Fusce laoreet dictum lectus eu lobortis. Sed eu molestie mauris. Morbi a lobortis nisi, vel accumsan dolor. " +
                "Vivamus nec metus neque. Nam euismod eget dolor quis malesuada. Cras nec porttitor urna. Nam consectetur nisl " +
                "et eleifend sodales. Curabitur accumsan neque nunc, ac efficitur tellus maximus sit amet. Nulla et nisl volutpat, " +
                "varius eros sit amet, luctus eros. Donec a lacus quis tellus auctor varius a id neque. Pellentesque euismod arcu " +
                "fringilla, eu bibendum enim accumsan. Nullam malesuada eu est rutrum tempor. Mauris aliquam laoreet nisl mattis lacinia.";
        String judul2 = "Tantangan Berjualan di E-Commerce";
        String isi_materi2 = "Lorem2 ipsum dolor sit amet, consectetur adipiscing elit. Donec vel ex ex. Nullam in molestie augue. " +
                "Curabitur eros purus, egestas et erat in, facilisis tincidunt dui. Nullam euismod, neque et efficitur viverra, " +
                "sem leo rutrum eros, nec semper felis nulla ac neque. Donec commodo sem eget mi maximus dignissim. Sed quam est, " +
                "ultrices imperdiet porttitor a, efficitur ultrices justo. Etiam nec lacinia augue. Morbi ornare, est luctus lobortis " +
                "venenatis, erat elit laoreet metus, id vestibulum nisi magna non ex. Donec semper ex a feugiat luctus. Nullam luctus " +
                "eros at lorem vestibulum, nec luctus est ornare. Suspendisse potenti. Sed egestas urna nec nunc tincidunt, eu " +
                "pulvinar arcu mattis. In viverra sem at nunc tristique elementum eget eu justo. Sed elit nulla, fermentum eu " +
                "felis a, euismod egestas elit. Sed in metus justo. Fusce semper libero arcu, pellentesque imperdiet leo finibus " +
                "vel. Sed mollis posuere enim sed malesuada. Donec sapien sapien, interdum at odio vel, accumsan consectetur urna. " +
                "Morbi libero mi, feugiat at est nec, mattis pretium ante. Mauris elementum facilisis congue. Lorem ipsum dolor " +
                "sit amet, consectetur adipiscing elit. Quisque imperdiet eleifend laoreet. Praesent consectetur malesuada tempor. " +
                "Vestibulum malesuada ipsum at tincidunt gravida. Nulla accumsan nisl a mi euismod, vitae elementum orci porta. " +
                "Fusce laoreet dictum lectus eu lobortis. Sed eu molestie mauris. Morbi a lobortis nisi, vel accumsan dolor. " +
                "fringilla, eu bibendum enim accumsan. Nullam malesuada eu est rutrum tempor. Mauris aliquam laoreet nisl mattis lacinia.";
        String judul3 = "Lima Kunci Berjualan di E-Commerce";
        String isi_materi3 = "Lorem3 ipsum dolor sit amet, consectetur adipiscing elit. Donec vel ex ex. Nullam in molestie augue. " +
                "felis a, euismod egestas elit. Sed in metus justo. Fusce semper libero arcu, pellentesque imperdiet leo finibus " +
                "vel. Sed mollis posuere enim sed malesuada. Donec sapien sapien, interdum at odio vel, accumsan consectetur urna. " +
                "Morbi libero mi, feugiat at est nec, mattis pretium ante. Mauris elementum facilisis congue. Lorem ipsum dolor " +
                "sit amet, consectetur adipiscing elit. Quisque imperdiet eleifend laoreet. Praesent consectetur malesuada tempor. " +
                "Vestibulum malesuada ipsum at tincidunt gravida. Nulla accumsan nisl a mi euismod, vitae elementum orci porta. " +
                "Fusce laoreet dictum lectus eu lobortis. Sed eu molestie mauris. Morbi a lobortis nisi, vel accumsan dolor. " +
                "Vivamus nec metus neque. Nam euismod eget dolor quis malesuada. Cras nec porttitor urna. Nam consectetur nisl " +
                "et eleifend sodales. Curabitur accumsan neque nunc, ac efficitur tellus maximus sit amet. Nulla et nisl volutpat, " +
                "varius eros sit amet, luctus eros. Donec a lacus quis tellus auctor varius a id neque. Pellentesque euismod arcu " +
                "ac ex malesuada, at luctus nunc semper. Duis dapibus orci non orci gravida, in venenatis tellus tristique. " +
                "Pellentesque tempor quam nec lacus pretium, eget laoreet augue pellentesque. Quisque venenatis eu ex vitae " +
                "condimentum. Proin ut ipsum at tortor hendrerit feugiat. Sed in aliquam purus, quis porta quam. Suspendisse a " +
                "sodales turpis. Proin eu lacus et orci venenatis facilisis. Curabitur eu dolor a enim eleifend ultricies. Vivamus " +
                "ut imperdiet erat. Vivamus a feugiat orci. Mauris et velit aliquet, posuere sem at, vehicula diam. Phasellus ut quam " +
                "lectus. Cras eget ipsum ut velit vulputate bibendum eu et ex. Ut ut quam ut tellus aliquam scelerisque. Morbi " +
                "scelerisque vulputate massa, a aliquet ipsum ultricies vel. Donec sed risus ut orci iaculis dictum. Pellentesque " +
                "in vulputate magna. Nullam sed lorem volutpat, porttitor nisl quis, posuere tellus. Mauris viverra lorem non leo " +
                "fringilla, eu bibendum enim accumsan. Nullam malesuada eu est rutrum tempor. Mauris aliquam laoreet nisl mattis lacinia.";
        String judul4 = "Studi Kasus 1";
        String isi_materi4 = "Lorem4 ipsum dolor sit amet, consectetur adipiscing elit. Donec vel ex ex. Nullam in molestie augue. " +
                "Curabitur eros purus, egestas et erat in, facilisis tincidunt dui. Nullam euismod, neque et efficitur viverra, " +
                "sem leo rutrum eros, nec semper felis nulla ac neque. Donec commodo sem eget mi maximus dignissim. Sed quam est, " +
                "ultrices imperdiet porttitor a, efficitur ultrices justo. Etiam nec lacinia augue. Morbi ornare, est luctus lobortis " +
                "venenatis, erat elit laoreet metus, id vestibulum nisi magna non ex. Donec semper ex a feugiat luctus. Nullam luctus " +
                "eros at lorem vestibulum, nec luctus est ornare. Suspendisse potenti. Sed egestas urna nec nunc tincidunt, eu " +
                "pulvinar arcu mattis. In viverra sem at nunc tristique elementum eget eu justo. Sed elit nulla, fermentum eu " +
                "felis a, euismod egestas elit. Sed in metus justo. Fusce semper libero arcu, pellentesque imperdiet leo finibus " +
                "vel. Sed mollis posuere enim sed malesuada. Donec sapien sapien, interdum at odio vel, accumsan consectetur urna. " +
                "Morbi libero mi, feugiat at est nec, mattis pretium ante. Mauris elementum facilisis congue. Lorem ipsum dolor " +
                "et eleifend sodales. Curabitur accumsan neque nunc, ac efficitur tellus maximus sit amet. Nulla et nisl volutpat, " +
                "varius eros sit amet, luctus eros. Donec a lacus quis tellus auctor varius a id neque. Pellentesque euismod arcu " +
                "fringilla, eu bibendum enim accumsan. Nullam malesuada eu est rutrum tempor. Mauris aliquam laoreet nisl mattis lacinia.";
        String judul5 = "Studi Kasus 2";
        String isi_materi5 = "Lorem5 ipsum dolor sit amet, consectetur adipiscing elit. Donec vel ex ex. Nullam in molestie augue. " +
                "Curabitur eros purus, egestas et erat in, facilisis tincidunt dui. Nullam euismod, neque et efficitur viverra, " +
                "sem leo rutrum eros, nec semper felis nulla ac neque. Donec commodo sem eget mi maximus dignissim. Sed quam est, " +
                "ultrices imperdiet porttitor a, efficitur ultrices justo. Etiam nec lacinia augue. Morbi ornare, est luctus lobortis " +
                "venenatis, erat elit laoreet metus, id vestibulum nisi magna non ex. Donec semper ex a feugiat luctus. " +
                "Morbi libero mi, feugiat at est nec, mattis pretium ante. Mauris elementum facilisis congue. Lorem ipsum dolor " +
                "sit amet, consectetur adipiscing elit. Quisque imperdiet eleifend laoreet. Praesent consectetur malesuada tempor. " +
                "Vestibulum malesuada ipsum at tincidunt gravida. Nulla accumsan nisl a mi euismod, vitae elementum orci porta. " +
                "Fusce laoreet dictum lectus eu lobortis. Sed eu molestie mauris. Morbi a lobortis nisi, vel accumsan dolor. " +
                "Vivamus nec metus neque. Nam euismod eget dolor quis malesuada. Cras nec porttitor urna. Nam consectetur nisl " +
                "et eleifend sodales. Curabitur accumsan neque nunc, ac efficitur tellus maximus sit amet. Nulla et nisl volutpat, " +
                "varius eros sit amet, luctus eros. Donec a lacus quis tellus auctor varius a id neque. Pellentesque euismod arcu " +
                "ac ex malesuada, at luctus nunc semper. Duis dapibus orci non orci gravida, in venenatis tellus tristique. " +
                "Pellentesque tempor quam nec lacus pretium, eget laoreet augue pellentesque. Quisque venenatis eu ex vitae " +
                "sodales turpis. Proin eu lacus et orci venenatis facilisis. Curabitur eu dolor a enim eleifend ultricies. Vivamus " +
                "scelerisque vulputate massa, a aliquet ipsum ultricies vel. Donec sed risus ut orci iaculis dictum. Pellentesque " +
                "in vulputate magna. Nullam sed lorem volutpat, porttitor nisl quis, posuere tellus. Mauris viverra lorem non leo " +
                "fringilla, eu bibendum enim accumsan. Nullam malesuada eu est rutrum tempor. Mauris aliquam laoreet nisl mattis lacinia.";
        String id_kelas1 = "1";

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        ContentValues contentValues2 = new ContentValues();
        ContentValues contentValues3 = new ContentValues();
        ContentValues contentValues4 = new ContentValues();
        ContentValues contentValues5 = new ContentValues();

        contentValues1.put("judul", judul1);
        contentValues1.put("isi_materi", isi_materi1);
        contentValues1.put("id_kelas", id_kelas1);
        contentValues2.put("judul", judul2);
        contentValues2.put("isi_materi", isi_materi2);
        contentValues2.put("id_kelas", id_kelas1);
        contentValues3.put("judul", judul3);
        contentValues3.put("isi_materi", isi_materi3);
        contentValues3.put("id_kelas", id_kelas1);
        contentValues4.put("judul", judul4);
        contentValues4.put("isi_materi", isi_materi4);
        contentValues4.put("id_kelas", id_kelas1);
        contentValues5.put("judul", judul5);
        contentValues5.put("isi_materi", isi_materi5);
        contentValues5.put("id_kelas", id_kelas1);
        MyDB.insert("detail_kelas", null, contentValues1);
        MyDB.insert("detail_kelas", null, contentValues2);
        MyDB.insert("detail_kelas", null, contentValues3);
        MyDB.insert("detail_kelas", null, contentValues4);
        MyDB.insert("detail_kelas", null, contentValues5);
    }

    public void addSeminar() {
        String nama1 = "Manfaat PEN Bagi UMKM";
        String kategori1 = "Pemasaran";
        String pembicara1 = "Budi";
        String pelaksana1 = "Kominfo";
        String tanggal1 = "08-06-2021";
        String lokasi1 = "bit.ly/seminarPen";
        String nama2 = "Inovasi UMKM Go DIgital";
        String kategori2 = "Inovasi";
        String pembicara2 = "Cahyono";
        String pelaksana2 = "StartUp";
        String tanggal2 = "10-06-2021";
        String lokasi2 = "bit.ly/inovasiUMKM";
        String nama3 = "Membangun Brand di Sosmed";
        String kategori3 = "Branding";
        String pembicara3 = "Syukri";
        String pelaksana3 = "Kominfo";
        String tanggal3 = "18-06-2021";
        String lokasi3 = "bit.ly/BrandSosmed";
        String nama4 = "Membangun UMKM Kreatif";
        String kategori4 = "Pengembangan diri";
        String pembicara4 = "Prambudi";
        String pelaksana4 = "Pemkab Malang";
        String tanggal4 = "08-06-2021";
        String lokasi4 = "bit.ly/seminarUMKMKreatif";
        String nama5 = "Standar Keamanan Pangan ";
        String kategori5 = "Pertanian";
        String pembicara5 = "Antonio";
        String pelaksana5 = "Poltek Jember";
        String tanggal5 = "08-06-2021";
        String lokasi5 = "bit.ly/seminarSKP";

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        ContentValues contentValues2 = new ContentValues();
        ContentValues contentValues3 = new ContentValues();
        ContentValues contentValues4 = new ContentValues();
        ContentValues contentValues5 = new ContentValues();
        contentValues1.put("nama_seminar", nama1);
        contentValues1.put("kategori_seminar", kategori1);
        contentValues1.put("pembicara", pembicara1);
        contentValues1.put("pelaksana", pelaksana1);
        contentValues1.put("tanggal", tanggal1);
        contentValues1.put("lokasi", lokasi1);
        MyDB.insert("seminar", null, contentValues1);
        contentValues2.put("nama_seminar", nama2);
        contentValues2.put("kategori_seminar", kategori2);
        contentValues2.put("pembicara", pembicara2);
        contentValues2.put("pelaksana", pelaksana2);
        contentValues2.put("tanggal", tanggal2);
        contentValues2.put("lokasi", lokasi2);
        MyDB.insert("seminar", null, contentValues2);
        contentValues3.put("nama_seminar", nama3);
        contentValues3.put("kategori_seminar", kategori3);
        contentValues3.put("pembicara", pembicara3);
        contentValues3.put("pelaksana", pelaksana3);
        contentValues3.put("tanggal", tanggal3);
        contentValues3.put("lokasi", lokasi3);
        MyDB.insert("seminar", null, contentValues3);
        contentValues4.put("nama_seminar", nama4);
        contentValues4.put("kategori_seminar", kategori4);
        contentValues4.put("pembicara", pembicara4);
        contentValues4.put("pelaksana", pelaksana4);
        contentValues4.put("tanggal", tanggal4);
        contentValues4.put("lokasi", lokasi4);
        MyDB.insert("seminar", null, contentValues4);
        contentValues5.put("nama_seminar", nama5);
        contentValues5.put("kategori_seminar", kategori5);
        contentValues5.put("pembicara", pembicara5);
        contentValues5.put("pelaksana", pelaksana5);
        contentValues5.put("tanggal", tanggal5);
        contentValues5.put("lokasi", lokasi5);
        MyDB.insert("seminar", null, contentValues5);
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

    Cursor readDataKelasPopuler() {
        String query = "SELECT * FROM kelas LIMIT 3";
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

    Cursor readDataSeminarPopuler() {
        String query = "SELECT * FROM seminar  LIMIT 3";
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = null;
        if (MyDB != null) {
            cursor = MyDB.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDataKategoriKelas() {
        String query = "SELECT kategori_kelas FROM kelas";
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = null;
        if (MyDB != null) {
            cursor = MyDB.rawQuery(query, null);
        }
        return cursor;
    }

}
