using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using travelAgency2.Repository.Database;
using travelAgency2.src.Domain;
using travelAgency2.src.Repository;
using travelAgency2.Utils;

namespace travelAgency2.Repository
{
    internal class UserRepository : Repository<long, User>
    {
        private UserTable table;

        public UserRepository()
        {
            table = (UserTable) Resources.getTableFactory().getTable(Constants.Db.Tables.USER);
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

        internal User findUser(string username, string password)
        {
            return table.findUser(username, password);
        }
    }
}
