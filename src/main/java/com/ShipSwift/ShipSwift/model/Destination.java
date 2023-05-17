package com.ShipSwift.ShipSwift.model;
import com.ShipSwift.ShipSwift.model.enums.ShipmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "destinations")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1, max = 250, message = "Name Receiver should have between 1 and 250 characters")
    private String nameReceiver;

    @Column(nullable = false)
    @Size(min = 1, max = 250, message = "Last Name Receiver should have between 1 and 250 characters")
    private String lastNameReceiver;

    @Column(nullable = false)
    @Size(min = 10, max = 25, message = "Phone Number should have between 10 and 25 characters")
    private String phoneNumberReceiver;

    @Column(nullable = false)
    @Size(max = 250, message = "Email should have max 250 characters")
    @Email(message = "Email should be valid")
    private String emailReceiver;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShipmentType type;

    @OneToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;

    @OneToOne
    @JoinColumn(name = "residence_id", referencedColumnName = "id")
    private Residence residence;

}
