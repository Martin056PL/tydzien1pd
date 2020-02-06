package pl.bykowsi.kurs.tydzien1pd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSetting;
import pl.bykowsi.kurs.tydzien1pd.generator.BasketGenerator;
import pl.bykowsi.kurs.tydzien1pd.model.Basket;
import pl.bykowsi.kurs.tydzien1pd.model.CalculationPriceData;
import pl.bykowsi.kurs.tydzien1pd.screenmessage.MessagePrinter;
import pl.bykowsi.kurs.tydzien1pd.service.ShopService;
import pl.bykowsi.kurs.tydzien1pd.utils.CalculatorUtil;

import java.math.BigDecimal;

import static pl.bykowsi.kurs.tydzien1pd.utils.CalculatorUtil.calculateBasket;

@Service
@Profile("plus")
public class PlusShopService extends StartShopService implements ShopService {

    protected final BigDecimal VAT;

    @Autowired
    public PlusShopService(BasketGenerator basketGenerator,
                           LanguageSetting languageSetting,
                           CalculationPriceData calculationPriceData,
                           @Value("${price.VAT}") Integer VAT) {
        super(basketGenerator, languageSetting, calculationPriceData);
        this.VAT = BigDecimal.valueOf(VAT);
    }

    @Override
    public void calculateFinalPrice() {
        Basket generatedBasket = createBasket(basketGenerator);
        MessagePrinter.printBasket(generatedBasket, languageSetting);
        calculateBasketValue(generatedBasket);
        MessagePrinter.plusPrintData(languageSetting, calculationPriceData);
    }

    private void calculateBasketValue(Basket basket) {
        BigDecimal sum = calculateBasket(basket);
        BigDecimal grossPrice = CalculatorUtil.calculateGrossPrice(sum, VAT);
        setCalculationData(sum, grossPrice);
    }

    private void setCalculationData(BigDecimal sum,
                                    BigDecimal grossPrice) {
        calculationPriceData.setSum(sum);
        calculationPriceData.setVAT(VAT);
        calculationPriceData.setGrossPrice(grossPrice);
    }


}
