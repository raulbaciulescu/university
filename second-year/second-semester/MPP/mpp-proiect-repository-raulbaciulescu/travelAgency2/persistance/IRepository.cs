

using model;

namespace persistance
{
    internal interface IRepository<ID, T> where T : Entity<ID>
    {
        void Add(T entity);
        void Update(T entity, T newEntity);
        T FindByID(ID id);
        void Delete(ID id);
        List<T> GetAll();
    }
}
