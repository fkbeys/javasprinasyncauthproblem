package com.kayaspring.kayaspring.business.services;

import com.kayaspring.kayaspring.api.middlewares.logging.ILogger;
import com.kayaspring.kayaspring.auth.JwtUtils;
import com.kayaspring.kayaspring.common.GenericResultClass;
import com.kayaspring.kayaspring.data.repositories.IRoleRepository;
import com.kayaspring.kayaspring.data.repositories.IUserRepository;
import com.kayaspring.kayaspring.entities.enums.UserRoleEnums;
import com.kayaspring.kayaspring.entities.models.Category;
import com.kayaspring.kayaspring.entities.models.User.AppUser;
import com.kayaspring.kayaspring.entities.models.User.UserDetailsImpl;
import com.kayaspring.kayaspring.entities.models.User.UserRole;
import com.kayaspring.kayaspring.entities.requests.LoginRequest;
import com.kayaspring.kayaspring.entities.requests.SignupRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final ILogger logger;

    private final JwtUtils jwtUtils;

    public AuthenticationService(AuthenticationManager authenticationManager, IUserRepository userRepository, IRoleRepository roleRepository, PasswordEncoder encoder, ILogger logger, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.logger = logger;
        this.jwtUtils = jwtUtils;


    }

    public GenericResultClass authenticateUser(@RequestBody LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return GenericResultClass.Success(jwt, 1);
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }

    }

    public GenericResultClass registerUser(@RequestBody SignupRequest signUpRequest) {

        try {

            GenericResultClass result = null;
            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                return GenericResultClass.UnSuccessful("Error: Username is already taken!");

            }
            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return GenericResultClass.UnSuccessful("Error: Email is already in use!");

            }

            AppUser user = new AppUser(signUpRequest.getUsername(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));

            List<String> strRoles = Arrays.asList(signUpRequest.getRole().split(","));

            Set<UserRole> roles = new HashSet<>();


            String roleNotFoundErrorStr = "Error: Role is not found.";

            if (strRoles == null) {
                UserRole userRole = roleRepository.findByName(UserRoleEnums.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException(roleNotFoundErrorStr));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            UserRole adminRole = roleRepository.findByName(UserRoleEnums.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException(roleNotFoundErrorStr));
                            roles.add(adminRole);

                            break;
                        case "mod":
                            UserRole modRole = roleRepository.findByName(UserRoleEnums.ROLE_MODERATOR)
                                    .orElseThrow(() -> new RuntimeException(roleNotFoundErrorStr));
                            roles.add(modRole);

                            break;
                        default:
                            UserRole userRole = roleRepository.findByName(UserRoleEnums.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException(roleNotFoundErrorStr));
                            roles.add(userRole);
                    }
                });
            }
            user.setRoles(roles);
            userRepository.save(user);

            return GenericResultClass.Success("User registered successfully!", 1);

        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }

    }

    public UserDetailsImpl getCurrentUserImpl() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication.getPrincipal() instanceof UserDetailsImpl)) {
            throw new IllegalStateException("Unknown user principal: " + authentication.getPrincipal());
        }
        return (UserDetailsImpl) authentication.getPrincipal();
    }

    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication.getPrincipal() instanceof UserDetailsImpl)) {
            throw new IllegalStateException("Unknown user principal: " + authentication.getPrincipal());
        }
        return ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
    }


}
