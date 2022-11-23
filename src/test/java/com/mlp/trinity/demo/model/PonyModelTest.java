package com.mlp.trinity.demo.model;

import com.mlp.trinity.demo.models.PonyModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PonyModelTest {

    @Test
    public void id_SetterAndGetter(){
        PonyModel input = new PonyModel();
        input.setId(1L);
        assertEquals(1L, input.getId());
    }
    @Test
    public void name_SetterAndGetter(){
        PonyModel input = new PonyModel();
        input.setName("Cali");
        assertEquals("Cali", input.getName());
    }
    @Test
    public void health_SetterAndGetter(){
        PonyModel input = new PonyModel();
        input.setHealth(10);
        assertEquals(10, input.getHealth());
    }
    @Test
    public void isAlive_SetterAndGetter(){
        PonyModel input = new PonyModel();
        input.setAlive(false);
        assertEquals(false, input.getAlive());
    }
    @Test
    public void Age_SetterAndGetter(){
        PonyModel input = new PonyModel();
        input.setAge(20);
        assertEquals(20, input.getAge());
    }
    @Test
    public void hasHorn_SetterAndGetter(){
        PonyModel input = new PonyModel();
        input.setHasHorn(true);
        assertEquals(true, input.getHasHorn());
    }
    @Test
    public void hasWings_SetterAndGetter(){
        PonyModel input = new PonyModel();
        input.setHasWings(true);
        assertEquals(true, input.getHasWings());
    }
    @Test
    public void PontModel_ToString(){
        PonyModel input = new PonyModel();
        String expected = "PonyModel{id=null, name='null', health=null, isAlive=null, age=null, hasHorn=null, hasWings=null}";
        assertEquals(expected, input.toString());
    }
}
