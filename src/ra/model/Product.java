package ra.model;

import jdk.jshell.Snippet;
import ra.service.CatalogService;
import ra.service.ProductService;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Product implements Comparable<Product> {
    private String productId;
    private String productName;
    private double productPrice;
    private String productDescription;
    private  int stock;
    private Catalog catalog;
    private boolean status;

    public Product() {
    }

    public Product(String productId, String productName,
                   double productPrice, String productDescription,
                   int stock, Catalog catalog, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.stock = stock;
        this.catalog = catalog;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputProductData(Scanner scanner){
        this.productId = inputProductId(scanner);
        this.productName = inputProductName(scanner);
        this.productPrice = inputProductPrice(scanner);
        this.productDescription = inputProductDescription(scanner);
        this.stock = inputProductStock(scanner);
        this.catalog = inputProductCatalog(scanner);
        this.status =inputProductStatus(scanner);


    }

    public String inputProductId(Scanner scanner){
        System.out.println("Enter product ID");
        do {
            String productId = scanner.nextLine();
            String productIdRegex = "^P\\d{3}";
               if (Pattern.matches(productIdRegex, productId)){
                   boolean isExist = false;
                   for (Product product : ProductService.products){
                       if (product.getProductId().equals(productId)){
                           isExist = true;
                           break;
                       }
                   }
                   if (isExist){
                       System.err.println("Product Id is already exist");
                   }else {
                       return productId;
                   }
               }else {
                   System.err.println("Product ID is must 4 chars and start with 'P', please try again");
               }

        }while (true);

    }

    public String inputProductName(Scanner scanner){
        System.out.println("Enter product name");
        do {
            String productName = scanner.nextLine();
            if (productName.isEmpty()){
                System.err.println("Product name is cannot empty,please try again");
            }else {
                boolean isExist = false;
                for (Product product : ProductService.products){
                    if (product.getProductName().equals(productName)){
                        isExist = true;
                        break;
                    }
                }
                if (isExist){
                    System.err.println("Product Name is already exist");
                }else {
                    return productName;
                }
            }
        }while (true);
    }

    public double inputProductPrice(Scanner scanner){
        System.out.println("Enter product price");
        do {
            try {
                double productPrice = Integer.parseInt(scanner.nextLine());
                    if (productPrice < 0){
                        System.err.println("Product price is must >");
                    }else {
                        return productPrice;
                    }

            }catch (NumberFormatException e){
                System.err.println("Enter product price must be a number > 0");
            }

        }while (true);
    }

    public String inputProductDescription(Scanner scanner){
        System.out.println("Enter product description");
        do {
            String productDescription = scanner.nextLine();
            if (productDescription.isEmpty()){
                System.err.println("Product description is cannot empty,please try again");
            }else {
                return productDescription;
            }
        }while (true);
    }

    public int inputProductStock(Scanner scanner){
        System.out.println("Enter product stock");
        do {
            try {
                int productStock = Integer.parseInt(scanner.nextLine());
                if (productStock>=10){
                    return productStock;
                }else {
                    System.err.println("Enter product stock must be a number >= 10");
                }

            }catch (NumberFormatException e){
                System.err.println("Enter product stock must be a number >= 10");
            }

        }while (true);
    }

    public Catalog inputProductCatalog(Scanner scanner){
        do {
            for (int i = 0; i < CatalogService.catalogs.size(); i++){
                System.out.println((i+1) + ". " + CatalogService.catalogs.get(i).getCatalogName());
            }
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice > 0 && choice <= CatalogService.catalogs.size()){
                    return CatalogService.catalogs.get(choice - 1);
                }
            }catch (NumberFormatException e){
                System.err.println("Enter choice is number");
            }
        }while (true);
    }

    public Boolean inputProductStatus(Scanner scanner){
        System.out.println("Enter product status");
        do {
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")){
                return Boolean.parseBoolean(status);
            }else {
                System.err.println("Product Status only 2 value 'true' and 'false'");
            }
        }while (true);
    }

    public void outputProductData(){
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s \n",
                "Product id","Product Name","price","Description","Stock","Catalog","Status");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s \n",
                this.productId,this.productName,this.productPrice,this.productDescription,
                this.stock,this.catalog.getCatalogName(),this.status?"Active":"Inactive");
        }

    @Override
    public int compareTo(Product o) {
        return Double.compare(o.getProductPrice(), this.getProductPrice());
    }

}
