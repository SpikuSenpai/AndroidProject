package cutingapp.cuting.org.androidproject.lib.jobs;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by j_md_ on 21/11/2017.
 */

public class Location implements Serializable {


    @SerializedName("loc_name")
    @Expose

    private String name;

    @SerializedName("lat")
    @Expose
    private float latitude;

    @SerializedName("long")
    @Expose
    private float longitude;

    public Location(String name, float latitude, float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
