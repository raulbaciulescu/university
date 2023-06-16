
namespace model
{
    [Serializable]
    public class Flight: Entity<long>
    {
        public Location start { get; set; }
        public Location destination { get; set; }
        public DateTime startDate { get; set; }
        public int nrOfSeats { get; set; }
        public Flight() : base(0L)
        {
            
        }
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
            return Id + " " + start + " " + destination + " " + startDate + " " + nrOfSeats;
        }
    }
}
