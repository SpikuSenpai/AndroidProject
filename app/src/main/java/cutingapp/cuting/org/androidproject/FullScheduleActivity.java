package cutingapp.cuting.org.androidproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import cutingapp.cuting.org.androidproject.lib.helpers.Helper;
import cutingapp.cuting.org.androidproject.lib.jobs.Job;
import cutingapp.cuting.org.androidproject.lib.user.Employee;
import cutingapp.cuting.org.androidproject.lib.user.Employer;

public class FullScheduleActivity extends AppCompatActivity
        implements UpcomingJobsViewFragment.OnFragmentInteractionListener{
    private Helper helper;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_schedule);
        Bundle data = this.getIntent().getExtras();

        if (data != null) {
            helper = (Helper) data.getSerializable("helper");
            employee = (Employee) data.getSerializable("employee");
            //   employer = (Employer) data.getSerializable("employer");
        }
        LinearLayout layoutUpcoming = (LinearLayout) findViewById(R.id.scroll_linearLayout1);
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE - dd/MM", Locale.getDefault());
        int backColor = Color.parseColor("#c11c2b99"); // light grey
        int textColor = Color.parseColor("#ffffff"); //grey
        HashMap<String, Job> applied_jobs = employee.getApplied_jobs();
        String[] days = new String[applied_jobs.size()];

        for (int i = 0; i < applied_jobs.size(); i++) {
            FrameLayout frameLayoutFragment = new FrameLayout(getApplicationContext());
            frameLayoutFragment.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            frameLayoutFragment.setId(i + 10);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            UpcomingJobsViewFragment toAdd = UpcomingJobsViewFragment.newInstance(findJobofGivenDay(applied_jobs, cal.getTime()));
            fragmentTransaction.add(frameLayoutFragment.getId(), toAdd);
            fragmentTransaction.commit();
            TextView dayname = makeDayNameTextView(i, days[i], backColor, textColor, frameLayoutFragment.getId());
            layoutUpcoming.addView(dayname);
            layoutUpcoming.addView(frameLayoutFragment);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    private ArrayList<Job> findJobofGivenDay(HashMap<String,Job> jobsToCheck) {
        ArrayList<Job> returnjobs = new ArrayList<Job>();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM", Locale.getDefault());
        int j = 0;

        try {
//            nowTime = new Time(timeformat.parse(timeformat.format(dateToCheck)).getTime());
            dateToCheck = dateformat.parse(dateformat.format(dateToCheck));

//            Log.println(Log.DEBUG,"TIMETOCHECK", String.valueOf(nowTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Iterator<Map.Entry<String, Job>> itr = jobsToCheck.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Job> pair = (Map.Entry<String, Job>) itr.next();
            Job temp = pair.getValue();
            Date jobDate = null;
            try {
                jobDate = dateformat.parse(dateformat.format(temp.getStartDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (dateToCheck.equals(jobDate)) {
                returnjobs.add(j, temp);
                j++;
            }
        }
        return returnjobs;
    }

    private TextView makeDayNameTextView(final int id, String name, int backColor, int textColor, final int frameLayout) {
        /**
         *
         *   <TextView
         android:id="@+id/day_1"
         android:layout_width="match_parent"
         android:layout_height="36dp"
         android:paddingLeft="15dp"
         android:paddingRight="15dp"
         android:background="@color/colorPrimary"
         android:drawableEnd="@android:drawable/arrow_down_float"
         android:gravity="center_vertical"
         android:text="Monday - 20/11/2017"
         android:textColor="@android:color/white"
         android:textSize="19sp"
         android:textStyle="bold" />
         *
         * */

        final TextView dayView = new TextView(getApplicationContext());
        dayView.setBackgroundColor(backColor);
        dayView.setText(name);
        dayView.setTextColor(textColor);
        dayView.setTypeface(dayView.getTypeface(), Typeface.BOLD);
        dayView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19);
        dayView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 180));
        dayView.setPadding(15, 0, 15, 0);
        dayView.setGravity(Gravity.CENTER_VERTICAL);
        dayView.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.arrow_up_float, 0);
        dayView.setId(id);
        dayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout f = (FrameLayout) findViewById(frameLayout);

                if (f.getVisibility() == View.VISIBLE) {
                    f.setVisibility(View.GONE);
                    dayView.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.arrow_down_float, 0);
                } else {
                    f.setVisibility(View.VISIBLE);
                    dayView.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.arrow_up_float, 0);
                }
            }
        });
        return dayView;
    }

}
