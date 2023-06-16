package pizzashop.repository;

import pizzashop.model.MenuDataModel;

import java.util.List;

public interface MenuRepository {
    List<MenuDataModel> getMenu();
}
