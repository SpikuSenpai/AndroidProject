package cutingapp.cuting.org.androidproject.lib.user;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

import cutingapp.cuting.org.androidproject.lib.jobs.Job;

/**
 * Created by j_md_ on 24/11/2017.
 */

public class EmployerDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObj = json.getAsJsonObject();
        GenderType gender = context.deserialize(jsonObj.get("gender"),GenderType.class);
        Type hashmapJobsType = new TypeToken<HashMap<String, Job>>() {
        }.getType();
        HashMap<String,Job> pending_jobs = context.deserialize(jsonObj.get("pending_jobs"),hashmapJobsType);
        HashMap<String,Job> comp_jobs = context.deserialize(jsonObj.get("completed_jobs"),hashmapJobsType);
        HashMap<String,Job> created_jobs = context.deserialize(jsonObj.get("created_jobs"),hashmapJobsType);

        return new Employer(
                jsonObj.get("id").getAsInt(),
                jsonObj.get("name").getAsString(),
                jsonObj.get("surname").getAsString(),
                jsonObj.get("cid").getAsInt(),
                jsonObj.get("email").getAsString(),
                jsonObj.get("phone_number").getAsString(),
                jsonObj.get("password").getAsString(),
                gender,
                jsonObj.get("priv").getAsInt(),
                jsonObj.get("organization").getAsString(),
                created_jobs,
                pending_jobs,
                comp_jobs

        );
    }
}
