using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using model.Dto;
using model.Utils;
using travelAgency2.Repository.Database;

namespace persistance.Database
{
    internal class PurchaseTable : ITable<long, PurchaseDto>
    {
        private SQLiteConnection connection;
        private Dictionary<Db.Queries, SQLiteCommand> statements;
        private Dictionary<Db.Queries, SQLiteCommand> statementsTourist;
        private static readonly log4net.ILog logger = log4net.LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);

        public PurchaseTable(SQLiteConnection connection)
        {
            logger.Info("Initializing PurchaseTable");
            this.connection = connection;
            this.statements = new Dictionary<Db.Queries, SQLiteCommand>();
            this.statementsTourist = new Dictionary<Db.Queries, SQLiteCommand>();
            InitStatements();
        }

        void InitStatements()
        {
            SQLiteCommand command1 = new SQLiteCommand(connection);
            SQLiteCommand command2 = new SQLiteCommand(connection);
            SQLiteCommand command3 = new SQLiteCommand(connection);
            SQLiteCommand command4 = new SQLiteCommand(connection);
            SQLiteCommand command5 = new SQLiteCommand(connection);
            SQLiteCommand command6 = new SQLiteCommand(connection);
            command1.CommandText = "INSERT INTO purchase (id, flightId, clientName, clientAddress, nrOfSeats) " +
                "VALUES (@id, @flightId, @clientName, @clientAddress, @nrOfSeats)";
            command2.CommandText = "DELETE FROM purchase WHERE id = @id";
            command3.CommandText = "SELECT FROM purchase WHERE id = @id";
            command4.CommandText = "SELECT * FROM purchase";
            command5.CommandText = "INSERT INTO tourist(name, purchaseId) VALUES(?, ?)";
            command6.CommandText = "SELECT * FROM tourist WHERE purchaseId = ?";
            statements.Add(Db.Queries.ADD, command1);
            statements.Add(Db.Queries.DELETE, command2);
            statements.Add(Db.Queries.FIND_BY_ID, command3);
            statements.Add(Db.Queries.GET_ALL, command4);
            statementsTourist.Add(Db.Queries.ADD, command5);
            statementsTourist.Add(Db.Queries.FIND_BY_ID, command6);
        }
        public void AddTourists(long id, List<String> tourists)
        {
            logger.Info("enter in add tourist");
            SQLiteCommand statement = statementsTourist[Db.Queries.ADD];
            foreach (String tour in tourists)
            {
                try
                {
                    statement.Parameters.AddWithValue("@name", tour);
                    statement.Parameters.AddWithValue("@purchaseId", id);
                    int numberofrowsaffected = statement.ExecuteNonQuery();
                    logger.InfoFormat("Saved {0} instances", numberofrowsaffected);
                }
                catch (Exception ex)
                {
                    logger.Error(ex);
                    Console.WriteLine(ex.Message);
                }

            }
            logger.Info("exit from add tourist");
        }
        public void Add(PurchaseDto purchaseDto)
        {
            logger.Info("enter in add purchase");
            SQLiteCommand statement = statements[Db.Queries.ADD];
            try
            {
                statement.Parameters.AddWithValue("@id", purchaseDto.Id);
                statement.Parameters.AddWithValue("@flightId", purchaseDto.flightId);
                statement.Parameters.AddWithValue("@clientName", purchaseDto.clientName);
                statement.Parameters.AddWithValue("@clientAddress", purchaseDto.clientAddress);
                statement.Parameters.AddWithValue("@nrOfSeats", purchaseDto.nrOfSeats);
                AddTourists(purchaseDto.Id, purchaseDto.tourists);
                int numberofrowsaffected = statement.ExecuteNonQuery();
                logger.InfoFormat("Saved {0} instances", numberofrowsaffected);
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                Console.WriteLine(ex.Message);
            }
            logger.Info("exit from add purchase");
        }

        public void Delete(long id)
        {
            //TODO
        }
        public void Update(PurchaseDto purchaseDto, PurchaseDto purchaseDto1)
        {
            //TODO
        }
        public List<String> getTourists(long id)
        {
            logger.Info("enter in getTourists");
            List<String> tourists = new List<String>();
            SQLiteCommand statement = statementsTourist[Db.Queries.FIND_BY_ID];
            statement.Parameters.AddWithValue("@id", id);
            SQLiteDataReader reader = statement.ExecuteReader();
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    tourists.Add(reader.GetString(0));
                }
            }
            reader.Close();
            logger.Info("exit from get tourists");
            return tourists;
        }
        public PurchaseDto FindById(long id)
        {
            logger.Info("enter in findById purchase");
            List<PurchaseDto> purchaseDtos = new List<PurchaseDto>();
            SQLiteCommand statement = statements[Db.Queries.FIND_BY_ID];
            statement.Parameters.AddWithValue("@id", id);
            SQLiteDataReader reader = statement.ExecuteReader();
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    List<String> tourists = getTourists(id);
                    PurchaseDto purchaseDto = new PurchaseDto((long) reader.GetInt64(0),
                        (long)reader.GetInt64(1), reader.GetString(2), reader.GetString(3), tourists, reader.GetInt32(4));
                    purchaseDtos.Add(purchaseDto);
                }
            }
            reader.Close();
            logger.Info("exit from findById purchase");
            return purchaseDtos[0];
        }

        public List<PurchaseDto> GetAll()
        {
            logger.Info("enter in getAll purchase");
            List<PurchaseDto> purchaseDtos = new List<PurchaseDto>();
            SQLiteCommand statement = statements[Db.Queries.GET_ALL];
            SQLiteDataReader reader = statement.ExecuteReader();
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    long id = (long)reader.GetInt64(0);
                    List<String> tourists = getTourists(id);
                    PurchaseDto purchaseDto = new PurchaseDto(id,
                        (long)reader.GetInt64(1), reader.GetString(2), reader.GetString(3), tourists, reader.GetInt32(4));
                    purchaseDtos.Add(purchaseDto);
                }
            }
            reader.Close();
            logger.Info("exit from getAll purchase");
            return purchaseDtos;
        }
    }
}
