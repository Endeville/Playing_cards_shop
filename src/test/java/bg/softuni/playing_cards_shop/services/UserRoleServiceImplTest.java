package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.entities.UserRoleEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;
import bg.softuni.playing_cards_shop.repositories.UserRoleRepository;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRoleServiceImplTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleServiceImpl userRoleService;

    @BeforeEach
    void setup(){
        userRoleService=new UserRoleServiceImpl(userRoleRepository);
    }

    @Test
    public void testFindByRole_RoleExists(){
        var userRole=new UserRoleEntity()
                .setRole(UserRole.CLIENT);

        when(userRoleRepository.findUserRoleEntityByRole(userRole.getRole()))
                .thenReturn(Optional.of(userRole));

        var result=userRoleService.findByRole(userRole.getRole());

        assertEquals(userRole.getRole(), result.getRole());
    }

    @Test
    public void testFindByRole_RoleDoesNotExist(){
        when(userRoleRepository.findUserRoleEntityByRole(any()))
                .thenReturn(Optional.empty());

        assertThrows(
                ObjectNotFoundException.class,
                ()->userRoleService.findByRole(any())
        );
    }

}
