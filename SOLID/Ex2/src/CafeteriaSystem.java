import java.util.*;

public class CafeteriaSystem {

    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final PricingService pricing;
    private final InvoicePrinter printer = new InvoicePrinter();

    private int invoiceSeq = 1000;

    public CafeteriaSystem() {
        this.store = new FileInvoiceStore(new FileStore());
        this.pricing = new PricingService(
                new DefaultTaxPolicy(),
                new DefaultDiscountPolicy()
        );
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        PricingResult result =
                pricing.compute(customerType, lines, menu);

        double taxPct = new DefaultTaxPolicy().taxPercent(customerType);

        String invoiceText =
                printer.print(invId, lines, menu, result, taxPct);

        System.out.print(invoiceText);

        store.save(invId, invoiceText);
        System.out.println(
                "Saved invoice: " + invId +
                " (lines=" + store.countLines(invId) + ")"
        );
    }
}