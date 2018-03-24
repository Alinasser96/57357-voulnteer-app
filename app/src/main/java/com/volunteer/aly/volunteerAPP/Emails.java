package com.volunteer.aly.volunteerAPP;

/**
 * Created by aly on 3/14/18.
 */

public class Emails {
    String email_name,email_id,name,members,data;

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emails(String email_name, String email_id, String name,String mambers,String data) {
        this.email_name = email_name;
        this.email_id = email_id;
        this.name = name;
        this.members=members;
        this.data=data;

    }

    public Emails() {
    }

    public String getEmail_name() {
        return email_name;
    }

    public void setEmail_name(String email_name) {
        this.email_name = email_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
}
