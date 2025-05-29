import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String RECEIPTS_FOLDER = "receipts/";
    private static final String LINE_SEPARATOR = "=".repeat(40);
    private static final String SUB_SEPARATOR = "-".repeat(30);
    private static final String DELI_NAME = "Simply Deli-sh";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeliMenu deliMenu = new DeliMenu();
        Order currentOrder = new Order();
        ReceiptService receiptService = new ReceiptService(RECEIPTS_FOLDER);

        boolean inHomeMenu = true;
        while (inHomeMenu) {
            System.out.println("\n" + LINE_SEPARATOR);
            System.out