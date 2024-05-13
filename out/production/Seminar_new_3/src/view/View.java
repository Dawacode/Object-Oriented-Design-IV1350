

package view;

import controller.Controller;
import model.ItemDTO;
import model.ReceiptDTO;
import model.SaleObserver;

import java.util.List;

/**
 * This is a placeholder for the view. It will only contain hardcoded calls to the controller.
 */
    public class View {

    private final Controller controller;

    private TotalRevenueView totalRevenueView;
    /**
     * Creates a new instance that uses the specified controller.
     *
     * @param controller The controller used for all systems operations.
     */
    public View(Controller controller) {
        this.controller = controller;
        sampleExecution();


    }

    private void sampleExecution() {
            System.out.println();

            System.out.println("-----------------------Start sale : ------------------------");

        controller.startSale();
        controller.addSaleObserver(new TotalRevenueView());

                 int quantity1 = 1;
                 ItemDTO scannedItem1 = controller.searchForItem(1, quantity1);
                 System.out.println("- Add " + quantity1 + " item with item id " + scannedItem1.getID());
                 System.out.println("ID : " + scannedItem1.getID());
                 System.out.println("Price : " + scannedItem1.getPrice() + " SEK");
                 System.out.println("VAT : " + scannedItem1.getVAT());
                 System.out.println("Quantity : " + scannedItem1.getQuantity());
                 int totalPrice1 = controller.getSaleDTO().getTotalPrice();
                 double totalVAT1 = controller.getSaleDTO().getTotalVAT();
                 System.out.println("Total cost ( incl VAT ):" + totalPrice1 + " SEK");
                 System.out.println("Total VAT :" + totalVAT1 + " SEK");
                 System.out.println();


                 int quantity2 = 1;
                 ItemDTO scannedItem2 = controller.searchForItem(1, quantity2);
                 System.out.println("- Add " + quantity2 + " item with item id " + scannedItem2.getID());
                 System.out.println("ID : " + scannedItem2.getID());
                 System.out.println("Price : " + scannedItem2.getPrice() + " SEK");
                 System.out.println("VAT : " + scannedItem2.getVAT());
                 System.out.println("Quantity : " + scannedItem2.getQuantity());
                 int totalPrice2 = controller.getSaleDTO().getTotalPrice();
                 double totalVAT2 = controller.getSaleDTO().getTotalVAT();
                 System.out.println("Total cost ( incl VAT ):" + totalPrice2 + " SEK");
                 System.out.println("Total VAT :" + totalVAT2 + " SEK");

                 System.out.println();

                 int quantity3 = 2;
                 ItemDTO scannedItem3 = controller.searchForItem(3, quantity3);
                 System.out.println("- Add " + quantity3 + " item with item id " + scannedItem3.getID());
                 System.out.println("ID : " + scannedItem3.getID());
                 System.out.println("Price : " + scannedItem3.getPrice() + " SEK");
                 System.out.println("VAT : " + scannedItem3.getVAT());
                 System.out.println("Quantity : " + scannedItem3.getQuantity());
                 int totalPrice3 = controller.getSaleDTO().getTotalPrice();
                 double totalVAT3 = controller.getSaleDTO().getTotalVAT();
                 System.out.println("Total cost ( incl VAT ):" + totalPrice3 + " SEK");
                 System.out.println("Total VAT :" + totalVAT3 + " SEK");

                 System.out.println();

                 int quantity4 = 2;
                 ItemDTO scannedItem4 = controller.searchForItem(4, quantity4);
                 System.out.println("- Add " + quantity4 + " item with item id " + scannedItem4.getID());
                 System.out.println("ID : " + scannedItem4.getID());
                 System.out.println("Price : " + scannedItem4.getPrice() + " SEK");
                 System.out.println("VAT : " + scannedItem4.getVAT());
                 System.out.println("Quantity : " + scannedItem4.getQuantity());
                 int totalPrice4 = controller.getSaleDTO().getTotalPrice();
                 double totalVAT4 = controller.getSaleDTO().getTotalVAT();
                 System.out.println("Total cost ( incl VAT ):" + totalPrice4 + " SEK");
                 System.out.println("Total VAT :" + totalVAT4 + " SEK");

                 System.out.println();



            System.out.println("-----------------------End sale : ------------------------");
            controller.endSale();
            int paymentAmount = 200;
            System.out.println("Customer pays " + paymentAmount +" SEK");
            int finalPrice = controller.getSaleDTO().getTotalPrice();
            System.out.println("Total cost ( incl VAT ): " + finalPrice +" SEK");

            System.out.println();

            System.out.println("-----------------------Begin receipt : ------------------------");
            ReceiptDTO receipt=controller.pay(paymentAmount);
            System.out.println("Time of Sale : " + receipt.getSaleTime());

            itemIterator(receipt.getItemList());


            System.out.println("Total : " + receipt.getTotalPrice() +" SEK");

            System.out.println("VAT : " + receipt.getTotalVAT() +" SEK");
            System.out.println();
            System.out.println("Cash :                        " + receipt.getPaid() +" SEK");

            System.out.println("Change :                      " + receipt.getChange() +" SEK");
            System.out.println();


            System.out.println("-----------------------End receipt : ------------------------");
            System.out.println();
            System.out.println("Change to give to the customer : " + receipt.getChange() +" SEK");


    }

    private void itemIterator(List<ItemDTO> list){
        for(ItemDTO item : list){
            System.out.println("Item ID : " + item.getID() + "       " + item.getQuantity() + " x " + item.getPrice() +" SEK    "+ item.getPrice()* item.getQuantity() +" SEK");
        }
    }

}
