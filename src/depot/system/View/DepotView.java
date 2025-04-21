/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package depot.system.View;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author dania
 */
public class DepotView extends JFrame {
    private JTextArea customerArea;
    private JList<String> parcelList; // Change to JList for selection
    private JTextArea processingArea;
    private JButton processButton;
    private JButton addCustomerButton;
    private JButton addParcelButton;
    private JButton clearProcessedButton;
    private JButton generateReportButton;
    private JButton calculateFeeButton;
    private DefaultListModel<String> parcelListModel; // Model for JList

    public DepotView() {
        setTitle("Depot Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setPreferredSize(new Dimension(1000, 80));
        JLabel headerLabel = new JLabel("Depot Management System");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 26));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Main Content Panel
        JPanel contentPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Customer Queue Panel
        JPanel customerPanel = new JPanel(new BorderLayout());
        customerPanel.setBorder(BorderFactory.createTitledBorder("Customer Queue"));
        customerPanel.setBackground(new Color(245, 245, 245));
        customerArea = new JTextArea(20, 20);
        customerArea.setEditable(false);
        JScrollPane customerScrollPane = new JScrollPane(customerArea);
        customerPanel.add(customerScrollPane, BorderLayout.CENTER);

        // Parcel Processing Panel
        JPanel parcelPanel = new JPanel(new BorderLayout());
        parcelPanel.setBorder(BorderFactory.createTitledBorder("Parcel Processing"));
        parcelPanel.setBackground(new Color(245, 245, 245));
        parcelListModel = new DefaultListModel<>();
        parcelList = new JList<>(parcelListModel); // Use JList with DefaultListModel
        JScrollPane parcelScrollPane = new JScrollPane(parcelList);
        parcelPanel.add(parcelScrollPane, BorderLayout.CENTER);

        // Current Processing Details Panel
        JPanel processingPanel = new JPanel(new BorderLayout());
        processingPanel.setBorder(BorderFactory.createTitledBorder("Currently Processing"));
        processingPanel.setBackground(new Color(245, 245, 245));
        processingArea = new JTextArea(20, 20);
        processingArea.setEditable(false);
        JScrollPane processingScrollPane = new JScrollPane(processingArea);
        processingPanel.add(processingScrollPane, BorderLayout.CENTER);

        contentPanel.add(customerPanel);
        contentPanel.add(parcelPanel);
        contentPanel.add(processingPanel);

        // Footer Panel with Buttons
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footerPanel.setBackground(new Color(70, 130, 180));

        processButton = new JButton("Process Parcel");
        processButton.setFont(new Font("Arial", Font.BOLD, 16));
        processButton.setBackground(new Color(50, 205, 50));
        processButton.setForeground(Color.WHITE);

        addCustomerButton = new JButton("Add Customer");
        addCustomerButton.setFont(new Font("Arial", Font.BOLD, 16));
        addCustomerButton.setBackground(new Color(30, 144, 255));
        addCustomerButton.setForeground(Color.WHITE);

        addParcelButton = new JButton("Add Parcel");
        addParcelButton.setFont(new Font("Arial", Font.BOLD, 16));
        addParcelButton.setBackground(new Color(255, 165, 0));
        addParcelButton.setForeground(Color.WHITE);

        clearProcessedButton = new JButton("Clear Processed");
        clearProcessedButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearProcessedButton.setBackground(new Color(255, 99, 71));
        clearProcessedButton.setForeground(Color.WHITE);

        generateReportButton = new JButton("Generate Report");
        generateReportButton.setFont(new Font("Arial", Font.BOLD, 16));
        generateReportButton.setBackground(new Color(128, 0, 128));
        generateReportButton.setForeground(Color.WHITE);

        calculateFeeButton = new JButton("Calculate Fee");
        calculateFeeButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateFeeButton.setBackground(new Color(255, 140, 0));
        calculateFeeButton.setForeground(Color.WHITE);

        footerPanel.add(processButton);
        footerPanel.add(addCustomerButton);
        footerPanel.add(addParcelButton);
        footerPanel.add(clearProcessedButton);
        footerPanel.add(generateReportButton);
        footerPanel.add(calculateFeeButton);

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JTextArea getCustomerArea() {
        return customerArea;
    }

 public DefaultListModel<String> getParcelListModel() {
    return parcelListModel; // Return the list model for updating the JList
}

public JList<String> getParcelList() {
    return parcelList; // Return the JList for selection purposes
}

    public JTextArea getProcessingArea() {
        return processingArea;
    }

    public JButton getProcessButton() {
        return processButton;
    }

    public JButton getAddCustomerButton() {
        return addCustomerButton;
    }

    public JButton getAddParcelButton() {
        return addParcelButton;
    }

    public JButton getClearProcessedButton() {
        return clearProcessedButton;
    }

    public JButton getGenerateReportButton() {
        return generateReportButton;
    }

    public JButton getCalculateFeeButton() {
        return calculateFeeButton;
    }

    public Object getParcelArea() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  


}