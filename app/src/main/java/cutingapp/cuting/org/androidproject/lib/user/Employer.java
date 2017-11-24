package cutingapp.cuting.org.androidproject.lib.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;

import cutingapp.cuting.org.androidproject.lib.jobs.Job;

/**
 * Created by j_md_ on 24/11/2017.
 */

public class Employer extends User implements Serializable {

    @SerializedName("organization")
    @Expose
    private String organization;

    @SerializedName("created_jobs")
    @Expose
    private HashMap<String, Job> created_jobs;

    @SerializedName("pending_jobs")
    @Expose
    private HashMap<String, Job> pending_jobs;

    @SerializedName("completed_jobs")
    @Expose
    private HashMap<String, Job> completed_jobs;

    public Employer(int id, String name, String surname, int cid, String email, String phone_number, String password, GenderType gender, int priv, String organization, HashMap<String, Job> created_jobs, HashMap<String, Job> pending_jobs, HashMap<String, Job> completed_jobs) {
        super(id, name, surname, cid, email, phone_number, password, gender, priv);
        this.organization = organization;
        this.created_jobs = created_jobs;
        this.pending_jobs = pending_jobs;
        this.completed_jobs = completed_jobs;
    }

    public Employer(String organization, HashMap<String, Job> created_jobs, HashMap<String, Job> pending_jobs, HashMap<String, Job> completed_jobs) {
        this.organization = organization;
        this.created_jobs = created_jobs;
        this.pending_jobs = pending_jobs;
        this.completed_jobs = completed_jobs;
    }

    public Employer() {
        this.organization = null;
        this.created_jobs = new HashMap<String, Job>();
        this.pending_jobs = new HashMap<String, Job>();
        this.completed_jobs = new HashMap<String, Job>();
    }

    public Employer(int id, String name, String surname, int cid, String email, String phone_number, String password, GenderType gender, int priv, String organization) {
        super(id, name, surname, cid, email, phone_number, password, gender, priv);
        this.organization = organization;
        this.created_jobs = new HashMap<String, Job>();
        this.pending_jobs = new HashMap<String, Job>();
        this.completed_jobs = new HashMap<String, Job>();
    }
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public HashMap<String, Job> getCreated_jobs() {
        return created_jobs;
    }

    public void setCreated_jobs(HashMap<String, Job> created_jobs) {
        this.created_jobs = created_jobs;
    }

    public HashMap<String, Job> getPending_jobs() {
        return pending_jobs;
    }

    public void setPending_jobs(HashMap<String, Job> pending_jobs) {
        this.pending_jobs = pending_jobs;
    }

    public HashMap<String, Job> getCompleted_jobs() {
        return completed_jobs;
    }

    public void setCompleted_jobs(HashMap<String, Job> completed_jobs) {
        this.completed_jobs = completed_jobs;
    }

    public void addCreatedJob(Job newCreatedJob){
        this.completed_jobs.put(String.valueOf(newCreatedJob.getId()),newCreatedJob);
    }

    private void removeCreatedJob(int jobIDtoremove){
        this.created_jobs.remove(String.valueOf(jobIDtoremove));

    }

    private void removePendingJob(int jobIDtoremove){
        this.pending_jobs.remove(String.valueOf(jobIDtoremove));
    }

   public void moveCreatedtoPending(int jobToMove){
       this.pending_jobs.put(String.valueOf(jobToMove),this.created_jobs.get(String.valueOf(jobToMove)));
       removeCreatedJob(jobToMove);
   }

    public void moveCreatedtoPending(Job jobToMove){
        this.pending_jobs.put(String.valueOf(jobToMove.getId()),jobToMove);
        removeCreatedJob(jobToMove.getId());
    }

    public void movePendingtoCompleted(int jobToMove){
        this.completed_jobs.put(String.valueOf(jobToMove),this.pending_jobs.get(String.valueOf(jobToMove)));
        removePendingJob(jobToMove);
    }

    public void movePendingtoCompleted(Job jobToMove){
        this.completed_jobs.put(String.valueOf(jobToMove.getId()),jobToMove);
        removePendingJob(jobToMove.getId());
    }


}
