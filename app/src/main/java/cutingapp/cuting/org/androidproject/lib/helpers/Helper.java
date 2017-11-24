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

public class Helper {
    private Context appContext;

    private HashMap<Integer,Employee> employees;
    private HashMap<Integer,Employer> employers;
    private HashMap<Integer,Job> availableJobs;
    private String employeesFile;
    private String employersFile;
    private String availableJobsFile;
    private GsonBuilder gsonBuilder;
    private Gson gson;
    private Type employeeType;
    private Type employerType;
    private Type availableJobsType;

    public Helper(Context appContext, String employeesFile, String employersFile, String availableJobs) {
        this.appContext = appContext;
        this.employeesFile = employeesFile;
        this.employersFile = employersFile;
        this.availableJobsFile = availableJobs;
        this.employees = new HashMap<Integer, Employee>();
        this.employers = new HashMap<Integer, Employer>();
        this.availableJobs = new HashMap<Integer,Job>();
        employeeType = new TypeToken<HashMap<Integer, Employee>>() {
        }.getType();
        employerType = new TypeToken<HashMap<Integer, Employer>>() {
        }.getType();
        availableJobsType = new TypeToken<HashMap<Integer, Job>>() {
        }.getType();

        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Job.class, new JobDeserializer());
        gsonBuilder.registerTypeAdapter(JobType.class, new JobTypeDeserializer());
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
        gsonBuilder.registerTypeAdapter(Location.class, new LocationDeserializer());
        gsonBuilder.registerTypeAdapter(Employee.class, new EmployeeDeserializer());
        gsonBuilder.registerTypeAdapter(Employer.class, new EmployerDeserializer());
        gsonBuilder.registerTypeAdapter(GenderType.class, new GenderTypeDeserializer());
        gson = gsonBuilder.create();
    }

    public void writeToFile(String filename, String data){
        FileOutputStream fOut = null;
        OutputStreamWriter osw = null;

        try{

            fOut = appContext.openFileOutput(filename, Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(fOut);
            osw.write(data);
            osw.flush();

        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        finally {
            try {
                osw.close();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String readFromFile(String file) {

        String ret = null;

        try {
            InputStream inputStream = appContext.openFileInput(file);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("f","File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("f", "Can not read file: " + e.toString());
        }

        return ret;
    }

    public Context getAppContext() {
        return appContext;
    }

    public HashMap<Integer, Employee> getEmployees() {

        return employees;
    }

    public void readEmployees(){
        employees = gson.fromJson(readFromFile(employeesFile),employeeType);
    }

    public void readEmployers(){
        employers = gson.fromJson(readFromFile(employersFile),employerType);
    }

    public void readAvailableJobs(){
        availableJobs = gson.fromJson(readFromFile(availableJobsFile),availableJobsType);
    }
    public HashMap<Integer, Employer> getEmployers() {
        return employers;
    }

    public HashMap<Integer, Job> getAvailableJobs() {
        return availableJobs;
    }

    public void updateAvailableJobs(){
        writeToFile(availableJobsFile,gson.toJson(availableJobs));
    }

    public void updateEmployees(){
        writeToFile(employeesFile,gson.toJson(employees));
    }

    public void updateEmployers(){
        writeToFile(employersFile,gson.toJson(employers));
    }


    public void addAvailableJob(Job  newJob){
        this.availableJobs.put(newJob.getId(),newJob);
    }
    public void removeAvailableJob(int jobIDtoremove){
        this.availableJobs.remove(jobIDtoremove);
    }

    public void removeAvailableJob(Job jobIDtoremove){
        this.availableJobs.remove(jobIDtoremove.getId());
    }
}
