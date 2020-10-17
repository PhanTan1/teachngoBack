package be.teachngo.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String street;
    @Column(name = "street_number", length = 6, nullable = false)
    private String streetNumber;
    @Column(name = "postal_code", length = 8, nullable = false)
    private String postalCode;
    @Column(name = "Country", length = 45, nullable = false)
    private String country;
    @Column(name = "box_number", length = 6)
    private String boxNumber;


}
