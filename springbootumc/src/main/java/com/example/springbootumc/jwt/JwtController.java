package com.example.springbootumc.jwt;

import com.example.springbootumc.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    @RequestMapping(value = "/token")
    public String generateToken(@RequestParam("email") String email,@RequestParam String password){
        System.out.println("email:  "+email);

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));

        }
        catch (UsernameNotFoundException e){
            e.printStackTrace();
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
       String token =   this.jwtUtil.generateToken(userDetails);
        System.out.println("TOKEN"+ token);

        return "redirect:/setSession";
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
