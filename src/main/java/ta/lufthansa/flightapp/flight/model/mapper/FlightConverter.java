package ta.lufthansa.flightapp.flight.model.mapper;

import ta.lufthansa.flightapp.flight.model.dto.FlightDTO;
import ta.lufthansa.flightapp.flight.model.entity.FlightEntity;

public class FlightConverter {

    public static FlightDTO convertFlightEntityToDTO(FlightEntity flightEntity) {
        FlightDTO flightDTO = new FlightDTO();

        flightDTO.setId(flightEntity.getId());
        flightDTO.setFlightNumber(flightEntity.getFlightNumber());
        flightDTO.setFromAirport(String.valueOf(flightEntity.getFromAirport()));
        flightDTO.setToAirport(String.valueOf(flightEntity.getToAirport()));
        flightDTO.setDepartureDate(flightEntity.getDepartureDate());
        flightDTO.setArrivalDate(flightEntity.getArrivalDate());

        return flightDTO;
    }
}
