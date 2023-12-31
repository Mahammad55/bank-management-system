package az.orient.bankdemo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "customers")
@DynamicInsert
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String surname;

    String address;

    @Temporal(TemporalType.DATE)
    Date dob;

    String phone;

    String pin;

    String seria;

    String cif;

    @ColumnDefault(value = "1")
    Integer active;

    @CreationTimestamp
    LocalDateTime dateDate;
}
