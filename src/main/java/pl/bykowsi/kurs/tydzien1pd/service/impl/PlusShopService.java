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
@Profile("plus")
public class PlusShopService extends StartShopService implements ShopService {

    protected final BigDecimal VAT;
    private static final BigDecimal hundred = BigDecimal.valueOf(100);

    @Autowired
    public PlusShopService(Basket basket, LanguageSettings languageSettings, @Value("${price.VAT}") Integer VAT) {
        super(basket, languageSettings);
        this.VAT = BigDecimal.valueOf(VAT);
    }

    @Override
    public void calculateFinalPrice() {
        BigDecimal sum = calculateBasket(basket, languageSettings);
        BigDecimal grossSum = sum.multiply(hundred.add(VAT).divide(hundred));
        PrintInfo.PlusPrintData(languageSettings, sum, grossSum, VAT);
    }
}
