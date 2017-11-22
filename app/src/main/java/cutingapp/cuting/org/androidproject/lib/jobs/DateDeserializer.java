package cutingapp.cuting.org.androidproject.lib.jobs;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by j_md_ on 21/11/2017.
 */

public class DateDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(json.getAsString());
        } catch (ParseException e) {
            Log.println(Log.DEBUG, "NONE", "Failed to parse Date due to: " + e.getLocalizedMessage());
            return null;
        }

    }
}
