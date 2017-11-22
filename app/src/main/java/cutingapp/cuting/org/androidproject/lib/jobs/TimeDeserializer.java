package cutingapp.cuting.org.androidproject.lib.jobs;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * Created by j_md_ on 21/11/2017.
 */

public class TimeDeserializer implements JsonDeserializer<Time> {
    @Override
    public Time deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            String time = json.getAsString();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
            long ms = formatter.parse(time).getTime();
            Time t = new Time(ms);
            return t;
        } catch (ParseException e) {
            Log.println(Log.DEBUG, "NONE", "Failed to parse Time due to: " + e.getLocalizedMessage());
            return null;
        }
    }
}
