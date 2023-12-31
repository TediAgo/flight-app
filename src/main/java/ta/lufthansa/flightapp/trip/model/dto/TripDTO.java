package ta.lufthansa.flightapp.trip.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ta.lufthansa.flightapp.flight.model.dto.FlightDTO;
import ta.lufthansa.flightapp.user.model.dto.UserDTO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {

    private Integer id;
    private String tripReason;
    private String description;
    private String fromCountry;
    private String toCountry;
    @FutureOrPresent
    private LocalDateTime departureDate;
    @FutureOrPresent
    private LocalDateTime arrivalDate;
    private String tripStatus;
    private FlightDTO flightDTO;
    private UserDTO userDTO;
}
