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
                "\t\"1\": {\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"name\": \"haris\",\n" +
                "\t\t\"surname\": \"kozis\",\n" +
                "\t\t\"priv\": 0,\n" +
                "\t\t\"sid\": 2130123,\n" +
                "\t\t\"cid\": 12334534,\n" +
                "\t\t\"email\": \"employee@gmail.com\",\n" +
                "\t\t\"phone_number\": \"99999999\",\n" +
                "\t\t\"gender\": \"MALE\",\n" +
                "\t\t\"skills\": [\n" +
                "\t\t\t\"videography\",\n" +
                "\t\t\t\"photography\"\n" +
                "\t\t],\n" +
                "\t\t\"password\": \"123456789\",\n" +
                "\t\t\"applied_jobs\": {\n" +
                "\t\t\t\"100\": {\n" +
                "\t\t\t\t\"id\": 100,\n" +
                "\t\t\t\t\"name\": \"ΠΑΡΟΥΣΙΑΣΗ ΒΙΒΛΙΟΥ\",\n" +
                "\t\t\t\t\"type\": \"videography\",\n" +
                "\t\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\t\"person_in_charge\": \"ALEX PAPAKOSTAS\",\n" +
                "\t\t\t\t\"start_date\": \"28/11/2017\",\n" +
                "\t\t\t\t\"end_date\": \"28/11/2017\",\n" +
                "\t\t\t\t\"start_time\": \"20:00\",\n" +
                "\t\t\t\t\"end_time\": \"21:00\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"rating\": null,\n" +
                "\t\t\t\t\"employee_id\": \"1\",\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"ΤΑΣΟΣ ΠΑΠΑΔΟΠΟΥΛΟΣ ΑΜΦ1\",\n" +
                "\t\t\t\t\t\"lat\": 34.6754767,\n" +
                "\t\t\t\t\t\"long\": 33.0447799\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"Η βιντεοσκόπηση όλων των ομιλιών είναι επιθυμητή σε αυτόνομα αρχεία έτσι ώστε να γίνουν υλικό προς χρήση τόσο από το ΚΤΙΣΗΣ όσο και από το Εικονικό Μουσείο Κυπριακών Τροφίμων και Διατροφής\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"101\": {\n" +
                "\t\t\t\t\"id\": 101,\n" +
                "\t\t\t\t\"name\": \"ΦΩΤΟΓΡΑΦΗΣΗ ΠΡΟΣΩΠΙΚΟΥ\",\n" +
                "\t\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\t\"person_in_charge\": \"KOSTAS GEORGIOY\",\n" +
                "\t\t\t\t\"start_date\": \"29/11/2017\",\n" +
                "\t\t\t\t\"end_date\": \"29/11/2017\",\n" +
                "\t\t\t\t\"start_time\": \"10:00\",\n" +
                "\t\t\t\t\"end_time\": \"16:00\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"employee_id\": \"1\",\n" +
                "\t\t\t\t\"rating\": null,\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"Αμφιθέατρο Πεύκιος Γεωργιάδης\",\n" +
                "\t\t\t\t\t\"lat\": 34.6766777,\n" +
                "\t\t\t\t\t\"long\": 33.0423474\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"Φωτογράφηση Προσωπικού Τεχνολογικού Πανεπιστημίου Κύπρου\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"102\": {\n" +
                "\t\t\t\t\"id\": 102,\n" +
                "\t\t\t\t\"name\": \"ΟΜΙΛΙΑ ΓΙΑ ΕΠΑΓΓΕΛΑΜΤΙΚΗ ΑΠΟΚΑΤΑΣΤΑΣΗ\",\n" +
                "\t\t\t\t\"type\": \"videography\",\n" +
                "\t\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\t\"person_in_charge\": \"ANTREOU ANTREAS\",\n" +
                "\t\t\t\t\"start_date\": \"30/11/2017\",\n" +
                "\t\t\t\t\"end_date\": \"30/11/2017\",\n" +
                "\t\t\t\t\"start_time\": \"17:30\",\n" +
                "\t\t\t\t\"end_time\": \"19:30\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"employee_id\": \"1\",\n" +
                "\t\t\t\t\"rating\": null,\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"ΤΑΣΟΣ ΠΑΠΑΔΟΠΟΥΛΟΣ ΑΜΦ1\",\n" +
                "\t\t\t\t\t\"lat\": 34.6754767,\n" +
                "\t\t\t\t\t\"long\": 33.0447799\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"Η βιντεοσκόπηση όλων των ομιλιών είναι επιθυμητή σε αυτόνομα αρχεία έτσι ώστε να γίνουν υλικό προς χρήση τόσο από το ΚΤΙΣΗΣ όσο και από το Εικονικό Μουσείο Κυπριακών Τροφίμων και Διατροφής\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"completed_jobs\": {\n" +
                "\t\t\t\"201\": {\n" +
                "\t\t\t\t\"id\": 201,\n" +
                "\t\t\t\t\"name\": \"3rd International Conference & Exhibition on Semiotics and Visual Communication.\",\n" +
                "\t\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\t\"organization\": \"Cyprus Semiotic Association\",\n" +
                "\t\t\t\t\"person_in_charge\": \"Ευριπίδης Ζαντίδης\",\n" +
                "\t\t\t\t\"start_date\": \"03/11/2017\",\n" +
                "\t\t\t\t\"end_date\": \"03/11/2017\",\n" +
                "\t\t\t\t\"start_time\": \"20:30\",\n" +
                "\t\t\t\t\"end_time\": \"22:00\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"employee_id\": \"1\",\n" +
                "\t\t\t\t\"rating\": 6,\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"Tasos Papadopoulos, Amphithatre 1 (001) ground floor\",\n" +
                "\t\t\t\t\t\"lat\": 54.65465,\n" +
                "\t\t\t\t\t\"long\": 54.5466\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"video recording 2 speeches and poster exhibition opening\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"202\": {\n" +
                "\t\t\"id\": 201,\n" +
                "\t\t\"name\": \"ΠΑΡΟΥΣΙΑΣΗ ΠΤΥΧΙΑΚΩΝ ΕΡΓΑΣΙΩΝ\",\n" +
                "\t\t\"type\": \"photography\",\n" +
                "\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\"person_in_charge\": \"ΙΩΑΝΝΗΣ ΙΩΑΝΝΟΥ\",\n" +
                "\t\t\"start_date\": \"15/11/2017\",\n" +
                "\t\t\"end_date\": \"15/11/2017\",\n" +
                "\t\t\"start_time\": \"10:30\",\n" +
                "\t\t\"end_time\": \"14:00\",\n" +
                "\t\t\"employer_id\": \"1\",\n" +
                "\t\t\"employee_id\": \"1\",\n" +
                "\t\t\"rating\": 6,\n" +
                "\t\t\"location\": {\n" +
                "\t\t\t\"loc_name\": \"Tasos Papadopoulos, Amphithatre 1 (001) ground floor\",\n" +
                "\t\t\t\"lat\": 54.65465,\n" +
                "\t\t\t\"long\": 54.5466\n" +
                "\t\t},\n" +
                "\t\t\"desc\": \"video recording 2 speeches and poster exhibition opening\"\n" +
                "\t}\n" +
                "\t\t\t\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\t\n" +
                "\n" +
                "\n" +
                "}";


        helperObj.writeToFile(getApplicationContext(),"employees.json", employees);
        helperObj.readEmployees(getApplicationContext());


        String available_jobs = "{\n" +
                "\t      \"2\": {\n" +
                "\t    \"id\": 2,\n" +
                "        \"name\": \"ΟΙΝΟΥΣΑ ΚΥΠΡΟΣ\",\n" +
                "        \"type\": \"photography\",\n" +
                "        \"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "        \"person_in_charge\": \"DIMITRIS TSALTAS\",\n" +
                "        \"start_date\": \"24/12/2017\",\n" +
                "        \"end_date\": \"24/12/2017\",\n" +
                "        \"start_time\": \"17:30\",\n" +
                "        \"end_time\": \"21:00\",\n" +
                "\t\t\"employer_id\": \"1\",\n" +
                "\t\t\"employee_id\": null,\n" +
                "        \"location\": {\n" +
                "          \"loc_name\": \"ΤΑΣΟΣ ΠΑΠΑΔΟΠΟΥΛΟΣ ΑΜΦ1\",\n" +
                "          \"lat\": 34.6754767,\n" +
                "          \"long\": 33.0447799\n" +
                "        },\n" +
                "        \"desc\": \"Η βιντεοσκόπηση όλων των ομιλιών είναι επιθυμητή σε αυτόνομα αρχεία έτσι ώστε να γίνουν υλικό προς χρήση τόσο από το ΚΤΙΣΗΣ όσο και από το Εικονικό Μουσείο Κυπριακών Τροφίμων και Διατροφής\",\n" +
                "\t\t\"rating\": null\n" +
                "\t  }\n" +
                "\t\n" +
                "}";

        String employers = "{\n" +
                "\t\"1\": {\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"name\": \"charalampos\",\n" +
                "\t\t\"surname\": \"kozis\",\n" +
                "\t\t\"priv\": 1,\n" +
                "\t\t\"cid\": 12334534,\n" +
                "\t\t\"email\": \"employer@gmail.com\",\n" +
                "\t\t\"phone_number\": \"1023910\",\n" +
                "\t\t\"gender\": \"MALE\",\n" +
                "\t\t\"organization\": \"asdasd\",\n" +
                "\t\t\"password\": \"123456789\",\n" +
                "\t\t\"created_jobs\": {\n" +
                "\t\t\t\"2\": {\n" +
                "\t\t\t\t\"id\": 2,\n" +
                "\t\t\t\t\"name\": \"ΟΙΝΟΥΣΑ ΚΥΠΡΟΣ\",\n" +
                "\t\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\t\"person_in_charge\": \"DIMITRIS TSALTAS\",\n" +
                "\t\t\t\t\"start_date\": \"27/11/2017\",\n" +
                "\t\t\t\t\"end_date\": \"27/11/2017\",\n" +
                "\t\t\t\t\"start_time\": \"17:30\",\n" +
                "\t\t\t\t\"end_time\": \"21:00\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"employee_id\": null,\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"ΤΑΣΟΣ ΠΑΠΑΔΟΠΟΥΛΟΣ ΑΜΦ1\",\n" +
                "\t\t\t\t\t\"lat\": 34.6754767,\n" +
                "\t\t\t\t\t\"long\": 33.0447799\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"Η βιντεοσκόπηση όλων των ομιλιών είναι επιθυμητή σε αυτόνομα αρχεία έτσι ώστε να γίνουν υλικό προς χρήση τόσο από το ΚΤΙΣΗΣ όσο και από το Εικονικό Μουσείο Κυπριακών Τροφίμων και Διατροφής\",\n" +
                "\t\t\t\t\"rating\": null\n" +
                "\t\t\t},\n" +
                "\n" +
                "\t\t\t\"3\": {\n" +
                "\t\t\t\t\"id\": 3,\n" +
                "\t\t\t\t\"name\": \"ΓΑΛΑΤΑ\",\n" +
                "\t\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\t\"person_in_charge\": \"HAYIANNIS CHRISTODOULOS\",\n" +
                "\t\t\t\t\"start_date\": \"28/11/2017\",\n" +
                "\t\t\t\t\"end_date\": \"28/11/2017\",\n" +
                "\t\t\t\t\"start_time\": \"9:00\",\n" +
                "\t\t\t\t\"end_time\": \"12:00\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"employee_id\": null,\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"ΤΑΣΟΣ ΠΑΠΑΔΟΠΟΥΛΟΣ ΑΜΦ1\",\n" +
                "\t\t\t\t\t\"lat\": 34.6754767,\n" +
                "\t\t\t\t\t\"long\": 33.0447799\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"Η βιντεοσκόπηση όλων των ομιλιών είναι επιθυμητή σε αυτόνομα αρχεία έτσι ώστε να γίνουν υλικό προς χρήση τόσο από το ΚΤΙΣΗΣ όσο και από το Εικονικό Μουσείο Κυπριακών Τροφίμων και Διατροφής\",\n" +
                "\t\t\t\t\"rating\": null\n" +
                "\t\t\t},\n" +
                "\n" +
                "\n" +
                "\t\t\t\"4\": {\n" +
                "\t\t\t\t\"id\": 4,\n" +
                "\t\t\t\t\"name\": \"HACKATHON\",\n" +
                "\t\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\t\"person_in_charge\": \"KOZIS CHARALAMPOS\",\n" +
                "\t\t\t\t\"start_date\": \"29/11/2017\",\n" +
                "\t\t\t\t\"end_date\": \"29/11/2017\",\n" +
                "\t\t\t\t\"start_time\": \"18:30\",\n" +
                "\t\t\t\t\"end_time\": \"20:00\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"employee_id\": null,\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"ΤΑΣΟΣ ΠΑΠΑΔΟΠΟΥΛΟΣ ΑΜΦ1\",\n" +
                "\t\t\t\t\t\"lat\": 34.6754767,\n" +
                "\t\t\t\t\t\"long\": 33.0447799\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"Η βιντεοσκόπηση όλων των ομιλιών είναι επιθυμητή σε αυτόνομα αρχεία έτσι ώστε να γίνουν υλικό προς χρήση τόσο από το ΚΤΙΣΗΣ όσο και από το Εικονικό Μουσείο Κυπριακών Τροφίμων και Διατροφής\",\n" +
                "\t\t\t\t\"rating\": null\n" +
                "\t\t\t},\n" +
                "\t\t\t\"5\": {\n" +
                "\t\t\t\t\"id\": 5,\n" +
                "\t\t\t\t\"name\": \"CYRIX\",\n" +
                "\t\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\t\"person_in_charge\": \"KYRIAKOY CHARALAMPOS\",\n" +
                "\t\t\t\t\"start_date\": \"30/11/2017\",\n" +
                "\t\t\t\t\"end_date\": \"30/11/2017\",\n" +
                "\t\t\t\t\"start_time\": \"11:30\",\n" +
                "\t\t\t\t\"end_time\": \"14:00\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"employee_id\": null,\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"ΤΑΣΟΣ ΠΑΠΑΔΟΠΟΥΛΟΣ ΑΜΦ1\",\n" +
                "\t\t\t\t\t\"lat\": 34.6754767,\n" +
                "\t\t\t\t\t\"long\": 33.0447799\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"Η βιντεοσκόπηση όλων των ομιλιών είναι επιθυμητή σε αυτόνομα αρχεία έτσι ώστε να γίνουν υλικό προς χρήση τόσο από το ΚΤΙΣΗΣ όσο και από το Εικονικό Μουσείο Κυπριακών Τροφίμων και Διατροφής\",\n" +
                "\t\t\t\t\"rating\": null\n" +
                "\t\t\t},\n" +
                "\n" +
                "\t\t\t\"6\": {\n" +
                "\t\t\t\t\"id\": 6,\n" +
                "\t\t\t\t\"name\": \"SPACE UPS\",\n" +
                "\t\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\t\"person_in_charge\": \"ARISTOTELOUS ELENA\",\n" +
                "\t\t\t\t\"start_date\": \"01/12/2017\",\n" +
                "\t\t\t\t\"end_date\": \"01/12/2017\",\n" +
                "\t\t\t\t\"start_time\": \"13:30\",\n" +
                "\t\t\t\t\"end_time\": \"15:30\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"employee_id\": null,\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"ΤΑΣΟΣ ΠΑΠΑΔΟΠΟΥΛΟΣ ΑΜΦ1\",\n" +
                "\t\t\t\t\t\"lat\": 34.6754767,\n" +
                "\t\t\t\t\t\"long\": 33.0447799\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"Η βιντεοσκόπηση όλων των ομιλιών είναι επιθυμητή σε αυτόνομα αρχεία έτσι ώστε να γίνουν υλικό προς χρήση τόσο από το ΚΤΙΣΗΣ όσο και από το Εικονικό Μουσείο Κυπριακών Τροφίμων και Διατροφής\",\n" +
                "\t\t\t\t\"rating\": null\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"pending_jobs\": {\n" +
                "\t\t\t\"100\": {\n" +
                "\t\t\t\t\"id\": 100,\n" +
                "\t\t\t\t\"name\": \"ΠΑΡΟΥΣΙΑΣΗ ΒΙΒΛΙΟΥ\",\n" +
                "\t\t\t\t\"type\": \"videography\",\n" +
                "\t\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\t\"person_in_charge\": \"ALEX PAPAKOSTAS\",\n" +
                "\t\t\t\t\"start_date\": \"28/11/2017\",\n" +
                "\t\t\t\t\"end_date\": \"28/11/2017\",\n" +
                "\t\t\t\t\"start_time\": \"20:00\",\n" +
                "\t\t\t\t\"end_time\": \"21:00\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"rating\": null,\n" +
                "\t\t\t\t\"employee_id\": \"1\",\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"ΤΑΣΟΣ ΠΑΠΑΔΟΠΟΥΛΟΣ ΑΜΦ1\",\n" +
                "\t\t\t\t\t\"lat\": 34.6754767,\n" +
                "\t\t\t\t\t\"long\": 33.0447799\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"Η βιντεοσκόπηση όλων των ομιλιών είναι επιθυμητή σε αυτόνομα αρχεία έτσι ώστε να γίνουν υλικό προς χρήση τόσο από το ΚΤΙΣΗΣ όσο και από το Εικονικό Μουσείο Κυπριακών Τροφίμων και Διατροφής\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"101\": {\n" +
                "\t\t\t\t\"id\": 101,\n" +
                "\t\t\t\t\"name\": \"ΦΩΤΟΓΡΑΦΗΣΗ ΠΡΟΣΩΠΙΚΟΥ\",\n" +
                "\t\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\t\"person_in_charge\": \"KOSTAS GEORGIOY\",\n" +
                "\t\t\t\t\"start_date\": \"29/11/2017\",\n" +
                "\t\t\t\t\"end_date\": \"29/11/2017\",\n" +
                "\t\t\t\t\"start_time\": \"10:00\",\n" +
                "\t\t\t\t\"end_time\": \"16:00\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"employee_id\": \"1\",\n" +
                "\t\t\t\t\"rating\": null,\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"Αμφιθέατρο Πεύκιος Γεωργιάδης\",\n" +
                "\t\t\t\t\t\"lat\": 34.6766777,\n" +
                "\t\t\t\t\t\"long\": 33.0423474\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"Φωτογράφηση Προσωπικού Τεχνολογικού Πανεπιστημίου Κύπρου\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"102\": {\n" +
                "\t\t\t\t\"id\": 102,\n" +
                "\t\t\t\t\"name\": \"ΟΜΙΛΙΑ ΓΙΑ ΕΠΑΓΓΕΛΑΜΤΙΚΗ ΑΠΟΚΑΤΑΣΤΑΣΗ\",\n" +
                "\t\t\t\t\"type\": \"videography\",\n" +
                "\t\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\t\"person_in_charge\": \"ANTREOU ANTREAS\",\n" +
                "\t\t\t\t\"start_date\": \"30/11/2017\",\n" +
                "\t\t\t\t\"end_date\": \"30/11/2017\",\n" +
                "\t\t\t\t\"start_time\": \"17:30\",\n" +
                "\t\t\t\t\"end_time\": \"19:30\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"employee_id\": \"1\",\n" +
                "\t\t\t\t\"rating\": null,\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"ΤΑΣΟΣ ΠΑΠΑΔΟΠΟΥΛΟΣ ΑΜΦ1\",\n" +
                "\t\t\t\t\t\"lat\": 34.6754767,\n" +
                "\t\t\t\t\t\"long\": 33.0447799\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"Η βιντεοσκόπηση όλων των ομιλιών είναι επιθυμητή σε αυτόνομα αρχεία έτσι ώστε να γίνουν υλικό προς χρήση τόσο από το ΚΤΙΣΗΣ όσο και από το Εικονικό Μουσείο Κυπριακών Τροφίμων και Διατροφής\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"completed_jobs\": {\n" +
                "\t\t\t\"201\": {\n" +
                "\t\t\t\t\"id\": 201,\n" +
                "\t\t\t\t\"name\": \"3rd International Conference & Exhibition on Semiotics and Visual Communication.\",\n" +
                "\t\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\t\"organization\": \"Cyprus Semiotic Association\",\n" +
                "\t\t\t\t\"person_in_charge\": \"Ευριπίδης Ζαντίδης\",\n" +
                "\t\t\t\t\"start_date\": \"03/11/2017\",\n" +
                "\t\t\t\t\"end_date\": \"03/11/2017\",\n" +
                "\t\t\t\t\"start_time\": \"20:30\",\n" +
                "\t\t\t\t\"end_time\": \"22:00\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"employee_id\": \"1\",\n" +
                "\t\t\t\t\"rating\": 6,\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"Tasos Papadopoulos, Amphithatre 1 (001) ground floor\",\n" +
                "\t\t\t\t\t\"lat\": 54.65465,\n" +
                "\t\t\t\t\t\"long\": 54.5466\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"video recording 2 speeches and poster exhibition opening\"\n" +
                "\t\t\t},\n" +
                "\n" +
                "\t\t\t\"202\": {\n" +
                "\t\t\t\t\"id\": 202,\n" +
                "\t\t\t\t\"name\": \"ΠΑΡΟΥΣΙΑΣΗ ΠΤΥΧΙΑΚΩΝ ΕΡΓΑΣΙΩΝ\",\n" +
                "\t\t\t\t\"type\": \"photography\",\n" +
                "\t\t\t\t\"organization\": \"CYPRUS UNIVERSITY OF TECHNOLOGY\",\n" +
                "\t\t\t\t\"person_in_charge\": \"ΙΩΑΝΝΗΣ ΙΩΑΝΝΟΥ\",\n" +
                "\t\t\t\t\"start_date\": \"15/11/2017\",\n" +
                "\t\t\t\t\"end_date\": \"15/11/2017\",\n" +
                "\t\t\t\t\"start_time\": \"10:30\",\n" +
                "\t\t\t\t\"end_time\": \"14:00\",\n" +
                "\t\t\t\t\"employer_id\": \"1\",\n" +
                "\t\t\t\t\"employee_id\": \"1\",\n" +
                "\t\t\t\t\"rating\": 6,\n" +
                "\t\t\t\t\"location\": {\n" +
                "\t\t\t\t\t\"loc_name\": \"Tasos Papadopoulos, Amphithatre 1 (001) ground floor\",\n" +
                "\t\t\t\t\t\"lat\": 54.65465,\n" +
                "\t\t\t\t\t\"long\": 54.5466\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"desc\": \"video recording 2 speeches and poster exhibition opening\"\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";


        helperObj.writeToFile(getApplicationContext(),"employers.json",employers);
        helperObj.readEmployers(getApplicationContext());
        helperObj.writeToFile(getApplicationContext(),"available_jobs.json", available_jobs);
        helperObj.readAvailableJobs(getApplicationContext());

        final Bundle jobData = new Bundle();
        jobData.putSerializable("helper",helperObj);
//        boolean test = true;
//        if (test) {
//            jobData.putSerializable("employer", helperObj.getEmployers().get("1"));
//        }
//        else {
            jobData.putSerializable("employee", helperObj.getEmployees().get("1"));
//        }

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
