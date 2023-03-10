package com.animal.doginfo.controllers;

import com.animal.doginfo.models.Breed;
import com.animal.doginfo.models.User;
import com.animal.doginfo.payload.response.MessageResponse;
import com.animal.doginfo.repositories.BreedRepository;
import com.animal.doginfo.repositories.UserRepository;
import com.animal.doginfo.security.services.UserDetailsImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import static java.util.stream.Collectors.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/breeds")

public class BreedController {
    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    UserRepository userRepository;

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

    @PostMapping(value = "favorites/{id}")
    public ResponseEntity<?> addFavorite(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found."));

        Breed breed = breedRepository.getOne(id); // add error handling here
        Set<Breed> breeds = user.getBreeds();
        if(!breeds.contains(breed)){
            breeds.add(breed);
        }
        user.setBreeds(breeds);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse(breed.getBreed_name() + " added to favorites for user " + username));
    }

    @GetMapping()
    @RequestMapping("favorites")
    public Set<Breed> getFavorites(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found."));

        Set<Breed> breeds = user.getBreeds();
        
        return breeds;
    }

    @PostMapping(value = "favorites/delete/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found."));

        Breed breed = breedRepository.getOne(id); // add error handling here
        Set<Breed> breeds = user.getBreeds();
        if(breeds.contains(breed)){
            breeds.remove(breed);
        }
        user.setBreeds(breeds);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse(breed.getBreed_name() + " removed from favorites for user " + username));
    }

    @PostMapping(value = "search/{searchValue}")
    public List<Breed> searchBreed(@PathVariable("searchValue") String searchValue){
        String searchString = searchValue.toLowerCase();
        List<Breed> breeds = list();
        List<Breed> result = breeds.stream()
                .filter(item -> item.getBreed_name().toLowerCase().startsWith(searchString)).collect(toList());
        return result;
    }
}
