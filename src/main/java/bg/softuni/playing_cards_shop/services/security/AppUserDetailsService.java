package bg.softuni.playing_cards_shop.services.security;

import bg.softuni.playing_cards_shop.models.entities.UserEntity;
import bg.softuni.playing_cards_shop.repositories.UserRepository;
import org.modelmapper.Converters;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Set;

public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findUserEntityByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("No user with username" + username + "found."));
    }

    private UserDetails map(UserEntity userEntity) {
        return new AppUser(
                userEntity.getUsername(),
                userEntity.getPassword(),
                Set.of(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().getRole().name())));
    }
}
