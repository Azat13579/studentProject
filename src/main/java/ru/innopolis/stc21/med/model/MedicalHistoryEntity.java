package ru.innopolis.stc21.med.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import ru.innopolis.stc21.med.configs.Role;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="medical_history")
public class MedicalHistoryEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="date_visit", nullable=false)
    private Date date_visit;

    @ManyToOne (optional=false, fetch = FetchType.EAGER)
    @JoinColumn (name="id_user",referencedColumnName="id")
    private UsersEntity user;

    @Column(name="neiro_diagtose", nullable=false, length=200)
    private String neiro_diagtose;

    @Column(name="accuracy", nullable=false)
    private String accuracy;

    @Column(name="comment_user", nullable=false)
    private String comment;

    @Column(name="img_name", nullable=false, length=150)
    private String imgName;

   // @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "pacient_info_id", referencedColumnName = "id")
    //private PacientInfoEntity pacientInfo;

    @Override
    public String toString() {
        return "id = " + this.id + " , login = " + this.neiro_diagtose;
    }

}