package cutingapp.cuting.org.androidproject;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

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

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import cutingapp.cuting.org.androidproject.lib.jobs.Job;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        UpcomingJobsViewFragment.OnFragmentInteractionListener {
    private HashMap<String, ArrayList<Job>> jobs;
    private final int DAYS_TO_SHOW = 5;
    private final String CUTING_EDGE_EMAIL = "cutingedgelimassol@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle data = this.getIntent().getExtras();
        if (data != null) {
            jobs = (HashMap<String, ArrayList<Job>>) data.getSerializable("jobs");
        }

        LinearLayout layoutUpcoming = (LinearLayout) findViewById(R.id.scroll_linearLayout);
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE - dd/MM", Locale.getDefault());
        int backColor = Color.parseColor("#c11c2b99"); // light grey
        int textColor = Color.parseColor("#ffffff"); //grey
        Calendar cal = Calendar.getInstance();
        String[] days = new String[DAYS_TO_SHOW];
        ArrayList<Job> applied_jobs = jobs.get("applied_jobs");

        for (int i = 0; i < DAYS_TO_SHOW; i++) {
            days[i] = formatter.format(cal.getTime());
            FrameLayout frameLayoutFragment = new FrameLayout(getApplicationContext());
            frameLayoutFragment.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            frameLayoutFragment.setId(i + 10);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            UpcomingJobsViewFragment toAdd = UpcomingJobsViewFragment.newInstance(findJobofGivenDay(applied_jobs, cal.getTime()));
            fragmentTransaction.add(frameLayoutFragment.getId(), toAdd);
            fragmentTransaction.commit();
            TextView dayname = makeDayNameTextView(i, days[i], backColor, textColor, frameLayoutFragment.getId());
            cal.add(Calendar.DAY_OF_MONTH, 1);
            layoutUpcoming.addView(dayname);
            layoutUpcoming.addView(frameLayoutFragment);
        }

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
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

        if (id == R.id.nav_cuting_email) {
            sendEmail();
        } else if (id == R.id.nav_cuting_location) {
            openGoogleMapsWithLocation();
        } else if (id == R.id.nav_cuting_website) {
            startWebpageCuting();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private ArrayList<Job> findJobofGivenDay(ArrayList<Job> jobsToCheck, Date dateToCheck) {
        ArrayList<Job> returnjobs = new ArrayList<Job>();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM", Locale.getDefault());
//        SimpleDateFormat timeformat = new SimpleDateFormat("H",Locale.getDefault());
//        Log.println(Log.DEBUG,"DATETOCHECK", dateToCheck.toString());
//        Time nowTime = null;
        int j = 0;
        try {
//            nowTime = new Time(timeformat.parse(timeformat.format(dateToCheck)).getTime());
            dateToCheck = dateformat.parse(dateformat.format(dateToCheck));

//            Log.println(Log.DEBUG,"TIMETOCHECK", String.valueOf(nowTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jobsToCheck.size(); i++) {
            Job temp = jobsToCheck.get(i);
            Date jobDate = null;
            Time jobTime = null;
            try {
                jobDate = dateformat.parse(dateformat.format(temp.getStartDate()));
//                jobTime = new Time(timeformat.parse(temp.getStartTime().toString()).getTime());
                Log.println(Log.DEBUG, "DATE", String.valueOf(jobTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (dateToCheck.equals(jobDate)) {
                returnjobs.add(j, jobsToCheck.get(i));
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
