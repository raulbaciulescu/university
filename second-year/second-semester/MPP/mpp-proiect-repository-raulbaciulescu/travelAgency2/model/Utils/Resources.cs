

namespace model
{
    public class Resources
    {
        static Resources instance = null;
        private long? selectedId = null;
        public static Resources GetInstance()
        {
            if (instance == null)
                instance = new Resources();
            return instance;
        }
        private Resources() 
        {
        }

        public void SetSelectedId(long flight)
        {
            this.selectedId = flight;
        }

        public long GetSelectedId()
        {
            return (long) this.selectedId;
        }
        
    }
}
