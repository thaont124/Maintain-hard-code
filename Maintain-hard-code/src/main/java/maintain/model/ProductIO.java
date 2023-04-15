package maintain.model;

import maintain.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductIO {

    private static List<Product> productList;

    static {
        productList = new ArrayList<>();

        productList.add(new Product("8601","86 (the band) - True Life Songs and Pictures",14.95));
        productList.add(new Product("pf01","Paddlefoot - The first CD",12.95));
        productList.add(new Product("pf02","Paddlefoot - The second CD",14.95));
        productList.add(new Product("jr01","Joe Rut - Genuine Wood Grained Finish",14.95));
    }

    public static List<Product> getAllProducts() {
        return productList;
    }
    public boolean existProduct(Product product){
        if(productList.contains(product))
            return true;
        return false;
    }

    public static Product getProductByCode(String code) {
        for (Product product : productList) {
            if (product.getCode().equals(code)) {
                return product;
            }
        }
        return null;
    }

    public static void addProduct(Product product) {
        productList.add(product);
    }

    public static void updateProduct(Product updateProduct) {
        Product oldProduct = getProductByCode(updateProduct.getCode());
        if (oldProduct != null) {
            productList.remove(oldProduct);
            productList.add(updateProduct);
        }
    }

    public static void deleteProduct(String code) {
        Product product = getProductByCode(code);
        productList.remove(product);
        System.out.println(productList);
    }

    public List<Product> readProducts() {
        return productList;
    }
}
