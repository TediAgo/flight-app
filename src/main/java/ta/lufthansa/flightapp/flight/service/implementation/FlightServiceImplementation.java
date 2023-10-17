package ta.lufthansa.flightapp.flight.service.implementation;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ta.lufthansa.flightapp.exceptions.NotFoundException;
import ta.lufthansa.flightapp.exceptions.ServiceException;
import ta.lufthansa.flightapp.flight.model.dto.FlightDTO;
import ta.lufthansa.flightapp.flight.model.entity.FlightEntity;
import ta.lufthansa.flightapp.flight.model.enums.Airport;
import ta.lufthansa.flightapp.flight.model.mapper.FlightConverter;
import ta.lufthansa.flightapp.flight.repository.FlightRepository;
import ta.lufthansa.flightapp.flight.service.FlightService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlightServiceImplementation implements FlightService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImplementation.class);

    private final FlightRepository flightRepository;

    @Override
    public FlightDTO getFlight(Integer id) {
        if (flightRepository.findById(id).isEmpty() || flightRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Flight not found.");
            throw new NotFoundException("Flight not found.");
        }
        return FlightConverter.convertFlightEntityToDTO(flightRepository.findById(id).get());
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .filter(flight -> flight.getValidity().equals(Boolean.TRUE))
                .map(FlightConverter::convertFlightEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        if(flightDTO.getDepartureDate().isAfter(flightDTO.getArrivalDate())) {
            LOGGER.info("Flight Arrival Date should be after Departure Date.");
            throw new ServiceException("Flight Arrival Date should be after Departure Date.");
        }

        FlightEntity flight = new FlightEntity();
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setFromAirport(Airport.valueOf(flightDTO.getFromAirport()));
        flight.setToAirport(Airport.valueOf(flightDTO.getToAirport()));
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setArrivalDate(flightDTO.getArrivalDate());
        flight.setValidity(Boolean.TRUE);
        flightRepository.save(flight);
        return FlightConverter.convertFlightEntityToDTO(flight);
    }

    @Override
    public Integer deleteFlight(Integer id) {
        if (flightRepository.findById(id).isEmpty() || flightRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Flight not found.");
            throw new NotFoundException("Flight not found.");
        }
        FlightEntity flight = flightRepository.findById(id).get();
        flight.setValidity(Boolean.FALSE);
        flightRepository.save(flight);
        return id;
    }

    @Override
    public FlightDTO restoreFlight(Integer id) {
        if (flightRepository.findById(id).isEmpty() || flightRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            LOGGER.info("Flight not found.");
            throw new NotFoundException("Flight not found.");
        }
        FlightEntity flight = flightRepository.findById(id).get();
        flight.setValidity(Boolean.TRUE);
        flightRepository.save(flight);
        return FlightConverter.convertFlightEntityToDTO(flight);
    }
}
