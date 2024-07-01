package ra.model;

import ra.service.CatalogService;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Catalog {
    private int catalogId;
    private String catalogName;
    private String catalogDescription;

    public Catalog() {
    }

    public Catalog(int catalogId, String catalogName, String catalogDescription) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.catalogDescription = catalogDescription;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogDescription() {
        return catalogDescription;
    }

    public void setCatalogDescription(String catalogDescription) {
        this.catalogDescription = catalogDescription;
    }

    public void inputCatalogData(Scanner scanner) {
        this.catalogId = inputCatalogId();
        this.catalogName = inputCatalogName(scanner);
        this.catalogDescription = inputCatalogDescription(scanner);
    }
    public int inputCatalogId() {
        if (CatalogService.catalogs.isEmpty()){
            return 1;
        }else {
            int indexMax = CatalogService.catalogs.get(0).getCatalogId();
            for (int i = 0; i < CatalogService.catalogs.size(); i++) {
                if (CatalogService.catalogs.get(i).getCatalogId()>indexMax) {
                    indexMax = CatalogService.catalogs.get(i).getCatalogId();
                }
            }
            return indexMax + 1;
        }
    }
    public String inputCatalogName(Scanner scanner) {
        System.out.println("Enter Catalog Name");
        do {
           String catalogName = scanner.nextLine();
           if (catalogName.isEmpty()) {
               System.out.println("Catalog name cannot be empty");
           }else {
               boolean isExist = false;
               for (Catalog catalog : CatalogService.catalogs) {
                   if (catalog.getCatalogName().equals(catalogName)) {
                        isExist = true;
                        break;
                   }
               }
               if (!isExist) {
                  return this.catalogName = catalogName;
               }else {
                   System.err.println("Catalog name already exists, please try again");
               }
           }
        }while (true);
    }
    public String inputCatalogDescription(Scanner scanner) {
        System.out.println("Enter Catalog Description");
        do {
                String catalogDescription = scanner.nextLine();
                if (catalogDescription.isEmpty()) {
                    System.out.println("Catalog description cannot be empty");
                }else {
                    return catalogDescription;
                }

        }while (true);
    }
    public void outputCatalogData() {
        System.out.printf("%-20s %-20s %-20s\n",
                "Catalog ID", "Catalog Name", "Catalog Description");
        System.out.printf("%-20s %-20s %-20s\n",
                this.catalogId, this.catalogName, this.catalogDescription);

    }
}

