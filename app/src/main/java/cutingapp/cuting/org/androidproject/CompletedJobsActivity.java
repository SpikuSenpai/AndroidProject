package cutingapp.cuting.org.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import cutingapp.cuting.org.androidproject.lib.helpers.Helper;
import cutingapp.cuting.org.androidproject.lib.jobs.Job;
import cutingapp.cuting.org.androidproject.lib.user.Employee;
import cutingapp.cuting.org.androidproject.lib.user.Employer;

public class CompletedJobsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_jobs);
        Bundle data = this.getIntent().getExtras();

        if (data == null) {
            Toast.makeText(getApplicationContext(),"Unexpected Error Happened",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),SplashActivity.class));
        }
        else {
            HashMap<String,Job> completedJobs = (HashMap<String,Job>) data.getSerializable("completed_jobs");
            Employee employee = (Employee) data.getSerializable("employee");
            Employer employer = (Employer) data.getSerializable("employer");
            Helper helper = (Helper) data.getSerializable("helper");

            int backColor = Color.parseColor("#ff7040"); // blue
            int textColor = Color.parseColor("#3c3f41"); //grey
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE - dd/MM", Locale.getDefault());
            LinearLayout layoutUpcoming = (LinearLayout) findViewById(R.id.completed_jobs_view);

            Iterator<Map.Entry<String, Job>> itr = completedJobs.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, Job> pair = (Map.Entry<String, Job>) itr.next();
                Job temp = pair.getValue();
                TextView cjob = makeJobNameTextView(
                        temp.getName() +
                                "\n\nCompleted on: " + formatter.format(temp.getEndDate()),
                        backColor,
                        textColor,
                        R.drawable.goto_job_arrow,
                        R.drawable.bottom_border);

                Bundle jobBundle = new Bundle();
                jobBundle.putSerializable("completed_job", temp);
                jobBundle.putSerializable("helper", helper);
                jobBundle.putSerializable("employee", employee);
                jobBundle.putSerializable("employer", employer);
                final Intent intent = new Intent(getApplicationContext(), JobViewActivity.class);
                intent.putExtras(jobBundle);
                cjob.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(intent);
                    }
                });
                layoutUpcoming.addView(cjob);
            }

        }

    }

    private TextView makeJobNameTextView(String name, int backColor, int textColor, int rightArrow, int border){
        /** <TextView
         android:id="@+id/job1_name"
         android:layout_width="match_parent"
         android:layout_height="40dp"
         android:paddingLeft="15dp"
         android:gravity="center_vertical"
         android:text="Upcoming 1"
         android:textColor="@color/grey"
         android:drawableEnd="@drawable/goto_job_arrow"
         android:textSize="17sp" />
         *
         *
         * */

        TextView jobNameView = new TextView(getApplicationContext());
        jobNameView.setBackgroundColor(backColor);
        jobNameView.setBackgroundResource(border);
        jobNameView.setText(name);
        jobNameView.setTextColor(textColor);
        jobNameView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        jobNameView.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        jobNameView.setPadding(35,0,0,0);
        jobNameView.setGravity(Gravity.CENTER_VERTICAL);
        jobNameView.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightArrow, 0);
        jobNameView.setId(View.generateViewId());
        return jobNameView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed(); // to return to home without restarting activity
                return true;
        }

        return (super.onOptionsItemSelected(item));
    }
}
