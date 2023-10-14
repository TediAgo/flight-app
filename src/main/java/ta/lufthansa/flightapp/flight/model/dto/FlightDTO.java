package ta.lufthansa.flightapp.flight.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {

    private Integer id;
    private Long flightNumber;
    private String fromAirport;
    private String toAirport;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
}
