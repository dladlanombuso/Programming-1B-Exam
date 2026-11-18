import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductApp extends JFrame {

    private JTextField txtCustomerName;
    private JComboBox<String> cmbSelectProduct;
    private JTextField txtPrice;
    private JButton btnProcess;

    public ProductApp() {
        setTitle("Product App");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- Q.2.4 Menu System ---
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Tools Menu
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");

        processItem.addActionListener(e -> processSalesInformation());
        clearItem.addActionListener(e -> clearForm());

        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // --- Q.2.2 Form Components ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Customer Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Customer Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtCustomerName = new JTextField(20);
        formPanel.add(txtCustomerName, gbc);

        // Select Product
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Select Product:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        String[] mattresses = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        cmbSelectProduct = new JComboBox<>(mattresses);
        formPanel.add(cmbSelectProduct, gbc);

        // Enter Price
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Enter Price:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtPrice = new JTextField(20);
        formPanel.add(txtPrice, gbc);

        // Process Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        btnProcess = new JButton("PROCESS");
        btnProcess.addActionListener(e -> processSalesInformation());
        formPanel.add(btnProcess, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    // --- Q.2.3 Process Logic & File Saving ---
    private void processSalesInformation() {
        String customerName = txtCustomerName.getText().trim();
        String productName = (String) cmbSelectProduct.getSelectedItem();
        String priceText = txtPrice.getText().trim();

        if (customerName.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both the customer name and price.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(priceText);

            // Use ProductCalculations class
            ProductCalculations calc = new ProductCalculations(price);
            double vat = calc.GetVat();
            double commission = calc.GetCommission();
            double total = calc.GetTotal();

            // Format matching the screenshot in Q.2.2
            String messageOutput = "Customer Name: " + customerName + "\n" +
                                   "Product: " + productName + "\n" +
                                   "VAT: R " + vat + "\n" +
                                   "COMMISSION: R " + commission + "\n" +
                                   "TOTAL: R " + total;

            JOptionPane.showMessageDialog(this, messageOutput, "Message", JOptionPane.INFORMATION_MESSAGE);

            // Save to data.txt matching the screenshot in Q.2.3
            saveToFile(messageOutput);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveToFile(String details) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt", true))) {
            writer.println("DATA LOG");
            writer.println("****************************");
            writer.println(details);
            writer.println("****************************");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving data to file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- Q.2.4 Clear Logic ---
    private void clearForm() {
        txtCustomerName.setText("");
        txtPrice.setText("");
        cmbSelectProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}