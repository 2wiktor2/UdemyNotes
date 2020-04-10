package com.example.udemynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    ArrayList<Note> notes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);

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


        NotesAdapter adapter = new NotesAdapter(notes);
       // recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNotes.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewNotes.setAdapter(adapter);

    }
}
