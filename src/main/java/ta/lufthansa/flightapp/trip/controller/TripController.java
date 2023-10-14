package ta.lufthansa.flightapp.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ta.lufthansa.flightapp.trip.service.TripService;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripService tripService;
}
