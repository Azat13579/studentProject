package ru.innopolis.stc21.med.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc21.med.exception.RecordNotFoundException;
import ru.innopolis.stc21.med.model.MedicalHistoryEntity;
import ru.innopolis.stc21.med.model.UsersEntity;
import ru.innopolis.stc21.med.repository.MedicalHistoryRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryService {
    @Autowired
    MedicalHistoryRepository repository;

    public List<MedicalHistoryEntity> getAllByUser(UsersEntity usersEntity)
    {
        List<MedicalHistoryEntity> historyList = repository.getAllByUserOrderByIdDesc(usersEntity);
        if(historyList.size() > 0) {
            return historyList;
        } else {
            return new ArrayList<MedicalHistoryEntity>();
        }
    }

   public MedicalHistoryEntity create(Date date, UsersEntity usersEntity) {
            MedicalHistoryEntity entityNew = new MedicalHistoryEntity();
            entityNew.setUser(usersEntity);
            entityNew.setDate_visit(date);
            entityNew.setNeiro_diagtose(" ");
            entityNew.setAccuracy(" ");
            entityNew.setImgName(" ");
            entityNew.setComment(" ");
            return repository.save(entityNew);
    }

    public MedicalHistoryEntity save(MedicalHistoryEntity medicalHistoryEntity) {
        return repository.save(medicalHistoryEntity);
    }

    public MedicalHistoryEntity getById(Long id) throws RecordNotFoundException {
        Optional<MedicalHistoryEntity> history = repository.findById(id);
        if (history.isPresent()) {
            return history.get();
        } else {
            throw new RecordNotFoundException("No history record exist for given id");
        }
    }
   /* public void save(UsersEntity usersEntity)
    {
        repository.save(usersEntity);
    }*/

    public void deleteHistoryById(Long id) throws RecordNotFoundException {
        Optional<MedicalHistoryEntity> history = repository.findById(id);

        if (history.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }

    public MedicalHistoryEntity getHistoryById(Long id) throws RecordNotFoundException {
        Optional<MedicalHistoryEntity> history = repository.findById(id);
        if (history.isPresent()) {
            return history.get();
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }
}
