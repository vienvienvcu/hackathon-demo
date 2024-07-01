package run;

import ra.model.CartItem;
import ra.model.Catalog;
import ra.model.Product;
import ra.service.CartItemService;
import ra.service.CatalogService;
import ra.service.ProductService;

import java.util.Collections;
import java.util.Scanner;

public class ProductManagement {
    public static CatalogService catalogService = new CatalogService();
    public static ProductService productService = new ProductService();
    public static CartItemService cartItemService = new CartItemService();
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******************BASIC MENU****************************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Dành cho người dùng (***)");
            System.out.println("4. Thoát");
            System.out.println("your choice 1- 3");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    menuCatalog();

                    break;
                case 2:
                    menuProduct();

                    break;
                case 3:
                    menuUser(scanner);

                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid choice, please try again choice 1-3");
            }

        }while (true);
    }
    public static void menuCatalog(){
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true;
        do {
            System.out.println("********************CATALOG-MANAGEMENT********************");
            System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
            System.out.println("2. Hiển thị thông tin tất cả các danh mục");
            System.out.println("3.Sửa tên danh mục theo mã danh mục");
            System.out.println("4.Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm)");
            System.out.println("5.Quay lại");
            System.out.println("your choice 1-5");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addCatalog(scanner);
                    break;
                case 2:
                    showCatalog();
                    break;
                case 3:
                    updateCatalogName(scanner);
                    break;
                case 4:
                    deleteCatalog(scanner);
                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.err.println("Invalid choice, please try again choice 1-5");
            }
        }while (isExit);
    }

    private static void deleteCatalog(Scanner scanner) {
        System.out.println("enter catalog id you want to delete");
        try {
            int idDelete = Integer.parseInt(scanner.nextLine());
            catalogService.delete(idDelete);
            System.out.println("delete success");
        }catch (NumberFormatException e) {
            System.err.println("Invalid catalog id, please try again");
        }
    }

    private static void updateCatalogName(Scanner scanner) {
        System.out.println("Enter catalog id you want to update");
        try {
            int idUpdate = Integer.parseInt(scanner.nextLine());
            Catalog indexUpdate = catalogService.findById(idUpdate);
            if (indexUpdate != null) {
                System.out.println("update catalog name:");
                indexUpdate.setCatalogName(scanner.nextLine());
                System.out.println("update susses");
            }else {
                System.err.println("update catalog name not found");
            }
        }catch (NumberFormatException e) {
            System.err.println("Invalid catalog id, please try again");
        }
    }

    private static void showCatalog() {
        if (catalogService.getAll().isEmpty()){
            System.err.println("Catalog is empty");
        }
        for (Catalog catalog : catalogService.getAll()){
            catalog.outputCatalogData();
        }
    }

    private static void addCatalog(Scanner scanner) {
        System.out.println("Enter the number of the catalog you want to add: ");
            int n = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < n; i++) {
                Catalog catalog = new Catalog();
                catalog.inputCatalogData(scanner);
                catalogService.save(catalog);
        }
    }

    public static void menuProduct(){
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true;
        do {
            System.out.println("********************PRODUCT-MANAGEMENT********************");
            System.out.println("1. Nhập số sản sản phẩm và nhập thông tin sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.println("4. Xóa sản phẩm theo mã");
            System.out.println("5. Tìm kiếm sách theo tên sách");
            System.out.println("6. Thay đổi thông tin của sách theo mã sách");
            System.out.println("7. quay lai");
            System.out.println("your choice 1-7");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    showProduct();
                    break;
                case 3:
                    sortByPrice();
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    searchProduct(scanner);
                    break;
                case 6:
                    updateProduct(scanner);
                    break;
                case 7:
                    isExit = false;
                    break;
                default:
                    System.err.println("Invalid choice, please try again choice 1-7");
            }
        }while (isExit);
    }

    private static void updateProduct(Scanner scanner) {
        System.out.println("Enter the  product id you want to update: ");
        String productId = scanner.nextLine();
        Product indexUpdate = productService.findById(productId);
        if (indexUpdate != null) {
            boolean isExit = true;
            do {
                System.out.println("1.update product name:");
                System.out.println("2.update product price: ");
                System.out.println("3.update product description: ");
                System.out.println("4.update product stock: ");
                System.out.println("5.update product of catalog name: ");
                System.out.println("6.update product status: ");
                System.out.println("7. exit");
                System.out.println("your choice 1-6 ");
                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("input product name:");
                            indexUpdate.setProductName(indexUpdate.inputProductName(scanner));
                            break;
                        case 2:
                            System.out.println("input product price:");
                            indexUpdate.setProductPrice(indexUpdate.inputProductPrice(scanner));
                            break;
                        case 3:
                            System.out.println("input product description:");
                            indexUpdate.setProductDescription(indexUpdate.inputProductDescription(scanner));
                            break;
                        case 4:
                            System.out.println("input product stock:");
                            indexUpdate.setStock(indexUpdate.inputProductStock(scanner));
                            break;
                        case 5:
                            System.out.println("input product of catalog name:");
                            indexUpdate.setCatalog(indexUpdate.inputProductCatalog(scanner));
                            break;
                        case 6:
                            System.out.println("input product status:");
                            indexUpdate.setStatus(indexUpdate.inputProductStatus(scanner));
                            break;
                        case 7:
                            isExit = false;
                            break;
                        default:
                            System.err.println("Invalid choice, please try again choice 1-7");
                    }

                }catch (NumberFormatException e) {
                    System.err.println("Invalid number, please try again");
                }
            }while (isExit);
        }else {
            System.err.println("Invalid product id, please try again");
        }
    }

    private static void searchProduct(Scanner scanner) {
        System.out.println("Enter the name of the product: ");
        String productName = scanner.nextLine().toLowerCase();
        boolean isExist = false;
        for (Product product : ProductService.products){
            if (product.getProductName().toLowerCase().contains(productName)){
                isExist = true;
                  product.outputProductData();
            }
        }
        if (!isExist){
            System.err.println("Product not found");
        }
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.println("Enter the id of the product you want to delete: ");
        String idDelete = scanner.nextLine();
        productService.delete(idDelete);
    }

    private static void sortByPrice() {
        Collections.sort(ProductService.products);
        for (Product product : ProductService.products){
            product.outputProductData();
        }

    }

    private static void showProduct() {
        if (productService.getAll().isEmpty()){
            System.err.println("Product is empty");
        }
        for (Product product : ProductService.products){
                product.outputProductData();
        }
    }

    private static void addProduct(Scanner scanner) {
        System.out.println("Enter the number of the product: ");
        try {
            int number = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < number; i++) {
                Product product = new Product();
                product.inputProductData(scanner);
                productService.save(product);
            }
        }catch (NumberFormatException e) {
            System.err.println("Invalid number, please try again");
        }
    }


    public static Product menuUser(Scanner scanner){
        boolean isExit = true;
        do {
            System.out.println("********************CATALOG-MANAGEMENT********************");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Thêm vào giỏ hàng");
            System.out.println("3. Xem tất cả sản phẩm giỏ hàng");
            System.out.println("4. Thay đổi số lượng sản phẩm trong giỏ hàng");
            System.out.println("5. Xóa 1 sản phẩm trong giỏ hàng");
            System.out.println("6. Xóa toàn bộ sản phẩm trong giỏ hàng");
            System.out.println("7. quay lai");
            System.out.println("your choice 1-5");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showAllProduct();
                    break;
                case 2:
                    addProductToCart(scanner);
                    break;
                case 3:
                    showProductInCart();
                    break;
                case 4:
                    updateCartItemQuantity(scanner);
                    break;
                case 5:
                    deleteProductInCart(scanner);
                    break;
                case 6:
                    allDeleteProductCart();
                    break;
                case 7:
                    isExit = false;
                    break;
                default:
                    System.err.println("Invalid choice, please try again choice 1-5");
            }
        }while (isExit);
        return null;
    }

    private static void showAllProduct() {
        System.out.println("Display all products being sold");
        for (Product product : ProductService.products){
            if (product.isStatus()){
                product.outputProductData();
            }
        }
    }
    private static void deleteProductInCart(Scanner scanner) {
        System.out.println("enter the cart id you want to delete: ");
        int indexDelete = Integer.parseInt(scanner.nextLine());
        cartItemService.delete(indexDelete);
    }


    private static void showProductInCart() {
        if (cartItemService.getAll().isEmpty()){
            System.err.println("Cart item is empty");
        }
        for (CartItem cartItem : cartItemService.getAll()){
            cartItem.displayCartItem();
        }

    }

    private static void addProductToCart(Scanner scanner) {
        CartItem cartItem = new CartItem();
        cartItem.inputCartItemData(scanner);
        cartItemService.save(cartItem);
    }

    private static void updateCartItemQuantity(Scanner scanner) {
        System.out.print("Enter CartItem ID to update quantity: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        CartItem cartItem = cartItemService.findById(id);
        if (cartItem != null) {
            System.out.print("Enter new quantity: ");
            int newQuantity = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            cartItemService.updateQuantity(id, newQuantity);
        } else {
            System.out.println("Cart item not found.");
        }
    }

    private static void allDeleteProductCart() {
        cartItemService.getAll().clear();
        System.out.println("Delete all cart successfully");
    }
}
