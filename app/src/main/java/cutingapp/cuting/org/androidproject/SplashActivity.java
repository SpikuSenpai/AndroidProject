package cutingapp.cuting.org.androidproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import cutingapp.cuting.org.androidproject.lib.jobs.*;


/**
 * Created by j_md_ on 21/11/2017.
 */

public class SplashActivity extends AppCompatActivity {
    private Context splashContext;
    private GsonBuilder gsonBuilder;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        splashContext = this;
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Job.class, new JobDeserializer());
        gsonBuilder.registerTypeAdapter(JobType.class, new JobTypeDeserializer());
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
        gsonBuilder.registerTypeAdapter(Location.class, new LocationDeserializer());
        gson = gsonBuilder.create();

        String jsonData = "{\n" +
                "\t\"applied_jobs\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"id\":1,\n" +
                "\t\t\t\"name\": \"Event Ktisis\",\n" +
                "\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\"start_date\": \"23/11/2017\",\n" +
                "\t\t\t\"end_date\": \"23/11/2017\",\n" +
                "\t\t\t\"start_time\": \"10:00\",\n" +
                "\t\t\t\"end_time\": \"11:00\",\n" +
                "\t\t\t\"location\": {\n" +
                "\t\t\t\t\"loc_name\": \"ktirio tepak\",\n" +
                "\t\t\t\t\"lat\": 5465465,\n" +
                "\t\t\t\t\"long\": 545466\n" +
                "\t\t\t},\n" +
                "\t\t\t\"desc\": \"blabalabalbalbal\"\n" +
                "\t\t\t\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\":2,\n" +
                "\t\t\t\"name\": \"TEmp Ktisis\",\n" +
                "\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\"start_date\": \"20/12/2017\",\n" +
                "\t\t\t\"end_date\": \"20/12/2017\",\n" +
                "\t\t\t\"start_time\": \"10:00\",\n" +
                "\t\t\t\"end_time\": \"11:00\",\n" +
                "\t\t\t\"location\": {\n" +
                "\t\t\t\t\"loc_name\": \"ktirio tepak\",\n" +
                "\t\t\t\t\"lat\": 5465465,\n" +
                "\t\t\t\t\"long\": 545466\n" +
                "\t\t\t},\n" +
                "\t\t\t\"desc\": \"blabalabalbalbal\"\n" +
                "\t\t\t\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\":3,\n" +
                "\t\t\t\"name\": \"Something Event\",\n" +
                "\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\"start_date\": \"21/11/2017\",\n" +
                "\t\t\t\"end_date\": \"21/11/2017\",\n" +
                "\t\t\t\"start_time\": \"10:00\",\n" +
                "\t\t\t\"end_time\": \"11:00\",\n" +
                "\t\t\t\"location\": {\n" +
                "\t\t\t\t\"loc_name\": \"ktirio tepak\",\n" +
                "\t\t\t\t\"lat\": 5465465,\n" +
                "\t\t\t\t\"long\": 545466\n" +
                "\t\t\t},\n" +
                "\t\t\t\"desc\": \"blabalabalbalbal\"\n" +
                "\t\t\t\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\n" +
                "\t\"completed_jobs\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"id\":1,\n" +
                "\t\t\t\"name\": \"Event Ktisis\",\n" +
                "\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\"start_date\": \"20/03/2017\",\n" +
                "\t\t\t\"end_date\": \"20/03/2017\",\n" +
                "\t\t\t\"start_time\": \"10:00\",\n" +
                "\t\t\t\"end_time\": \"11:00\",\n" +
                "\t\t\t\"location\": {\n" +
                "\t\t\t\t\"loc_name\": \"ktirio tepak\",\n" +
                "\t\t\t\t\"lat\": 5465465,\n" +
                "\t\t\t\t\"long\": 545466\n" +
                "\t\t\t},\n" +
                "\t\t\t\"desc\": \"blabalabalbalbal\"\n" +
                "\t\t\t\n" +
                "\t\t}\n" +
                "\t]\n" +
                "\n" +
                "}";
        writeToFile("jobs.json",jsonData);

        Type hashlistype = new TypeToken<HashMap<String, ArrayList<Job>>>() {
        }.getType();

        String jobsdata = readFromFile("jobs.json");

        HashMap<String, ArrayList<Job>> jobs = gson.fromJson(jobsdata, hashlistype);
        final Bundle jobData = new Bundle();
        jobData.putSerializable("jobs",jobs);

        /** dame tha checkarume an iparxi file g t user*/





        /**********************************************/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashContext, HomeActivity.class);
                intent.putExtras(jobData);
                startActivity(intent);
                finish();
            }
        }, 1500);


    }

    private void writeToFile(String filename, String data){
        FileOutputStream fOut = null;
        OutputStreamWriter osw = null;

        try{

            fOut = getApplicationContext().openFileOutput(filename,Context.MODE_PRIVATE);
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
    private String readFromFile(String file) {

        String ret = "";

        try {
            InputStream inputStream = getApplicationContext().openFileInput(file);

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
}
