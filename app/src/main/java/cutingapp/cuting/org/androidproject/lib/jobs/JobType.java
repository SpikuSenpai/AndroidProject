package cutingapp.cuting.org.androidproject.lib.jobs;

import java.io.Serializable;

/**
 * Created by j_md_ on 21/11/2017.
 */

public enum JobType implements Serializable{
    PHOTOGRAPHY("photography"), VIDEOGRAPHY("videography");
    private String type;
    JobType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
