package fr.fms.apitrainings.security;

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import fr.fms.apitrainings.entities.Users;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    public static final String SECRET = "secret";
    public static final String AUTH_HEADER = "Authorization";

}
