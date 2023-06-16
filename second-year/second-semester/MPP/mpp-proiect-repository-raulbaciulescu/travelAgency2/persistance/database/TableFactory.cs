
using System.Data.SQLite;
using System.Configuration;
using Microsoft.VisualBasic;
using model.Utils;
using persistance.Database;
using travelAgency2.Repository.Database;

namespace persistance.Database
{
    public class TableFactory
    {
        private SQLiteConnection connection;
        public TableFactory()
        {
            string config = ConfigurationManager.AppSettings.Get("db");
            this.connection = new SQLiteConnection("URI=file:D:\\Facultate\\MPP\\mpp-proiect-repository-raulbaciulescu\\travelAgency.db");
            try
            {
                connection.Open();
                Console.WriteLine("open");
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
        }

        public object GetTable(Db.Tables table)
        {
            return table switch
            {
                Db.Tables.USER => new UserTable(connection),
                Db.Tables.FLIGHT => new FlightTable(connection),
                Db.Tables.PURCHASE => new PurchaseTable(connection),
                _ => new LocationTable(connection)
            };
        }
}

}
