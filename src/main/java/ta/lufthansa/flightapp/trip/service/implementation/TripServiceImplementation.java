package ta.lufthansa.flightapp.trip.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.lufthansa.flightapp.trip.repository.TripRepository;
import ta.lufthansa.flightapp.trip.service.TripService;

@Service
public class TripServiceImplementation implements TripService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripServiceImplementation.class);

    @Autowired
    private TripRepository tripRepository;
}
