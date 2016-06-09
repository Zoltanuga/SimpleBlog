package by.it.auth;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private String name;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String surname;

    public CustomUser(String email, String password, String name, String surname, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.name = name;
        this.surname = surname;
    }


}
