package pl.bykowsi.kurs.tydzien1pd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSettings;
import pl.bykowsi.kurs.tydzien1pd.model.PriceCalculationsData;
import pl.bykowsi.kurs.tydzien1pd.screeninfo.PrintMessages;
import pl.bykowsi.kurs.tydzien1pd.service.Basket;
import pl.bykowsi.kurs.tydzien1pd.service.ShopService;

import java.math.BigDecimal;

@Service
@Profile("pro")
public class ProShopService extends PlusShopService implements ShopService {

    protected final BigDecimal discount;

    @Autowired
    public ProShopService(Basket basket, LanguageSettings languageSettings, PriceCalculationsData priceCalculationsData, @Value("${price.VAT}") Integer VAT, @Value("${price.discount}") Integer discount) {
        super(basket, languageSettings, priceCalculationsData, VAT);
        this.discount = BigDecimal.valueOf(discount);
    }

    @Override
    public void calculateFinalPrice() {
        BigDecimal sum = calculateBasket(basket, languageSettings);
        BigDecimal grossPrice = sum.multiply(hundred.add(VAT)).divide(hundred);
        BigDecimal discountedGrossPrice = grossPrice.multiply(hundred.subtract(discount)).divide(hundred);
        BigDecimal discountRatio = hundred.subtract(discount);

        setCalculationData(discountRatio, sum, grossPrice, discountedGrossPrice);

        PrintMessages.ProPrintData(languageSettings, priceCalculationsData);
    }

    private void setCalculationData(BigDecimal discountRatio, BigDecimal sum, BigDecimal grossPrice, BigDecimal discountedGrossPrice) {
        priceCalculationsData.setSum(sum);
        priceCalculationsData.setGrossPrice(grossPrice);
        priceCalculationsData.setDiscountedGrossPrice(discountedGrossPrice);
        priceCalculationsData.setVAT(VAT);
        priceCalculationsData.setDiscountRatio(discountRatio);
    }

}
