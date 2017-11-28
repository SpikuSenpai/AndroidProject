package cutingapp.cuting.org.androidproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import cutingapp.cuting.org.androidproject.lib.helpers.Helper;
import cutingapp.cuting.org.androidproject.lib.jobs.Job;
import cutingapp.cuting.org.androidproject.lib.user.Employee;
import cutingapp.cuting.org.androidproject.lib.user.Employer;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AvailableJobsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AvailableJobsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AvailableJobsFragment extends Fragment {
    ArrayList<Job> jobs;
    Helper helper;
    Employee employee;
    Employer employer;

    private OnFragmentInteractionListener mListener;

    public AvailableJobsFragment() {
        // Required empty public constructor
        jobs = new ArrayList<>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment AvailableJobsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AvailableJobsFragment newInstance(ArrayList<Job> jobsToShow, Helper helper, Employer employer, Employee employee) {
        Bundle bundle = new Bundle();

        bundle.putSerializable("jobs", jobsToShow);
        bundle.putSerializable("helper", helper);
        bundle.putSerializable("employer", employer);
        bundle.putSerializable("employee",employee);
        AvailableJobsFragment fragment = new AvailableJobsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            jobs = (ArrayList<Job>) getArguments().getSerializable("jobs");
            helper = (Helper) getArguments().getSerializable("helper");
            employee = (Employee) getArguments().getSerializable("employee");
            employer = (Employer) getArguments().getSerializable("employer");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_available_jobs, container, false);
        LinearLayout linearLayout = (LinearLayout)rootView.findViewById(R.id.available_jobs);

        SimpleDateFormat timef = new SimpleDateFormat("HH:mm", Locale.getDefault());
        int numJobs = jobs.size();
        Log.println(Log.DEBUG,"NONE","NumJobs = " + numJobs);
        int backColor;
        int textColor;
        String txtViewName = "";
        if(numJobs > 0){
            Collections.sort(jobs, new Comparator<Job>() {
                @Override
                public int compare(Job r1, Job r2) {
                    return r1.getStartDate().compareTo(r2.getStartDate());
                }
            });
            backColor = Color.parseColor("#f1f1f3"); // light grey
            textColor = Color.parseColor("#3c3f41"); //grey
            Date now = (Calendar.getInstance()).getTime();
            SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            try {
                now = dateformat.parse(dateformat.format(now));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            for(int i = 0; i < numJobs; i++){
                Job tempJob = jobs.get(i);
                Date timeOfEvent = tempJob.getStartTime();
                try {
                    timeOfEvent = dateformat.parse(dateformat.format(timeOfEvent));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                if(timeOfEvent.after(now)) {
                    txtViewName =
                           "\n"  + tempJob.getName() + "\n\n" +
                            timef.format(tempJob.getStartTime()) + " - " + timef.format(tempJob.getEndTime()) + "\n\n" +
                                    tempJob.getType().getType().toUpperCase() + "\n\n"  +
                                    tempJob.getOrganization() + "\n";
                    TextView tempTxtView = makeJobNameTextView(txtViewName, getActivity(), backColor, textColor, R.drawable.goto_job_arrow, R.drawable.bottom_border);
                    Bundle jobBundle = new Bundle();
                    jobBundle.putSerializable("available_job", tempJob);
                    jobBundle.putSerializable("helper", helper);
                    jobBundle.putSerializable("employer", employer);
                    jobBundle.putSerializable("employee", employee);
                    final Intent intent = new Intent(this.getContext(), JobViewActivity.class);
                    intent.putExtras(jobBundle);

                    tempTxtView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(intent);
                        }
                    });
                    linearLayout.addView(tempTxtView);
//                }
            }


        }

        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private TextView makeJobNameTextView(String name, Activity currentActivity, int backColor, int textColor, int rightArrow, int border){
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

        TextView jobNameView = new TextView(currentActivity);
        jobNameView.setBackgroundColor(backColor);
        jobNameView.setBackgroundResource(border);
        jobNameView.setText(name);
        jobNameView.setTextColor(textColor);
        jobNameView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        jobNameView.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        jobNameView.setPadding(35,0,0,0);
        jobNameView.setGravity(Gravity.CENTER_VERTICAL);
        jobNameView.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightArrow, 0);
        jobNameView.setId(View.generateViewId());
        return jobNameView;
    }
}
