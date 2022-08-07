package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.entities.PictureEntity;
import bg.softuni.playing_cards_shop.repositories.PictureRepository;
import bg.softuni.playing_cards_shop.services.cloudinary.CloudinaryService;
import bg.softuni.playing_cards_shop.services.interfaces.PictureService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class PictureServiceImplTest {

    @Mock
    private PictureRepository pictureRepository;

    @InjectMocks
    private PictureServiceImpl pictureService;

    @InjectMocks
    private CloudinaryService cloudinaryService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setup(){
        this.pictureService=new PictureServiceImpl(pictureRepository, cloudinaryService, modelMapper);
    }


    @Test
    public void testGetDefaultProfilePicture_PictureExists() {

        var pictureTest = new PictureEntity()
                .setUrl("https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_creator_wgjltr.jpg")
                .setPublicId("default_creator_wgjltr.jpg");

        lenient().when(this.pictureRepository.findPictureEntityByUrl(pictureTest.getUrl()))
                .thenReturn(Optional.of(pictureTest));

        var picture = this.pictureService.getDefaultProfilePicture();

        Assertions.assertEquals(pictureTest.getUrl(), picture.getUrl());
        Assertions.assertEquals(pictureTest.getPublicId(), picture.getPublicId());
    }

    @Test
    public void testGetDefaultProfilePicture_PictureDoesNotExist() {

        lenient().when(this.pictureRepository.findPictureEntityByUrl("https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_creator_wgjltr.jpg"))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                ObjectNotFoundException.class,
                () -> this.pictureService.getDefaultProfilePicture()
        );
    }

    @Test
    public void testGetPictureUrl(){
        var picture=new PictureEntity()
                .setUrl("testUrl.jpg");

        Assertions.assertEquals("testUrl.jpg", pictureService.getPictureUrl(picture));
    }

    @Test
    public void testGetDefaultDistributorProfile_PictureExists(){
        var pictureTest = new PictureEntity()
                .setUrl("https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_distributor_ogp1ju.png")
                .setPublicId("default_distributor_ogp1ju.png");

        lenient().when(this.pictureRepository.findPictureEntityByUrl(pictureTest.getUrl()))
                .thenReturn(Optional.of(pictureTest));

        var picture = this.pictureService.getDefaultDistributorProfile();

        Assertions.assertEquals(pictureTest.getUrl(), picture.getUrl());
        Assertions.assertEquals(pictureTest.getPublicId(), pictureTest.getPublicId());
    }

    @Test
    public void testGetDefaultDistributorProfile_PictureDoesNotExist(){
        lenient().when(this.pictureRepository.findPictureEntityByUrl("https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_distributor_ogp1ju.png"))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                ObjectNotFoundException.class,
                () -> this.pictureService.getDefaultDistributorProfile()
        );
    }

}
