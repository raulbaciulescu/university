using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using travelAgency2.Repository;
using travelAgency2.Repository.Database;
using travelAgency2.Service;
using travelAgency2.src.Domain;

namespace travelAgency2.Utils
{


    internal class Resources
    {
        static Resources instance = null;
        private static TableFactory tableFactory;
        private UserRepository userRepository;
        private LocationRepository locationRepository;
        private FlightRepository flightRepository;
        private PurchaseRepository purchaseRepository;
        private UserService userService;
        private LoginService loginService;
        private FlightService flightService;
        private PurchaseService purchaseService;
        private Flight selectedFlight = null;
        public static Resources GetInstance()
        {
            if (instance == null)
                instance = new Resources();
            return instance;
        }
        private Resources() 
        {
            tableFactory = new TableFactory();
            userRepository = new UserRepository();
            locationRepository = new LocationRepository();
            flightRepository = new FlightRepository();
            purchaseRepository = new PurchaseRepository();
            userService = new UserService(userRepository);
            loginService = new LoginService();
            flightService = new FlightService(flightRepository);
            purchaseService = new PurchaseService(purchaseRepository, flightService);
        }

        public void SetSelectedFlight(Flight flight)
        {
            this.selectedFlight = flight;
        }

        public Flight GetSelectedFlight()
        {
            return this.selectedFlight;
        }

        public PurchaseService GetPurchaseService()
        {
            return purchaseService;
        }
        public FlightService GetFlightService()
        {
            return flightService;
        }
        public LoginService GetLoginService()
        {
            return loginService;
        }
        public UserService GetUserService()
        {
            return userService;
        }
        public LocationRepository getLocationRepository()
        {
            return locationRepository;
        }
        public static TableFactory getTableFactory()
        {
            return tableFactory;
        }

        public UserRepository getUserRepository()
        {
            return userRepository;
        }

        public FlightRepository getFlightRepository()
        {
            return flightRepository;
        }

        public PurchaseRepository getPurchaseRepository()
        {
            return purchaseRepository;
        }
    }
}
