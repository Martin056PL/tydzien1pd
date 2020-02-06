package pl.bykowsi.kurs.tydzien1pd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSetting;
import pl.bykowsi.kurs.tydzien1pd.generator.BasketGenerator;
import pl.bykowsi.kurs.tydzien1pd.model.Basket;
import pl.bykowsi.kurs.tydzien1pd.model.CalculationPriceData;
import pl.bykowsi.kurs.tydzien1pd.screenmessage.MessagePrinter;
import pl.bykowsi.kurs.tydzien1pd.service.ShopService;

import java.math.BigDecimal;

import static pl.bykowsi.kurs.tydzien1pd.utils.CalculatorUtil.calculateBasket;

@Service
@Profile("start")
public class StartShopService implements ShopService {

    protected final BasketGenerator basketGenerator;
    protected final LanguageSetting languageSetting;
    protected CalculationPriceData calculationPriceData;


    @Autowired
    public StartShopService(BasketGenerator basketGenerator,
                            LanguageSetting languageSetting,
                            CalculationPriceData calculationPriceData) {
        this.basketGenerator = basketGenerator;
        this.languageSetting = languageSetting;
        this.calculationPriceData = calculationPriceData;
    }

    @Override
    public void calculateFinalPrice() {
        Basket generatedBasket = createBasket(basketGenerator);
        MessagePrinter.printBasket(generatedBasket, languageSetting);
        calculateBasketValue(generatedBasket);
        MessagePrinter.startPrintData(languageSetting, calculationPriceData);
    }

    private void calculateBasketValue(Basket basket) {
        BigDecimal sum = calculateBasket(basket);
        setCalculationData(sum);
    }

    private void setCalculationData(BigDecimal sum) {
        calculationPriceData.setSum(sum);
    }
}
