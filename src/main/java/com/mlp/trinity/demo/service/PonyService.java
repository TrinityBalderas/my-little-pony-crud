package com.mlp.trinity.demo.service;

import com.mlp.trinity.demo.exception.PonyNameAlreadyExists;
import com.mlp.trinity.demo.exception.PonyNameLengthEception;
import com.mlp.trinity.demo.exception.PonyNotFoundException;
import com.mlp.trinity.demo.models.PonyModel;
import com.mlp.trinity.demo.repository.PonyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PonyService {
    private PonyRepository ponyRepository;
    @Autowired
    public PonyService(PonyRepository ponyRepository){
        this.ponyRepository = ponyRepository;
    }

    public List<PonyModel> getAllPonys() {
        return ponyRepository.findAll();
    }

    public PonyModel getPonyById(Long id){
        Optional<PonyModel> seaarchResult = ponyRepository.findById(id);
        if(seaarchResult.isEmpty()){
            throw new PonyNotFoundException("Pony " + id + " not found.");
        }
        return seaarchResult.get();
    }
    public PonyModel createPony (PonyModel body){
        if(ponyRepository.existsByName(body.getName())){
            throw new PonyNameAlreadyExists(body.getName() + " already exists.");
        }
        if(body.getName().length() < 2){
            throw new PonyNameLengthEception(body.getName() + " is too short.");
        }
        PonyModel pony = new PonyModel();
        String name = body.getName();
        String letter = (name.substring(0,1));
        letter = letter.toUpperCase();
        String restOfWord = (name.substring(1));
        String newName = letter+restOfWord;
        pony.setName(newName);
        PonyModel save = ponyRepository.save(pony);
        return save;
    }
    public PonyModel updatePony (Long id, PonyModel body){
        body.setId(id);
        PonyModel original = ponyRepository.findById(id).get();
        if(body.getHealth() != null){
            original.setHealth(body.getHealth());
        }
        if(body.getAlive() != null){
            original.setAlive(body.getAlive());
        }
        if(body.getAge() != null){
            original.setAge(body.getAge());
        }
        if(body.getHasHorn() != null){
            original.setHasHorn(body.getHasHorn());
        }
        if(body.getHasWings() != null){
            original.setHasWings(body.getHasWings());
        }
        return ponyRepository.save(original);
    }
    public void deletePony(Long id){
        if(ponyRepository.existsById(id)){
            ponyRepository.deleteById(id);
        }
    }
}
