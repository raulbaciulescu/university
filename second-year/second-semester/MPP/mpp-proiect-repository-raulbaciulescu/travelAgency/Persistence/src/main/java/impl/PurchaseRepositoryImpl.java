package impl;

import api.Constants;
import api.FlightRepository;
import api.PurchaseRepository;
import api.Table;
import domain.Flight;
import domain.Purchase;
import domain.dto.PurchaseDto;
import impl.database.PurchaseTable;
import impl.database.TableFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PurchaseRepositoryImpl implements PurchaseRepository {
    private final Table<Long, PurchaseDto> table;
    private final FlightRepository flightRepository;

    public PurchaseRepositoryImpl(TableFactory factory, FlightRepository flightRepository) throws SQLException {
        table = (PurchaseTable) factory.getTable(Constants.Db.Tables.PURCHASE);
        this.flightRepository = flightRepository;
    }

    @Override
    public void add(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto(purchase.getFlight().getId(), purchase.getClientName(),
                purchase.getClientAddress(), purchase.getTourists(), purchase.getNrOfSeats());
        purchaseDto.setId(purchase.getId());
        table.add(purchaseDto);
    }

    @Override
    public void update(Purchase entity, Purchase newEntity) {
        PurchaseDto purchaseDto = new PurchaseDto(entity.getFlight().getId(), entity.getClientName(), entity.getClientAddress(),
                entity.getTourists(), entity.getNrOfSeats());
        PurchaseDto purchaseDtoNew = new PurchaseDto(newEntity.getFlight().getId(), newEntity.getClientName(), newEntity.getClientAddress(),
                newEntity.getTourists(), newEntity.getNrOfSeats());
        purchaseDto.setId(entity.getId());
        purchaseDtoNew.setId(entity.getId());
        table.update(purchaseDto, purchaseDtoNew);
    }


    @Override
    public Optional<Purchase> findByID(Long aLong) throws SQLException {
        Optional<PurchaseDto> opt = table.findById(aLong);
        if (opt.isPresent()) {
            Optional<Flight> flight = flightRepository.findByID(opt.get().getFlightId());
            if (flight.isPresent()) {
                Purchase purchase = new Purchase(flight.get(), opt.get().getClientName(), opt.get().getClientAddress(),
                        opt.get().getTourists(),opt.get().getNrOfSeats());
                return Optional.of(purchase);
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Purchase> getAll() {
        List<PurchaseDto> purchaseDtos = table.getAll();
        List<Purchase> purchases = new ArrayList<>();
        for (PurchaseDto purchaseDto : purchaseDtos) {
            try {
                //Optional<Flight> flight = Resources.getInstance().getFlightRepository().findByID(purchaseDto.getFlightId());
                Optional<Flight> flight = flightRepository.findByID(purchaseDto.getFlightId());
                if (flight.isPresent()) {
                    Purchase purchase = new Purchase(flight.get(), purchaseDto.getClientName(), purchaseDto.getClientAddress(),
                            purchaseDto.getTourists(),purchaseDto.getNrOfSeats());
                    purchases.add(purchase);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return purchases;
    }
}
