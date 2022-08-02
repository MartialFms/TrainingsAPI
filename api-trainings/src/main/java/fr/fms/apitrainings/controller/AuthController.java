package fr.fms.apitrainings.controller;

import fr.fms.apitrainings.entities.Users;
import fr.fms.apitrainings.security.JwtUtils;
import fr.fms.apitrainings.security.payload.JwtResponse;
import fr.fms.apitrainings.security.payload.LoginRequest;
import fr.fms.apitrainings.service.ImplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ImplUserService implUserService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        //creation authentification
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        //generation du token
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = implUserService.jwtUtils.generateJwtToken(authentication);

        // creation de l'user en cast du principal authentifié
        Users user = (Users) authentication.getPrincipal();
        // donner les roles à la personne
        List<String> roles = user.getRoles().stream()
                .map(item -> item.getName())
                .collect(Collectors.toList());

        // renvoi tout en front
        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                roles));
    }
}

