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
                "\t\t\t\"name\": \"Training Program: \\\"Mastering Communication\\\"\",\n" +
                "\t\t\t\"type\": \"videography\",\n" +
                "\t\t\t\"organization\": \"CUT Υπηρεσία Ανθρώπινου Δυναμικού\",\n" +
                "\t\t\t\"person_in_charge\": \"konstantia kousoulou\",\n" +
                "\t\t\t\"start_date\": \"22/11/2017\",\n" +
                "\t\t\t\"end_date\": \"22/11/2017\",\n" +
                "\t\t\t\"start_time\": \"9:30\",\n" +
                "\t\t\t\"end_time\": \"11:30\",\n" +
                "\t\t\t\"location\": {\n" +
                "\t\t\t\t\"loc_name\": \"Poseidonia Beach Hotel\",\n" +
                "\t\t\t\t\"lat\": 54.65465,\n" +
                "\t\t\t\t\"long\": 54.5466\n" +
                "\t\t\t},\n" +
                "\t\t\t\"desc\": \" μονόλεπτης παρουσίασης για κάθε συνάδελφο. Όλες οι παρουσιάσεις θα μεταφερθούν από την sd card σε laptop για να σχολιαστούν από τους συμμετέχοντες\"\n" +
                "\t\t\t\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\":2,\n" +
                "\t\t\t\"name\": \"ΟΙΝΟΥΣΑ ΚΥΠΡΟΣ\",\n" +
                "\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\"person_in_charge\": \"DIMITRIS TSALTAS\",\n" +
                "\t\t\t\"start_date\": \"24/11/2017\",\n" +
                "\t\t\t\"end_date\": \"24/11/2017\",\n" +
                "\t\t\t\"start_time\": \"17:30\",\n" +
                "\t\t\t\"end_time\": \"21:00\",\n" +
                "\t\t\t\"location\": {\n" +
                "\t\t\t\t\"loc_name\": \"ΤΑΣΟΣ ΠΑΠΑΔΟΠΟΥΛΟΣ ΑΜΦ1\",\n" +
                "\t\t\t\t\"lat\": 34.6754767,\n" +
                "\t\t\t\t\"long\": 33.0447799\n" +
                "\t\t\t},\n" +
                "\t\t\t\"desc\": \"Η βιντεοσκόπηση όλων των ομιλιών είναι επιθυμητή σε αυτόνομα αρχεία έτσι ώστε να γίνουν υλικό προς χρήση τόσο από το ΚΤΙΣΗΣ όσο και από το Εικονικό Μουσείο Κυπριακών Τροφίμων και Διατροφής\"\n" +
                "\t\t\t\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\":3,\n" +
                "\t\t\t\"name\": \"Something Event\",\n" +
                "\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\"person_in_charge\": \"DIMITRIS TSALTAS\",\n" +
                "\t\t\t\"start_date\": \"26/11/2017\",\n" +
                "\t\t\t\"end_date\": \"26/11/2017\",\n" +
                "\t\t\t\"start_time\": \"19:00\",\n" +
                "\t\t\t\"end_time\": \"21:00\",\n" +
                "\t\t\t\"location\": {\n" +
                "\t\t\t\t\"loc_name\": \"Αμφιθέατρο Πεύκιος Γεωργιάδης\",\n" +
                "\t\t\t\t\"lat\": 34.6766777,\n" +
                "\t\t\t\t\"long\": 33.0423474\n" +
                "\t\t\t},\n" +
                "\t\t\t\"desc\": \"Φωτογράφηση και βιντεοσκόπηση της εκδήλωσης (χωρίς ζωντανή μετάδοση)\"\n" +
                "\t\t\t\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\n" +
                "\t\"completed_jobs\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"id\":1,\n" +
                "\t\t\t\"name\": \"3rd International Conference & Exhibition on Semiotics and Visual Communication.\",\n" +
                "\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\"organization\": \"Cyprus Semiotic Association\",\n" +
                "\t\t\t\"person_in_charge\": \"Ευριπίδης Ζαντίδης\",\n" +
                "\t\t\t\"start_date\": \"03/11/2017\",\n" +
                "\t\t\t\"end_date\": \"03/11/2017\",\n" +
                "\t\t\t\"start_time\": \"20:30\",\n" +
                "\t\t\t\"end_time\": \"22:00\",\n" +
                "\t\t\t\"location\": {\n" +
                "\t\t\t\t\"loc_name\": \"Tasos Papadopoulos, Amphithatre 1 (001) ground floor\",\n" +
                "\t\t\t\t\"lat\": 54.65465,\n" +
                "\t\t\t\t\"long\": 54.5466\n" +
                "\t\t\t},\n" +
                "\t\t\t\"desc\": \"video recording 2 speeches and poster exhibition opening\"\n" +
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

        String ret = null;

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
