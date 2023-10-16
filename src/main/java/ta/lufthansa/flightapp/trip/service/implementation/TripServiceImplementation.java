package ta.lufthansa.flightapp.trip.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.lufthansa.flightapp.flight.repository.FlightRepository;
import ta.lufthansa.flightapp.trip.model.dto.TripDTO;
import ta.lufthansa.flightapp.trip.model.entity.TripEntity;
import ta.lufthansa.flightapp.trip.model.enums.Country;
import ta.lufthansa.flightapp.trip.model.enums.TripReason;
import ta.lufthansa.flightapp.trip.model.enums.TripStatus;
import ta.lufthansa.flightapp.trip.model.mapper.TripConverter;
import ta.lufthansa.flightapp.trip.repository.TripRepository;
import ta.lufthansa.flightapp.trip.service.TripService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImplementation implements TripService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripServiceImplementation.class);

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private FlightRepository flightRepository;


    @Override
    public TripDTO getTrip(Integer id) {
        if (tripRepository.findById(id).isEmpty() || tripRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Trip not found.");
            throw new RuntimeException("Trip not found.");
        }
        return TripConverter.convertTripEntityToDTO(tripRepository.findById(id).get());
    }

    @Override
    public List<TripDTO> getAllTrips() {
        return tripRepository.findAll()
                .stream()
                .filter(trip -> trip.getValidity().equals(Boolean.TRUE))
                .map(TripConverter::convertTripEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TripDTO createTrip(TripDTO tripDTO) {
        TripEntity trip = new TripEntity();
        trip.setTripReason(TripReason.valueOf(tripDTO.getTripReason()));
        trip.setDescription(tripDTO.getDescription());
        trip.setFromCountry(Country.valueOf(tripDTO.getFromCountry()));
        trip.setToCountry(Country.valueOf(tripDTO.getToCountry()));
        trip.setDepartureDate(tripDTO.getDepartureDate());
        trip.setArrivalDate(tripDTO.getArrivalDate());
        trip.setTripStatus(TripStatus.CREATED);
        trip.setValidity(Boolean.TRUE);

        tripRepository.save(trip);
        return TripConverter.convertTripEntityToDTO(trip);
    }

    @Override
    public TripDTO approvalRequest(Integer tripId) {
        if (tripRepository.findById(tripId).isEmpty() || tripRepository.findById(tripId).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Trip not found.");
            throw new RuntimeException("Trip not found.");
        }
        if (tripRepository.findById(tripId).get().getTripStatus() != TripStatus.CREATED) {
            LOGGER.info("Trip should be in Created state.");
            throw new RuntimeException("Trip should be in Created state.");
        }

        TripEntity trip = tripRepository.findById(tripId).get();
        trip.setTripStatus(TripStatus.WAITING_FOR_APPROVAL);
        tripRepository.save(trip);

        return TripConverter.convertTripEntityToDTO(trip);
    }

    @Override
    public TripDTO approveTrip(Integer tripId) {
        if (tripRepository.findById(tripId).isEmpty() || tripRepository.findById(tripId).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Trip not found.");
            throw new RuntimeException("Trip not found.");
        }
        if (tripRepository.findById(tripId).get().getTripStatus() != TripStatus.WAITING_FOR_APPROVAL) {
            LOGGER.info("Trip should be in Waiting For Approval state.");
            throw new RuntimeException("Trip should be in Waiting For Approval state.");
        }

        TripEntity trip = tripRepository.findById(tripId).get();
        trip.setTripStatus(TripStatus.APPROVED);
        tripRepository.save(trip);

        return TripConverter.convertTripEntityToDTO(trip);
    }

    @Override
    public TripDTO addFlight(Integer tripId, Integer flightId) {
        if (tripRepository.findById(tripId).isEmpty() || tripRepository.findById(tripId).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Trip not found.");
            throw new RuntimeException("Trip not found.");
        }
        if (tripRepository.findById(tripId).get().getTripStatus() != TripStatus.APPROVED) {
            LOGGER.info("Trip should be in Approved state.");
            throw new RuntimeException("Trip should be in Approved state.");
        }
        if (flightRepository.findById(flightId).isEmpty()
                || flightRepository.findById(flightId).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Flight not found.");
            throw new RuntimeException("Flight not found.");
        }

        TripEntity trip = tripRepository.findById(tripId).get();
        trip.setFlightEntity(flightRepository.findById(flightId).get());
        tripRepository.save(trip);

        return TripConverter.convertTripEntityToDTO(trip);
    }

    /*@Override
    public TripDTO changeTrip(TripDTO tripDTO) {
        if (tripRepository.findById(tripDTO.getId()).isEmpty() || tripRepository.findById(tripDTO.getId()).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Trip not found.");
            throw new RuntimeException("Trip not found.");
        }
        TripEntity trip = tripRepository.findById(tripDTO.getId()).get();
        trip.setTripReason(TripReason.valueOf(tripDTO.getTripReason()));
        trip.setDescription(tripDTO.getDescription());
        trip.setFromCountry(Country.valueOf(tripDTO.getFromCountry()));
        trip.setToCountry(Country.valueOf(tripDTO.getToCountry()));
        trip.setDepartureDate(tripDTO.getDepartureDate());
        trip.setArrivalDate(tripDTO.getArrivalDate());
        trip.setTripStatus(TripStatus.valueOf(tripDTO.getTripStatus()));
        trip.setValidity(Boolean.TRUE);

        tripRepository.save(trip);
        return TripConverter.convertTripEntityToDTO(trip);
    }*/

    @Override
    public Integer deleteTrip(Integer id) {
        if (tripRepository.findById(id).isEmpty() || tripRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Trip not found.");
            throw new RuntimeException("Trip not found.");
        }
        TripEntity trip = tripRepository.findById(id).get();
        trip.setValidity(Boolean.FALSE);
        tripRepository.save(trip);
        return id;
    }

    @Override
    public TripDTO restoreTrip(Integer id) {
        if (tripRepository.findById(id).isEmpty() || tripRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            LOGGER.info("Trip not found.");
            throw new RuntimeException("Trip not found.");
        }
        TripEntity trip = tripRepository.findById(id).get();
        trip.setValidity(Boolean.TRUE);
        tripRepository.save(trip);
        return TripConverter.convertTripEntityToDTO(trip);
    }
}
