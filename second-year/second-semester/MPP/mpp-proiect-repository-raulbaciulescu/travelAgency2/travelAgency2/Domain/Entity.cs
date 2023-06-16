using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace travelAgency2.src.Domain
{
    internal class Entity<ID>
    {
        public ID Id { get; set; }

        public Entity(ID id)
        {
            Id = id;
        }

    }
}
