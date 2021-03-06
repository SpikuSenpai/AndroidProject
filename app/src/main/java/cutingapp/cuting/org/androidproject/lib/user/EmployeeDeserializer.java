package cutingapp.cuting.org.androidproject.lib.user;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import cutingapp.cuting.org.androidproject.lib.jobs.Job;

/**
 * Created by j_md_ on 24/11/2017.
 */

public class EmployeeDeserializer implements JsonDeserializer<Employee> {
    @Override
    public Employee deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObj = json.getAsJsonObject();
        GenderType gender = context.deserialize(jsonObj.get("gender"),GenderType.class);
        Type arraylist = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> skills =context.deserialize(jsonObj.get("skills").getAsJsonArray(), arraylist );

        Type hashmapJobsType = new TypeToken<HashMap<String, Job>>() {
        }.getType();
        HashMap<String,Job> applied_jobs = context.deserialize(jsonObj.get("applied_jobs"),hashmapJobsType);
        HashMap<String,Job> comp_jobs = context.deserialize(jsonObj.get("completed_jobs"),hashmapJobsType);

        return new Employee(
                jsonObj.get("id").getAsInt(),
                jsonObj.get("name").getAsString(),
                jsonObj.get("surname").getAsString(),
                jsonObj.get("cid").getAsInt(),
                jsonObj.get("email").getAsString(),
                jsonObj.get("phone_number").getAsString(),
                jsonObj.get("password").getAsString(),
                gender,
                jsonObj.get("priv").getAsInt(),
                jsonObj.get("sid").getAsInt(),
                skills,
                applied_jobs,
                comp_jobs
                );
    }
}
