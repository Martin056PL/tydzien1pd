package pl.bykowsi.kurs.tydzien1pd.utils;

import pl.bykowsi.kurs.tydzien1pd.model.Basket;
import pl.bykowsi.kurs.tydzien1pd.model.Product;

import java.math.BigDecimal;

public class CalculatorUtil {

    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    public static BigDecimal calculateBasket(Basket basket) {
        return basket.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal calculateGrossPrice(BigDecimal sum, BigDecimal VAT) {
        return sum.multiply(HUNDRED.add(VAT).divide(HUNDRED));
    }

    public static BigDecimal calculateDiscountedGrossPrice(BigDecimal grossPrice, BigDecimal discount) {
        return grossPrice.multiply(HUNDRED.subtract(discount)).divide(HUNDRED);
    }

    public static BigDecimal calculateDiscountRatio(BigDecimal discount) {
        return HUNDRED.subtract(discount);
    }


}
