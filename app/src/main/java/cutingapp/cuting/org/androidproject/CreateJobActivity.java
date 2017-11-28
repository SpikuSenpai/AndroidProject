package cutingapp.cuting.org.androidproject;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import cutingapp.cuting.org.androidproject.lib.helpers.Helper;
import cutingapp.cuting.org.androidproject.lib.jobs.Job;
import cutingapp.cuting.org.androidproject.lib.jobs.JobType;
import cutingapp.cuting.org.androidproject.lib.jobs.Location;
import cutingapp.cuting.org.androidproject.lib.user.Employer;

import static android.R.attr.phoneNumber;

public class CreateJobActivity extends AppCompatActivity {

    Helper helper;
    Employer employer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);
        Bundle data = this.getIntent().getExtras();
        helper = (Helper) data.getSerializable("helper");
        employer = (Employer) this.getIntent().getExtras().getSerializable("employer");

    }

    public void createJob(View view){

        EditText title = (EditText) findViewById(R.id.job_title);
        RadioGroup rg = (RadioGroup) findViewById(R.id.type_of_job);
        EditText organiser = (EditText) findViewById(R.id.organiser);
        EditText description = (EditText) findViewById(R.id.description);
       // DatePicker startDate = (DatePicker) findViewById(R.id.dateStart);
       // DatePicker endDate = (DatePicker) findViewById(R.id.dateEnd);

        Job job = new Job();

        job.setEmployee_id(String.valueOf(employer.getId()));
        job.setName(title.getText().toString());
        job.setOrganization(organiser.getText().toString());
        job.setDesc(description.getText().toString());
        job.setLocation(new Location("Cyprus", 55.3434f,57.5467f));
        job.setStartDate(new Date());
        job.setEndDate(new Date());
        if(rg.getCheckedRadioButtonId() == R.id.video){
            job.setType(JobType.VIDEOGRAPHY);
        }else if(rg.getCheckedRadioButtonId() == R.id.photography){
            job.setType(JobType.PHOTOGRAPHY);
        }
        employer.addCreatedJob(job);
        helper.updateEmployers(getApplicationContext());
        helper.addAvailableJob(job);
        helper.updateAvailableJobs(getApplicationContext());

        Bundle b = new Bundle();
        Intent i;
        b.putSerializable("employer", employer);
        b.putSerializable("helper", helper);
        i = new Intent(getApplicationContext(), HomeActivity.class);
        i.putExtras(b);
        startActivity(i);

    }
}
