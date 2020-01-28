package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Company;
import com.cyberoxi.hstpfacilities.models.Establishment;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
public interface EstablishmentService {

    Iterable<Establishment> getEstablishments(long companyId);

    Establishment getEstablishment(long id);

    Company addEstablishment(long companyId, Establishment establishment);

    Establishment updateEstablishment(long id, Establishment establishment);
}
