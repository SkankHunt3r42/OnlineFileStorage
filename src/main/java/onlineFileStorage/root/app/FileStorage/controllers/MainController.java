package onlineFileStorage.root.app.FileStorage.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import onlineFileStorage.root.app.FileStorage.handlers.exception.ExistingUsernameException;
import onlineFileStorage.root.app.FileStorage.models.RoleEntity;
import onlineFileStorage.root.app.FileStorage.models.UserEntity;
import onlineFileStorage.root.app.FileStorage.repo.RoleRepository;
import onlineFileStorage.root.app.FileStorage.repo.UserRepository;
import onlineFileStorage.root.app.FileStorage.securityConfigs.CustomService;
import onlineFileStorage.root.app.FileStorage.securityConfigs.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/client/user")
public class MainController {

    private AuthenticationManager manager;
    private UserRepository repository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private CustomService service;
    private JwtGenerator generator;
    @Autowired
    public MainController(AuthenticationManager manager,
                          UserRepository repository,
                          RoleRepository roleRepository,
                          PasswordEncoder encoder,
                          CustomService service,
                          JwtGenerator generator) {
        this.manager = manager;
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.service = service;
        this.generator = generator;
    }




    @Operation(summary = "Registration",
            parameters ={
                        @Parameter(name = "username",description = "Username",schema = @Schema(type = "string")),
                        @Parameter(name = "email",description = "User email",schema = @Schema(type = "string")),
                        @Parameter(name = "password",description = "User password",schema = @Schema(type = "string"))})
    @PostMapping("register")
     public String register(@RequestBody UserEntity entity) throws InterruptedException {

        if (repository.existsByUsername(entity.getUsername())) {
            throw new ExistingUsernameException("Username is already taken!");
        }
        entity.setPassword(encoder.encode(entity.getPassword()));
        RoleEntity roles = roleRepository.findByRole("USER").get();
        entity.add(roles);

        repository.save(entity);

        return "User was successful register !";
    }
    @Operation(summary = "Log in",
            description = "Log in account,that should contains in data base",
            parameters ={
                    @Parameter(name = "username",description = "Username",schema = @Schema(type = "string")),
                    @Parameter(name = "email",description = "User email",schema = @Schema(type = "string")),
                    @Parameter(name = "password",description = "User password",schema = @Schema(type = "string"))})
    @PostMapping("login")
    public String login(@RequestBody UserEntity entity){
         UserDetails user = service.loadUserByUsername(entity.getUsername());
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(entity.getUsername(),entity.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = generator.generateToken(auth);
        return "User was successful login \n Authorization token: " + token;
    }
}
