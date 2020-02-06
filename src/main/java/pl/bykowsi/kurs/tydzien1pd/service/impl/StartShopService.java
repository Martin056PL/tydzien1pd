package pl.bykowsi.kurs.tydzien1pd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSettings;
import pl.bykowsi.kurs.tydzien1pd.model.PriceCalculationsData;
import pl.bykowsi.kurs.tydzien1pd.model.Product;
import pl.bykowsi.kurs.tydzien1pd.screeninfo.MessagesPrinter;
import pl.bykowsi.kurs.tydzien1pd.service.Basket;
import pl.bykowsi.kurs.tydzien1pd.service.ShopService;

import java.math.BigDecimal;
import java.util.List;

@Service
@Profile("start")
public class StartShopService implements ShopService {

    protected final Basket basket;
    protected final LanguageSettings languageSettings;
    protected PriceCalculationsData priceCalculationsData;


    @Autowired
    public StartShopService(Basket basket, LanguageSettings languageSettings, PriceCalculationsData priceCalculationsData) {
        this.basket = basket;
        this.languageSettings = languageSettings;
        this.priceCalculationsData = priceCalculationsData;
    }

    @Override
    public void calculateFinalPrice() {
        List<Product> generatedBasket = CreateBasket(basket);
        MessagesPrinter.printBasket(generatedBasket, languageSettings);
        BigDecimal sum = calculateBasket(generatedBasket);
        setCalculationData(sum);
        MessagesPrinter.startPrintData(languageSettings, priceCalculationsData);
    }

    private void setCalculationData(BigDecimal sum){
        priceCalculationsData.setSum(sum);
    }
}
