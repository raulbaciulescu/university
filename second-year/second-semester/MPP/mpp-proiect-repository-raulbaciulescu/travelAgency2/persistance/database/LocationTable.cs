
using System.Data.SQLite;
using Microsoft.VisualBasic;
using model;
using model.Utils;
using travelAgency2.Repository.Database;


namespace persistance.Database
{
    internal class LocationTable : ITable<long, Location>
    {
        private SQLiteConnection connection;
        private Dictionary<Db.Queries, SQLiteCommand> statements;
        private static readonly log4net.ILog logger = log4net.LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);


        public LocationTable(SQLiteConnection connection)
        {
            logger.Info("Initializing LocationTable");
            this.connection = connection;
            this.statements = new Dictionary<Db.Queries, SQLiteCommand>();
            InitStatements();
        }

        void InitStatements()
        {
            SQLiteCommand command1 = new SQLiteCommand(connection);
            SQLiteCommand command2 = new SQLiteCommand(connection);
            SQLiteCommand command3 = new SQLiteCommand(connection);
            SQLiteCommand command4 = new SQLiteCommand(connection);
            command1.CommandText = "INSERT INTO location (id, name, airport) VALUES (@id, @name, @airport)";
            command2.CommandText = "DELETE FROM location WHERE id = @id";
            command3.CommandText = "SELECT * FROM location WHERE id = @id";
            command4.CommandText = "SELECT * FROM location";
            statements.Add(Db.Queries.ADD, command1);
            statements.Add(Db.Queries.DELETE, command2);
            statements.Add(Db.Queries.FIND_BY_ID, command3);
            statements.Add(Db.Queries.GET_ALL, command4);
        }
        public void Add(Location location)
        {
            logger.Info("enter in add location");
            SQLiteCommand statement = statements[Db.Queries.ADD];
            try
            {
                statement.Parameters.AddWithValue("@id", location.Id);
                statement.Parameters.AddWithValue("@name", location.name);
                statement.Parameters.AddWithValue("@airport", location.airport);
                int numberofrowsaffected = statement.ExecuteNonQuery();
                logger.InfoFormat("Saved {0} instances", numberofrowsaffected);
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                Console.WriteLine(ex.Message);
            }
            logger.Info("exit from add location");
        }
        public void Delete(long id)
        {
            //TODO
        }

        public void Update(Location loc1, Location loc2)
        {
            //TODO
        }
        public Location FindById(long id)
        {
            logger.Info("enter in findById location");
            List<Location> locations = new List<Location>();
            SQLiteCommand statement = statements[Db.Queries.FIND_BY_ID];
            statement.Parameters.AddWithValue("@id", id);
            SQLiteDataReader reader = statement.ExecuteReader();
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    Location location = new Location((long) reader.GetInt64(2), reader.GetString(0), reader.GetString(1));
                    locations.Add(location);
                }
            }
            reader.Close();
            logger.Info("exit from findById location");
            return locations[0];
        }

        public List<Location> GetAll()
        {
            logger.Info("enter in getAll location");
            List<Location> locations = new List<Location>();
            SQLiteCommand statement = statements[Db.Queries.GET_ALL];
            SQLiteDataReader reader = statement.ExecuteReader();
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    Location location = new Location((long)reader.GetInt64(2), reader.GetString(0), reader.GetString(1));
                    locations.Add(location);
                }
            }
            reader.Close();
            logger.Info("exit from getAll location");
            return locations;
        }
    }
}
