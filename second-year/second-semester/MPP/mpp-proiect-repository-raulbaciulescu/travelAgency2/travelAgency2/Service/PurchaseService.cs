using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using travelAgency2.Repository;
using travelAgency2.src.Domain;

namespace travelAgency2.Service
{
    internal class PurchaseService
    {
        private PurchaseRepository purchaseRepository;
        private FlightService flightService;
        private Random random;

        public PurchaseService(PurchaseRepository purchaseRepository, FlightService flightService)
        {
            this.purchaseRepository = purchaseRepository;
            this.flightService = flightService;
            this.random = new Random();
        }


        public void Add(Flight flight, String clientName, String clientAddress, List<String> tourists, int nrOfSeats)
        {
            if (nrOfSeats > flight.nrOfSeats)
                throw new Exception();
            long id = random.Next(0, 999999999);
            Purchase purchase = new Purchase(id, flight, clientName, clientAddress, tourists, nrOfSeats);
            purchaseRepository.Add(purchase);
            Flight flightNew = new Flight(flight.Id, flight.start, flight.destination,
                    flight.startDate, flight.nrOfSeats - nrOfSeats);
            flightService.Update(flight, flightNew);
        }
    }
}
