using System.Data.SQLite;
using Microsoft.VisualBasic;
using travelAgency2.Repository.Database;

using model;
using model.Dto;
using model.Utils;

namespace persistance.Database
{
    internal class FlightTable : ITable<long, FlightDto>
    {
        private SQLiteConnection connection;
        private Dictionary<Db.Queries, SQLiteCommand> statements;
        private static readonly log4net.ILog logger = log4net.LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);

        public FlightTable(SQLiteConnection connection)
        {
            logger.Info("Initializing FlightTable");
            this.connection = connection;
            this.statements = new Dictionary<Db.Queries, SQLiteCommand>();
            InitStatements();
        }

        private void InitStatements()
        {

            SQLiteCommand command1 = new SQLiteCommand(connection);
            SQLiteCommand command2 = new SQLiteCommand(connection);
            SQLiteCommand command3 = new SQLiteCommand(connection);
            SQLiteCommand command4 = new SQLiteCommand(connection);
            SQLiteCommand command5 = new SQLiteCommand(connection);
            command1.CommandText = "INSERT INTO flight (id, startId, destinationId, startDate, nrOfSeats) " +
                "VALUES (@id, @startId, @destinationId, @startDate, @nrOfSeats)";
            command2.CommandText = "DELETE * FROM flight WHERE id = @id";
            command3.CommandText = "SELECT * FROM flight WHERE id = @id";
            command4.CommandText = "SELECT * FROM flight";
            command5.CommandText = "UPDATE flight SET nrOfSeats = @nrOfSeats WHERE id = @id";
            statements.Add(Db.Queries.ADD, command1);
            statements.Add(Db.Queries.DELETE, command2);
            statements.Add(Db.Queries.FIND_BY_ID, command3);
            statements.Add(Db.Queries.GET_ALL, command4);
            statements.Add(Db.Queries.UPDATE, command5);
        }

        public void Add(FlightDto flight)
        {
            logger.Info("enter in add flight");
            SQLiteCommand statement = statements[Db.Queries.ADD];
            try
            {
                statement.Parameters.AddWithValue("@id", flight.Id);
                statement.Parameters.AddWithValue("@startId", flight.startId);
                statement.Parameters.AddWithValue("@destinationId", flight.destinationId);
                statement.Parameters.AddWithValue("@startDate", flight.startDate.ToString());
                statement.Parameters.AddWithValue("@nrOfSeats", flight.nrOfSeats);
                int numberofrowsaffected = statement.ExecuteNonQuery();
                logger.InfoFormat("Saved {0} instances", numberofrowsaffected);
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                Console.WriteLine(ex.Message);
            }
            logger.Info("exit from add flight");
        }
        public void Delete(long id)
        {
            //TODO
        }

        public void Update(FlightDto flightDto, FlightDto flightDto1)
        {
            logger.Info("enter in update flight");
            SQLiteCommand statement = statements[Db.Queries.UPDATE];
            try
            {
                statement.Parameters.AddWithValue("@id", flightDto.Id);
                statement.Parameters.AddWithValue("@nrOfSeats", flightDto1.nrOfSeats);
                int numberofrowsaffected = statement.ExecuteNonQuery();
                logger.InfoFormat("Updated {0} instances", numberofrowsaffected);
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                Console.WriteLine(ex.Message);
            }
            logger.Info("exit from update flight");
        }
        public FlightDto FindById(long id)
        {
            logger.Info("enter in findById flight");
            List<FlightDto> flights = new List<FlightDto>();
            SQLiteCommand statement = statements[Db.Queries.FIND_BY_ID];
            statement.Parameters.AddWithValue("@id", id);
            SQLiteDataReader reader = statement.ExecuteReader();
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    FlightDto flightDto = new FlightDto((long)reader.GetInt64(4),
                        (long)reader.GetInt64(0), (long)reader.GetInt64(1), DateTime.Parse(reader.GetString(2)),
                        reader.GetInt32(3));
                    flights.Add(flightDto);
                }
            }
            reader.Close();
            logger.Info("exit from findById location");
            return flights[0];
        }

        public List<FlightDto> GetAll()
        {
            logger.Info("enter in getAll flight");
            List<FlightDto> flights = new List<FlightDto>();
            SQLiteCommand statement = statements[Db.Queries.GET_ALL];
            SQLiteDataReader reader = statement.ExecuteReader();
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    FlightDto flightDto = new FlightDto((long)reader.GetInt64(4),
                        (long)reader.GetInt64(0), (long)reader.GetInt64(1), DateTime.Parse(reader.GetString(2)),
                        reader.GetInt32(3));
                    flights.Add(flightDto);
                }
            }
            reader.Close();
            logger.Info("exit from getAll location");
            return flights;
        }
    }
}
