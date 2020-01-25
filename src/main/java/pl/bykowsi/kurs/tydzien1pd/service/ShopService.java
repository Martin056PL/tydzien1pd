package pl.bykowsi.kurs.tydzien1pd.service;

import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSettings;
import pl.bykowsi.kurs.tydzien1pd.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

public interface ShopService {

    void calculateFinalPrice();

    default BigDecimal calculateBasket(Basket basket, LanguageSettings languageSettings) {
        List<Product> list = basket.createAndGetRandomBasket();
        list.forEach(p -> System.out.println(languageSettings.getMessageSource().getMessage("singleProductPosition", new Object[]{p.getName(), p.getPrice()}, Locale.forLanguageTag(languageSettings.getLanguageVersion()))));
        return list.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
