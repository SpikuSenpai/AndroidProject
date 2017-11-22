package cutingapp.cuting.org.androidproject.lib.jobs;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by j_md_ on 21/11/2017.
 */

public class JobTypeDeserializer implements JsonDeserializer<JobType> {
    @Override
    public JobType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JobType[] types = JobType.values();
        for (JobType t : types)
        {

            if (t.getType().equalsIgnoreCase(json.getAsString())) {
                return t;
            }
        }
        return null;
    }
}
