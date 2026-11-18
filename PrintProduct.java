public class PrintProduct {
    private IProduct productData;

    public PrintProduct(IProduct productData) {
        this.productData = productData;
    }

    public void printTotalSales() {
        System.out.println("TOTAL PRODUCT SALES: " + productData.GetTotalProductsSold());
    }

    public void printAverageSales() {
        System.out.printf("AVERAGE PRODUCT SALES: %.0f\n", productData.GetAverageProductSold());
    }

    public void printMostPopularProduct() {
        System.out.println("MOST POPULAR PRODUCT: " + productData.GetMostPopularProduct());
    }

    public void printFullReport() {
        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println("******************************************");
        printTotalSales();
        printAverageSales();
        printMostPopularProduct();
    }
}