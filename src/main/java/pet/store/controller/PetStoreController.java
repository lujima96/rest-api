package pet.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet-store")
@Slf4j
public class PetStoreController {

    @Autowired
    private PetStoreService petStoreService;

    @PostMapping
    public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
        return petStoreService.savePetStore(petStoreData);
    }
    
    @PutMapping("/{petStoreId}")
    public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
        log.info("Updating pet store with ID: {}", petStoreId);
        // Set the pet store ID in the DTO using the value from the URI
        petStoreData.setPetStoreId(petStoreId);
        // Call the service to save the updated pet store data
        return petStoreService.savePetStore(petStoreData);
    }



}
