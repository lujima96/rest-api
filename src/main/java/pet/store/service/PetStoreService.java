package pet.store.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
@Transactional
public class PetStoreService {
	
@Autowired
private PetStoreDao petStoreDao;

/**
 * Saves or updates a PetStore using the provided DTO
 */

//Finds or creates pet store

public PetStoreData savePetStore(PetStoreData petStoreData) {
    // 1) Find or create the PetStore
    PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
    
    // 2) Copy fields from DTO to entity
    copyPetStoreFields(petStore, petStoreData);
    
    // 3) Save the entity
    petStore = petStoreDao.save(petStore);
    
    // 4) Return a DTO that reflects what was saved
    return new PetStoreData(petStore);
}

private PetStore findOrCreatePetStore(Long petStoreId) {
	// If petStoreId is null, create a new entity
	if(petStoreId == null) {
		return new PetStore();
	}
	// Othewise, look up PetStore in DB, throw if not found
    return petStoreDao.findById(petStoreId)
            .orElseThrow(() -> new NoSuchElementException(
                "PetStore with ID=" + petStoreId + " was not found."));
}

// This bad boy copies PetStore fields from the DTO to the entity

private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
	petStore.setPetStoreName(petStoreData.getPetStoreName());
	petStore.setPetStorePhone(petStoreData.getPetStorePhone());
	petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
	petStore.setPetStoreCity(petStoreData.getPetStoreCity());
	petStore.setPetStoreState(petStoreData.getPetStoreState());
	petStore.setPetStoreZip(petStoreData.getPetStoreZip());
    
    /*

    




    private String petStoreZip;
     */
}

}
