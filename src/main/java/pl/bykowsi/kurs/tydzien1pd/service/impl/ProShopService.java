package pl.bykowsi.kurs.tydzien1pd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSettings;
import pl.bykowsi.kurs.tydzien1pd.screeninfo.PrintInfo;
import pl.bykowsi.kurs.tydzien1pd.service.Basket;
import pl.bykowsi.kurs.tydzien1pd.service.ShopService;

import java.math.BigDecimal;

@Service
@Profile("pro")
public class ProShopService implements ShopService {

    private final Basket basket;
    private final BigDecimal VAT;
    private final BigDecimal discount;
    private BigDecimal sum;
    private final LanguageSettings languageSettings;
    private static final BigDecimal hundred = BigDecimal.valueOf(100);

    @Autowired
    public ProShopService(@Value("${price.VAT}") Integer VAT, @Value("${price.discount}") Integer discount, Basket basket, LanguageSettings languageSettings) {
        this.VAT = BigDecimal.valueOf(VAT);
        this.discount = BigDecimal.valueOf(discount);
        this.basket = basket;
        this.sum = BigDecimal.ZERO;
        this.languageSettings = languageSettings;
    }

    @Override
    public void calculateFinalPrice() {
        sum = calculateBasket(basket,languageSettings);
        BigDecimal grossPrice = sum.multiply(hundred.add(VAT)).divide(hundred);
        BigDecimal discountedGrossPrice = grossPrice.multiply(hundred.subtract(discount)).divide(hundred);
        PrintInfo.ProPrintData(languageSettings, sum,  grossPrice, VAT, discountedGrossPrice, 100 - discount.intValue());
    }

}
