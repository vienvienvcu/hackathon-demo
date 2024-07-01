package ra.service;

import ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProduct{
    public static List<Product> products = new ArrayList<>();
    @Override
    public List getAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        Product indexCheck = findById(product.getProductId());
        if (indexCheck != null) {
            products.set(Integer.parseInt(indexCheck.getProductId()), product);
        }else {
            products.add(product);
        }

    }

    @Override
    public Product findById(String id) {
        for (Product product : products) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        Product indexDelete = findById(id);
        if (indexDelete != null) {
            products.remove(indexDelete);
        }else {
            System.err.println("No such product,can't delete");
        }

    }

}
