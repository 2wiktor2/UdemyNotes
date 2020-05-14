package com.example.udemynotes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NotesDataBase extends RoomDatabase {

    private static NotesDataBase dataBase;
    private static final  String DB_NAME = "notes2.db";
    private static final Object LOCK = new Object();

    public static  NotesDataBase getInstance(Context context){
        synchronized (LOCK){
            if (dataBase == null){
                dataBase = Room.databaseBuilder(context, NotesDataBase.class, DB_NAME)
                        .allowMainThreadQueries() // только для тестирования!!! мы запускаем обращение к базе данных в главном потоке.
                        // Удалить этот метод как только мы начнем работать в другом потоке!!
                        .build();
            }
        }
        return dataBase;
    }

    public abstract  NotesDao notesDao();
}
