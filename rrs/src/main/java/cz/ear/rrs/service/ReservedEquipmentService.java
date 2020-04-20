package cz.ear.rrs.service;

import cz.ear.rrs.dao.EquipmentDao;
import cz.ear.rrs.model.ReservedEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//TODO: prepsat do EquipmentService
@Service
public class ReservedEquipmentService {

        private final EquipmentDao equipmentDao;
        private final ReservationService reservationService;
        private final EquipmentService equipmentService;

        @Autowired
        public ReservedEquipmentService(EquipmentDao equipmentDao, ReservationService reservationService, EquipmentService equipmentService) {
            this.equipmentDao = equipmentDao;
            this.reservationService = reservationService;
            this.equipmentService = equipmentService;
        }


        @Transactional
        public List<ReservedEquipment> findAll() {
            return equipmentDao.findAllReservedEquipment();
        }

        @Transactional
        public List<Object[]> test() {
            return equipmentDao.getOverlapsingEquipment(reservationService.find(3), equipmentService.find(1) );
        }

    }

