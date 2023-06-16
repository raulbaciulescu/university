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
    internal class LocationRepository : Repository<long, Location>
    {
        private Table<long, Location> table;

        public LocationRepository()
        {
            table = (LocationTable) Resources.getTableFactory().getTable(Constants.Db.Tables.LOCATION);
        }

        public void Add(Location entity)
        {
            table.Add(entity);
        }

        public void Delete(long id)
        {
            throw new NotImplementedException();
        }

        public Location FindByID(long id)
        {
            return table.FindById(id);
        }

        public List<Location> GetAll()
        {
            return table.GetAll();
        }


        public void Update(Location entity, Location enitityNew)
        {
            throw new NotImplementedException();
        }
    }
}
