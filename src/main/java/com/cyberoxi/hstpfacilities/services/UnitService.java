package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.responses.UnitBrief;
import com.cyberoxi.hstpfacilities.models.responses.UnitReport;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/22/2020
 */
public interface UnitService {

    Iterable<Unit> getUnits();

    Iterable<UnitBrief> getUnitsBrief();

    Unit getUnit(long id);

    Unit saveUnit(Unit unit);

    Unit updateUnit(long id, Unit unit);

    UnitReport getUnitReport(long id);
}
