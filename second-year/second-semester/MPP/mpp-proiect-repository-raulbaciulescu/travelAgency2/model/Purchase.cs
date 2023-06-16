using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using model;

namespace model
{
    [Serializable]
    public class Purchase : Entity<long>
    {

        public Flight flight { get; set; }
        public string clientName { get; set; }
        public string clientAddress { get; set; }
        public List<string> tourists { get; set; }
        public int nrOfSeats { get; set; }

        public Purchase(long id, Flight flight, string clientName, string clientAddress, List<string> tourists, int nrOfSeats) : base(id)
        {
            this.flight = flight;
            this.clientName = clientName;
            this.clientAddress = clientAddress;
            this.tourists = tourists;
            this.nrOfSeats = nrOfSeats; 
        }
        
        public Purchase(Flight flight, string clientName, string clientAddress, List<string> tourists, int nrOfSeats) : base(0L)
        {
            this.flight = flight;
            this.clientName = clientName;
            this.clientAddress = clientAddress;
            this.tourists = tourists;
            this.nrOfSeats = nrOfSeats; 
        }
        
        public override string ToString()
        {
            return base.Id + " " + flight + " " + clientName + " " + clientAddress;
        }

        public override bool Equals(object obj)
        {
            return obj is Purchase purchase &&
                   Id == purchase.Id &&
                   EqualityComparer<Flight>.Default.Equals(flight, purchase.flight) &&
                   clientName == purchase.clientName &&
                   clientAddress == purchase.clientAddress &&
                   EqualityComparer<List<string>>.Default.Equals(tourists, purchase.tourists) &&
                   nrOfSeats == purchase.nrOfSeats;
        }

        public override int GetHashCode()
        {
            int hashCode = -1198551226;
            hashCode = hashCode * -1521134295 + Id.GetHashCode();
            hashCode = hashCode * -1521134295 + EqualityComparer<Flight>.Default.GetHashCode(flight);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(clientName);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(clientAddress);
            hashCode = hashCode * -1521134295 + EqualityComparer<List<string>>.Default.GetHashCode(tourists);
            hashCode = hashCode * -1521134295 + nrOfSeats.GetHashCode();
            return hashCode;
        }
    }
}
