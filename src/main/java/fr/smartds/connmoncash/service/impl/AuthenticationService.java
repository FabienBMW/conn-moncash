package fr.smartds.connmoncash.service.impl;


import fr.smartds.connmoncash.entities.Authentication;
import fr.smartds.connmoncash.exceptions.DAOException;
import fr.smartds.connmoncash.exceptions.FormValidationException;
import fr.smartds.connmoncash.repositories.AuthenticationRepository;
import fr.smartds.connmoncash.service.AuthenticationIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthenticationService implements AuthenticationIService {
    @Autowired
    private AuthenticationRepository repository;

    @Override
    public List<Authentication> getAll() throws DAOException {
        return null;
    }

    @Override
    public void add(Authentication entity) throws DAOException {
        try {
            repository.save(entity);
        }catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        if( find(id) !=null) {
            Authentication ctr = find(id);
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
    public Authentication find(Long id) throws DAOException {
        if(id ==null) {
            throw new DAOException("ERROR_NOT_FOUND_OBJET");
        }else {
            try {
                Optional<Authentication> authentication = repository.findById(id);
                return authentication.get();
            } catch (DAOException e) {
                throw new DAOException(e.getMessage());
            }catch (NoSuchElementException e) {
                return null;
            }
        }
    }

    @Override
    public Authentication findId(Long id) throws FormValidationException {
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

    @Override
    public Authentication getOldToken() {
        return null;
    }


//    @Override
//    public Authentication getOldToken() {
//        try {
//            return repository.getOldToken();
//        }catch (DAOException e) {
//            throw new DAOException(e.getMessage());
//        }
//    }
}
