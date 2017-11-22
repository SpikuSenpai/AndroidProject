package cutingapp.cuting.org.androidproject;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void signUp(View view){
        Intent intent= new Intent(this,AuditActivity.class);
        EditText name= (EditText) findViewById(R.id.name);
        EditText surname= (EditText) findViewById(R.id.surname);
        EditText id= (EditText) findViewById(R.id.id);
        EditText sid= (EditText) findViewById(R.id.sid);
        EditText email= (EditText) findViewById(R.id.email);
        CheckBox photo = (CheckBox) findViewById(R.id.photo);
        CheckBox video = (CheckBox) findViewById(R.id.video);

        try {

            File filecutting= new File(Environment.getRootDirectory(), "Notes");
            if (!filecutting.exists()) {
                filecutting.mkdirs();
            }

            FileWriter writer = new FileWriter(filecutting);
            writer.append( name + " \n" + surname + "\n" + id  + " \n" + sid + "\n" + email + "\n");
            if(photo.isChecked()){
                writer.append("Photograpy\n");
            }
            if(video.isChecked()){
                writer.append("Videograpy\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
