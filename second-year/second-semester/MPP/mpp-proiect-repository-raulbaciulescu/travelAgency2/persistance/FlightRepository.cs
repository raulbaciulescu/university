

using model;
using model.Dto;
using model.Utils;
using persistance.Database;

namespace persistance
{
    public class FlightRepository : IRepository<long, Flight>
    {
        private ITable<long, FlightDto> table;
        private LocationRepository locationRepository;

        public FlightRepository(LocationRepository locationRepository, TableFactory factory)
        {
            table = (FlightTable) factory.GetTable(Db.Tables.FLIGHT);
            this.locationRepository = locationRepository;
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
            Location start = locationRepository.FindByID(flightDto.startId);
            Location destination = locationRepository.FindByID(flightDto.destinationId);
            return new Flight(flightDto.Id, start, destination, flightDto.startDate, flightDto.nrOfSeats);
        }

        public List<Flight> GetAll()
        {
            List<FlightDto> flightDtos = table.GetAll();
            List<Flight> flights = new List<Flight>();
            foreach (FlightDto flightDto in flightDtos)
            {
                if (flightDto.nrOfSeats > 0)
                {
                    Location start = locationRepository.FindByID(flightDto.startId);
                    Location destination = locationRepository.FindByID(flightDto.destinationId);
                    flights.Add(new Flight(flightDto.Id, start, destination, flightDto.startDate, flightDto.nrOfSeats));
                }
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
