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
public class ProShopService extends PlusShopService implements ShopService {

    protected final BigDecimal discount;

    @Autowired
    public ProShopService(Basket basket, LanguageSettings languageSettings, @Value("${price.VAT}") Integer VAT, @Value("${price.discount}") Integer discount) {
        super(basket, languageSettings, VAT);
        this.discount = BigDecimal.valueOf(discount);
    }

    @Override
    public void calculateFinalPrice() {
        BigDecimal sum = calculateBasket(basket, languageSettings);
        BigDecimal grossPrice = sum.multiply(hundred.add(VAT)).divide(hundred);
        BigDecimal discountedGrossPrice = grossPrice.multiply(hundred.subtract(discount)).divide(hundred);
        PrintInfo.ProPrintData(languageSettings, sum, grossPrice, VAT, discountedGrossPrice, 100 - discount.intValue());
    }

}
