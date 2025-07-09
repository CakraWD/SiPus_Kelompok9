package com.example.sipus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelperSipus extends SQLiteOpenHelper {

    public DataBaseHelperSipus(Context context) {
        super(context, "DatabaseUser.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabel akun pengguna
        db.execSQL("CREATE TABLE allusers (" +
                "email TEXT PRIMARY KEY, " +
                "password TEXT, " +
                "nama TEXT, " +
                "telepon TEXT, " +
                "alamat TEXT, " +
                "jenis_kelamin TEXT)");

        // Tabel pinjaman buku
        db.execSQL("CREATE TABLE pinjaman (" +
                "email TEXT PRIMARY KEY, " + // satu email = satu pinjaman
                "id_anggota TEXT, " +
                "tanggal_pinjam TEXT, " +
                "tanggal_kembali TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS allusers");
        db.execSQL("DROP TABLE IF EXISTS pinjaman");
        onCreate(db);
    }

    // Insert data pengguna baru saat register
    public boolean insertData(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        long result = db.insert("allusers", null, values);
        return result != -1;
    }

    // Cek apakah email sudah terdaftar
    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM allusers WHERE email = ?", new String[]{email});
        boolean exists = (cursor != null && cursor.getCount() > 0);
        if (cursor != null) cursor.close();
        return exists;
    }

    // Cek login berdasarkan email dan password
    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM allusers WHERE email = ? AND password = ?", new String[]{email, password});
        boolean valid = (cursor != null && cursor.getCount() > 0);
        if (cursor != null) cursor.close();
        return valid;
    }

    // Ambil data pengguna berdasarkan email
    public Cursor getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM allusers WHERE email = ?", new String[]{email});
    }

    // Update data pengguna
    public boolean updateUserProfile(String email, String nama, String telepon, String alamat, String jenisKelamin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama", nama);
        values.put("telepon", telepon);
        values.put("alamat", alamat);
        values.put("jenis_kelamin", jenisKelamin);
        long result = db.update("allusers", values, "email = ?", new String[]{email});
        return result != -1;
    }

    // Insert atau update data pinjaman berdasarkan email
    public boolean insertOrUpdatePinjaman(String email, String idAnggota, String tanggalPinjam, String tanggalKembali) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("id_anggota", idAnggota);
        values.put("tanggal_pinjam", tanggalPinjam);
        values.put("tanggal_kembali", tanggalKembali);

        Cursor existing = getPinjamanByEmail(email);
        if (existing != null && existing.moveToFirst()) {
            // update
            long result = db.update("pinjaman", values, "email = ?", new String[]{email});
            return result != -1;
        } else {
            // insert
            long result = db.insert("pinjaman", null, values);
            return result != -1;
        }
    }

    // Ambil data pinjaman berdasarkan email
    public Cursor getPinjamanByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM pinjaman WHERE email = ?", new String[]{email});
    }

    // Hapus data pinjaman berdasarkan email
    public boolean deletePinjamanByEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deleted = db.delete("pinjaman", "email = ?", new String[]{email});
        return deleted > 0;
    }
}
