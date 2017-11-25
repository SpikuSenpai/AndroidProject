package cutingapp.cuting.org.androidproject.lib.jobs;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.Date;

/**
 * Created by j_md_ on 21/11/2017.
 */

public class JobDeserializer implements JsonDeserializer<Job> {
    @Override
    public Job deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObj = json.getAsJsonObject();

        JobType type = context.deserialize(jsonObj.get("type"), JobType.class);
        Date startDate = context.deserialize(jsonObj.get("start_date"), Date.class);
        Date endDate= context.deserialize(jsonObj.get("end_date"), Date.class);
        Time startTime = context.deserialize(jsonObj.get("start_time"), Time.class);
        Time endTime = context.deserialize(jsonObj.get("end_time"), Time.class);
        Location location = context.deserialize(jsonObj.get("location"), Location.class);

        return new Job(
                jsonObj.get("id").getAsInt(),
                jsonObj.get("name").getAsString(),
                type,
                jsonObj.get("organization").getAsString(),
                jsonObj.get("person_in_charge").getAsString(),
                startDate,
                endDate,
                startTime,
                endTime,
                location,
                jsonObj.get("desc").getAsString(),
                jsonObj.get("employer_id").getAsString(),
                (jsonObj.get("employee_id") instanceof JsonNull) ? null : jsonObj.get("employee_id").getAsString());

    }
}
