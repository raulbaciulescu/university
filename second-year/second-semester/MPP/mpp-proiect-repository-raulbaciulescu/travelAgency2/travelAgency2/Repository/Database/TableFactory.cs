using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SQLite;
using System.Configuration;
using travelAgency2.Utils;
using travelAgency2.src.Domain;

namespace travelAgency2.Repository.Database
{
    internal class TableFactory
    {
        private SQLiteConnection connection;
        public TableFactory()
        {
            string config = ConfigurationManager.AppSettings.Get("db");
            this.connection = new SQLiteConnection(config);
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

        public object getTable(Constants.Db.Tables table)
        {
            if (table == Constants.Db.Tables.USER)
                return new UserTable(connection);
            if (table == Constants.Db.Tables.FLIGHT)
                return new FlightTable(connection);
            if (table == Constants.Db.Tables.PURCHASE)
                return new PurchaseTable(connection);
            return new LocationTable(connection);
    }
}

}
