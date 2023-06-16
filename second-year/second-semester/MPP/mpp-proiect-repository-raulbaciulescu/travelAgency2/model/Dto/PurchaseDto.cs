namespace model.Dto
{
    public class PurchaseDto : Entity<long>
    {

        public long flightId { get; set; }
        public string clientName { get; set; }
        public string clientAddress { get; set; }
        public List<string> tourists { get; set; }
        public int nrOfSeats { get; set; }

        public PurchaseDto(long id, long flightId, string clientName, string clientAddress, List<string> tourists, int nrOfSeats) : base(id)
        {
            this.flightId = flightId;
            this.clientName = clientName;
            this.clientAddress = clientAddress;
            this.tourists = tourists;
            this.nrOfSeats = nrOfSeats;
        }
    }
}
