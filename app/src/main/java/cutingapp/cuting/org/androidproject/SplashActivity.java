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

import cutingapp.cuting.org.androidproject.lib.helpers.Helper;
import cutingapp.cuting.org.androidproject.lib.jobs.*;


/**
 * Created by j_md_ on 21/11/2017.
 */

public class SplashActivity extends AppCompatActivity {
    private Context splashContext;
    private GsonBuilder gsonBuilder;
    private Gson gson;
    Helper helperObj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        splashContext = this;
        helperObj = new Helper("employees.json", "employers.json", "available_jobs.json");



        String employees = "{\n" +
                "  \"1\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"haris\",\n" +
                "    \"surname\": \"kozis\",\n" +
                "    \"priv\": 1,\n" +
                "    \"sid\": 2130123,\n" +
                "    \"cid\": 12334534,\n" +
                "    \"email\": \"harisjmd@gmail.com\",\n" +
                "    \"phone_number\": \"99999999\",\n" +
                "    \"gender\": \"MALE\",\n" +
                "    \"skills\": [\n" +
                "      \"videography\",\n" +
                "      \"photography\"\n" +
                "    ],\n" +
                "    \"password\": \"patata\",\n" +
                "    \"applied_jobs\": {\n" +
                "      \"2\": {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"ΟΙΝΟΥΣΑ ΚΥΠΡΟΣ\",\n" +
                "        \"type\": \"photography\",\n" +
                "        \"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "        \"person_in_charge\": \"DIMITRIS TSALTAS\",\n" +
                "        \"start_date\": \"24/11/2017\",\n" +
                "        \"end_date\": \"24/11/2017\",\n" +
                "        \"start_time\": \"17:30\",\n" +
                "        \"end_time\": \"21:00\",\n" +
                "\t\t\"employer_id\": \"1\",\n" +
                "\t\t\"employee_id\": \"1\",\n" +
                "        \"location\": {\n" +
                "          \"loc_name\": \"ΤΑΣΟΣ ΠΑΠΑΔΟΠΟΥΛΟΣ ΑΜΦ1\",\n" +
                "          \"lat\": 34.6754767,\n" +
                "          \"long\": 33.0447799\n" +
                "        },\n" +
                "        \"desc\": \"Η βιντεοσκόπηση όλων των ομιλιών είναι επιθυμητή σε αυτόνομα αρχεία έτσι ώστε να γίνουν υλικό προς χρήση τόσο από το ΚΤΙΣΗΣ όσο και από το Εικονικό Μουσείο Κυπριακών Τροφίμων και Διατροφής\"\n" +
                "      },\n" +
                "      \"3\": {\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"Something Event\",\n" +
                "        \"type\": \"photography\",\n" +
                "        \"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "        \"person_in_charge\": \"DIMITRIS TSALTAS\",\n" +
                "        \"start_date\": \"26/11/2017\",\n" +
                "        \"end_date\": \"26/11/2017\",\n" +
                "        \"start_time\": \"19:00\",\n" +
                "        \"end_time\": \"21:00\",\n" +
                "\t\t\"employer_id\": \"1\",\n" +
                "\t\t\"employee_id\": \"1\",\n" +
                "        \"location\": {\n" +
                "          \"loc_name\": \"Αμφιθέατρο Πεύκιος Γεωργιάδης\",\n" +
                "          \"lat\": 34.6766777,\n" +
                "          \"long\": 33.0423474\n" +
                "        },\n" +
                "        \"desc\": \"Φωτογράφηση και βιντεοσκόπηση της εκδήλωσης (χωρίς ζωντανή μετάδοση)\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"completed_jobs\": {\n" +
                "      \"1\": {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"3rd International Conference & Exhibition on Semiotics and Visual Communication.\",\n" +
                "        \"type\": \"photography\",\n" +
                "        \"organization\": \"Cyprus Semiotic Association\",\n" +
                "        \"person_in_charge\": \"Ευριπίδης Ζαντίδης\",\n" +
                "        \"start_date\": \"03/11/2017\",\n" +
                "        \"end_date\": \"03/11/2017\",\n" +
                "        \"start_time\": \"20:30\",\n" +
                "        \"end_time\": \"22:00\",\n" +
                "\t\t\"employer_id\": \"1\",\n" +
                "\t\t\"employee_id\": \"1\",\n" +
                "        \"location\": {\n" +
                "          \"loc_name\": \"Tasos Papadopoulos, Amphithatre 1 (001) ground floor\",\n" +
                "          \"lat\": 54.65465,\n" +
                "          \"long\": 54.5466\n" +
                "        },\n" +
                "        \"desc\": \"video recording 2 speeches and poster exhibition opening\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "\n" +
                "}";


        helperObj.writeToFile(getApplicationContext(),"employees.json", employees);
        helperObj.readEmployees(getApplicationContext());


