package ta.lufthansa.flightapp.trip.model.mapper;

import ta.lufthansa.flightapp.flight.model.mapper.FlightConverter;
import ta.lufthansa.flightapp.trip.model.dto.TripDTO;
import ta.lufthansa.flightapp.trip.model.entity.TripEntity;

public class TripConverter {

    public static TripDTO convertTripEntityToDTO(TripEntity tripEntity) {
        TripDTO tripDTO = new TripDTO();

        tripDTO.setId(tripEntity.getId());
        tripDTO.setTripReason(String.valueOf(tripEntity.getTripReason()));
        tripDTO.setDescription(tripEntity.getDescription());
        tripDTO.setFromCountry(String.valueOf(tripEntity.getFromCountry()));
        tripDTO.setToCountry(String.valueOf(tripEntity.getToCountry()));
        tripDTO.setDepartureDate(tripEntity.getDepartureDate());
        tripDTO.setArrivalDate(tripEntity.getArrivalDate());
        tripDTO.setTripStatus(String.valueOf(tripEntity.getTripStatus()));

        if(tripEntity.getFlightEntity() != null) {
            tripDTO.setFlight(FlightConverter.convertFlightEntityToDTO(tripEntity.getFlightEntity()));
        }

        return tripDTO;
    }
}
