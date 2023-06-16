

namespace persistance.Database
{
    internal interface ITable<ID, T>
    {
        void Add(T elem);
        void Delete(ID id);
        void Update(T elem, T newElem);
        T FindById(ID id);
        List<T> GetAll();
    }
}
