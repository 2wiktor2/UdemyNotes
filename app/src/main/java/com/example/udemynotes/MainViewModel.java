package com.example.udemynotes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static NotesDataBase dataBase;
    private LiveData<List<Note>> notes;

    public MainViewModel(@NonNull Application application) {
        super(application);
        dataBase = NotesDataBase.getInstance(getApplication());
        notes = dataBase.notesDao().getAllNotes();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void setNotes(LiveData<List<Note>> notes) {
        this.notes = notes;
    }

    public void insertNote(Note note){
        new InsertTask().execute(note);
    }

    private static class InsertTask extends AsyncTask<Note, Void, Void>{

        @Override
        protected Void doInBackground(Note... notes) {
            if (notes != null && notes.length > 0){
                dataBase.notesDao().insertNote(notes[0]);
            }
            return null;
        }
    }

    public void deleteNote(Note note){
        new deleteTask().execute(note);
    }

    private static class deleteTask extends AsyncTask<Note, Void, Void>{

        @Override
        protected Void doInBackground(Note... notes) {
            if (notes != null && notes.length > 0){
                dataBase.notesDao().deleteNote(notes[0]);
            }
            return null;
        }
    }

    public void deleteAllNotes(){
        new deleteAllTask().execute();
    }

    private static class deleteAllTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... notes) {
            dataBase.notesDao().deleteAllNotes();
            return null;
        }
    }
}
