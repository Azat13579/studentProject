package ru.innopolis.stc21.med.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import ru.innopolis.stc21.med.configs.Role;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name="USERS")
public class UsersEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="username", nullable=false, length=150)
    private String username;

    @Column(name="password", nullable=false, length=100)
    private String password;

    @Column(name="first_name", nullable=false, length=200)
    private String first_name;

    @Column(name="second_name", nullable=false, length=200)
    private String second_name;

    @Column(name="last_name", nullable=false, length=200)
    private String last_name;

    @Column(name="email", nullable=false, length=100)
    private String email;

    @Column(name="snils", nullable=false, length=100)
    private String snils;

    //@Column(name="snils", nullable=false, length=100)
    //private byte[] img;
   // @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "pacient_info_id", referencedColumnName = "id")
    //private PacientInfoEntity pacientInfo;

    @Transient
    private Boolean active;
    @Transient
    public List<Role> authorities;
    @Transient
    public boolean accountNonExpired;
    @Transient
    public boolean accountNonLocked;
    @Transient
    public boolean credentialsNonExpired;
    @Transient
    public boolean enabled;

    @Override
    public String toString() {
        return this.first_name + " " + this.second_name + " " + this.last_name;
    }



}