        String available_jobs = "{\n" +
                "\t\"1\":{\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"name\": \"Training Program: \\\"Mastering Communication\\\"\",\n" +
                "\t\t\"type\": \"videography\",\n" +
                "\t\t\"organization\": \"CUT Υπηρεσία Ανθρώπινου Δυναμικού\",\n" +
                "\t\t\"person_in_charge\": \"konstantia kousoulou\",\n" +
                "\t\t\"start_date\": \"22/11/2017\",\n" +
                "\t\t\"end_date\": \"22/11/2017\",\n" +
                "\t\t\"start_time\": \"9:30\",\n" +
                "\t\t\"end_time\": \"11:30\",\n" +
                "\t\t\"employer_id\": \"1\",\n" +
                "\t\t\"employee_id\": null,\n" +
                "\t\t\"location\": {\n" +
                "\t\t\t\"loc_name\": \"Poseidonia Beach Hotel\",\n" +
                "\t\t\t\"lat\": 54.65465,\n" +
                "\t\t\t\"long\": 54.5466\n" +
                "\t\t},\n" +
                "\t\t\"desc\": \" μονόλεπτης παρουσίασης για κάθε συνάδελφο. Όλες οι παρουσιάσεις θα μεταφερθούν από την sd card σε laptop για να σχολιαστούν από τους συμμετέχοντες\"\n" +
                "\t}\n" +
                "}";

        String employers = "{\n" +
                "  \"1\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"charalampos\",\n" +
                "    \"surname\": \"kozis\",\n" +
                "    \"priv\": 1,\n" +
                "    \"cid\": 12334534,\n" +
                "    \"email\": \"patatas@gmail.com\",\n" +
                "    \"phone_number\":\"1023910\",\n" +
                "    \"gender\": \"MALE\",\n" +
                "    \"organization\": \"asdasd\",\n" +
                "    \"password\": \"patata\",\n" +
                "    \"pending_jobs\": {\n" +
                "    \n" +
                "      \"2\": {\n" +
                "\t    \"id\": 2,\n" +
                "        \"name\": \"ΟΙΝΟΥΣΑ ΚΥΠΡΟΣ\",\n" +
                "        \"type\": \"photography\",\n" +
                "        \"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "        \"person_in_charge\": \"DIMITRIS TSALTAS\",\n" +
                "        \"start_date\": \"24/11/2017\",\n" +
                "        \"end_date\": \"24/11/2017\",\n" +
                "        \"start_time\": \"17:30\",\n" +
                "        \"end_time\": \"21:00\",\n" +
                "\t\t\"employer_id\": \"1\",\n" +
                "\t\t\"employee_id\": \"1\",\n" +
                "        \"location\": {\n" +
                "          \"loc_name\": \"ΤΑΣΟΣ ΠΑΠΑΔΟΠΟΥΛΟΣ ΑΜΦ1\",\n" +
                "          \"lat\": 34.6754767,\n" +
                "          \"long\": 33.0447799\n" +
                "        },\n" +
                "        \"desc\": \"Η βιντεοσκόπηση όλων των ομιλιών είναι επιθυμητή σε αυτόνομα αρχεία έτσι ώστε να γίνουν υλικό προς χρήση τόσο από το ΚΤΙΣΗΣ όσο και από το Εικονικό Μουσείο Κυπριακών Τροφίμων και Διατροφής\"\n" +
                "      },\n" +
                "      \"3\": {\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"Something Event\",\n" +
                "        \"type\": \"photography\",\n" +
                "        \"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "        \"person_in_charge\": \"DIMITRIS TSALTAS\",\n" +
                "        \"start_date\": \"26/11/2017\",\n" +
                "        \"end_date\": \"26/11/2017\",\n" +
                "        \"start_time\": \"19:00\",\n" +
                "        \"end_time\": \"21:00\",\n" +
                "\t\t\"employer_id\": \"1\",\n" +
                "\t\t\"employee_id\"= \"1\",\n" +
                "        \"location\": {\n" +
                "          \"loc_name\": \"Αμφιθέατρο Πεύκιος Γεωργιάδης\",\n" +
                "          \"lat\": 34.6766777,\n" +
                "          \"long\": 33.0423474\n" +
                "        },\n" +
                "        \"desc\": \"Φωτογράφηση και βιντεοσκόπηση της εκδήλωσης (χωρίς ζωντανή μετάδοση)\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"completed_jobs\": {\n" +
                "      \"1\": {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"3rd International Conference & Exhibition on Semiotics and Visual Communication.\",\n" +
                "        \"type\": \"photography\",\n" +
                "        \"organization\": \"Cyprus Semiotic Association\",\n" +
                "        \"person_in_charge\": \"Ευριπίδης Ζαντίδης\",\n" +
                "        \"start_date\": \"03/11/2017\",\n" +
                "        \"end_date\": \"03/11/2017\",\n" +
                "        \"start_time\": \"20:30\",\n" +
                "        \"end_time\": \"22:00\",\n" +
                "\t\t\"employer_id\": \"1\",\n" +
                "\t\t\"employee_id\": \"1\",\n" +
                "        \"location\": {\n" +
                "          \"loc_name\": \"Tasos Papadopoulos, Amphithatre 1 (001) ground floor\",\n" +
                "          \"lat\": 54.65465,\n" +
                "          \"long\": 54.5466\n" +
                "        },\n" +
                "        \"desc\": \"video recording 2 speeches and poster exhibition opening\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";


        helperObj.writeToFile(getApplicationContext(),"employers.json",employers);
        helperObj.readEmployers(getApplicationContext());
        helperObj.writeToFile(getApplicationContext(),"available_jobs.json", available_jobs);
        helperObj.readAvailableJobs(getApplicationContext());

        final Bundle jobData = new Bundle();
        jobData.putSerializable("helper",helperObj);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashContext, LoginActivity.class);
                intent.putExtras(jobData);
                startActivity(intent);
                finish();
            }
        }, 1500);


    }


}
