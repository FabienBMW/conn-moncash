package fr.smartds.connmoncash.web.api;

import fr.smartds.connmoncash.dto.Credential;
import fr.smartds.connmoncash.dto.TransferDto;
import fr.smartds.connmoncash.dto.TransferResponse;
import fr.smartds.connmoncash.entities.Authentication;
import fr.smartds.connmoncash.entities.Transfer;
import fr.smartds.connmoncash.exceptions.DAOException;
import fr.smartds.connmoncash.feignclients.MonCashFeignService;
import fr.smartds.connmoncash.service.impl.AuthenticationService;
import fr.smartds.connmoncash.service.impl.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping(value = "api/conn_moncash")
public class Controller {




    public Controller(TransferService transferService, AuthenticationService authenticationService, MonCashFeignService monCashFeignService) {
        this.transferService = transferService;
        this.authenticationService = authenticationService;
        this.monCashFeignService = monCashFeignService;
    }

    @Autowired
    private TransferService transferService;

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private MonCashFeignService monCashFeignService;

    @PostMapping("/oauth/token")
    Authentication authenticationOperation(){
        Authentication response = new Authentication();
        Credential credential = new Credential();
        try{
            Authentication authentication = monCashFeignService.authentication(credential.getClient_id(), credential.getClient_secret());
            authentication.setId(null);
            authentication.setCreate_date(new Date());
            response = authentication;
            authenticationService.add(authentication);
        }catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
        return response;
    }

    @PostMapping("/v1/Transfert")
    Transfer transferOperation(
            @RequestBody TransferDto transferDto
            ){
        Transfer transfer = new Transfer();
        Authentication authentication = new Authentication();
        try{
            Authentication authenticationGet = authenticationService.getOldToken();
            if (IsExpiredToken(authenticationGet)){
                authentication = authenticationGet;
            }else {
                authentication = authenticationOperation();
            }
            TransferResponse transferResponse = monCashFeignService.transfer(transferDto.getAmount(), transferDto.getReceiver(), transferDto.getDesc(), authentication.getAccess_token());
            // on mettra un log a ce niveau pour verifier la valeur qu'on

            if (transferResponse.getStatus().equals(HttpStatus.OK)){
                transfer = transferResponse.getTransfer();
                transferService.add(transfer);
            }
        }catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
        return transfer;
    }

    Boolean IsExpiredToken(Authentication authentication){
        //date courrente
        Calendar currentDate = Calendar.getInstance();
        // date de creation du token
        Calendar createToken = null;
        createToken.setTime(authentication.getCreate_date());
        // on ajoute expires_in pour determiner la date d'expiration
        createToken.add(Calendar.MINUTE,authentication.getExpires_in());
        Calendar expiredDate = createToken;
        // on compare expiresDate avec currenteDate
        return currentDate.before(expiredDate);  // si currentDate se situe avant expiredDate renvoie true. il renvoie false dans e cas contraire
    }

}
