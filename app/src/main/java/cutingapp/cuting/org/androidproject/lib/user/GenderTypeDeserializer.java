package cutingapp.cuting.org.androidproject.lib.user;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by j_md_ on 24/11/2017.
 */

public class GenderTypeDeserializer implements JsonDeserializer<GenderType> {
    @Override
    public GenderType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        GenderType[] types = GenderType.values();
        for (GenderType t : types)
        {

            if (t.getType().equalsIgnoreCase(json.getAsString())) {
                return t;
            }
        }
        return null;
    }
}
