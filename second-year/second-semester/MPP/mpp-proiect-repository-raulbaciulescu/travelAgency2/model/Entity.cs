using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace model
{
    [Serializable]
    public class Entity<ID>
    {
        public ID Id { get; set; }

        public Entity(ID id)
        {
            Id = id;
        }

    }
}
