package fr.smartds.connmoncash.service.impl;


import fr.smartds.connmoncash.dto.Credential;
import fr.smartds.connmoncash.entities.Authentication;
import fr.smartds.connmoncash.exceptions.DAOException;
import fr.smartds.connmoncash.exceptions.FormValidationException;
import fr.smartds.connmoncash.feignclients.MonCashFeignService;
import fr.smartds.connmoncash.repositories.AuthenticationRepository;
import fr.smartds.connmoncash.service.AuthenticationIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthenticationService implements AuthenticationIService {
    @Autowired
    private AuthenticationRepository repository;

    @Autowired
    private MonCashFeignService monCashFeignService;


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

        Authentication authentication;
        try {
            Optional<Authentication> auth = repository.findFirstByOrderByDateDesc();
            if (auth.isPresent()) {
                authentication = auth.get();
            } else {
                authentication = null;
            }
            return authentication;
        }catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public Authentication authenticationOperation(){
        Authentication response = new Authentication();
        Credential credential = new Credential();

        try{
            Authentication authentication = monCashFeignService.authentication(credential.getClient_id(), credential.getClient_secret());
            authentication.setDate(new Date());
            response = authentication;
            add(authentication);
        }catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
        return response;
    }

    public  Boolean IsExpiredToken(Authentication authentication){
        //date courrente
        Calendar currentDate = Calendar.getInstance();
        // date de creation du token
        Calendar createToken = null;
        createToken.setTime(authentication.getDate());
        // on ajoute expires_in pour determiner la date d'expiration
        createToken.add(Calendar.MINUTE,authentication.getExpires_in());
        Calendar expiredDate = createToken;
        // on compare expiresDate avec currenteDate
        return currentDate.before(expiredDate);  // si currentDate se situe avant expiredDate renvoie true. il renvoie false dans e cas contraire
    }
}
