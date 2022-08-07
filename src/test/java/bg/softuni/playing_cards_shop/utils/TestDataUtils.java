package bg.softuni.playing_cards_shop.utils;

import bg.softuni.playing_cards_shop.models.entities.UserEntity;
import bg.softuni.playing_cards_shop.models.entities.UserRoleEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;
import bg.softuni.playing_cards_shop.repositories.UserRepository;
import bg.softuni.playing_cards_shop.repositories.UserRoleRepository;
import org.springframework.stereotype.Component;

@Component
public class TestDataUtils {

    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;


    public TestDataUtils(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }

    public void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.CLIENT);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    public void initUsers() {
        if (userRepository.count() == 0) {
            createTestAdmin("testAdmin");
            createTestClient("testClient");
        }
    }

    public UserEntity createTestAdmin(String username) {
        initRoles();

        var user = new UserEntity()
                .setUsername(username)
                .setPassword("123456")
                .setEmail("email1@test.com")
                .setRating((short) 5)
                .setRole(userRoleRepository.findUserRoleEntityByRole(UserRole.ADMIN).get());

        return userRepository.save(user);
    }

    public UserEntity createTestClient(String username) {
        initRoles();

        var user = new UserEntity()
                .setUsername(username)
                .setPassword("123456")
                .setEmail("email2@test.com")
                .setRating((short) 4)
                .setRole(userRoleRepository.findUserRoleEntityByRole(UserRole.CLIENT).get());

        return userRepository.save(user);
    }

    public void cleanUp() {
        userRepository.deleteAll();
        ;
        userRoleRepository.deleteAll();
    }

}
