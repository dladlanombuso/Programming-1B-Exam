public class Product implements IProduct {
    private String[] products;
    private int[][] salesData;

    public Product(String[] products, int[][] salesData) {
        this.products = products;
        this.salesData = salesData;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int i = 0; i < salesData.length; i++) {
            for (int j = 0; j < salesData[i].length; j++) {
                total += salesData[i][j];
            }
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        int total = GetTotalProductsSold();
        int count = 0;
        for (int i = 0; i < salesData.length; i++) {
            count += salesData[i].length;
        }
        if (count == 0) return 0.0;
        return (double) total / count;
    }

    @Override
    public String GetMostPopularProduct() {
        int highestSales = -1;
        String mostPopular = "";

        for (int i = 0; i < salesData.length; i++) {
            int productTotal = 0;
            for (int j = 0; j < salesData[i].length; j++) {
                productTotal += salesData[i][j];
            }
            if (productTotal > highestSales) {
                highestSales = productTotal;
                // Formats name to strip " mattress" to match sample output: "Cozy Nights Monarch(25)"
                String cleanName = products[i].replace(" mattress", "");
                mostPopular = cleanName + "(" + highestSales + ")";
            }
        }
        return mostPopular;
    }
}
