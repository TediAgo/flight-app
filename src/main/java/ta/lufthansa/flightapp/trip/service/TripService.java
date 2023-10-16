package ta.lufthansa.flightapp.trip.service;

import ta.lufthansa.flightapp.trip.model.dto.TripDTO;

import java.util.List;

public interface TripService {
    TripDTO getTrip(Integer id);

    List<TripDTO> getAllTrips();

    TripDTO createTrip(TripDTO tripDTO);

    TripDTO approvalRequest(Integer tripId);

    TripDTO approveTrip(Integer tripId);

    TripDTO addFlight(Integer tripId, Integer flightId);

    //TripDTO changeTrip(TripDTO tripDTO);

    Integer deleteTrip(Integer id);

    TripDTO restoreTrip(Integer id);

}
