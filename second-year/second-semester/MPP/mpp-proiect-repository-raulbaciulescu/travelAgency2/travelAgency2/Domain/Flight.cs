using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace travelAgency2.src.Domain
{
    internal class Flight: Entity<long>
    {
        public Location start { get; set; }
        public Location destination { get; set; }
        public DateTime startDate { get; set; }
        public int nrOfSeats { get; set; }

        public Flight(long id, Location start, Location destination, DateTime startDate, int nrOfSeats) : base(id)
        {
            this.start = start;
            this.destination = destination;
            this.startDate = startDate;
            this.nrOfSeats = nrOfSeats;
        }

        public override string ToString()
        {
            //base.ToString() + ": " +
            return start + " " + destination + " " + startDate + " " + nrOfSeats;
        }
    }
}
