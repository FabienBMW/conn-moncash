package fr.smartds.connmoncash.service.impl;


import fr.smartds.connmoncash.entities.Transfer;
import fr.smartds.connmoncash.exceptions.DAOException;
import fr.smartds.connmoncash.exceptions.FormValidationException;
import fr.smartds.connmoncash.repositories.TransferRepository;
import fr.smartds.connmoncash.service.TransferIService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransferService implements TransferIService {
    @Autowired
    private TransferRepository repository;


    @Override
    public List<Transfer> getAll() throws DAOException {
        try {
            return repository.findAll();
        }catch (NoResultException e) {
            return new ArrayList<Transfer>();
        }catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void add(Transfer entity) throws DAOException {
        try {
            repository.save(entity);
        }catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        if( find(id) !=null) {
            Transfer ctr = find(id);
            try {
                repository.delete(ctr);
            }catch (DAOException e) {
                throw new DAOException(e.getMessage());
            }
        }else {
            throw new DAOException("ERROR_NOT_FOUND_OBJET");
        }
    }

    @Override
    public Transfer find(Long id) throws DAOException {
        if(id ==null) {
            throw new DAOException("ERROR_NOT_FOUND_OBJET");
        }else {
            try {
                Optional<Transfer> transfer = repository.findById(id);
                return transfer.get();
            } catch (DAOException e) {
                throw new DAOException(e.getMessage());
            }catch (NoSuchElementException e) {
                return null;
            }
        }
    }

    @Override
    public Transfer findId(Long id) throws FormValidationException {
        if (id != null) {
            if(find(id) != null) {
                return find(id);
            } else {
                throw new FormValidationException("ERROR_NOT_FOUND_OBJET");
            }
        } else {
            throw new FormValidationException("DAO_EXCEPTION");
        }
    }


}
