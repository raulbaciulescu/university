using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Configuration;
using System.Collections.Specialized;
using travelAgency2.src.Domain;
using travelAgency2.Utils;

namespace travelAgency2
{
    internal static class Program
    {

        private static readonly log4net.ILog log = log4net.LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {

            string conf;
            conf = ConfigurationManager.AppSettings.Get("db");
            Console.WriteLine(conf);

            User user = new User(3L, "raul", "pass", "raul", "baciulescu");
            //Resources.getInstance().getUserRepository().add(user);
            
            Console.WriteLine(Resources.GetInstance().getUserRepository().FindByID(1L));

            //Location location = new Location(2, "nume1", "airport2");
            //Resources.getInstance().getLocationRepository().add(location);
            //Resources.getInstance().getLocationRepository().getAll().ForEach(Console.WriteLine);

            //Flight flight = new Flight(2, location, location, DateTime.UtcNow, 3);
            //Resources.getInstance().getFlightRepository().add(flight);
            //Resources.getInstance().getFlightRepository().getAll().ForEach(Console.WriteLine);


            //List<String> tourists = new List<String>();
            //tourists.Add("raul");
            //tourists.Add("maria");
            //tourists.Add("adi");
            //Purchase purchase = new Purchase(2, flight, "paul", "anina", tourists, 4);
            //Resources.getInstance().getPurchaseRepository().add(purchase);
            //Resources.getInstance().getPurchaseRepository().getAll().ForEach(Console.WriteLine);


            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new LoginForm());
        }
    }
}
