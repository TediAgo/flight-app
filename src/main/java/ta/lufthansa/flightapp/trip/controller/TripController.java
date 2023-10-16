package ta.lufthansa.flightapp.trip.controller;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ta.lufthansa.flightapp.trip.model.dto.TripDTO;
import ta.lufthansa.flightapp.trip.service.TripService;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TripDTO> getTrip(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(tripService.getTrip(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<TripDTO>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TripDTO> createTrip(@NonNull @RequestBody TripDTO tripDTO) {
        return ResponseEntity.ok(tripService.createTrip(tripDTO));
    }

    @PutMapping("/{tripId}/approvalRequest")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TripDTO> approvalRequest(@NonNull @PathVariable(value = "tripId") Integer tripId) {
        return ResponseEntity.ok(tripService.approvalRequest(tripId));
    }

    @PutMapping("/{tripId}/approveTrip")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TripDTO> approveTrip(@NonNull @PathVariable(value = "tripId") Integer tripId) {
        return ResponseEntity.ok(tripService.approveTrip(tripId));
    }

    @PutMapping("/{tripId}/addFlight/{flightId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TripDTO> addFlight(@NonNull @PathVariable(value = "tripId") Integer tripId,
                                               @NonNull @PathVariable(value = "tripId") Integer flightId) {
        return ResponseEntity.ok(tripService.addFlight(tripId, flightId));
    }

    /*@PutMapping("/change")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TripDTO> changeTrip(@NonNull @RequestBody TripDTO tripDTO) {
        return ResponseEntity.ok(tripService.changeTrip(tripDTO));
    }*/

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Integer> deleteTrip(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(tripService.deleteTrip(id));
    }

    @PutMapping("/{id}/restore")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TripDTO> restoreTrip(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(tripService.restoreTrip(id));
    }
}
