package com.mlp.trinity.demo.service;

import com.mlp.trinity.demo.exception.PonyNameAlreadyExists;
import com.mlp.trinity.demo.exception.PonyNameLengthEception;
import com.mlp.trinity.demo.exception.PonyNotFoundException;
import com.mlp.trinity.demo.models.PonyModel;
import com.mlp.trinity.demo.repository.PonyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PonyServiceTest {
    private PonyRepository mockPonyRepository;
    private PonyModel insideDatabase;
   private PonyService ponyService;

    @BeforeEach
    public void setup(){
        insideDatabase = new PonyModel();
        insideDatabase.setId(1L);
        insideDatabase.setName("Cali");
        insideDatabase.setAge(3);
        insideDatabase.setHealth(20);
        insideDatabase.setAlive(true);
        insideDatabase.setHasHorn(false);
        insideDatabase.setHasWings(true);
        mockPonyRepository = mock(PonyRepository.class);
        ponyService = new PonyService(mockPonyRepository);
        when(mockPonyRepository.findById(1L)).thenReturn(Optional.of(insideDatabase));
        when(mockPonyRepository.save(any())).thenAnswer(method -> method.getArgument(0));
    }
    @Test
    public void getAll_CallsRepo(){
        ponyService.getAllPonys();
        Mockito.verify(mockPonyRepository).findAll();
    }
    @Test
    public void getById_getByRepo(){
        Mockito.when(mockPonyRepository.findById(1L)).thenReturn(Optional.of(insideDatabase));

        PonyModel actual = ponyService.getPonyById(1L);

        assertEquals(1L, actual.getId());
    }
    @Test
    public void getById_ifSearchResultIsEmptyThenReturnError(){
        PonyNotFoundException exception = assertThrows(PonyNotFoundException.class, () -> {
            ponyService.getPonyById(2L);
        });
        assertEquals("Pony 2 not found.", exception.getMessage());
    }
    @Test
    public void createPony_IfNameAlreadyExistsReturnError(){
        PonyModel input = new PonyModel();
        input.setName("Cali");
        PonyNameAlreadyExists exception = assertThrows(PonyNameAlreadyExists.class, () -> {
            Mockito.when(mockPonyRepository.existsByName(input.getName())).thenReturn(true);
            ponyService.createPony(input);
        });
        assertEquals("Cali already exists.", exception.getMessage());
    }
    @Test
    public void createPony_IfNameLengthIsNotOver2(){
        PonyModel input = new PonyModel();
        input.setName("B");
        PonyNameLengthEception exception = assertThrows(PonyNameLengthEception.class, () -> {
            ponyService.createPony(input);
        });
        assertEquals("B is too short.", exception.getMessage());
    }
    @Test
    public void createPony_TheFirstLetterIsUpperCase(){
        PonyModel input = new PonyModel();
        input.setName("bob");
        PonyModel actual = ponyService.createPony(input);
        assertEquals("Bob", actual.getName());
    }
    @Test
    public void createPony_calledByRepo(){
        PonyModel input = new PonyModel();
        input.setName("bob");
        Mockito.when(mockPonyRepository.existsByName(input.getName())).thenReturn(false);
        ponyService.createPony(input);
        verify(mockPonyRepository).save(any());
    }
    @Test
    public void updatePony_HealthShouldUpdateIfNotNull(){
        PonyModel input = new PonyModel();
        input.setHealth(10);

        PonyModel actual = ponyService.updatePony(1L, input);

        verify(mockPonyRepository).save(any());
        assertEquals(10, actual.getHealth());
    }
    @Test
    public void updatePony_IsAliveShouldUpdateIfNotNull(){
        PonyModel input = new PonyModel();
        input.setAlive(false);

        PonyModel actual = ponyService.updatePony(1L, input);

        verify(mockPonyRepository).save(any());
        assertEquals(false, actual.getAlive());
    }
    @Test
    public void updatePony_AgeShouldUpdateIfNotNull(){
        PonyModel input = new PonyModel();
        input.setAge(10);

        PonyModel actual = ponyService.updatePony(1L, input);

        verify(mockPonyRepository).save(any());
        assertEquals(10, actual.getAge());
    }
    @Test
    public void updatePony_HasHornsShouldUpdateIfNotNull(){
        PonyModel input = new PonyModel();
        input.setHasHorn(true);

        PonyModel actual = ponyService.updatePony(1L, input);

        verify(mockPonyRepository).save(any());
        assertEquals(true, actual.getHasHorn());
    }
    @Test
    public void updatePony_HasWingsShouldUpdateIfNotNull(){
        PonyModel input = new PonyModel();
        input.setHasWings(false);

        PonyModel actual = ponyService.updatePony(1L, input);
        assertEquals(false, actual.getHasWings());
    }
    @Test
    public void updatePony_HealthShouldUpdateIfNull(){
        PonyModel input = new PonyModel();
        input.setHealth(null);

        PonyModel actual = ponyService.updatePony(1L, input);

        verify(mockPonyRepository).save(any());
        assertEquals(20, actual.getHealth());
    }
    @Test
    public void updatePony_IsAliveShouldUpdateIfNull(){
        PonyModel input = new PonyModel();
        input.setAlive(null);

        PonyModel actual = ponyService.updatePony(1L, input);

        verify(mockPonyRepository).save(any());
        assertEquals(true, actual.getAlive());
    }
    @Test
    public void updatePony_AgeShouldUpdateIfNull(){
        PonyModel input = new PonyModel();
        input.setAge(null);

        PonyModel actual = ponyService.updatePony(1L, input);

        verify(mockPonyRepository).save(any());
        assertEquals(3, actual.getAge());
    }
    @Test
    public void updatePony_HasHornsShouldUpdateIfNull(){
        PonyModel input = new PonyModel();
        input.setHasHorn(null);

        PonyModel actual = ponyService.updatePony(1L, input);

        verify(mockPonyRepository).save(any());
        assertEquals(false, actual.getHasHorn());
    }
    @Test
    public void updatePony_HasWingsShouldUpdateIfNull(){
        PonyModel input = new PonyModel();
        input.setHasWings(null);

        PonyModel actual = ponyService.updatePony(1L, input);
        assertEquals(true, actual.getHasWings());
    }
    @Test
    public void deletePony_CalledByRepo(){
        Mockito.when(mockPonyRepository.existsById(1L)).thenReturn(true);

        ponyService.deletePony(1L);

        verify(mockPonyRepository).deleteById(1L);
    }
    @Test
    public void delete_ifInputDoesNotExistsThenDoesNothing(){
        Mockito.when(mockPonyRepository.existsById(2L)).thenReturn(false);

        ponyService.deletePony(2L);

        verify(mockPonyRepository, times(0)).deleteById(2L);
    }
}
