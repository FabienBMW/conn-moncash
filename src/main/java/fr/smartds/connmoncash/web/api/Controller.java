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

    @Autowired
    private TransferService transferService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private MonCashFeignService monCashFeignService;



    @PostMapping("/v1/Transfert")
    Transfer transferOperation(
            @RequestBody TransferDto transferDto
            ){
        return transferService.transferOperation(transferDto);
    }



}
