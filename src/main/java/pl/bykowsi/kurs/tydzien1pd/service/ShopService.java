package pl.bykowsi.kurs.tydzien1pd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSettings;
import pl.bykowsi.kurs.tydzien1pd.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

public interface ShopService {

    Logger logger = LoggerFactory.getLogger(ShopService.class);

    void calculateFinalPrice();

    default BigDecimal calculateBasket(Basket basket, LanguageSettings languageSettings) {
        List<Product> list = basket.createAndGetRandomBasket();
        list.forEach(p -> logger.warn(languageSettings.getMessageSource().getMessage("singleProductPosition", new Object[]{p.getName(), p.getPrice()}, Locale.forLanguageTag(languageSettings.getLanguageVersion()))));
        return list.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
