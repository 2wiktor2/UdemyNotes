package com.example.udemynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerViewNotes;
    public static final ArrayList<Note> notes = new ArrayList<>();
    private NotesAdapter adapter;
    private NotesDBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);

        dbHelper = new NotesDBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        if (notes.isEmpty()) {
            notes.add(new Note("Заголовок1", "Описание заметки1", " День недели1", 2));
            notes.add(new Note("Заголовок2", "Описание заметки2", " День недели2", 3));
            notes.add(new Note("Заголовок3", "Описание заметки3", " День недели3", 2));
            notes.add(new Note("Заголовок4", "Описание заметки4", " День недели4", 3));
            notes.add(new Note("Заголовок5", "Описание заметки5", " День недели5", 1));
            notes.add(new Note("Заголовок6", "Описание заметки6", " День недели6", 2));
            notes.add(new Note("Заголовок7", "Описание заметки7", " День недели7", 1));
            notes.add(new Note("Заголовок8", "Описание заметки8", " День недели8", 3));
            notes.add(new Note("Заголовок1", "Описание заметки1", " День недели1", 2));
            notes.add(new Note("Заголовок2", "Описание заметки2", " День недели2", 3));
            notes.add(new Note("Заголовок3", "Описание заметки3", " День недели3", 2));
            notes.add(new Note("Заголовок4", "Описание заметки4", " День недели4", 3));
            notes.add(new Note("Заголовок5", "Описание заметки5", " День недели5", 1));
            notes.add(new Note("Заголовок6", "Описание заметки6", " День недели6", 2));
            notes.add(new Note("Заголовок7", "Описание заметки7", " День недели7", 1));
            notes.add(new Note("Заголовок8", "Описание заметки8", " День недели8", 3));
        }

        for (Note note : notes) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotesContract.NotesEntry.COLUMN_TITLE, note.getTitle());
            contentValues.put(NotesContract.NotesEntry.COLUMN_DESCRIPTION, note.getDescription());
            contentValues.put(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK, note.getDayOfWeek());
            contentValues.put(NotesContract.NotesEntry.COLUMN_PRIORITY, note.getPriority());
            database.insert(NotesContract.NotesEntry.TABLE_NAME, null, contentValues);
        }

        ArrayList<Note> notesFromDB = new ArrayList<>();

        Cursor cursor = database.query(NotesContract.NotesEntry.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DESCRIPTION));
            String dayOfWeek = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK));
            int prioriry = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_PRIORITY));

            Note note = new Note(title, description, dayOfWeek, prioriry);
            notesFromDB.add(note);
        }
        cursor.close();

        adapter = new NotesAdapter(notesFromDB);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(adapter);

        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(MainActivity.this, "Click" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLingClick(int position) {
                remove(position);
                Toast.makeText(MainActivity.this, "Deleted" + position, Toast.LENGTH_SHORT).show();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                remove(viewHolder.getAdapterPosition());
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);

    }

    private void remove(int position) {
        notes.remove(position);
        adapter.notifyDataSetChanged();
    }

    public void onClickAddNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }
}
