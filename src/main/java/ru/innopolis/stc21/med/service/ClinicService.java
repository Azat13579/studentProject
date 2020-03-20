package ru.innopolis.stc21.med.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc21.med.exception.RecordNotFoundException;
import ru.innopolis.stc21.med.model.ClinicEntity;
import ru.innopolis.stc21.med.repository.ClinicRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClinicService {
    @Autowired
    ClinicRepository repository;

    public List<ClinicEntity> getAllClinics() {
        List<ClinicEntity> clinicsList = repository.findAll();
        if (clinicsList.size() > 0) {
            return clinicsList;
        } else {
            return new ArrayList<ClinicEntity>();
        }
    }

/*
    public ClinicEntity findClinicsEntitiesByClinic_id(Long id) throws RecordNotFoundException {

        ClinicEntity clinic = repository.findClinicsEntitiesByClinic_id(id);

*/
/*        if(clinic.isPresent()) {
            return clinic.get();
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }*//*

        return null;
    }
*/


    /**
     * Delete!!!
     */
/*    public UsersEntity getUserByName(String username) throws RecordNotFoundException
    {
        Optional<UsersEntity> user = Optional.ofNullable(repository.findByUsername(username));

        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }

    public UsersEntity create(String username, String password,
                              String first_name, String second_name, String last_name,
                              String email, String snils) {
            UsersEntity entityNew = new UsersEntity();
            //`entityNew.setId(4L);
            entityNew.setActive(true);
            entityNew.setUsername(username);
            entityNew.setPassword(password);
            entityNew.setAuthorities(ImmutableList.of(Role.PACIENT));
            entityNew.setAccountNonExpired(true);
            entityNew.setAccountNonLocked(true);
            entityNew.setEnabled(true);
            entityNew.setCredentialsNonExpired(true);
            entityNew.setFirst_name(first_name);
            entityNew.setSecond_name(second_name);
            entityNew.setLast_name(last_name);
            entityNew.setEmail(email);
            entityNew.setSnils(snils);
            return repository.save(entityNew);
    }

    public void save(UsersEntity usersEntity)
    {
        repository.save(usersEntity);
    }

    public void deleteUserById(Long id) throws RecordNotFoundException
    {
        Optional<UsersEntity> user = repository.findById(id);

        if(user.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        *//*User usr = new User();*//*
        // usersService.setUsername(username);
        // usersService.setPassword("password");
        //  usersService.setAuthorities(ImmutableList.of(Role.USER));
        // usersService.setAccountNonExpired(true);
        //  usersService.setAccountNonLocked(true);
        //  usersService.setEnabled(true);
        // usersService.setCredentialsNonExpired(true);
        UsersEntity usersEntity = null;
        usersEntity = repository.findByUsername(username);
        usersEntity.setUsername(username);
        //usersEntity.setPassword("password");
        usersEntity.setAuthorities(ImmutableList.of(Role.PACIENT));
        usersEntity.setAccountNonExpired(true);
        usersEntity.setAccountNonLocked(true);
        usersEntity.setEnabled(true);
        usersEntity.setCredentialsNonExpired(true);
        return usersEntity;
    }*/
}
