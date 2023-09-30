package com.Equipo4.ProyectoIntegradorEquipo4.authentication;


import com.Equipo4.ProyectoIntegradorEquipo4.service.EmailService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins="*", allowedHeaders="*")
@Data
public class AuthController {


    private final AuthService authService;

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        emailService.sendEmailRegister(request.getUsername());

        return ResponseEntity.ok(authService.register(request));

    }

}