package com.example.minggu7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context,"Userdataa.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE session(id integer PRIMARY KEY, login text)");
        DB.execSQL("CREATE TABLE Userdetails(username TEXT primary key,email TEXT,telNo TEXT,dob TEXT, password TEXT)");
        DB.execSQL("INSERT INTO session(id, login) VALUES (1, 'kosong')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS Userdetails");
        DB.execSQL("DROP TABLE IF EXISTS session");
        onCreate(DB);
    }
    public Boolean insertUserData(String username,String email,String telNo,String dob,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("telNo",telNo);
        contentValues.put("dob",dob);
        contentValues.put("password",password);
        long result = DB.insert("Userdetails",null,contentValues);
        DB.close();
        if(result == -1)
            return false;
        else
            return true;
    }
    public Boolean updateUserData(String username,String email,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);

        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ?",new String[]{username} );

        if (cursor.moveToFirst()) {  // Pindah ke baris pertama jika ada hasil
            long result = DB.update("Userdetails", contentValues, "username=?", new String[]{username});
            cursor.close();  // Jangan lupa tutup cursor

            return result != -1;  // Jika result bukan -1, berarti update berhasil
        } else {
            cursor.close();  // Tutup cursor jika data tidak ditemukan
            return false;
        }
    }
    public Boolean deleteUserData(String username){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ?",new String[]{username} );
        if(cursor.getCount()>0){
            long result = DB.delete("Userdetails","username=?",new String[]{username});
            if(result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Userdetails",null );
        return cursor;
    }

    public Boolean checkLogin(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Userdetails WHERE username = ? AND password = ?", new String[]{username, password});
        boolean result = cursor.getCount() > 0;
        cursor.close();  // Tutup Cursor
        return result;
    }

    public Boolean checkSession(String value){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM session WHERE login = ? ", new String[]{value});
        boolean result = cursor.getCount() > 0;
        cursor.close();  // Tutup Cursor
        return result;
    }
    //upgrade session
    public Boolean upgradeSession(String value, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("login", value);
        long update = db.update("session", values, "id= " +id, null);
        if (update == -1){
            return false;
        }else {
            return true;
        }
    }
}
