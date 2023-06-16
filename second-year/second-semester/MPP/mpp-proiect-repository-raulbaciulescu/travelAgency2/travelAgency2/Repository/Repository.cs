using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using travelAgency2.src.Domain;

namespace travelAgency2.src.Repository
{
    internal interface Repository<ID, T> where T : Entity<ID>
    {
        void Add(T entity);
        void Update(T entity, T newEntity);
        T FindByID(ID id);
        void Delete(ID id);
        List<T> GetAll();
    }
}
