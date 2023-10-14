package ta.lufthansa.flightapp.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ta.lufthansa.flightapp.flight.model.entity.FlightEntity;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Integer> {
}
