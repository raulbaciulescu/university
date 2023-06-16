
using model;
using model.Utils;
using persistance.Database;


namespace persistance
{
    public class LocationRepository : IRepository<long, Location>
    {
        private ITable<long, Location> table;

        public LocationRepository(TableFactory factory)
        {
            table = (LocationTable) factory.GetTable(Db.Tables.LOCATION);
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
