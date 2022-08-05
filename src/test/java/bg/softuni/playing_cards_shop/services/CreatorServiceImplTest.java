package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.cloudinary.CloudinaryImage;
import bg.softuni.playing_cards_shop.models.dtos.AddCreatorDto;
import bg.softuni.playing_cards_shop.models.entities.CreatorEntity;
import bg.softuni.playing_cards_shop.models.entities.PictureEntity;
import bg.softuni.playing_cards_shop.models.views.CreatorDetailsDto;
import bg.softuni.playing_cards_shop.repositories.CreatorRepository;
import bg.softuni.playing_cards_shop.repositories.PictureRepository;
import bg.softuni.playing_cards_shop.services.cloudinary.CloudinaryService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import com.cloudinary.Cloudinary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreatorServiceImplTest {

    @Mock
    private CreatorRepository creatorRepository;

    @InjectMocks
    private CreatorServiceImpl creatorService;

    @Mock
    private PictureServiceImpl pictureService;

    @Mock
    private PictureRepository pictureRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CloudinaryService cloudinaryService;

    @BeforeEach
    void setup(){
        pictureService=new PictureServiceImpl(pictureRepository, cloudinaryService, modelMapper);
        creatorService=new CreatorServiceImpl(creatorRepository,pictureService, modelMapper);
    }

    @Test
    public void testNameExists_NameExists(){
        var creator=new CreatorEntity()
                .setName("Creator1")
                .setDescription("Description1")
                .setPicture(defaultPictureProfile())
                .setDecks(null);

        when(creatorService.nameExists(creator.getName())).thenReturn(true);

        var result=creatorService.nameExists(creator.getName());

        assertTrue(result);
    }

    @Test
    public void testNameExists_NameDoesNotExist(){
        when(creatorService.nameExists(any())).thenReturn(false);

        var result=creatorService.nameExists("ferfwe");

        assertFalse(result);
    }

    @Test
    public void testFindByName_NameExists(){
        var creator=new CreatorEntity()
                .setName("Creator")
                .setDescription("CreatorDescription")
                .setDecks(null)
                .setPicture(defaultPictureProfile());

        when(creatorRepository.findCreatorEntityByName(creator.getName())).thenReturn(Optional.of(creator));

        var result=creatorService.findByName(creator.getName());

        assertEquals(creator.getName(), result.getName());
        assertEquals(creator.getDescription(), result.getDescription());
        assertEquals(creator.getPicture(), result.getPicture());
        assertEquals(creator.getDecks(), result.getDecks());
    }

    @Test
    public void testFindByName_NameDoesNotExist(){
        when(creatorRepository.findCreatorEntityByName(any()))
                .thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class,
                () -> this.creatorService.findByName(""));
    }

    @Test
    public void testGetCreatorDetailsByName_NameExists(){
        var creator=new CreatorEntity()
                .setName("Creator")
                .setDescription("Description")
                .setPicture(defaultPictureProfile())
                .setDecks(null);

        when(creatorRepository.findCreatorEntityByName(creator.getName()))
                .thenReturn(Optional.of(creator));

        when(modelMapper.map(creator, CreatorDetailsDto.class))
                .thenReturn(new CreatorDetailsDto()
                        .setName(creator.getName())
                        .setDescription(creator.getDescription())
                        .setPicture(""));

        var result=creatorService.getCreatorDetailsByName(creator.getName());

        assertEquals(creator.getName(), result.getName());
        assertEquals(creator.getDescription(), result.getDescription());
        assertEquals(creator.getPicture().getUrl(), result.getPicture());
    }

    @Test
    public void testGetCreatorDetailsByName_NameDoesNotExist(){

        when(creatorRepository.findCreatorEntityByName(any()))
                .thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class,
                ()->creatorService.findByName(any()));
    }

    @Test
    public void testGetCreatorsNames(){
        var creators=initCreators();

        when(this.creatorRepository.findAll())
                .thenReturn(creators);

        var result=this.creatorService.getCreatorsNames();

        assertEquals(2, result.size());
        assertEquals(result.get(0), "Creator1");
        assertEquals(result.get(1), "Creator2");

    }

    @Test
    public void testParseCreator_HasPicture() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        var creator=new AddCreatorDto()
                .setName("Creator")
                .setDescription("Description")
                .setPicture(new MockMultipartFile("text.txt","text.txt","text/plain", new byte[0]));

        when(pictureService.save(creator.getPicture()))
                .thenReturn(new PictureEntity()
                        .setUrl("text.txt")
                        .setPublicId("text.txt"));


        when(modelMapper.map(creator, CreatorEntity.class))
                .thenReturn(new CreatorEntity()
                        .setName(creator.getName())
                        .setDescription(creator.getDescription())
                        .setPicture(null)
                        .setDecks(null));

        var method=CreatorServiceImpl.class.getDeclaredMethod("parseToCreator", AddCreatorDto.class);
        method.setAccessible(true);
        var result=(CreatorEntity)  method.invoke(creatorService, creator);

        assertEquals(creator.getName(), result.getName());
        assertEquals(creator.getDescription(), result.getDescription());
        assertEquals(creator.getPicture().getOriginalFilename(), result.getPicture().getPublicId());
    }

    @Test
    public void testParseCreator_DoesNotHavePicture() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        var creator=new AddCreatorDto()
                .setName("Creator")
                .setDescription("Description")
                .setPicture(new MockMultipartFile("text.txt","","text/plain", new byte[0]));

        when(pictureRepository.findPictureEntityByUrl(defaultPictureProfile().getUrl()))
                .thenReturn(Optional.of(defaultPictureProfile()));

        when(modelMapper.map(creator, CreatorEntity.class))
                .thenReturn(new CreatorEntity()
                        .setName(creator.getName())
                        .setDescription(creator.getDescription())
                        .setPicture(null)
                        .setDecks(null));

        var method=CreatorServiceImpl.class.getDeclaredMethod("parseToCreator", AddCreatorDto.class);
        method.setAccessible(true);
        var result=(CreatorEntity)  method.invoke(creatorService, creator);

        assertEquals(creator.getName(), result.getName());
        assertEquals(creator.getDescription(), result.getDescription());
        assertEquals(defaultPictureProfile().getPublicId(), result.getPicture().getPublicId());
    }

    private PictureEntity defaultPictureProfile(){
        return new PictureEntity()
                .setUrl("https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_creator_wgjltr.jpg")
                .setPublicId("default_creator_wgjltr.jpg");
    }

    private List<CreatorEntity> initCreators(){
        var creator1=new CreatorEntity()
                .setName("Creator1")
                .setDescription("Description1")
                .setPicture(defaultPictureProfile())
                .setDecks(null);
        var creator2=new CreatorEntity()
                .setName("Creator2")
                .setDescription("Description2")
                .setPicture(defaultPictureProfile())
                .setDecks(null);

        return List.of(creator1, creator2);
    }
}
