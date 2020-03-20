package ru.innopolis.stc21.med.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc21.med.model.ClinicEntity;

@Repository
public interface ClinicRepository extends JpaRepository<ClinicEntity, Long> {
    //List<ClinicsEntity> getAllByClinic_id(Long id);
    ClinicEntity getClinicsEntityById(Long id);
}
