package fr.fms.apitrainings.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fms.apitrainings.dao.UsersRepository;
import fr.fms.apitrainings.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/test")
    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Users> listOfTrainings() {
        return usersRepository.findAll();
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorisationToken = request.getHeader("Authorization");
        if (authorisationToken != null && authorisationToken.startsWith("Bearer ")) {
            try {
                String token = authorisationToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("secret");
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(token);
                String username = decodedJWT.getSubject();

                Users users = usersRepository.findByEmail(username);

                String accessToken = JWT.create().withSubject(users.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000)).withIssuer(request.getRequestURL().toString()).withClaim("roles", users.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList())).sign(algorithm);

                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", accessToken);
                idToken.put("refresh-token", token);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);

            } catch (Exception e) {
                response.setHeader("error-message", e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            throw new RuntimeException("Refresh token required");
        }
    }
}
