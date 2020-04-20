package cz.ear.rrs.service;


import cz.ear.rrs.dao.EquipmentDao;
import cz.ear.rrs.dao.ReservationDao;
import cz.ear.rrs.model.Equipment;
import cz.ear.rrs.model.Rights;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentDao equipmentDao;

    @Autowired
    public EquipmentService(EquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }

    @Transactional
    public void persist(Equipment equipment) {
        equipmentDao.persist(equipment);
    }

    @Transactional
    public void remove(Equipment equipment) {
        equipmentDao.remove(equipment);
    }

    @Transactional
    public List<Equipment> findAll() {
        return equipmentDao.findAll();
    }

    @Transactional
    public Equipment find(int id) {
        return equipmentDao.find(id);
    }

    @Transactional
    public void update(Equipment equipment) {
        equipmentDao.update(equipment);
    }


}