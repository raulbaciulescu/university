namespace model.Dto
{
    public class FlightDto : Entity<long>
    {

        public long startId { get; set; }
        public long destinationId { get; set; }
        public DateTime startDate { get; set; }
        public int nrOfSeats { get; set; }

        public FlightDto(long id, long startId, long destinationId, DateTime startDate, int nrOfSeats) : base(id)
        {
            this.startId = startId;
            this.destinationId = destinationId;
            this.startDate = startDate;
            this.nrOfSeats = nrOfSeats;
        }


    }
}
