package pl.bykowsi.kurs.tydzien1pd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSettings;
import pl.bykowsi.kurs.tydzien1pd.screeninfo.PrintInfo;
import pl.bykowsi.kurs.tydzien1pd.service.Basket;
import pl.bykowsi.kurs.tydzien1pd.service.ShopService;

import java.math.BigDecimal;

@Service
@Profile("start")
public class StartShopService implements ShopService {

    protected final Basket basket;
    protected final LanguageSettings languageSettings;


    @Autowired
    public StartShopService(Basket basket, LanguageSettings languageSettings) {
        this.basket = basket;
        this.languageSettings = languageSettings;
    }

    @Override
    public void calculateFinalPrice() {
        BigDecimal sum = calculateBasket(basket, languageSettings);
        PrintInfo.StartPrintData(languageSettings, sum);
    }
}
