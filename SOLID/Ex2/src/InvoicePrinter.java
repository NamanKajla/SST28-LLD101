import java.util.List;
import java.util.Map;

public class InvoicePrinter {

    public String print(
            String invoiceId,
            List<OrderLine> lines,
            Map<String, MenuItem> menu,
            PricingResult result,
            double taxPct
    ) {
        StringBuilder out = new StringBuilder();

        out.append("Invoice# ").append(invoiceId).append("\n");

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            out.append(String.format("- %s x%d = %.2f\n",
                    item.name, l.qty, lineTotal));
        }

        out.append(String.format("Subtotal: %.2f\n", result.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, result.tax));
        out.append(String.format("Discount: -%.2f\n", result.discount));
        out.append(String.format("TOTAL: %.2f\n", result.total));

        return out.toString();
    }
}