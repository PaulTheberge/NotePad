package com.example.dryraven.notepad;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dryraven.notepad.data.NoteItem;
import com.example.dryraven.notepad.data.NotesDataSource;

import java.util.List;


public class MainActivity extends ListActivity {

    //these are my persistent lovelies
    private NotesDataSource dataSource;
    List<NoteItem> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //calls the super method for creating the activity
        super.onCreate(savedInstanceState);
        //loads the layout xml file (file extension unnecessary for the load)
        setContentView(R.layout.activity_main);

        //instantiate dataSource method
        dataSource = new NotesDataSource(this);

        //wrote this method, then clicked alt-enter, and automatically generated the code stub for that new array quick as beans!
        refreshDisplay();

    }

    private void refreshDisplay() {

        notesList = dataSource.findAll();
        ArrayAdapter<NoteItem> adapter =
                new ArrayAdapter<NoteItem>(this, R.layout.list_item_layout, notesList);
        setListAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_create) {
            createNote();

        }

        return super.onOptionsItemSelected(item);
    }

    private void createNote() {
        NoteItem note = NoteItem.getNew();
        Intent intent = new Intent(this, NoteEditorActivity.class);
        intent.putExtra("key", note.getKey());
        intent.putExtra("text", note.getText());
        //intent, requestCode can be whatever we decide
        startActivityForResult(intent, 1001);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        NoteItem note = notesList.get(position);
        Intent intent = new Intent(this, NoteEditorActivity.class);
        intent.putExtra("key", note.getKey());
        intent.putExtra("text", note.getText());
        //intent, requestCode can be whatever we decide
        startActivityForResult(intent, 1001);

    }
}
