package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.UserRegistrationDto;
import bg.softuni.playing_cards_shop.models.entities.UserEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;
import bg.softuni.playing_cards_shop.repositories.UserRepository;
import bg.softuni.playing_cards_shop.services.interfaces.UserRoleService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import bg.softuni.playing_cards_shop.services.security.AppUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;
    private final UserDetailsService appUserDetailsService;
//    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder, UserRoleService userRoleService,
                           UserDetailsService appUserDetailsService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
//        this.currentUser = currentUser;
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

//    @Override
//    public boolean login(UserLoginDto userLoginDto) {
//        Optional<UserEntity> user=this.userRepository.findUserEntityByUsername(userLoginDto.getUsername());
//
//        if(user.isEmpty()){
//            return false;
//        }
//
//        var success=this.passwordEncoder.matches(userLoginDto.getPassword(), user.get().getPassword());
//
//        if(success){
//            login(user.get());
//        }else{
//            this.logout();
//        }
//
//        return success;
//    }

//    @Override
//    public void logout() {
//        this.currentUser.logout();
//    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

//    @Override
//    public void login(UserEntity user) {
//        currentUser.login(modelMapper.map(user, CurrentUser.class));
//    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

}
