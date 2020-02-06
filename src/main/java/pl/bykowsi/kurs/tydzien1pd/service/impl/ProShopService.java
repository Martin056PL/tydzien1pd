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

import java.math.BigDecimal;

import static pl.bykowsi.kurs.tydzien1pd.utils.CalculatorUtil.*;

@Service
@Profile("pro")
public class ProShopService extends PlusShopService implements ShopService {

    protected final BigDecimal discount;

    @Autowired
    public ProShopService(BasketGenerator basketGenerator,
                          LanguageSetting languageSetting,
                          CalculationPriceData calculationPriceData,
                          @Value("${price.VAT}") Integer VAT,
                          @Value("${price.discount}") Integer discount) {
        super(basketGenerator, languageSetting, calculationPriceData, VAT);
        this.discount = BigDecimal.valueOf(discount);
    }

    @Override
    public void calculateFinalPrice() {
        Basket generatedBasket = createBasket(basketGenerator);
        MessagePrinter.printBasket(generatedBasket, languageSetting);
        calculateBasketValue(generatedBasket);
        MessagePrinter.proPrintData(languageSetting, calculationPriceData);
    }

    private void calculateBasketValue(Basket basket) {
        BigDecimal sum = calculateBasket(basket);
        BigDecimal grossPrice = calculateGrossPrice(sum, VAT);
        BigDecimal discountedGrossPrice = calculateDiscountedGrossPrice(grossPrice, discount);
        BigDecimal discountRatio = calculateDiscountRatio(discount);
        setCalculationData(discountRatio, sum, grossPrice, discountedGrossPrice);
    }


    private void setCalculationData(BigDecimal discountRatio,
                                    BigDecimal sum,
                                    BigDecimal grossPrice,
                                    BigDecimal discountedGrossPrice) {
        calculationPriceData.setSum(sum);
        calculationPriceData.setGrossPrice(grossPrice);
        calculationPriceData.setDiscountedGrossPrice(discountedGrossPrice);
        calculationPriceData.setVAT(VAT);
        calculationPriceData.setDiscountRatio(discountRatio);
    }

}
