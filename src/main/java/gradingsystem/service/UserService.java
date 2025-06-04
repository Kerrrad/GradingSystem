package gradingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import gradingsystem.model.User;
import gradingsystem.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Logger logger = LoggerFactory.getLogger(getClass());

        Optional<User> userOptional = userRepository.findByEmail(email);

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        List<GrantedAuthority> authorities = getAuthorities(user.getRoles());


        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                authorities
        );
    }

    private List<GrantedAuthority> getAuthorities(int role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
