package fr.fms.apitrainings.security.payload;

import fr.fms.apitrainings.entities.Customer;
import fr.fms.apitrainings.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String username;
    // private String password;
    private Collection<GrantedAuthority> authorities;

}
