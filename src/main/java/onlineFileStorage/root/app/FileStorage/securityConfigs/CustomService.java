package onlineFileStorage.root.app.FileStorage.securityConfigs;

import onlineFileStorage.root.app.FileStorage.models.RoleEntity;
import onlineFileStorage.root.app.FileStorage.models.UserEntity;
import onlineFileStorage.root.app.FileStorage.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public CustomService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Use not found exception"));
        return new User(user.getUsername(),user.getPassword(),mapToAuthority(user.getRoles()));
    }
    private Collection<GrantedAuthority> mapToAuthority(List<RoleEntity> roles){
        return roles.stream().map(e -> new SimpleGrantedAuthority(e.getRole())).collect(Collectors.toList());
    }
}
