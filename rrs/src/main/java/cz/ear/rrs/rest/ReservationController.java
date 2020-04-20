package cz.ear.rrs.rest;

import cz.ear.rrs.dto.ReservationDTO;
import cz.ear.rrs.exception.*;
import cz.ear.rrs.model.Reservation;
import cz.ear.rrs.rest.handler.ErrorInfo;
import cz.ear.rrs.rest.handler.RestExceptionHandler;
import cz.ear.rrs.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.spi.ResolveResult;
import java.util.List;

@RestController
@RequestMapping("/reservation")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_GUEST')")
public class ReservationController {
    private final ReservationService rs;
    private static final Logger LOG = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    public ReservationController(ReservationService rs) {
        this.rs = rs;
    }

    @GetMapping
    public List<Reservation> getReservations() {
        return rs.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable int id) {
        final Reservation r = rs.find(id);
        if (r == null) {
            throw NotFoundException.create("Reservation", id);
        }
        return r;
    }

    @PostMapping("/new")
//    @ExceptionHandler({
//            EquipmentIsNotAvailable.class, InsufficientAmountException.class, LowRightsException.class, NotFoundException.class,
//            PersistenceException.class, RoomIsNotAvailable.class, ValidationException.class})
    public ResponseEntity<ErrorInfo> createReservation(@RequestBody ReservationDTO reservationDTO) {

        Reservation r = reservationDTO.toEntity();
        r = rs.completeReservationEntity(reservationDTO, r);
        try {
            if (rs.validateReservation(r)) {
                rs.persist(r);
            }
        } catch (Exception e) {
            LOG.warn(e.getMessage()); //nebo severe
            return new RestExceptionHandler().handleException(e);
        }
        LOG.info("Created reservation with id" + r.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('0')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        final Reservation original = rs.find(id);
        if (!original.getId().equals(reservation.getId())) {
            throw new ValidationException("Product identifier in the data does not match the one in the request URL.");
        }
        rs.update(reservation);
        LOG.info("Updated reservation" + reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeReservation(@PathVariable int id) {
        final Reservation r = rs.find(id);
        if (r == null) {
            return;
        }
        rs.remove(r);
        LOG.info("Removed reservation {}.", r);
    }
}
