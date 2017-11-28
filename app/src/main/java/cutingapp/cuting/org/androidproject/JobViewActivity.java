package cutingapp.cuting.org.androidproject;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Locale;

import cutingapp.cuting.org.androidproject.lib.helpers.Helper;
import cutingapp.cuting.org.androidproject.lib.jobs.Job;
import cutingapp.cuting.org.androidproject.lib.user.Employee;
import cutingapp.cuting.org.androidproject.lib.user.Employer;


public class JobViewActivity extends AppCompatActivity {
    Helper helper;
    // Employee
    Job available_job;
    Job applied_job;

    // Employer
    Job pending_job;

    // Employee + Employer
    Job completed_job;

    Employee employee;
    Employer employer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view);
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM", Locale.getDefault());
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        helper = (Helper) this.getIntent().getExtras().getSerializable("helper");
        // Employee
        available_job = (Job) this.getIntent().getExtras().getSerializable("available_job");
        applied_job = (Job) this.getIntent().getExtras().getSerializable("applied_job");

        // Employer
        pending_job = (Job) this.getIntent().getExtras().getSerializable("pending_job");

        // Employee + Employer
        completed_job = (Job) this.getIntent().getExtras().getSerializable("completed_job");

        employee = (Employee) this.getIntent().getExtras().getSerializable("employee");
        employer = (Employer) this.getIntent().getExtras().getSerializable("employer");

        // Employee
        if (employee != null && employer == null) {
            ((LinearLayout) findViewById(R.id.view_employee)).setVisibility(View.GONE);

            if (available_job != null && applied_job == null) {
                ((LinearLayout) findViewById(R.id.rate_completed)).setVisibility(View.GONE);
                this.setTitle(available_job.getName());
                Button apply = (Button) findViewById(R.id.apply_button);
                makeGui(available_job);

                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String jobID = String.valueOf(available_job.getId());
                        // Remove Job from available
                        helper.getAvailableJobs().remove(jobID);
                        // Set Employee id to current Job
                        available_job.setEmployee_id(String.valueOf(employee.getId()));
                        // add jobtoshow to employee applied jobs
                        employee.addAppliedJob(available_job);
                        helper.getEmployees().get(String.valueOf(employee.getId())).addAppliedJob(available_job);
                        // add employee id to employer job
                        helper.getEmployers().get(String.valueOf(available_job.getEmployer_id())).moveCreatedtoPending(available_job);
                        helper.updateAvailableJobs(getApplicationContext());
                        helper.updateEmployees(getApplicationContext());
                        helper.updateEmployers(getApplicationContext());
                        Bundle b = new Bundle();
                        Intent i;
                        b.putSerializable("available_jobs", helper.getAvailableJobs());
                        b.putSerializable("employee", employee);
                        b.putSerializable("helper", helper);
                        i = new Intent(getApplicationContext(), BrowseJobsActivity.class);
                        i.putExtras(b);
                        startActivity(i);


                    }
                });


            } else if (applied_job != null && available_job == null) {
                this.setTitle(applied_job.getName());
                makeGui(applied_job);
                ((LinearLayout) findViewById(R.id.rate_completed)).setVisibility(View.GONE);
                ((Button) findViewById(R.id.apply_button)).setVisibility(View.GONE);


            } else if (completed_job != null) {
                this.setTitle(completed_job.getName());
                makeGui(completed_job);
                ((Button) findViewById(R.id.apply_button)).setVisibility(View.GONE);
                if (completed_job.getRating() > -1) {
                    ((TextView) findViewById(R.id.please_rate)).setText("Rated with:");
                    Log.println(Log.DEBUG, "RATING", String.valueOf(completed_job.getRating()));
                    ((Button) findViewById(R.id.rate_button)).setVisibility(View.GONE);
                    RatingBar rb = (RatingBar) findViewById(R.id.ratingbar_employee);
                    rb.setRating(completed_job.getRating());
                    rb.setIsIndicator(true);
                } else {
                    ((LinearLayout) findViewById(R.id.rate_completed)).setVisibility(View.GONE);
                }


            } else {
                Toast.makeText(getApplicationContext(), "Unexpected Error Happened", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), SplashActivity.class));
            }

        }
        //Employer
        if (employer != null && employee == null) {


            if (pending_job != null && completed_job == null) {
                TextView employeeView = (TextView) findViewById(R.id.employee_name);
                Employee temp = helper.getEmployees().get(pending_job.getEmployee_id());
                employeeView.setText(temp.getName() + " " + temp.getSurname());
                this.setTitle(pending_job.getName());
                ((Button) findViewById(R.id.apply_button)).setVisibility(View.GONE);
                makeGui(pending_job);
            } else if (completed_job != null && pending_job == null) {
                TextView employeeView = (TextView) findViewById(R.id.employee_name);
                Employee temp = helper.getEmployees().get(completed_job.getEmployee_id());
                employeeView.setText(temp.getName() + " " + temp.getSurname());
                this.setTitle(completed_job.getName());
                ((Button) findViewById(R.id.apply_button)).setVisibility(View.GONE);
                makeGui(completed_job);
                final RatingBar rb = (RatingBar) findViewById(R.id.ratingbar_employee);
                Log.println(Log.DEBUG, "RATING", String.valueOf((completed_job.getRating())));

                rb.setRating((completed_job.getRating() == -1) ? 0 : completed_job.getRating());
                rb.setIsIndicator(false);
                Button rateButton = (Button) findViewById(R.id.rate_button);


                rateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Log.println(Log.DEBUG,"RATING",String.valueOf((int) rb.getRating()));
                        String comp_id = String.valueOf(completed_job.getId());
                        employer.getCompleted_jobs().get(comp_id).setRating((int) rb.getRating());
                        helper.getEmployees().get(String.valueOf(completed_job.getEmployee_id())).getCompleted_jobs().get(comp_id).setRating((int) rb.getRating());
                        helper.getEmployers().get(String.valueOf(completed_job.getEmployer_id())).getCompleted_jobs().get(comp_id).setRating((int) rb.getRating());
                        helper.updateEmployees(getApplicationContext());
                        helper.updateEmployers(getApplicationContext());
                        Toast.makeText(getApplicationContext(), "Thank you for rating", Toast.LENGTH_LONG).show();

                    }

                });


            } else {
                Toast.makeText(getApplicationContext(), "Unexpected Error Happened", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), SplashActivity.class));
            }
        }


        if (employee == null && employer == null) {
            Toast.makeText(getApplicationContext(), "Unexpected Error Happened", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), SplashActivity.class));
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                onBackPressed();
                Bundle jobBundle = new Bundle();
                if (employee != null && employer == null) {
                    if (applied_job != null && available_job == null) {
//                        jobBundle.putSerializable("applied_jobs", employee.getApplied_jobs());
                        jobBundle.putSerializable("helper", helper);
                        jobBundle.putSerializable("employee", employee);
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtras(jobBundle);
                        startActivity(intent);
                    } else if (completed_job != null) {
                        jobBundle.putSerializable("completed_jobs", employee.getCompleted_jobs());
                        jobBundle.putSerializable("helper", helper);
                        jobBundle.putSerializable("employee", employee);
                        Intent intent = new Intent(getApplicationContext(), CompletedJobsActivity.class);
                        intent.putExtras(jobBundle);
                        startActivity(intent);
                    }


                } else if (employer != null && employee == null) {


                    if (pending_job != null && completed_job == null) {
//                        jobBundle.putSerializable("pending_jobs", helper.getEmployers().get(String.valueOf(employer.getId())).getPending_jobs());
                        jobBundle.putSerializable("helper", helper);
                        jobBundle.putSerializable("employer", employer);

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtras(jobBundle);
                        startActivity(intent);


                    } else if (completed_job != null && pending_job == null) {
                        jobBundle.putSerializable("completed_jobs", employer.getCompleted_jobs());
                        jobBundle.putSerializable("helper", helper);
                        jobBundle.putSerializable("employer", employer);

                        Intent intent = new Intent(getApplicationContext(), CompletedJobsActivity.class);
                        intent.putExtras(jobBundle);
                        startActivity(intent);
                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Unexpected Error Happened", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), SplashActivity.class));
                }

                return true;

        }
        return (super.onOptionsItemSelected(item));
    }

    private void makeGui(Job jobToShow) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM", Locale.getDefault());
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        TextView scheduled = (TextView) findViewById(R.id.date_time);
        TextView location = (TextView) findViewById(R.id.loc_view);
        TextView service = (TextView) findViewById(R.id.service);
        TextView person = (TextView) findViewById(R.id.person_in_charge_view);
        TextView desc = (TextView) findViewById(R.id.desc);


        scheduled.setText((" on " + dateFormat.format(jobToShow.getStartDate()) + " - " + dateFormat.format(jobToShow.getEndDate()) +
                "\n at " + timeformat.format(jobToShow.getStartTime()) + " - " + timeformat.format(jobToShow.getEndTime())));

        LatLng pos = new LatLng(jobToShow.getLocation().getLatitude(), jobToShow.getLocation().getLongitude());
        final Intent intent = new Intent(this, JobLocation.class);
        Bundle data = new Bundle();
        data.putParcelable("position", pos);
        intent.putExtras(data);

        location.setText(jobToShow.getLocation().getName());
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        service.setText(jobToShow.getType().getType().toUpperCase());

        person.setText(jobToShow.getOrganization() + "\n\n" + jobToShow.getPerson_in_charge());
        desc.setText(jobToShow.getDesc());
    }


}
