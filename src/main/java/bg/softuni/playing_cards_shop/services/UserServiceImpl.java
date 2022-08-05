package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.UserRegistrationDto;
import bg.softuni.playing_cards_shop.models.entities.*;
import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;
import bg.softuni.playing_cards_shop.models.views.AddressDto;
import bg.softuni.playing_cards_shop.models.views.UserProfileDto;
import bg.softuni.playing_cards_shop.models.views.rest.UserPromotedDto;
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

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

        var userDetails = appUserDetailsService.loadUserByUsername(user.getUsername());

        var auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder
                .getContext()
                .setAuthentication(auth);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserProfileDto findUserProfileByUsername(String username) {
        return this.modelMapper.map(
                this.userRepository.findUserEntityByUsername(username).orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_USER)),
                UserProfileDto.class);
    }

    @Override
    public void addAddress(String name, AddressEntity address) {
        var user = this.userRepository.findUserEntityByUsername(name)
                .orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_USER));
        var addresses = user.getAddresses();
        addresses.add(address);
        user.setAddresses(addresses);
        this.userRepository.save(user);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return this.userRepository.findUserEntityByUsername(username).orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_USER));
    }

    @Override
    public List<AddressDto> findAddressesByUserUsername(String username) {
        return this.userRepository.findUserEntityByUsername(username)
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_USER))
                .getAddresses().stream()
                .map(a->this.modelMapper.map(a, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserEntity getCurrentUser() {
        return this.userRepository.findUserEntityByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(()-> new ObjectNotFoundException(OBJECT_NAME_USER));
    }

    @Override
    public UserPromotedDto promote(String username) {
        var user=this.userRepository.findUserEntityByUsername(username)
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_USER));
        user.setRole(this.userRoleService.findByRole(UserRole.ADMIN));
        this.userRepository.save(user);

        return this.modelMapper.map(user, UserPromotedDto.class);
    }

    @Transactional
    @Override
    public void updateRatings() {
        var users=this.userRepository.findAll();
        for (UserEntity user : users) {
            var ratingSum=user.getOffers().stream()
                    .flatMapToInt(o->o.getReviews().stream().mapToInt(r-> (int) r.getRating()))
                    .reduce(Integer::sum)
                    .orElse(0);
            var ratingSize=0;
            for (OfferEntity offer : user.getOffers()) {
                ratingSize+=offer.getReviews().size();
            }
            if(ratingSize==0){
                user.setRating((short) 0);
            }else {
                user.setRating((short) (ratingSum / ratingSize));
            }
        }
        this.userRepository.saveAll(users);
    }

    @Override
    public boolean ownsProfile(String name, Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException(OBJECT_NAME_USER))
                .getUsername().equals(name);
    }


    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

}
