package com.mlp.trinity.demo.controller;

import com.mlp.trinity.demo.exception.PonyNameAlreadyExists;
import com.mlp.trinity.demo.exception.PonyNameLengthEception;
import com.mlp.trinity.demo.exception.PonyNotFoundException;
import com.mlp.trinity.demo.models.PonyModel;
import com.mlp.trinity.demo.response.PonyResponse;
import com.mlp.trinity.demo.service.PonyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PonyControllerTest {

    PonyService mockPonyService;

    PonyController ponyController;

    PonyModel insideDatabase;

    @BeforeEach
    public void set(){
        mockPonyService = mock(PonyService.class);
        ponyController = new PonyController(mockPonyService);
        insideDatabase = new PonyModel();
        insideDatabase.setName("Cali");
    }
    @Test
    public void getAll_CallsService(){
        ponyController.getAllPonys();
        verify(mockPonyService).getAllPonys();
    }
    @Test
    public void getAll_ReturnValueFromServicee(){
        when(mockPonyService.getAllPonys()).thenReturn(null);
        assertEquals(null, ponyController.getAllPonys());
    }
    @Test
    public void getNameById_shouldReturn404DoesNotExists(){
        Mockito.when(mockPonyService.getPonyById(2L)).thenThrow(new PonyNotFoundException("Pony 2 not found."));

        ResponseEntity<PonyModel> actual = ponyController.getPontById(2L);

        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }

    @Test
    public void createPony_shouldReturn400WhenRecordDoesNotReachOver2Length(){
        PonyModel input = new PonyModel();
        input.setName("B");
        Mockito.when(mockPonyService.createPony(input)).thenThrow(new PonyNameLengthEception("B is too short."));

        ResponseEntity<PonyModel> actual = ponyController.createPony(input);

        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }
    @Test
    public void createPony_shouldReturn400WhenNameAlreadyExists(){
        PonyModel input = new PonyModel();
        input.setName("Cali");
        Mockito.when(mockPonyService.createPony(input)).thenThrow(new PonyNameAlreadyExists("Cali already exists."));

        ResponseEntity<PonyModel> actual = ponyController.createPony(input);

        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }
    @Test
    public void updatePony_shouldReturn404WhenNameIsNotFound(){
        PonyModel input = new PonyModel();
        Mockito.when(mockPonyService.updatePony(2L, input)).thenThrow(new PonyNotFoundException("Pony 2 not found."));

        ResponseEntity<PonyModel> actual = ponyController.updatePony(2L, input);

        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }
    @Test
    public void deletePony_deletedByService(){
        ponyController.deletePony(1L);
        Mockito.verify(mockPonyService).deletePony(1L);
    }
    @Test
    public void delete_taskCompletedMessage(){
        PonyModel input = new PonyModel();

        ResponseEntity<PonyResponse> actual = ponyController.deletePony(1L);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("Pony 1 was deleted.", actual.getBody().message);
    }
}
