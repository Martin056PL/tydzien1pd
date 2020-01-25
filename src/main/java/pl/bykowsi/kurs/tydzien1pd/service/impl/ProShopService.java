package pl.bykowsi.kurs.tydzien1pd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.bykowsi.kurs.tydzien1pd.model.Product;
import pl.bykowsi.kurs.tydzien1pd.screeninfo.PrintInfo;
import pl.bykowsi.kurs.tydzien1pd.service.Basket;
import pl.bykowsi.kurs.tydzien1pd.service.ShopService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@Service
@Profile("pro")
public class ProShopService implements ShopService {

    private final Basket basket;
    private final BigDecimal VAT;
    private final BigDecimal discount;
    private BigDecimal sum;
    private MessageSource messageSource;
    private final String languageVersion;
    private static final BigDecimal hundred = BigDecimal.valueOf(100);

    @Autowired
    public ProShopService(@Value("${price.VAT}") Integer VAT, @Value("${price.discount}") Integer discount, Basket basket, MessageSource messageSource, @Value("${language.languageVersion}") String languageVersion) {
        this.VAT = BigDecimal.valueOf(VAT);
        this.discount = BigDecimal.valueOf(discount);
        this.basket = basket;
        this.sum = BigDecimal.ZERO;
        this.messageSource = messageSource;
        this.languageVersion = languageVersion;
    }

    @Override
    public void calculateFinalPrice() {
        List<Product> list = basket.getBasket();
        list.forEach(p -> System.out.println(messageSource.getMessage("singleProductPosition", new Object[]{p.getName(), p.getPrice()}, Locale.forLanguageTag(languageVersion))));
        sum = list.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal grossPrice = sum.multiply(hundred.add(VAT)).divide(hundred);
        BigDecimal discountedGrossPrice = grossPrice.multiply(hundred.subtract(discount)).divide(hundred);

        PrintInfo.ProPrintData(messageSource, sum, languageVersion, grossPrice, VAT, discountedGrossPrice, 100 - discount.intValue());
    }

}
