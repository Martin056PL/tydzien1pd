package pl.bykowsi.kurs.tydzien1pd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSettings;
import pl.bykowsi.kurs.tydzien1pd.model.PriceCalculationsData;
import pl.bykowsi.kurs.tydzien1pd.model.Product;
import pl.bykowsi.kurs.tydzien1pd.screeninfo.PrintMessages;
import pl.bykowsi.kurs.tydzien1pd.service.Basket;
import pl.bykowsi.kurs.tydzien1pd.service.ShopService;

import java.math.BigDecimal;
import java.util.List;

@Service
@Profile("plus")
public class PlusShopService extends StartShopService implements ShopService {

    protected final BigDecimal VAT;
    protected static final BigDecimal hundred = BigDecimal.valueOf(100);

    @Autowired
    public PlusShopService(Basket basket, LanguageSettings languageSettings, PriceCalculationsData priceCalculationsData, @Value("${price.VAT}") Integer VAT) {
        super(basket, languageSettings, priceCalculationsData);
        this.VAT = BigDecimal.valueOf(VAT);
    }

    @Override
    public void calculateFinalPrice() {

        List<Product> generatedBasket = CreateBasket(basket);
        PrintMessages.printBasket(generatedBasket, languageSettings);
        BigDecimal sum = calculateBasket(generatedBasket);
        BigDecimal grossPrice = sum.multiply(hundred.add(VAT).divide(hundred));

        setCalculationData(sum, grossPrice);

        PrintMessages.PlusPrintData(languageSettings, priceCalculationsData);
    }

    private void setCalculationData(BigDecimal sum, BigDecimal grossPrice) {
        priceCalculationsData.setSum(sum);
        priceCalculationsData.setVAT(VAT);
        priceCalculationsData.setGrossPrice(grossPrice);
    }


}
