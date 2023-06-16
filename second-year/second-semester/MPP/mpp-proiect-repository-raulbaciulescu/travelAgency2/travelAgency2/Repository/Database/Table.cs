using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using travelAgency2.src.Domain;

namespace travelAgency2.Repository.Database
{
    internal interface Table<ID, T> where T : Entity<ID>
    {
        void Add(T elem);
        void Delete(ID id);
        void Update(T elem, T newElem);
        T FindById(ID id);
        List<T> GetAll();
    }
}
