package pet.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PetStoreService {

    @Autowired
    private EmployeeDao employeeDao;
    
    @Autowired
    private PetStoreDao petStoreDao;
    
    @Autowired
    private CustomerDao customerDao;

    // Save or update a pet store
    public PetStoreData savePetStore(PetStoreData petStoreData) {
        PetStore petStore = convertToEntity(petStoreData);
        PetStore savedPetStore = petStoreDao.save(petStore);
        return new PetStoreData(savedPetStore);
    }
    
    // Helper: Convert PetStoreData DTO to PetStore entity
    private PetStore convertToEntity(PetStoreData petStoreData) {
        PetStore petStore = new PetStore();
        petStore.setPetStoreId(petStoreData.getPetStoreId());
        petStore.setPetStoreName(petStoreData.getPetStoreName());
        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
        petStore.setPetStoreCity(petStoreData.getPetStoreCity());
        petStore.setPetStoreState(petStoreData.getPetStoreState());
        petStore.setPetStoreZip(petStoreData.getPetStoreZip());
        petStore.setPetStorePhone(petStoreData.getPetStorePhone());
        return petStore;
    }

    // Save or update an employee for the given pet store
    @Transactional
    public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee employeeDto) {
        PetStore petStore = petStoreDao.findById(petStoreId)
                .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + petStoreId));
        
        Employee employee = (employeeDto.getEmployeeId() == null) 
                ? new Employee() 
                : employeeDao.findById(employeeDto.getEmployeeId()).orElse(new Employee());

        employee.setEmployeeFirstName(employeeDto.getEmployeeFirstName());
        employee.setEmployeeLastName(employeeDto.getEmployeeLastName());
        employee.setEmployeePhone(employeeDto.getEmployeePhone());
        employee.setEmployeeJobTitle(employeeDto.getEmployeeJobTitle());
        employee.setPetStore(petStore);
        petStore.getEmployees().add(employee);
        
        Employee savedEmployee = employeeDao.save(employee);
        return new PetStoreEmployee(savedEmployee);
    }

    // Save or update a customer for the given pet store
    @Transactional
    public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer customerDto) {
        PetStore petStore = petStoreDao.findById(petStoreId)
                .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + petStoreId));
        
        Customer customer = (customerDto.getCustomerId() == null)
                ? new Customer()
                : customerDao.findById(customerDto.getCustomerId()).orElse(new Customer());
        
        customer.setCustomerFirstName(customerDto.getCustomerFirstName());
        customer.setCustomerLastName(customerDto.getCustomerLastName());
        customer.setCustomerEmail(customerDto.getCustomerEmail());
        
        if (!customer.getPetStores().contains(petStore)) {
            customer.getPetStores().add(petStore);
        }
        if (!petStore.getCustomers().contains(customer)) {
            petStore.getCustomers().add(customer);
        }
        
        Customer savedCustomer = customerDao.save(customer);
        return new PetStoreCustomer(savedCustomer);
    }
    
    // Retrieve all pet stores (summary view without customers and employees)
    @Transactional(readOnly = true)
    public List<PetStoreData> retrieveAllPetStores() {
        List<PetStore> petStores = petStoreDao.findAll();
        List<PetStoreData> summaries = petStores.stream()
                .map(PetStoreData::new)
                .collect(Collectors.toList());
        // Clear customer and employee collections for summary view
        summaries.forEach(summary -> {
            summary.setCustomers(new HashSet<>());
            summary.setEmployees(new HashSet<>());
        });
        return summaries;
    }
    
    // Retrieve a pet store by its ID (includes customers and employees)
    @Transactional(readOnly = true)
    public PetStoreData retrievePetStoreById(Long petStoreId) {
        PetStore petStore = petStoreDao.findById(petStoreId)
                .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + petStoreId));
        return new PetStoreData(petStore);
    }
    
    // Delete a pet store by its ID
    @Transactional
    public void deletePetStoreById(Long petStoreId) {
        PetStore petStore = petStoreDao.findById(petStoreId)
                .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + petStoreId));
        petStoreDao.delete(petStore);
    }
}
