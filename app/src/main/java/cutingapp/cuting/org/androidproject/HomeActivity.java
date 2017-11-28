package cutingapp.cuting.org.androidproject;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import cutingapp.cuting.org.androidproject.lib.user.Employer;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        UpcomingJobsViewFragment.OnFragmentInteractionListener {
    private HashMap<String, ArrayList<Job>> jobs;
    private final int DAYS_TO_SHOW = 5;
    private final String CUTING_EDGE_EMAIL = "cutingedgelimassol@gmail.com";
    private Helper helper;
    private Employee employee;
    private Employer employer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle data = this.getIntent().getExtras();

        if (data == null) {
            Toast.makeText(getApplicationContext(),"Unexpected Error Happened",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),SplashActivity.class));
        }
        else {
            helper = (Helper) data.getSerializable("helper");
            employee = (Employee) data.getSerializable("employee");
            employer = (Employer) data.getSerializable("employer");


            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setItemIconTintList(null);
            navigationView.setNavigationItemSelectedListener(this);

            // Employee
            if (employee != null && employer == null) {

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.setVisibility(View.GONE);

                LinearLayout layoutUpcoming = (LinearLayout) findViewById(R.id.scroll_linearLayout);
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE - dd/MM", Locale.getDefault());
                int backColor = Color.parseColor("#ff7040"); // light grey
                int textColor = Color.parseColor("#FF000000"); //grey
                Calendar cal = Calendar.getInstance();
                String[] days = new String[DAYS_TO_SHOW];
                HashMap<String, Job> applied_jobs = employee.getApplied_jobs();

                for (int i = 0; i < DAYS_TO_SHOW; i++) {
                    days[i] = formatter.format(cal.getTime());
                    FrameLayout frameLayoutFragment = new FrameLayout(getApplicationContext());
                    frameLayoutFragment.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    frameLayoutFragment.setId(View.generateViewId());
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ArrayList<Job> tempJobs = findJobofGivenDay(applied_jobs, cal.getTime());
                    if(tempJobs.size() > 0){
                        UpcomingJobsViewFragment toAdd = UpcomingJobsViewFragment.newInstance(tempJobs,null,employee,helper);
                        fragmentTransaction.add(frameLayoutFragment.getId(), toAdd);
                        fragmentTransaction.commit();
                        TextView dayname = makeDayNameTextView(i, days[i], backColor, textColor, frameLayoutFragment.getId());

                        layoutUpcoming.addView(dayname);
                        layoutUpcoming.addView(frameLayoutFragment);
                    }
                    cal.add(Calendar.DAY_OF_MONTH, 1);


                }

                ((MenuItem) navigationView.getMenu().findItem(R.id.nav_comp_jobs_r)).setVisible(false);
                ((MenuItem) navigationView.getMenu().findItem(R.id.nav_create_job)).setVisible(false);
                TextView usernameTextview = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_name);
                TextView useremailTextview = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_email);
                usernameTextview.setText(employee.getName() + " " + employee.getSurname());
                useremailTextview.setText(employee.getEmail());
            }
            //Employer
            else if (employer != null && employee == null) {

                final Bundle bund = new Bundle();
                final Intent intent = new Intent(getApplicationContext(),CreateJobActivity.class);
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bund.putSerializable("helper", helper);
                        bund.putSerializable("employer",employer);
                        intent.putExtras(bund);
                        startActivity(intent);
                    }
                });
                LinearLayout layoutUpcoming = (LinearLayout) findViewById(R.id.scroll_linearLayout);
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE - dd/MM", Locale.getDefault());
                int backColor = Color.parseColor("#ff7040"); // light grey
                int textColor = Color.parseColor("#FF000000"); //grey
                Calendar cal = Calendar.getInstance();
                String[] days = new String[DAYS_TO_SHOW];
                HashMap<String, Job> pending_jobs = employer.getPending_jobs();

                for (int i = 0; i < DAYS_TO_SHOW; i++) {
                    days[i] = formatter.format(cal.getTime());
                    FrameLayout frameLayoutFragment = new FrameLayout(getApplicationContext());
                    frameLayoutFragment.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    frameLayoutFragment.setId(i + 10);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ArrayList<Job> tempJobs = findJobofGivenDay(pending_jobs, cal.getTime());
                    if(tempJobs.size() > 0){
                        UpcomingJobsViewFragment toAdd = UpcomingJobsViewFragment.newInstance(tempJobs,employer,null,helper);
                        fragmentTransaction.add(frameLayoutFragment.getId(), toAdd);
                        fragmentTransaction.commit();
                        TextView dayname = makeDayNameTextView(i, days[i], backColor, textColor, frameLayoutFragment.getId());

                        layoutUpcoming.addView(dayname);
                        layoutUpcoming.addView(frameLayoutFragment);
                    }
                    cal.add(Calendar.DAY_OF_MONTH, 1);


                }

                TextView usernameTextview = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_name);
                TextView useremailTextview = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_email);
                ((MenuItem) navigationView.getMenu().findItem(R.id.nav_full_schedule)).setVisible(false);
                ((MenuItem) navigationView.getMenu().findItem(R.id.nav_browse_jobs)).setVisible(false);
                ((MenuItem) navigationView.getMenu().findItem(R.id.nav_comp_jobs_e)).setVisible(false);

                usernameTextview.setText(employer.getName() + " " + employer.getSurname());
                useremailTextview.setText(employer.getEmail());
            }
            // null both
            else {
                Toast.makeText(getApplicationContext(), "Unexpected Error Happened", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), SplashActivity.class));
            }
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Bundle b = new Bundle();
        Intent i;
        if (id == R.id.nav_cuting_email) {
            sendEmail();
        } else if (id == R.id.nav_cuting_location) {
            openGoogleMapsWithLocation();
        } else if (id == R.id.nav_cuting_website) {
            startWebpageCuting();
        }else if (id == R.id.nav_full_schedule){
            Intent intent = new Intent(this,FullScheduleActivity.class);
            Bundle applied_jobs = new Bundle();
            applied_jobs.putSerializable("helper",helper);
            applied_jobs.putSerializable("employee",employee);
            intent.putExtras(applied_jobs);
            startActivity(intent);
        }
        else if(id == R.id.nav_browse_jobs){
            b.putSerializable("helper", helper);
            b.putSerializable("available_jobs",helper.getAvailableJobs());
            b.putSerializable("employee", employee);
            i = new Intent(getApplicationContext(), BrowseJobsActivity.class);
            i.putExtras(b);
            startActivity(i);
        }
        else if(id == R.id.nav_comp_jobs_e){
            b.putSerializable("helper", helper);
            b.putSerializable("employee",employee);
            b.putSerializable("completed_jobs", employee.getCompleted_jobs());
            i = new Intent(getApplicationContext(),CompletedJobsActivity.class);
            i.putExtras(b);
            startActivity(i);
        }
        else if(id == R.id.nav_comp_jobs_r){
            b.putSerializable("helper", helper);
            b.putSerializable("employer",employer);
            b.putSerializable("completed_jobs", employer.getCompleted_jobs());
            i = new Intent(getApplicationContext(),CompletedJobsActivity.class);
            i.putExtras(b);
            startActivity(i);

        }
        else if(id == R.id.nav_create_job){
            //TODO Create Job Employer
            b.putSerializable("helper", helper);
            b.putSerializable("employer",employer);
            i = new Intent(getApplicationContext(),CreateJobActivity.class);
            i.putExtras(b);
            startActivity(i);
        }
        else if(id == R.id.nav_logout){
            // Logout
            startActivity( new Intent(getApplicationContext(),SplashActivity.class));
        }
        else if(id == R.id.nav_edit_prof){
            b.putSerializable("helper", helper);
            b.putSerializable("employer",employer);
            b.putSerializable("employee",employee);
            i = new Intent(getApplicationContext(),EditProfileActivity.class);
            i.putExtras(b);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private ArrayList<Job> findJobofGivenDay(HashMap<String,Job> jobsToCheck, Date dateToCheck) {
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

    private void sendEmail() {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:"+ CUTING_EDGE_EMAIL));
        startActivity(Intent.createChooser(i, "Send mail with"));
    }

    private void openGoogleMapsWithLocation() {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/search/?api=1&query=CUTing+Edge-An+American+Space,+Cyprus+University+of+Technology"));
        startActivity(intent);
    }

    private void startWebpageCuting() {
        Intent i = new Intent(this,WebViewCutingActivity.class);
        i.putExtra("webpageToShow","http://web.cut.ac.cy/cutingedge/");
        startActivity(i);
    }
}
