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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import cutingapp.cuting.org.androidproject.lib.jobs.Job;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpcomingJobsViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpcomingJobsViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpcomingJobsViewFragment extends Fragment {

    // TODO: Rename and change types of parameters
    ArrayList<Job> jobs;


    private OnFragmentInteractionListener mListener;

    public UpcomingJobsViewFragment() {
        // Required empty public constructor
        jobs = new ArrayList<>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment UpcomingJobsViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingJobsViewFragment newInstance(ArrayList<Job> jobsToShow) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("jobs", jobsToShow);
        UpcomingJobsViewFragment fragment = new UpcomingJobsViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            jobs = (ArrayList<Job>) getArguments().getSerializable("jobs");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_upcoming_jobs_view, container, false);
        LinearLayout linearLayout = (LinearLayout)rootView.findViewById(R.id.jobs);

        SimpleDateFormat timef = new SimpleDateFormat("HH:mm", Locale.getDefault());
        int numJobs = jobs.size();
        Log.println(Log.DEBUG,"NONE","NumJobs = " + numJobs);
        int backColor;
        int textColor;
        String txtViewName = "";
        if(numJobs > 0){
            backColor = Color.parseColor("#f1f1f3"); // light grey
            textColor = Color.parseColor("#3c3f41"); //grey
            Date now = (Calendar.getInstance()).getTime();
            for(int i = 0; i < numJobs; i++){
                Job tempJob = jobs.get(i);
                if(tempJob.getStartDate().after(now) || tempJob.getStartTime().after(now)) {
                    txtViewName = tempJob.getName() + " at " + timef.format(tempJob.getStartTime()) + " - " + timef.format(tempJob.getEndTime());
                    TextView tempTxtView = makeJobNameTextView(txtViewName, getActivity(), backColor, textColor, R.drawable.goto_job_arrow, R.drawable.bottom_border);
                    Bundle jobBundle = new Bundle();
                    jobBundle.putSerializable("job_obj", tempJob);
                    final Intent intent = new Intent(this.getContext(), JobViewActivity.class);
                    intent.putExtras(jobBundle);

                    tempTxtView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(intent);
                        }
                    });
                    linearLayout.addView(tempTxtView);
                }
            }
            if(linearLayout.getChildCount() == 0){
                backColor = Color.parseColor("#d3c1c1c1"); // darker grey
                textColor = Color.parseColor("#3c3f41"); //grey
                linearLayout.addView(makeNothingTextView("Nothing Scheduled",getActivity(),backColor,textColor));
            }

        }
        else{

            backColor = Color.parseColor("#d3c1c1c1"); // darker grey
            textColor = Color.parseColor("#3c3f41"); //grey
            TextView tempTxtView = makeNothingTextView("Nothing Scheduled",getActivity(),backColor,textColor);
            linearLayout.addView(tempTxtView);
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
        jobNameView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        jobNameView.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,160));
        jobNameView.setPadding(35,0,0,0);
        jobNameView.setGravity(Gravity.CENTER_VERTICAL);
        jobNameView.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightArrow, 0);
        jobNameView.setId(View.generateViewId());
        return jobNameView;
    }


    private TextView makeNothingTextView(String name, Activity currentActivity, int backColor, int textColor){
        /**
         *   <TextView
         android:id="@+id/nothing_to_show"
         android:layout_width="match_parent"
         android:layout_height="150dp"
         android:background="@color/light_grey"
         android:gravity="center"
         android:text="@string/nothing_scheduled"
         android:textColor="@color/grey"
         android:textSize="16sp" />
         *
         * */

        TextView jobNameView = new TextView(currentActivity);
        jobNameView.setBackgroundColor(backColor);
        jobNameView.setText(name);
        jobNameView.setTextColor(textColor);
        jobNameView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        jobNameView.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,400));
        jobNameView.setGravity(Gravity.CENTER);
        jobNameView.setId(View.generateViewId());
        return jobNameView;
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
}
