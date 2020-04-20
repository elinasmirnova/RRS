package cz.ear.rrs.rest;

import cz.ear.rrs.exception.NotFoundException;
import cz.ear.rrs.exception.ValidationException;
import cz.ear.rrs.model.Building;
import cz.ear.rrs.rest.util.RestUtils;
import cz.ear.rrs.service.BuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/building")
public class BuildingController {

    private static final Logger LOG = LoggerFactory.getLogger(BuildingController.class);

    private final BuildingService bs;

    @Autowired
    public BuildingController(BuildingService bs) {
        this.bs = bs;
    }

    @PreAuthorize("hasAuthority('0')")
    @GetMapping
    public List<Building> getBuildings() {
        return bs.findAll();
    }

    @PreAuthorize("hasAuthority('0')")
    @GetMapping("/{id}")
    public Building getBuildingById(@PathVariable int id) {
        final Building b = bs.find(id);
        if (b == null) {
            throw NotFoundException.create("Building", id);
        }
        return b;
    }

    @PreAuthorize("hasAuthority('0')")
    @PostMapping
    public ResponseEntity<Void> createBuilding(@RequestBody Building building) {
        bs.persist(building);
        LOG.info("Created building with id {}.", building.getId());
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", building.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('0')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBuilding(@PathVariable int id, @RequestBody Building building) {
        final Building original = bs.find(id);
        if (!original.getId().equals(building.getId())) {
            throw new ValidationException("Product identifier in the data does not match the one in the request URL.");
        }
        bs.update(building);
        LOG.info("Updated building with id {}.", building.getId());
    }

    @PreAuthorize("hasAuthority('0')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBuilding(@PathVariable int id) {
        Building b = getBuildingById(id);
        if (b == null) {
            return;
        }
        bs.remove(b);
        LOG.info("Deleted building with id {}.", b.getId());
    }

}
