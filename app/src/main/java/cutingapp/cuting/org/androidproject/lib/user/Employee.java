package cutingapp.cuting.org.androidproject.lib.user;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.Streams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import cutingapp.cuting.org.androidproject.lib.jobs.Job;

/**
 * Created by j_md_ on 24/11/2017.
 */

public class Employee extends User implements Serializable{

    @SerializedName("sid")
    @Expose
    private int sid;

    @SerializedName("skills")
    @Expose
    private ArrayList<String> skills;

    @SerializedName("applied_jobs")
    @Expose
    private HashMap<String,Job> applied_jobs;

    @SerializedName("completed_jobs")
    @Expose
    private HashMap<String,Job> completed_jobs;

    public Employee(int id, String name, String surname, int cid, String email, String phone_number, String password, GenderType gender, int priv, int sid, ArrayList<String> skills, HashMap<String, Job> applied_jobs, HashMap<String, Job> completed_jobs) {
        super(id, name, surname, cid, email, phone_number, password, gender, priv);
        this.sid = sid;
        this.skills = skills;
        this.applied_jobs = applied_jobs;
        this.completed_jobs = completed_jobs;
    }

    public Employee(int sid, ArrayList<String> skills, HashMap<String, Job> applied_jobs, HashMap<String, Job> completed_jobs) {
        this.sid = sid;
        this.skills = skills;
        this.applied_jobs = applied_jobs;
        this.completed_jobs = completed_jobs;
    }

    public Employee() {
        this.sid = -1;
        this.skills = new ArrayList<>();
        this.applied_jobs = new HashMap<String, Job>();
        this.completed_jobs = new HashMap<String, Job>();
    }

    public Employee(int id, String name, String surname, int cid, String email, String phone_number, String password, GenderType gender, int priv, int sid, ArrayList<String> skills) {
        super(id, name, surname, cid, email, phone_number, password, gender, priv);
        this.sid = sid;
        this.skills = skills;
        this.applied_jobs = new HashMap<String, Job>();
        this.completed_jobs = new HashMap<String, Job>();
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public HashMap<String, Job> getApplied_jobs() {
        return applied_jobs;
    }

    public void setApplied_jobs(HashMap<String, Job> applied_jobs) {
        this.applied_jobs = applied_jobs;
    }

    public HashMap<String, Job> getCompleted_jobs() {
        return completed_jobs;
    }

    public void setCompleted_jobs(HashMap<String, Job> completed_jobs) {
        this.completed_jobs = completed_jobs;
    }

    public void addAppliedJob(Job newJob){
        this.applied_jobs.put(String.valueOf(newJob.getId()),newJob);
    }

    private void removeAppliedJob(String jobID){
        this.applied_jobs.remove(jobID);
    }

    private void addCompletedJob(Job completedJob){
        this.completed_jobs.put(String.valueOf(completedJob.getId()),completedJob);
    }

    public void moveJobToCompleted(Job comJob){
        addCompletedJob(comJob);
        removeAppliedJob(String.valueOf(comJob.getId()));
    }

    public void moveJobToCompleted(int comJobID){

        addCompletedJob(this.applied_jobs.get(comJobID));
        removeAppliedJob(String.valueOf(comJobID));
    }

    public void addSkill(String newSkill){
        this.skills.add(newSkill);
    }
}
