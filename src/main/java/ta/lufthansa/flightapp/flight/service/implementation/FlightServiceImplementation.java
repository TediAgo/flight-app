package ta.lufthansa.flightapp.flight.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.lufthansa.flightapp.flight.repository.FlightRepository;
import ta.lufthansa.flightapp.flight.service.FlightService;

@Service
public class FlightServiceImplementation implements FlightService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImplementation.class);

    @Autowired
    private FlightRepository flightRepository;

}
