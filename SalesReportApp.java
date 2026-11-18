import java.util.Scanner;

public class SalesReportApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] products = {
            "Cozy Nights Monarch mattress",
            "Restonic Barbados mattress",
            "Sealy Phuket mattress"
        };

        int[][] salesData = new int[3][3];

        for (int i = 0; i < products.length; i++) {
            for (int month = 1; month <= 3; month++) {
                System.out.print("Enter number of sales for month " + month + " for " + products[i] + ": ");
                salesData[i][month - 1] = input.nextInt();
            }
            System.out.println();
        }

        Product product = new Product(products, salesData);
        PrintProduct printer = new PrintProduct(product);
        printer.printFullReport();

        input.close();
    }
}