using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace model
{
    [Serializable]
    public class Location : Entity<long>
    {
        public string name { get; set; }
        public string airport { get; set; }

        public Location(long id, string name, string airport) : base(id)
        {
            this.name = name;
            this.airport = airport;
        }
        public Location() : base(0L)
        {
            
        }
        
        public Location(string name) : base(0L)
        {
            this.name = name;
            airport = "";
        }

        public override string ToString()
        {
            //base.ToString() + ": " +
            return name + " " + airport;
        }
    }
}
