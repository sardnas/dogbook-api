package com.animal.doginfo.controllers;

import com.animal.doginfo.models.Breed;
import com.animal.doginfo.repositories.BreedRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/breeds")
public class BreedController {
    @Autowired
    private BreedRepository breedRepository;

    @GetMapping
    public List<Breed> list(){
        return breedRepository.findAll();
    }
    @GetMapping
    @RequestMapping("{id}")
    public Breed get(@PathVariable Long id){
        return breedRepository.getOne(id);
    }

    @PostMapping
    public Breed create(@RequestBody final Breed session){
        return breedRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        // Also need to check for children records before deleting.
        breedRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Breed update(@PathVariable Long id, @RequestBody Breed session){
        Breed existingSession = breedRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "breed_id");
        return breedRepository.saveAndFlush(existingSession);
    }
}
