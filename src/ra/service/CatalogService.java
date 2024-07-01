package ra.service;

import ra.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements ICatalog{
    public static List<Catalog> catalogs = new ArrayList<>();

    @Override
    public List<Catalog> getAll() {
        return catalogs;
    }

    @Override
    public void save(Catalog catalog) {
        Catalog checkIndex = findById(catalog.getCatalogId());
        if (checkIndex == null) {
            catalogs.add(catalog);
        }else {
            catalogs.set(catalogs.indexOf(checkIndex), catalog);
        }
    }

    @Override
    public Catalog findById(Integer id) {
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogId() == id) {
                return catalog;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        Catalog indexDelete = findById(id);
        if (indexDelete != null) {
            boolean isExist = false;
            for (int i = 0; i< ProductService.products.size(); i++){
                if (ProductService.products.get(i).getCatalog().getCatalogId()== id ){
                    isExist = true;
                    break;
                }
            }
            if (isExist){
                System.err.println("ko xóa khi có sản phẩm)");
            }else {
                catalogs.remove(indexDelete);
            }
        }else {
            System.err.println("No such class");
        }
    }
}
