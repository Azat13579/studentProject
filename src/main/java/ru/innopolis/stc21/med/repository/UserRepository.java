package ru.innopolis.stc21.med.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc21.med.model.UsersEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByUsername(String username);

}

