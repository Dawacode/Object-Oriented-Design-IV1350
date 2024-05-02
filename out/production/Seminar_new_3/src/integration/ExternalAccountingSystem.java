package integration;

import model.ReceiptDTO;

import java.util.List;
/*
 * Represents an external accounting system that keeps track of receipts.
 */
public class ExternalAccountingSystem {

    // List to simulate the external accounting system's storage of receipts
    private final List<ReceiptDTO> fakeExternalAccountingSystem;

    /*
     * Constructs a new ExternalAccountingSystem object with the provided list of receipts.
     * @param fakeExternalAccountingSystem The list to store simulated receipts.
     */
    public ExternalAccountingSystem(List<ReceiptDTO> fakeExternalAccountingSystem) {
        this.fakeExternalAccountingSystem = fakeExternalAccountingSystem;
    }

    /*
     * Updates the external accounting system by adding a new receipt.
     * @param receiptInfo The receipt information to be added.
     */
    public void update(ReceiptDTO receiptInfo) {
        fakeExternalAccountingSystem.add(receiptInfo);
    }
}
