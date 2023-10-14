package ta.lufthansa.flightapp.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ta.lufthansa.flightapp.trip.model.entity.TripEntity;

@Repository
public interface TripRepository extends JpaRepository<TripEntity, Integer> {
}
