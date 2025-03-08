package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {

    private Long petStoreId;
    private String petStoreName;
    private String petStoreAddress;
    private String petStoreCity;
    private String petStoreState;
    private String petStoreZip;
    private String petStorePhone;


    private Set<PetStoreCustomer> customers = new HashSet<>();
    private Set<PetStoreEmployee> employees = new HashSet<>();


    // Constructor that takes a PetStore entity, maps all fields

    public PetStoreData(PetStore petStore) {
        this.petStoreId = petStore.getPetStoreId();
        this.petStoreName = petStore.getPetStoreName();
        this.petStoreAddress = petStore.getPetStoreAddress();
        this.petStoreCity = petStore.getPetStoreCity();
        this.petStoreState = petStore.getPetStoreState();
        this.petStoreZip = petStore.getPetStoreZip();
        this.petStorePhone = petStore.getPetStorePhone();

        // Convert PetStore -> Set of PetStoreCustomer DTOs
        if (petStore.getCustomers() != null) {
            petStore.getCustomers().forEach(customer -> 
                this.customers.add(new PetStoreCustomer(customer)));
        }

        // Convert PetStore -> Set of PetStoreEmployee DTOs
        if (petStore.getEmployees() != null) {
            petStore.getEmployees().forEach(employee -> 
                this.employees.add(new PetStoreEmployee(employee)));
        }
    }
    
    // Inner DTO class for Customers

    @Data
    @NoArgsConstructor
    public static class PetStoreCustomer {
    

        private Long customerId;
        private String customerFirstName;
        private String customerLastName;
        private String customerEmail;

        // Constructor mapping from Customer entity
        public PetStoreCustomer(Customer customer) {
            this.customerId = customer.getCustomerId();
            this.customerFirstName = customer.getCustomerFirstName();
            this.customerLastName = customer.getCustomerLastName();
            this.customerEmail = customer.getCustomerEmail();
        }
    }

    @Data
    @NoArgsConstructor
    public static class PetStoreEmployee {
        // No @Id, no @GeneratedValue here — this is just a DTO

        private Long employeeId;
        private String employeeFirstName;
        private String employeeLastName;
        private String employeePhone;
        private String employeeJobTitle;

        // Constructor mapping from Employee entity
        public PetStoreEmployee(Employee employee) {
            this.employeeId = employee.getEmployeeId();
            this.employeeFirstName = employee.getEmployeeFirstName();
            this.employeeLastName = employee.getEmployeeLastName();
            this.employeePhone = employee.getEmployeePhone();
            this.employeeJobTitle = employee.getEmployeeJobTitle();
        }

		public void setName(Object name) {
		    if (name instanceof String) {
		        String fullName = (String) name;
		        String[] parts = fullName.split(" ", 2);
		        this.employeeFirstName = parts[0];
		        this.employeeLastName = (parts.length > 1) ? parts[1] : "";
		    } else {
		        throw new IllegalArgumentException("Name must be a String");
		    }
			
		}

		public Object getName() {
		    return employeeFirstName + " " + employeeLastName;
		}

		public Object getPosition() {
			return employeeJobTitle;
		}

		public void setPosition(Object position) {
		    if (position instanceof String) {
		        String fullName = (String) position;
		        String[] parts = fullName.split(" ", 2);
		        this.employeeFirstName = parts[0];
		        this.employeeLastName = (parts.length > 1) ? parts[1] : "";
		    } else {
		        throw new IllegalArgumentException("Name must be a String");
		    }
			
		}


    }
}
