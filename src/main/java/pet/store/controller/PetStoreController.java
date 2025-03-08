package pet.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.service.PetStoreService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {

    @Autowired
    private PetStoreService petStoreService;

    // Create a new pet store
    @PostMapping
    public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
        return petStoreService.savePetStore(petStoreData);
    }
    
    // Update an existing pet store
    @PutMapping("/{petStoreId}")
    public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
        log.info("Updating pet store with ID: {}", petStoreId);
        petStoreData.setPetStoreId(petStoreId);
        return petStoreService.savePetStore(petStoreData);
    }
    
    // Retrieve a pet store by its ID (includes customers and employees)
    @GetMapping("/{petStoreId}")
    public PetStoreData getPetStore(@PathVariable Long petStoreId) {
        return petStoreService.retrievePetStoreById(petStoreId);
    }
    
    // List all pet stores (summary data without customers and employees)
    @GetMapping
    public List<PetStoreData> listPetStores() {
        return petStoreService.retrieveAllPetStores();
    }
    
    // Add an employee to the pet store
    @PostMapping("/{petStoreId}/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreEmployee addEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee employee) {
        log.info("Adding employee to pet store with ID: {}", petStoreId);
        return petStoreService.saveEmployee(petStoreId, employee);
    }
    
    // Add a customer to the pet store
    @PostMapping("/{petStoreId}/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreCustomer addCustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer customer) {
        log.info("Adding customer to pet store with ID: {}", petStoreId);
        return petStoreService.saveCustomer(petStoreId, customer);
    }
    
    // Delete a pet store by its ID
    @DeleteMapping("/{petStoreId}")
    public Map<String, String> deletePetStore(@PathVariable Long petStoreId) {
        log.info("Deleting pet store with ID: {}", petStoreId);
        petStoreService.deletePetStoreById(petStoreId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Pet store with ID " + petStoreId + " deleted successfully");
        return response;
    }
}
