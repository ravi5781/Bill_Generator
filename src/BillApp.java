import java.util.Scanner;

public class BillApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompt for customer's name
        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();

        // Create a new bill for the customer
        Bill bill = new Bill(customerName);

        // Prompt for number of items
        System.out.print("Enter number of items: ");
        int itemCount;

        // Validate input for number of items
        while (true) {
            if (sc.hasNextInt()) {
                itemCount = sc.nextInt();
                sc.nextLine(); // Clear the buffer
                break;
            } else {
                System.out.print("Please enter a valid number for items: ");
                sc.next(); // Discard invalid input
            }
        }

        // Loop to take details for each item
        for (int i = 0; i < itemCount; i++) {
            System.out.println();
            System.out.printf("Enter details for item %d:\n", i + 1);

            // Take item name
            System.out.print("Item name: ");
            String itemName = sc.nextLine();

            // Take quantity and validate input
            System.out.print("Quantity: ");
            int quantity;
            while (true) {
                if (sc.hasNextInt()) {
                    quantity = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                    break;
                } else {
                    System.out.print("Please enter a valid quantity: ");
                    sc.next(); // Discard invalid input
                }
            }

            // Take price and validate input
            System.out.print("Price per item: ");
            double price;
            while (true) {
                if (sc.hasNextDouble()) {
                    price = sc.nextDouble();
                    sc.nextLine(); // Clear the buffer
                    break;
                } else {
                    System.out.print("Please enter a valid price: ");
                    sc.next(); // Discard invalid input
                }
            }

            // Create Item object and add to the bill
            Item item = new Item(itemName, quantity, price);
            bill.addItem(item);
        }
        System.out.println();
        // Generate and write the bill to a text file
        bill.generateBill();

        // Close the scanner
        sc.close();
    }
}
