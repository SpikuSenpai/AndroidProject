package cutingapp.cuting.org.androidproject.lib.user;

import java.io.Serializable;

/**
 * Created by j_md_ on 24/11/2017.
 */

public enum GenderType implements Serializable {
    MALE("MALE"), FEMALE("FEMALE");

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

     GenderType(String type){
        this.type = type;
    }
}
