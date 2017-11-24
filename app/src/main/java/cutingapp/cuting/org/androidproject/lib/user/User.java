package cutingapp.cuting.org.androidproject.lib.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by j_md_ on 24/11/2017.
 */

public class User  implements Serializable{

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;


    @SerializedName("cid")
    @Expose
    private int cid;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone_number")
    @Expose
    private String phone_number;

    @SerializedName("password")
    @Expose
    private String password;


    @SerializedName("gender")
    @Expose
    private GenderType gender;

    @SerializedName("priv")
    @Expose
    private int priv;

    public User(int id, String name, String surname, int cid, String email, String phone_number, String password, GenderType gender, int priv) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.cid = cid;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.gender = gender;
        this.priv = priv;
    }

    public User() {
        this.id = -1;
        this.name = null;
        this.surname = null;
        this.cid = -1;
        this.email = null;
        this.password = null;
        this.gender = null;
        this.priv = -1;
        this.phone_number = null;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public int getPriv() {
        return priv;
    }

    public void setPriv(int priv) {
        this.priv = priv;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
