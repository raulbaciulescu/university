
using model;
using model.Dto;
using model.Utils;
using persistance.Database;

namespace persistance
{
    public class PurchaseRepository : IRepository<long, Purchase>
    {
        private ITable<long, PurchaseDto> table;
        private FlightRepository flightRepository;
        
        public PurchaseRepository(TableFactory factory, FlightRepository flightRepository)
        {
            table = (PurchaseTable) factory.GetTable(Db.Tables.PURCHASE);
            this.flightRepository = flightRepository;
        }

        public void Add(Purchase purchase)
        {
            PurchaseDto purchaseDto = new PurchaseDto(purchase.Id, purchase.flight.Id, purchase.clientName,
                purchase.clientAddress, purchase.tourists, purchase.nrOfSeats);
            table.Add(purchaseDto);
        }

        public void Delete(long id)
        {
            throw new NotImplementedException();
        }

        public Purchase FindByID(long id)
        {
            PurchaseDto purchaseDto = table.FindById(id);
            Flight flight = flightRepository.FindByID(purchaseDto.flightId);
            return new Purchase(purchaseDto.Id, flight, purchaseDto.clientName, purchaseDto.clientAddress,
                purchaseDto.tourists, purchaseDto.nrOfSeats);
        }

        public List<Purchase> GetAll()
        {
            List<PurchaseDto> purchaseDtos = table.GetAll();
            List<Purchase> purchases = new List<Purchase>();
            foreach (PurchaseDto purchaseDto in purchaseDtos)
            {
                Flight flight = flightRepository.FindByID(purchaseDto.flightId);
                purchases.Add(new Purchase(purchaseDto.Id, flight, purchaseDto.clientName, purchaseDto.clientAddress,
                    purchaseDto.tourists, purchaseDto.nrOfSeats));
            }
            return purchases;
        }

        public void Update(Purchase entity, Purchase entityNew)
        {
            throw new NotImplementedException();
        }
    }
}
