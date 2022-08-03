package fr.fms.apitrainings.security.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.fms.apitrainings.entities.Customer;
import fr.fms.apitrainings.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
public class JwtResponse implements Serializable {

    private String token;
    private String email;
    private String username;

    private String password;


    private List<String> roles = new ArrayList<>();


    public JwtResponse(String token, String email, String username, String password, boolean enable, List<String> roles) {
        this.token = token;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + token +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
