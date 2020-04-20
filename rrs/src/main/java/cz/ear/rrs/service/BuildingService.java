package cz.ear.rrs.service;

import cz.ear.rrs.dao.BuildingDao;
import cz.ear.rrs.dao.EquipmentDao;
import cz.ear.rrs.dao.RoomDao;
import cz.ear.rrs.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class BuildingService {

    private final BuildingDao dao;

    @Autowired
    public BuildingService(BuildingDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void persist(Building building) {
        Objects.requireNonNull(building);
        dao.persist(building);
    }

    @Transactional
    public void update(Building building) {
        Objects.requireNonNull(building);
        dao.update(building);
    }

    @Transactional
    public void remove(Building building) {
        Objects.requireNonNull(building);
        dao.remove(building);
    }

    @Transactional
    public Building find(int id) {
        return dao.find(id);
    }

    @Transactional
    public List<Building> findAll() {
        return dao.findAll();
    }

}
