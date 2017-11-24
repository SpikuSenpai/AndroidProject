package cutingapp.cuting.org.androidproject.lib.helpers;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import cutingapp.cuting.org.androidproject.lib.jobs.DateDeserializer;
import cutingapp.cuting.org.androidproject.lib.jobs.Job;
import cutingapp.cuting.org.androidproject.lib.jobs.JobDeserializer;
import cutingapp.cuting.org.androidproject.lib.jobs.JobType;
import cutingapp.cuting.org.androidproject.lib.jobs.JobTypeDeserializer;
import cutingapp.cuting.org.androidproject.lib.jobs.Location;
import cutingapp.cuting.org.androidproject.lib.jobs.LocationDeserializer;
import cutingapp.cuting.org.androidproject.lib.jobs.TimeDeserializer;
import cutingapp.cuting.org.androidproject.lib.user.Employee;
import cutingapp.cuting.org.androidproject.lib.user.EmployeeDeserializer;
import cutingapp.cuting.org.androidproject.lib.user.Employer;
import cutingapp.cuting.org.androidproject.lib.user.EmployerDeserializer;
import cutingapp.cuting.org.androidproject.lib.user.GenderType;
import cutingapp.cuting.org.androidproject.lib.user.GenderTypeDeserializer;

/**
 * Created by j_md_ on 24/11/2017.
 */

public class Helper implements Serializable{

    private HashMap<String, Employee> employees;
    private HashMap<String, Employer> employers;
    private HashMap<String, Job> availableJobs;
    private String employeesFile;
    private String employersFile;
    private String availableJobsFile;


    public Helper( String employeesFile, String employersFile, String availableJobs) {
        this.employeesFile = employeesFile;
        this.employersFile = employersFile;
        this.availableJobsFile = availableJobs;
        this.employees = new HashMap<String, Employee>();
        this.employers = new HashMap<String, Employer>();
        this.availableJobs = new HashMap<String, Job>();

    }

    public void writeToFile(Context context, String filename, String data) {
        FileOutputStream fOut = null;
        OutputStreamWriter osw = null;

        try {

            fOut = context.openFileOutput(filename, Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(fOut);
            osw.write(data);
            osw.flush();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                osw.close();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readFromFile(Context context, String file) {

        String ret = null;

        try {
            InputStream inputStream = context.openFileInput(file);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("f", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("f", "Can not read file: " + e.toString());
        }

        return ret;
    }



    public HashMap<String, Employee> getEmployees() {

        return employees;
    }

    public void readEmployees(Context context) {
        Type employeeType = new TypeToken<HashMap<String, Employee>>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Job.class, new JobDeserializer());
        gsonBuilder.registerTypeAdapter(JobType.class, new JobTypeDeserializer());
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
        gsonBuilder.registerTypeAdapter(Location.class, new LocationDeserializer());
        gsonBuilder.registerTypeAdapter(Employee.class, new EmployeeDeserializer());
        gsonBuilder.registerTypeAdapter(GenderType.class, new GenderTypeDeserializer());
        Gson gson = gsonBuilder.create();

        employees = gson.fromJson(readFromFile(context,employeesFile), employeeType);
    }

    public void readEmployers(Context context) {

        Type  employerType = new TypeToken<HashMap<String, Employer>>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Job.class, new JobDeserializer());
        gsonBuilder.registerTypeAdapter(JobType.class, new JobTypeDeserializer());
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
        gsonBuilder.registerTypeAdapter(Location.class, new LocationDeserializer());
        gsonBuilder.registerTypeAdapter(Employer.class, new EmployerDeserializer());
        gsonBuilder.registerTypeAdapter(GenderType.class, new GenderTypeDeserializer());
        Gson gson = gsonBuilder.create();
        employers = gson.fromJson(readFromFile(context,employersFile), employerType);
    }

    public void readAvailableJobs(Context context ) {
        Type  availableJobsType = new TypeToken<HashMap<String, Job>>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Job.class, new JobDeserializer());
        gsonBuilder.registerTypeAdapter(JobType.class, new JobTypeDeserializer());
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
        gsonBuilder.registerTypeAdapter(Location.class, new LocationDeserializer());
        Gson gson = gsonBuilder.create();
        availableJobs = gson.fromJson(readFromFile(context,availableJobsFile), availableJobsType);
    }

    public HashMap<String, Employer> getEmployers() {
        return employers;
    }

    public HashMap<String, Job> getAvailableJobs() {
        return availableJobs;
    }

    public void updateAvailableJobs(Context context) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        writeToFile(context,availableJobsFile, gson.toJson(availableJobs));
    }

    public void updateEmployees(Context context) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        writeToFile(context,employeesFile, gson.toJson(employees));
    }

    public void updateEmployers(Context context) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        writeToFile(context, employersFile, gson.toJson(employers));
    }


    public void addAvailableJob(Job newJob) {
        this.availableJobs.put(String.valueOf(newJob.getId()), newJob);
    }

    public void removeAvailableJob(int jobIDtoremove) {
        this.availableJobs.remove(String.valueOf(jobIDtoremove));
    }

    public void removeAvailableJob(Job jobIDtoremove) {
        this.availableJobs.remove(String.valueOf(jobIDtoremove.getId()));
    }
}
