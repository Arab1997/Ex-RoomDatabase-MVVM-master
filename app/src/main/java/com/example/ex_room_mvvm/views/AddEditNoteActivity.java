package com.example.ex_room_mvvm.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.ex_room_mvvm.R;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_DESC = "EXTRA_DESC";
    public static final String EXTRA_PRIORITY = "EXTRA_PRIORITY";
    private EditText mTitle, mDesc;
    private NumberPicker mPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mTitle = findViewById(R.id.etTitle);
        mDesc = findViewById(R.id.etDesc);
        mPriority = findViewById(R.id.numberPickerPriority);

        mPriority.setMinValue(1);
        mPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            mTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            mDesc.setText(intent.getStringExtra(EXTRA_DESC));
            mPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String title = mTitle.getText().toString();
        String desc = mDesc.getText().toString();
        int priority = mPriority.getValue();

        if (title.trim().isEmpty() || desc.trim().isEmpty()) {
            Toast.makeText(this, "Form Cannot Be Blank", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESC, desc);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_note) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
