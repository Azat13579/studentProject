package ru.innopolis.stc21.med.model;

import lombok.Data;
import javax.persistence.*;
@Data
@Entity
@Table(name="clinics")
public class ClinicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name", nullable=false, length=150)
    private String name;

    @Column(name="address", nullable=false, length=100)
    private String address;

    @Column(name="telephone", nullable=false, length=200)
    private String telephone;

    @Column(name="email", nullable=false, length=200)
    private String email;

    @Column(name="coord_x", nullable=false, length=200)
    private String coordX;

    @Column(name="coord_y", nullable=false, length=100)
    private String coordY;


}