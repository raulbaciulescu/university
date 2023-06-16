
using model;
using model.Utils;
using persistance.Database;
using travelAgency2.Repository.Database;


namespace persistance
{
    public class UserRepository : IRepository<long, User>
    {
        private UserTable table;

        public UserRepository(TableFactory factory)
        {
            table = (UserTable) factory.GetTable(Db.Tables.USER);
         }
           
        public void Add(User entity)
        {
            table.Add(entity);
        }

        public void Delete(long id)
        {
            throw new NotImplementedException();
        }

        public User FindByID(long id)
        {
            return table.FindById(id);
        }

        public List<User> GetAll()
        {
            return table.GetAll();
        }

        public void Update(User entity, User entityNew)
        {
            throw new NotImplementedException();
        }

        public User FindUser(string username, string password)
        {
            return table.findUser(username, password);
        }
    }
}
