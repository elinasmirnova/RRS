package cz.ear.rrs.rest;

import cz.ear.rrs.model.Equipment;
import cz.ear.rrs.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentController.class);

    private final EquipmentService es;

    @Autowired
    public EquipmentController(EquipmentService es) {
        this.es = es;
    }

    @GetMapping
    public List<Equipment> getEquipment() {
        return es.findAll();
    }

    @GetMapping("/{id}")
    public Equipment getEquipmentById(@PathVariable int id) {
        return es.find(id);
    }

    @PostMapping
    public void createEquipment(@RequestBody Equipment equipment) {
        es.persist(equipment);
        LOG.debug("Created equipment {}.", equipment);
    }

    @PutMapping
    public void updateEquipment(@RequestBody Equipment equipment) {
        es.update(equipment);
        LOG.debug("Updated equipment {}.", equipment);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable int id) {
        es.remove(es.find(id));
        LOG.debug("Deleted equipment {}.", id);
    }

}
