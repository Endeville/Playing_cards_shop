package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.UserRegistrationDto;
import bg.softuni.playing_cards_shop.models.entities.UserEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;
import bg.softuni.playing_cards_shop.models.views.UserProfileDto;
import bg.softuni.playing_cards_shop.repositories.UserRepository;
import bg.softuni.playing_cards_shop.services.interfaces.UserRoleService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static bg.softuni.playing_cards_shop.constants.GlobalConstants.OBJECT_NAME_USER;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;
    private final UserDetailsService appUserDetailsService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder, UserRoleService userRoleService,
                           UserDetailsService appUserDetailsService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
        this.appUserDetailsService = appUserDetailsService;
    }


    @Override
    public void register(UserRegistrationDto userModel) {
        var user = modelMapper.map(userModel, UserEntity.class);
        user
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setRole(userRoleService.findByRole(UserRole.CLIENT));

        userRepository.save(user);

        var userDetails=appUserDetailsService.loadUserByUsername(user.getUsername());

        var auth= new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder
                .getContext()
                .setAuthentication(auth);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserProfileDto findUserById(Long id) {
        return this.modelMapper.map(
                this.userRepository.findById(id).orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_USER)),
                UserProfileDto.class);
    }


    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

}
