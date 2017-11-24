package cutingapp.cuting.org.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Locale;

import cutingapp.cuting.org.androidproject.lib.jobs.Job;


public class JobViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view);
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM", Locale.getDefault());
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Job jobToShow = (Job) this.getIntent().getExtras().getSerializable("job_obj");

        if(jobToShow != null) {
            this.setTitle(jobToShow.getName());
            TextView scheduled = (TextView) findViewById(R.id.date_time);
            TextView location = (TextView) findViewById(R.id.loc_view);
            TextView service = (TextView) findViewById(R.id.service);
            TextView person = (TextView) findViewById(R.id.person_in_charge_view);
            TextView desc = (TextView) findViewById(R.id.desc);
            scheduled.setText((" on " + dateFormat.format(jobToShow.getStartDate()) + " - " + dateFormat.format(jobToShow.getEndDate()) +
                    "\n at " + timeformat.format(jobToShow.getStartTime()) + " - " + timeformat.format(jobToShow.getEndTime())));

            LatLng pos = new LatLng(jobToShow.getLocation().getLatitude(),jobToShow.getLocation().getLongitude());
            final Intent intent = new Intent(this,JobLocation.class);
            Bundle data = new Bundle();
            data.putParcelable("position",pos);
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
