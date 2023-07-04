package fr.smartds.connmoncash.service;

import fr.smartds.connmoncash.dto.TransferDto;
import fr.smartds.connmoncash.entities.Transfer;
import fr.smartds.connmoncash.exceptions.DAOException;

import java.util.List;

public interface TransferIService extends ICrudService<Transfer, Long> {
    List<Transfer> getAll() throws DAOException;
    Transfer transferOperation(TransferDto transferDto);

}
