package cutingapp.cuting.org.androidproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cutingapp.cuting.org.androidproject.lib.helpers.Helper;
import cutingapp.cuting.org.androidproject.lib.user.Employee;

public class RegisterActivity extends AppCompatActivity {

    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Bundle data = this.getIntent().getExtras();
        helper = (Helper) data.getSerializable("helper");
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, AuditActivity.class);
        EditText name = (EditText) findViewById(R.id.name);
        EditText surname = (EditText) findViewById(R.id.surname);
        EditText id = (EditText) findViewById(R.id.id);
        EditText sid = (EditText) findViewById(R.id.sid);
        EditText email = (EditText) findViewById(R.id.email);
        CheckBox photo = (CheckBox) findViewById(R.id.photography);
        CheckBox video = (CheckBox) findViewById(R.id.video);
        Employee newEmployee = new Employee();
        if (photo.isChecked()) {
            newEmployee.addSkill("photography");

        }

        if (video.isChecked()) {
            newEmployee.addSkill("videography");

        }
        newEmployee.setId(Integer.parseInt(id.getText().toString()));
        newEmployee.setSid(Integer.parseInt(sid.getText().toString()));
        newEmployee.setId(newEmployee.getId());
        newEmployee.setName(name.getText().toString());
        newEmployee.setSurname(surname.getText().toString());
        newEmployee.setEmail(email.getText().toString());
        helper.getEmployees().put(String.valueOf(newEmployee.getId()),newEmployee);
        helper.updateEmployees(getApplicationContext());

        Toast.makeText(getApplicationContext(),"Registration Completed. You will be notified for your Audition",Toast.LENGTH_LONG).show();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        final Bundle s = new Bundle();
        s.putSerializable("helper",helper);
        s.putSerializable("employee", newEmployee);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtras(s);
                startActivity(intent);
                finish();
            }
        }, 1500);

    }
}
