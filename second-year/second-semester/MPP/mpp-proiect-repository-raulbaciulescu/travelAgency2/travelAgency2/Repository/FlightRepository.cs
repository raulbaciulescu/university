using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using travelAgency2.Domain.Dto;
using travelAgency2.Repository.Database;
using travelAgency2.src.Domain;
using travelAgency2.src.Repository;
using travelAgency2.Utils;

namespace travelAgency2.Repository
{
    internal class FlightRepository : Repository<long, Flight>
    {
        private Table<long, FlightDto> table;

        public FlightRepository()
        {
            table = (FlightTable) Resources.getTableFactory().getTable(Constants.Db.Tables.FLIGHT);
        }

        public void Add(Flight flight)
        {
            FlightDto flightDto = new FlightDto(flight.Id, flight.start.Id, flight.destination.Id, flight.startDate,
                flight.nrOfSeats);
            table.Add(flightDto);
        }

        public void Delete(long id)
        {
            throw new NotImplementedException();
        }

        public Flight FindByID(long id)
        {
            FlightDto flightDto = table.FindById(id);
            Location start = Resources.GetInstance().getLocationRepository().FindByID(flightDto.startId);
            Location destination = Resources.GetInstance().getLocationRepository().FindByID(flightDto.destinationId);
            return new Flight(flightDto.Id, start, destination, flightDto.startDate, flightDto.nrOfSeats);
        }

        public List<Flight> GetAll()
        {
            List<FlightDto> flightDtos = table.GetAll();
            List<Flight> flights = new List<Flight>();
            foreach (FlightDto flightDto in flightDtos)
            {
                Location start = Resources.GetInstance().getLocationRepository().FindByID(flightDto.startId);
                Location destination = Resources.GetInstance().getLocationRepository().FindByID(flightDto.destinationId);
                flights.Add(new Flight(flightDto.Id, start, destination, flightDto.startDate, flightDto.nrOfSeats));
            }
            return flights;
        }

        public void Update(Flight entity, Flight flightNew)
        {
            FlightDto flightDto = new FlightDto(entity.Id, entity.start.Id, entity.destination.Id,
                entity.startDate, entity.nrOfSeats);
            FlightDto flightDtoNew = new FlightDto(flightNew.Id, flightNew.start.Id, flightNew.destination.Id,
                    flightNew.startDate, flightNew.nrOfSeats);
            table.Update(flightDto, flightDtoNew);
        }
    }
}
