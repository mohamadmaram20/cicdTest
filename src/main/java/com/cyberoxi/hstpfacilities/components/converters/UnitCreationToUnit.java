package com.cyberoxi.hstpfacilities.components.converters;

import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.requests.UnitCreation;
import lombok.SneakyThrows;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 3/3/2020
 */
@Component
public class UnitCreationToUnit implements Converter<UnitCreation, Unit> {

    @Synchronized
    @Nullable
    @Override
    public Unit convert(UnitCreation unitCreation) {
        Unit unit = new Unit();
        unit.setUsername(unitCreation.getUsername());
        unit.setPassword(unitCreation.getPassword());
        unit.setRegistrationDate(unitCreation.getRegistrationDate());
        unit.setName(unitCreation.getName());
        unit.setType(unitCreation.getType());
        unit.setBranch(unitCreation.getBranch());
        unit.setReceptionType(unitCreation.getReceptionType());
        unit.setRegistrationNumber(unitCreation.getRegistrationNumber());
        unit.setNationalId(unitCreation.getNationalId());
        unit.setPerson(unitCreation.getPerson());
        unit.setPhoneNumber(unitCreation.getPhoneNumber());
        unit.setWebsite(unitCreation.getWebsite());
        unit.setFax(unitCreation.getFax());
        unit.setPostalAddress(unitCreation.getPostalAddress());
        unit.setEmail(unitCreation.getEmail());
        unit.setAvatar(unitCreation.getAvatar());
        return unit;
    }
}
