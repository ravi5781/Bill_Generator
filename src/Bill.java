import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Model Class
public class Bill {
    private String customerName;
    private List<Item> itemList;
    private LocalDateTime billingDate;
    private final double TAX_RATE = 0.10; // 10%

    public Bill(String customerName) {
        this.customerName = customerName;
        this.itemList = new ArrayList<>();
        this.billingDate = LocalDateTime.now();
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public double calculateSubtotal() {
        double subtotal = 0;
        for (Item item : itemList) {
            subtotal += item.getTotal();
        }
        return subtotal;
    }

    public double calculateTax(double subtotal) {
        return subtotal * TAX_RATE;
    }

    public double calculateTotal(double subtotal, double tax) {
        return subtotal + tax;
    }

    public void generateBill() {
        double subtotal = calculateSubtotal();
        double tax = calculateTax(subtotal);
        double total = calculateTotal(subtotal, tax);

        try (FileWriter writer = new FileWriter("bill.txt")) {

            writer.write("============== BILL RECEIPT ==============\n");
            writer.write(String.format("Customer Name: %s\n", customerName));
            writer.write(String.format("Billing Date : %s\n", billingDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"))));
            writer.write("------------------------------------------\n");
            writer.write(String.format("%-15s %5s %10s %10s\n", "Item", "Qty", "Price", "Total"));
            writer.write("------------------------------------------\n");

            for (Item item : itemList) {
                writer.write(String.format("%-15s %5d %10.2f %10.2f\n",
                        item.getName(), item.getQuantity(), item.getPrice(), item.getTotal()));
            }

            writer.write("------------------------------------------\n");
            writer.write(String.format("%-30s %10.2f\n", "Subtotal:", subtotal));
            writer.write(String.format("%-30s %10.2f\n", "Tax (10%):", tax));
            writer.write(String.format("%-30s %10.2f\n", "Total:", total));
            writer.write("==========================================\n");

            System.out.println("Bill generated successfully in bill.txt!");

        } catch (IOException e) {
            System.out.println("Error while writing bill: " + e.getMessage());
        }
    }
}
