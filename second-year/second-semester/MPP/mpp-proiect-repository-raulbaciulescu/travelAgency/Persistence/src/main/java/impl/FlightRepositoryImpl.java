package impl;

import api.Constants;
import api.FlightRepository;
import api.LocationRepository;
import api.Table;
import domain.Flight;
import domain.Location;
import domain.dto.FlightDto;
import impl.database.FlightTable;
import impl.database.TableFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightRepositoryImpl implements FlightRepository {
    private final Table<Long, FlightDto> table;
    private final LocationRepository locationRepository;

    public FlightRepositoryImpl(LocationRepository locationRepository, TableFactory factory) throws SQLException {
        table = (FlightTable) factory.getTable(Constants.Db.Tables.FLIGHT);
        this.locationRepository = locationRepository;
    }

    @Override
    public void add(Flight flight) {
        FlightDto flightDto = new FlightDto(flight.getStart().getId(), flight.getDestination().getId(), flight.getStartDate(),
                flight.getNrOfSeats());
        flightDto.setId(flight.getId());
        table.add(flightDto);
    }

    @Override
    public void update(Flight entity, Flight newEntity) {
        FlightDto flightDto = new FlightDto(entity.getStart().getId(), entity.getDestination().getId(),
                entity.getStartDate(), entity.getNrOfSeats());
        FlightDto flightDtoNew = new FlightDto(newEntity.getStart().getId(), newEntity.getDestination().getId(),
                newEntity.getStartDate(), newEntity.getNrOfSeats());
        flightDtoNew.setId(entity.getId());
        flightDto.setId(entity.getId());
        table.update(flightDto, flightDtoNew);
    }

    @Override
    public Optional<Flight> findByID(Long aLong) {
        Optional<FlightDto> optionalFlightDto = table.findById(aLong);
        if (optionalFlightDto.isPresent()) {
            try {
                Optional<Location> start = locationRepository.findByID(optionalFlightDto.get().getStartId());
                Optional<Location> destination = locationRepository.findByID(optionalFlightDto.get().getDestinationId());
                if (start.isPresent() && destination.isPresent()) {
                    Flight flight = new Flight(start.get(), destination.get(),
                            optionalFlightDto.get().getStartDate(), optionalFlightDto.get().getNrOfSeats());
                    flight.setId(aLong);
                    return Optional.of(flight);
                }
            } catch (SQLException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long aLong) {
        table.delete(aLong);
    }

    @Override
    public List<Flight> getAll() {
        List<FlightDto> flightDtos = table.getAll();
        List<Flight> flights = new ArrayList<>();
        for (FlightDto flightDto : flightDtos) {
            try {
                Optional<Location> start = locationRepository.findByID(flightDto.getStartId());
                Optional<Location> destination = locationRepository.findByID(flightDto.getDestinationId());
                //if (start.isPresent() && destination.isPresent() && flightDto.getNrOfSeats() > 0) {
                if (start.isPresent() && destination.isPresent()) {
                    Flight flight = new Flight(start.get(), destination.get(),
                            flightDto.getStartDate(), flightDto.getNrOfSeats());
                    flight.setId(flightDto.getId());
                    flights.add(flight);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return flights;
    }
}
