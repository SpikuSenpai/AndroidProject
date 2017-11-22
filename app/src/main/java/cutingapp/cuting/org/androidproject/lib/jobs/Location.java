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
    private int latitude;

    @SerializedName("long")
    @Expose
    private int longitude;


    public Location(String loc_name, int latitude, int longitude ){
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = loc_name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

}
