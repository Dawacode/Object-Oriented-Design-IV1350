package integration;

import model.ReceiptDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExternalAccountingSystemTest {
    private ExternalAccountingSystem accountingSystem;
    private List<ReceiptDTO> fakeExternalAccountingSystem;

    @BeforeEach
    void setUp() {
        fakeExternalAccountingSystem = new ArrayList<>();
        accountingSystem = new ExternalAccountingSystem(fakeExternalAccountingSystem);
    }


    @Test
    void testUpdate() {
        // Create a sample receipt
        ReceiptDTO receipt = new ReceiptDTO(
                LocalTime.now(),
                100, // totalPrice
                20.0, // totalVAT
                120, // paid
                20, // change
                new ArrayList<>() // itemList
        );

        // Update the accounting system with the receipt
        accountingSystem.update(receipt);

        // Ensure that the receipt has been added to the accounting system
        assertTrue(fakeExternalAccountingSystem.contains(receipt));
    }
}