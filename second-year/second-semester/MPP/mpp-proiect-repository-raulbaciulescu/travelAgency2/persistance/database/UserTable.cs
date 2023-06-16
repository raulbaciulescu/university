
using System.Data.SQLite;
using model;
using model.Utils;
using persistance.Database;

namespace travelAgency2.Repository.Database
{
    internal class UserTable : ITable<long, User>
    {
        private SQLiteConnection connection;
        private Dictionary<Db.Queries, SQLiteCommand> statements;
        private static readonly log4net.ILog logger = log4net.LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);


        public UserTable(SQLiteConnection connection)
        {
            logger.Info("Initializing UserTable");
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
            SQLiteCommand command5 = new SQLiteCommand(connection);
            command1.CommandText = "INSERT INTO user (id, username, password, firstName, lastName) VALUES (@id, @username, @password, @firstName, @lastName)";
            command2.CommandText = "DELETE FROM user WHERE id = @id";
            command3.CommandText = "SELECT * FROM user WHERE id = @id";
            command4.CommandText = "SELECT * FROM user";
            command5.CommandText = "SELECT * FROM user WHERE username = @username AND password = @password;";
            statements.Add(Db.Queries.ADD, command1);
            statements.Add(Db.Queries.DELETE, command2);
            statements.Add(Db.Queries.FIND_BY_ID, command3);
            statements.Add(Db.Queries.GET_ALL, command4);
            statements.Add(Db.Queries.FIND2, command5);
        }
        public void Update(User user, User userNew)
        {
            //TODO
        }

        public void Add(User user)
        {
            logger.Info("enter in add user");
            SQLiteCommand statement = statements[Db.Queries.ADD];
            try
            {
                statement.Parameters.AddWithValue("@id", user.Id);
                statement.Parameters.AddWithValue("@username", user.username);
                statement.Parameters.AddWithValue("@password", user.password);
                statement.Parameters.AddWithValue("@firstName", user.firstName);
                statement.Parameters.AddWithValue("@lastName", user.lastName);
                int numberofrowsaffected = statement.ExecuteNonQuery();
                String s = String.Format("Saved {0} instances", numberofrowsaffected);
                logger.Info(numberofrowsaffected);
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                Console.WriteLine(ex.Message);
            }
            logger.Info("exit from add user");
        }

        public void Delete(long id)
        {
           //TODO
        }

        public User FindById(long id)
        {
            logger.Info("enter in findById user");
            List<User> users = new List<User>();  
            SQLiteCommand statement = statements[Db.Queries.FIND_BY_ID];
            statement.Parameters.AddWithValue("@id", id);
            SQLiteDataReader reader = statement.ExecuteReader();
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    User user = new User((long)reader.GetInt64(4), reader.GetString(0), reader.GetString(1), reader.GetString(2), reader.GetString(3));
                    users.Add(user);
                }
            }
            logger.Info("exit from findById user");
            reader.Close();
            if (users.Count > 0)
                return users[0];
            return null;
        }

        public List<User> GetAll()
        {
            logger.Info("enter in getAll user");
            List<User> users = new List<User>();
            SQLiteCommand statement = statements[Db.Queries.GET_ALL];
            SQLiteDataReader reader = statement.ExecuteReader();
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    User user = new User((long) reader.GetInt64(4), reader.GetString(0), reader.GetString(1), reader.GetString(2), reader.GetString(3));
                    users.Add(user);
                }
            }
            reader.Close();
            logger.Info("exit from getAll user");
            return users;
        }
        public User findUser(string username, string password)
        {
            logger.Info("enter in findUser user");
            List<User> users = new List<User>();
            SQLiteCommand statement = statements[Db.Queries.FIND2];
            statement.Parameters.AddWithValue("@username", username);
            statement.Parameters.AddWithValue("@password", password);
            SQLiteDataReader reader = statement.ExecuteReader();
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    User user = new User((long)reader.GetInt64(4), reader.GetString(0), reader.GetString(1), reader.GetString(2), reader.GetString(3));
                    users.Add(user);
                }
            }
            logger.Info("exit from findUser user");
            reader.Close();
            if (users.Count > 0)
                return users[0];
            return null;
        }
    }
}
