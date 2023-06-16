using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace travelAgency2.src.Domain
{
    internal class Location : Entity<long>
    {
        public string name { get; set; }
        public string airport { get; set; }

        public Location(long id, string name, string airport) : base(id)
        {
            this.name = name;
            this.airport = airport;
        }

        public override string ToString()
        {
            //base.ToString() + ": " +
            return name + " " + airport;
        }
    }
}
