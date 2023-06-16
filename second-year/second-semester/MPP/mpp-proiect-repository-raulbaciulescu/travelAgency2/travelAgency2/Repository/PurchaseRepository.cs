using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using travelAgency2.Domain.Dto;
using travelAgency2.Repository.Database;
using travelAgency2.src.Domain;
using travelAgency2.src.Repository;
using travelAgency2.Utils;

namespace travelAgency2.Repository
{
    internal class PurchaseRepository : Repository<long, Purchase>
    {
        private Table<long, PurchaseDto> table;

        public PurchaseRepository()
        {
            table = (PurchaseTable)Resources.getTableFactory().getTable(Constants.Db.Tables.PURCHASE);
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
            Flight flight = Resources.GetInstance().getFlightRepository().FindByID(purchaseDto.flightId);
            return new Purchase(purchaseDto.Id, flight, purchaseDto.clientName, purchaseDto.clientAddress,
                purchaseDto.tourists, purchaseDto.nrOfSeats);
        }

        public List<Purchase> GetAll()
        {
            List<PurchaseDto> purchaseDtos = table.GetAll();
            List<Purchase> purchases = new List<Purchase>();
            foreach (PurchaseDto purchaseDto in purchaseDtos)
            {
                Flight flight = Resources.GetInstance().getFlightRepository().FindByID(purchaseDto.flightId);
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
