package com.mvuchevski.ilibrary.web;

import com.mvuchevski.ilibrary.models.User;
import com.mvuchevski.ilibrary.payload.JWTLoginSucessReponse;
import com.mvuchevski.ilibrary.payload.LoginRequest;
import com.mvuchevski.ilibrary.security.JwtTokenProvider;
import com.mvuchevski.ilibrary.services.CustomUserDetailsService;
import com.mvuchevski.ilibrary.services.MapValidationErrorService;
import com.mvuchevski.ilibrary.services.UserService;
import com.mvuchevski.ilibrary.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.Principal;

import static com.mvuchevski.ilibrary.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    MapValidationErrorService mapValidationErrorService;

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
                                              BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

        //UserDetails user = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());


        return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt, authentication.getAuthorities().iterator().next().toString()));
    }

    @PostMapping("/register")
    public ResponseEntity<?>  registerUser(@Valid @RequestBody User user, BindingResult result){

        userValidator.validate(user,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if(errorMap != null) return errorMap;

        User newUser = userService.saveUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/currentUser")
    public ResponseEntity<?> getCurrentUser(Principal principal){
        User user = userService.getUser(principal.getName());
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping("/grantMembership/{username}")
    public ResponseEntity<?> getCurrentUser(@PathVariable String username){
        System.out.println("INNN");
        boolean grantMem = userService.grantMembership(username);
        return new ResponseEntity<String>("The user '" + username + "' successfully was granted membership.",HttpStatus.OK);
    }


}
