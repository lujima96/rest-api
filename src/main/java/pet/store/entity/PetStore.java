package pet.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data

public class PetStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petStoreId;

    @Column(name = "pet_store_name", unique = true, nullable = false)
    private String petStoreName;

    @Column(name = "pet_store_address")
    private String petStoreAddress;

    @Column(name = "pet_store_city")
    private String petStoreCity;

    @Column(name = "pet_store_state")
    private String petStoreState;

    @Column(name = "pet_store_zip")
    private String petStoreZip;

    @Column(name = "pet_store_phone")
    private String petStorePhone;

    // Many-to-Many relationship with Customer
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "pet_store_customer",
        joinColumns = @JoinColumn(name = "pet_store_id"),
        inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Customer> customers = new HashSet<>();

    // One-to-Many relationship with Employee
    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Employee> employees = new HashSet<>();

	public Object getId() {
		return petStoreId;
	}
	}

