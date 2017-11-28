package cutingapp.cuting.org.androidproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class BrowseJobsActivity extends AppCompatActivity implements   AvailableJobsFragment.OnFragmentInteractionListener {
    private Helper helper;
    private HashMap<String,Job> available_jobs;
    private Employee employee;
    private final  int  DAYS_TO_SHOW = 180;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_jobs);

        Bundle data = this.getIntent().getExtras();
        if (data == null) {
            Toast.makeText(getApplicationContext(),"Unexpected Error Happened",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),SplashActivity.class));
        }
        else {
            employee = (Employee) data.getSerializable("employee");
            helper = (Helper) data.getSerializable("helper");
            available_jobs = (HashMap<String,Job>) data.getSerializable("available_jobs");

            if (available_jobs != null) {
                LinearLayout layoutUpcoming = (LinearLayout) findViewById(R.id.browse_jobs);
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE - dd/MM", Locale.getDefault());

                int backColor = Color.parseColor("#ff7040"); // light grey
                int textColor = Color.parseColor("#FF000000"); //grey

                Calendar cal = Calendar.getInstance();
                String[] days = new String[DAYS_TO_SHOW];

                for (int i = 0; i < DAYS_TO_SHOW; i++) {
                    days[i] = formatter.format(cal.getTime());
                    ArrayList<Job> tempJobs = findJobofGivenDay(available_jobs, cal.getTime());

                    if (tempJobs.size() > 0) {
                        FrameLayout frameLayoutFragment = new FrameLayout(getApplicationContext());
                        frameLayoutFragment.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        frameLayoutFragment.setId(View.generateViewId());
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        AvailableJobsFragment toAdd = AvailableJobsFragment.newInstance(tempJobs,helper, null, employee);
                        fragmentTransaction.add(frameLayoutFragment.getId(), toAdd);
                        fragmentTransaction.commit();
                        TextView dayname = makeDayNameTextView(i, days[i], backColor, textColor, frameLayoutFragment.getId());
                        layoutUpcoming.addView(dayname);
                        layoutUpcoming.addView(frameLayoutFragment);
                    }
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                }
            }
            // null Employee
            else {
                Toast.makeText(getApplicationContext(), "Unexpected Error Happened", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), SplashActivity.class));
            }
        }
    }

    private ArrayList<Job> findJobofGivenDay(HashMap<String,Job> jobsToCheck, Date dateToCheck) {
        ArrayList<Job> returnjobs = new ArrayList<Job>();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM", Locale.getDefault());
        int j = 0;

        try {
            dateToCheck = dateformat.parse(dateformat.format(dateToCheck));
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


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle b = new Bundle();
        Intent i;
        switch (item.getItemId()) {
            case android.R.id.home:
                b.putSerializable("employee", employee);
                b.putSerializable("helper", helper);
                i = new Intent(getApplicationContext(), HomeActivity.class);
                i.putExtras(b);
                startActivity(i);
                return true;
        }

        return (super.onOptionsItemSelected(item));
    }

}
