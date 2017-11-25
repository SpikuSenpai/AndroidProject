package cutingapp.cuting.org.androidproject.lib.jobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by j_md_ on 21/11/2017.
 */

public class Job implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("type")
    @Expose
    private JobType type;

    @SerializedName("organization")
    @Expose
    private String organization;

    @SerializedName("person_in_charge")
    @Expose
    private String person_in_charge;

    @SerializedName("start_date")
    @Expose
    private Date startDate;

    @SerializedName("end_date")
    @Expose
    private Date endDate;

    @SerializedName("start_time")
    @Expose
    private Time startTime;

    @SerializedName("end_time")
    @Expose
    private Time endTime;

    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("desc")
    @Expose
    private String desc;

    @SerializedName("employer_id")
    @Expose
    private String employer_id;

    @SerializedName("employee_id")
    @Expose
    private String employee_id;

    public Job(int id, String name, JobType type, String organization, String person_in_charge, Date startDate, Date endDate, Time startTime, Time endTime, Location location, String desc, String employer_id, String employee_id) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.organization = organization;
        this.person_in_charge = person_in_charge;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.desc = desc;
        this.employer_id = employer_id;
        this.employee_id = employee_id;
    }

    public Job() {

        this.id = -1;
        this.name = null;
        this.type = null;
        this.organization = null;
        this.person_in_charge = null;
        this.startDate = null;
        this.endDate = null;
        this.startTime = null;
        this.endTime = null;
        this.location = null;
        this.desc = null;
        this.employer_id = null;
        this.employee_id = null;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployer_id() {
        return employer_id;
    }

    public void setEmployer_id(String employer_id) {
        this.employer_id = employer_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPerson_in_charge() {
        return person_in_charge;
    }

    public void setPerson_in_charge(String person_in_charge) {
        this.person_in_charge = person_in_charge;
    }
}
