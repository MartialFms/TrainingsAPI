package fr.fms.apitrainings.controller;

import fr.fms.apitrainings.entities.Users;
import fr.fms.apitrainings.security.payload.LoginRequest;
import fr.fms.apitrainings.service.ImplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public void login( @RequestBody LoginRequest loginRequest) {

                    System.out.println(loginRequest.toString());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

                    System.out.println(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

                    System.out.println(authentication);



        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
       List<String> roles = userDetails.getAuthorities().stream()
               .map(item -> item.getAuthority())
               .collect(Collectors.toList());

       return ResponseEntity.ok(new JwtResponse(jwt,
               userDetails.getId(),
               userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
}
