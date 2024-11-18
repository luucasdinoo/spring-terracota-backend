package br.com.terracotabackend.controllers;

import br.com.terracotabackend.infra.security.TokenService;
import br.com.terracotabackend.model.dto.AuthenticationDTO;
import br.com.terracotabackend.model.dto.LoginResponseDTO;
import br.com.terracotabackend.model.dto.RegisterDTO;
import br.com.terracotabackend.model.entities.User;
import br.com.terracotabackend.model.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
//        if (this.repository.findByEmail(data.email()) != null)
//            return ResponseEntity.badRequest().build();
//
//        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
//        User newUser = new User(data.email(), encryptedPassword, data.role());
//
//        this.repository.save(newUser);
//        return ResponseEntity.ok().build();
//    }
}
