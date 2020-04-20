package cz.ear.rrs.rest;

import cz.ear.rrs.model.Room;
import cz.ear.rrs.rest.util.RestUtils;
import cz.ear.rrs.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private static final Logger LOG = LoggerFactory.getLogger(RoomController.class);

    private final RoomService rs;


    @Autowired
    public RoomController(RoomService rs) {
        this.rs = rs;
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable int id) {
        return rs.find(id);

    }

    @PreAuthorize("hasAnyAuthority('0')")
    @GetMapping
    public List<Room> getRooms() {
        return rs.findAll();
    }


    @GetMapping("/{id}/available/{date}")
    public HashMap getRoomAvailability(@PathVariable int id, @PathVariable String date) {
        HashMap<String, List> schedule = rs.getRoomAvailability(rs.find(id), LocalDate.parse(date));
        return schedule;
    }

    @PreAuthorize("hasAuthority('0')")
    @GetMapping("/usage")
    public HashMap getRoomUsage() {
        return rs.getRoomUsage();
    }

    @PreAuthorize("hasAuthority('0')")
    @PostMapping
    public ResponseEntity<Object> addRoom(@RequestBody Room room) {
        rs.persist(room);
        LOG.info("Created room {}.", room);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", room.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('0')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoom(@PathVariable int id) {
        rs.remove(rs.find(id));
    }

    @PreAuthorize("hasAuthority('0')")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRoom(@RequestBody Room room) {
        rs.update(room);
    }

}
