package cutingapp.cuting.org.androidproject.lib.jobs;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by j_md_ on 21/11/2017.
 */

public class LocationDeserializer implements JsonDeserializer<Location> {
    @Override
    public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObj = json.getAsJsonObject();
        return new Location(jsonObj.get("loc_name").getAsString(),jsonObj.get("lat").getAsFloat(),jsonObj.get("long").getAsFloat());
    }
}
