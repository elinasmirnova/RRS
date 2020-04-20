package cz.ear.rrs.dto;

import cz.ear.rrs.model.Rights;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class AccountDTO {

    @NotNull
    private String firstname;

    @NotNull
    private String password;

    @NotNull
    private String lastname;

    @NotNull
    private String username;

    @NotNull
    private ArrayList<Integer> buildingId;

    @NotNull
    private Rights rights;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
