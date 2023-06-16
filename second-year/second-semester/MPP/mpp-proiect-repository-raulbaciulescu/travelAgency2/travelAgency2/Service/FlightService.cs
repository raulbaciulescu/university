using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using travelAgency2.Repository;
using travelAgency2.src.Domain;

namespace travelAgency2.Service
{
    internal class FlightService
    {
        private FlightRepository flightRepository;
        private Random random;

        public FlightService(FlightRepository flightRepository)
        {
            this.flightRepository = flightRepository;
            this.random = new Random();
        }

        public Flight FindById(long id)
        {
            return flightRepository.FindByID(id);
        }

        public List<Flight> GetAll()
        {
            List<Flight> list = flightRepository.GetAll();
            return list.Where(flight => flight.nrOfSeats > 0).ToList();
        }

        public void Update(Flight flight, Flight flightNew)
        {
            flightRepository.Update(flight, flightNew);
        }
    }
}
