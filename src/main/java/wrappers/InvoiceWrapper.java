package wrappers;

/**
 * Created by Eric on 23-06-16.
 */
public class InvoiceWrapper {

    private String carIdentifier;

    private String licencePlate;

    private double totalAmount;

    private String invoiceDate;

    public InvoiceWrapper(String carIdentifier, String licencePlate, double totalAmount, String invoiceDate) {
        this.carIdentifier = carIdentifier;
        this.licencePlate = licencePlate;
        this.totalAmount = totalAmount;
        this.invoiceDate = invoiceDate;
    }

    public String getCarIdentifier() {
        return carIdentifier;
    }

    public void setCarIdentifier(String carIdentifier) {
        this.carIdentifier = carIdentifier;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
