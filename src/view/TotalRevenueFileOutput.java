package view;
import java.io.FileWriter;
import java.io.IOException;

public class TotalRevenueFileOutput extends TotalRevenueView {
    private static final String FILE_PATH = "total_revenue.txt";
    private int totalRevenue = 0;

    @Override
    public void updateTotalRevenue(int totalRevenue) {
        this.totalRevenue += totalRevenue;
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write("Total revenue: " + this.totalRevenue + " SEK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
