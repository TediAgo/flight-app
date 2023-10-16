package ta.lufthansa.flightapp.flight.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ta.lufthansa.flightapp.flight.model.dto.FlightDTO;
import ta.lufthansa.flightapp.flight.model.entity.FlightEntity;

@Mapper
public interface FlightMapper {

    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    FlightDTO flightToDTO(FlightEntity flight);

    FlightEntity flightDTOToFlight(FlightDTO flightDTO);
}