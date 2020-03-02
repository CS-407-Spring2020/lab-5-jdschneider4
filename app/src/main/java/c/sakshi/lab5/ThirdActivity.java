package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    DBHelper dbHelper;
    int noteid = -1;

    public void onButtonPress(View v){

        EditText myTextField = findViewById(R.id.editText);
        String content = myTextField.getText().toString();

        Context context = getApplicationContext();
        sqLiteDatabase = context.openOrCreateDatabase("notes",Context.MODE_PRIVATE,null);
        dbHelper = new DBHelper(sqLiteDatabase);

//        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5",Context.MODE_PRIVATE);

       //String username = sharedPreferences.getString(MainActivity.usernameKey,"");;

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid==-1){
            title = "NOTE_" + (Main2Activity.notes.size()+1);
            dbHelper.saveNotes(MainActivity.usernameKey,title,content,date);
        }else {
            title = "NOTE_" + (noteid+1);
            dbHelper.updateNotes(content,date,title,MainActivity.usernameKey);
        }
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid",noteid);

        if(noteid!=-1){
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();
            EditText editText = findViewById(R.id.editText);
            editText.setText(noteContent);

        }
    }
}
