package domain.dto;

import domain.Purchase;
import domain.User;

public class DtoUtils {
    public static User getFromDTO(UserDto usdto){
        String username = usdto.getUsername();
        String password = usdto.getPassword();
        //return new User(id, pass);
        return new User(username, password);
    }
    public static UserDto getDTO(User user){
        String username = user.getUsername();
        String pass = user.getPassword();
        return new UserDto(username, pass);
    }

    public static PurchaseDto getDTO(Purchase purchase) {
        return new PurchaseDto(purchase.getFlight().getId(),
                purchase.getClientName(), purchase.getClientAddress(), purchase.getTourists(), purchase.getNrOfSeats());
    }

//    public static Purchase getFromDto(PurchaseDto purchaseDto) {
//        return new Purchase()
//    }
}
