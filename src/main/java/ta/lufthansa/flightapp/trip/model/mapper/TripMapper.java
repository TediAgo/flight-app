package ta.lufthansa.flightapp.trip.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ta.lufthansa.flightapp.trip.model.dto.TripDTO;
import ta.lufthansa.flightapp.trip.model.entity.TripEntity;

@Mapper
public interface TripMapper {

    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

    TripDTO tripToDTO(TripEntity trip);

    TripEntity tipDTOToTrip(TripDTO tripDTO);
}
