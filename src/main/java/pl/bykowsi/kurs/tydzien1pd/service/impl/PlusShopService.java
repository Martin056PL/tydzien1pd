package pl.bykowsi.kurs.tydzien1pd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSettings;
import pl.bykowsi.kurs.tydzien1pd.model.Product;
import pl.bykowsi.kurs.tydzien1pd.screeninfo.PrintInfo;
import pl.bykowsi.kurs.tydzien1pd.service.Basket;
import pl.bykowsi.kurs.tydzien1pd.service.ShopService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@Service
@Profile("plus")
public class PlusShopService implements ShopService {

    private final Basket basket;
    private final BigDecimal VAT;
    private BigDecimal sum;
    private final LanguageSettings languageSettings;
    private static final BigDecimal hundred = BigDecimal.valueOf(100);

    @Autowired
    public PlusShopService(@Value("${price.VAT}") Integer VAT, Basket basket, LanguageSettings languageSettings) {
        this.VAT = BigDecimal.valueOf(VAT);
        this.basket = basket;
        this.sum = BigDecimal.ZERO;
        this.languageSettings = languageSettings;
    }


    @Override
    public void calculateFinalPrice() {
        List<Product> list = basket.getBasket();

        list.forEach(p -> System.out.println(languageSettings.getMessageSource().getMessage("singleProductPosition", new Object[]{p.getName(), p.getPrice()}, Locale.forLanguageTag(languageSettings.getLanguageVersion()))));
        sum = list.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal grossSum = sum.multiply(hundred.add(VAT).divide(hundred));
        PrintInfo.PlusPrintData(languageSettings, sum, grossSum, VAT);
    }
}
