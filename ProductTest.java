import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    private final String[] testProducts = {
        "Cozy Nights Monarch mattress",
        "Restonic Barbados mattress",
        "Sealy Phuket mattress"
    };

    private final int[][] testSalesData = {
        {25, 18, 20}, // Total = 63
        {15, 10, 12}, // Total = 37
        {18, 15, 20}  // Total = 53
    };

    @Test
    public void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
        Product product = new Product(testProducts, testSalesData);
        int expectedTotal = 153;
        int actualTotal = product.GetTotalProductsSold();
        
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        Product product = new Product(testProducts, testSalesData);
        double expectedAverage = 17.0;
        double actualAverage = product.GetAverageProductSold();
        
        assertEquals(expectedAverage, actualAverage, 0.001);
    }
}