package cutingapp.cuting.org.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cutingapp.cuting.org.androidproject.lib.helpers.Helper;
import cutingapp.cuting.org.androidproject.lib.user.Employee;
import cutingapp.cuting.org.androidproject.lib.user.Employer;

public class EditProfileActivity extends AppCompatActivity {

    private Helper helper;
    private Employee employee;
    private Employer employer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Bundle data = this.getIntent().getExtras();
        TextView name = (TextView) findViewById(R.id.full_name);
        EditText phoneNumber = (EditText) findViewById(R.id.phone_number_change);
        EditText email = (EditText) findViewById(R.id.email_change);


        if (data == null) {
            Toast.makeText(getApplicationContext(), "Unexpected Error Happened", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), SplashActivity.class));
        } else {
            helper = (Helper) data.getSerializable("helper");
            employee = (Employee) data.getSerializable("employee");
            employer = (Employer) data.getSerializable("employer");

            if (employee != null && employer == null) {
                name.setText(employee.getName() + " " + employee.getSurname());
                email.setText(employee.getEmail());
                phoneNumber.setText(employee.getPhone_number());


            } else if (employer != null && employee == null) {
                name.setText(employer.getName() + " " + employer.getSurname());
                email.setText(employer.getEmail());
                phoneNumber.setText(employer.getPhone_number());
            } else {
                Toast.makeText(getApplicationContext(), "Unexpected Error Happened", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), SplashActivity.class));
            }

        }
    }

    public void saveChanges(View view) {

        EditText phoneNumber = (EditText) findViewById(R.id.phone_number_change);
        EditText email = (EditText) findViewById(R.id.email_change);

        if (employee != null && employer == null) {
            employee.setEmail((email.getText().toString().isEmpty() || email.getText().toString().equals("")) ? employee.getEmail() : email.getText().toString());
            employee.setPhone_number((phoneNumber.getText().toString().isEmpty() || phoneNumber.getText().toString().equals("")) ? employee.getPhone_number() : phoneNumber.getText().toString());
            helper.updateEmployees(getApplicationContext());
        } else if (employer != null && employee == null) {
            employer.setEmail((email.getText().toString().isEmpty() || email.getText().toString().equals("")) ? employer.getEmail() : email.getText().toString());
            employer.setPhone_number((phoneNumber.getText().toString().isEmpty() || phoneNumber.getText().toString().equals("")) ? employer.getPhone_number() : phoneNumber.getText().toString());
            helper.updateEmployers(getApplicationContext());
        } else {
            Toast.makeText(getApplicationContext(), "Unexpected Error Happened", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), SplashActivity.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Bundle s = new Bundle();
                s.putSerializable("employee", employee);
                s.putSerializable("employer", employer);
                s.putSerializable("helper",helper);
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                i.putExtras(s);
                startActivity(i);
                return true;
        }

        return (super.onOptionsItemSelected(item));
    }
}
