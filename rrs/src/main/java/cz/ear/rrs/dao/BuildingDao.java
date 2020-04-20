package cz.ear.rrs.dao;

import cz.ear.rrs.model.Building;
import org.springframework.stereotype.Repository;

@Repository
public class BuildingDao extends BaseDao<Building> {

    public BuildingDao() {
        super(Building.class);
    }
}
