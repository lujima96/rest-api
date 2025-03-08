package pet.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    
    private String employeeFirstName;
    private String employeeLastName;
    private String employeePhone;
    private String employeeJobTitle;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_store_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private PetStore petStore;

	public Long getId() {
	return employeeId;
	}

	public Object getName() {
		return employeeFirstName + " " + employeeLastName;
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

	public Object getPosition() {
		return employeeJobTitle;
	}


}
