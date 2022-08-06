package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddCreatorDto;
import bg.softuni.playing_cards_shop.models.dtos.AddDistributorDto;
import bg.softuni.playing_cards_shop.models.entities.CreatorEntity;
import bg.softuni.playing_cards_shop.models.entities.DistributorEntity;
import bg.softuni.playing_cards_shop.models.entities.PictureEntity;
import bg.softuni.playing_cards_shop.models.views.DistributorDetailsDto;
import bg.softuni.playing_cards_shop.repositories.CreatorRepository;
import bg.softuni.playing_cards_shop.repositories.DistributorRepository;
import bg.softuni.playing_cards_shop.repositories.PictureRepository;
import bg.softuni.playing_cards_shop.services.cloudinary.CloudinaryService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DistributorImplTest {

    @Mock
    private DistributorRepository distributorRepository;

    @InjectMocks
    private DistributorServiceImpl distributorService;

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
        distributorService=new DistributorServiceImpl(distributorRepository,pictureService, modelMapper);
    }

    @Test
    public void testBrandExists_BrandExists(){
        var distributor=getTestDistributor();

        when(distributorRepository.existsByBrand(distributor.getBrand()))
                .thenReturn(true);

        assertTrue(distributorService.brandExists(distributor.getBrand()));
    }

    @Test
    public void testBrandExists_BrandDoesNotExist(){
        when(distributorRepository.existsByBrand(any()))
                .thenReturn(false);

        assertFalse(distributorService.brandExists(any()));
    }

    @Test
    public void testFindDistributorByBrand_BrandExists(){
        var distributor=getTestDistributor();

        when(distributorRepository.findDistributorEntityByBrand(distributor.getBrand()))
                .thenReturn(Optional.of(distributor));

        var result=distributorService.findDistributorByBrand(distributor.getBrand());

        assertEquals(distributor.getBrand(), result.getBrand());
        assertEquals(distributor.getDescription(), result.getDescription());
        assertEquals(distributor.getSiteUrl(), result.getSiteUrl());
        assertEquals(distributor.getPicture(), result.getPicture());
        assertEquals(distributor.getDecks(), result.getDecks());
    }

    @Test
    public void testFindDistributorByBrand_BrandDoesNotExist(){
        when(distributorRepository.findDistributorEntityByBrand(any()))
                .thenReturn(Optional.empty());

        assertThrows(
                ObjectNotFoundException.class,
                ()->distributorService.findDistributorByBrand(any())
        );
    }

    @Test
    public void testGetDistributorNames(){
        var distributors=getTestDistributors();

        when(distributorRepository.findAll())
                .thenReturn(distributors);

        var result=distributorService.getDistributorsNames();

        assertEquals(distributors.size(), result.size());
        assertEquals(distributors.get(0).getBrand(), result.get(0));
        assertEquals(distributors.get(1).getBrand(), result.get(1));
        assertEquals(distributors.get(2).getBrand(), result.get(2));
    }

    @Test
    public void testFindDistributorDetailsByBrand_BrandExists(){
        var distributor=getTestDistributor();

        when(distributorRepository.findDistributorEntityByBrand(distributor.getBrand()))
                .thenReturn(Optional.of(distributor));

        when(modelMapper.map(distributor, DistributorDetailsDto.class))
                .thenReturn(new DistributorDetailsDto()
                        .setBrand(distributor.getBrand())
                        .setDescription(distributor.getDescription())
                        .setPicture(distributor.getPicture().getUrl())
                        .setSiteUrl(distributor.getSiteUrl()));

        var result=distributorService.findDistributorDetailsByBrand(distributor.getBrand());

        assertEquals(distributor.getBrand(), result.getBrand());
        assertEquals(distributor.getDescription(), result.getDescription());
        assertEquals(distributor.getSiteUrl(), result.getSiteUrl());
        assertEquals(distributor.getPicture().getUrl(), result.getPicture());
    }

    @Test
    public void testFindDistributorDetailsByBrand_BrandDoesNotExist(){
        when(distributorRepository.findDistributorEntityByBrand(any()))
                .thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class,
                ()->distributorService.findDistributorDetailsByBrand(any()));
    }


    @Test
    public void testParseDistributor_HasPicture() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var distributor=new AddDistributorDto()
                .setBrand("Distributor")
                .setDescription("Description")
                .setSiteUrl("https://site.com")
                .setPicture(new MockMultipartFile("text.txt","text.txt","text/plain", new byte[0]));

        when(pictureService.save(distributor.getPicture()))
                .thenReturn(new PictureEntity()
                        .setUrl("text.txt")
                        .setPublicId("text.txt"));


        when(modelMapper.map(distributor, DistributorEntity.class))
                .thenReturn(new DistributorEntity()
                        .setBrand(distributor.getBrand())
                        .setDescription(distributor.getDescription())
                        .setSiteUrl(distributor.getSiteUrl())
                        .setPicture(null)
                        .setDecks(null));

        var method=DistributorServiceImpl.class.getDeclaredMethod("parseToDistributor", AddDistributorDto.class);
        method.setAccessible(true);
        var result=(DistributorEntity)  method.invoke(distributorService, distributor);

        assertEquals(distributor.getBrand(), result.getBrand());
        assertEquals(distributor.getDescription(), result.getDescription());
        assertEquals(distributor.getSiteUrl(), result.getSiteUrl());
        assertEquals(distributor.getPicture().getOriginalFilename(), result.getPicture().getPublicId());
    }

    @Test
    public void testParseDistributor_DoesNotHavePicture() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var distributor=new AddDistributorDto()
                .setBrand("Distributor")
                .setDescription("Description")
                .setSiteUrl("https://site.com")
                .setPicture(new MockMultipartFile("text.txt","","text/plain", new byte[0]));

        when(pictureRepository.findPictureEntityByUrl(defaultDistributorPicture().getUrl()))
                .thenReturn(Optional.of(defaultDistributorPicture()));


        when(modelMapper.map(distributor, DistributorEntity.class))
                .thenReturn(new DistributorEntity()
                        .setBrand(distributor.getBrand())
                        .setDescription(distributor.getDescription())
                        .setSiteUrl(distributor.getSiteUrl())
                        .setPicture(null)
                        .setDecks(null));

        var method=DistributorServiceImpl.class.getDeclaredMethod("parseToDistributor", AddDistributorDto.class);
        method.setAccessible(true);
        var result=(DistributorEntity)  method.invoke(distributorService, distributor);

        assertEquals(distributor.getBrand(), result.getBrand());
        assertEquals(distributor.getDescription(), result.getDescription());
        assertEquals(distributor.getSiteUrl(), result.getSiteUrl());
        assertEquals(defaultDistributorPicture().getPublicId(), result.getPicture().getPublicId());
    }

    private PictureEntity defaultDistributorPicture(){
        return new PictureEntity()
                .setUrl("https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_distributor_ogp1ju.png")
                .setPublicId("default_distributor_ogp1ju.png");
    }

    private DistributorEntity getTestDistributor(){
        return new DistributorEntity()
                .setBrand("Brand")
                .setDescription("Description")
                .setSiteUrl("https://site.com")
                .setPicture(defaultDistributorPicture())
                .setDecks(null);
    }

    private List<DistributorEntity> getTestDistributors(){
        var distributor1=new DistributorEntity()
                .setBrand("Brand1")
                .setDescription("Description1")
                .setSiteUrl("https://site1.com")
                .setPicture(defaultDistributorPicture())
                .setDecks(null);
        var distributor2=new DistributorEntity()
                .setBrand("Brand2")
                .setDescription("Description2")
                .setSiteUrl("https://site2.com")
                .setPicture(defaultDistributorPicture())
                .setDecks(null);

        var distributor3=new DistributorEntity()
                .setBrand("Brand3")
                .setDescription("Description3")
                .setSiteUrl("https://site3.com")
                .setPicture(defaultDistributorPicture())
                .setDecks(null);

        return List.of(distributor1, distributor2, distributor3);
    }
}
