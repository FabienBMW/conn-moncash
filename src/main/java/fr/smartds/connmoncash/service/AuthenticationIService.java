package fr.smartds.connmoncash.service;

import fr.smartds.connmoncash.entities.Authentication;

public interface AuthenticationIService extends ICrudService<Authentication, Long> {
    Authentication getOldToken();
}
