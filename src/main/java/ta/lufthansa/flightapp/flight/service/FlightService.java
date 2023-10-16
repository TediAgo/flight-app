package ta.lufthansa.flightapp.flight.service;

import ta.lufthansa.flightapp.flight.model.dto.FlightDTO;

import java.util.List;

public interface FlightService {
    FlightDTO getFlight(Integer id);

    List<FlightDTO> getAllFlights();

    FlightDTO createFlight(FlightDTO flightDTO);

    Integer deleteFlight(Integer id);

    FlightDTO restoreFlight(Integer id);
}
