package pl.bykowsi.kurs.tydzien1pd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.bykowsi.kurs.tydzien1pd.model.Basket;
import pl.bykowsi.kurs.tydzien1pd.model.Product;
import pl.bykowsi.kurs.tydzien1pd.service.ShopService;

import java.math.BigDecimal;
import java.util.List;

@Service
@Profile("pro")
public class ProShopService implements ShopService {

    private final Basket basket;
    private final int VAT;
    private final int discount;

    @Autowired
    public ProShopService(@Value("${price.VAT}") Integer VAT,@Value("${price.discount}") Integer discount, Basket basket) {
        this.VAT = VAT;
        this.discount = discount;
        this.basket = basket;
    }

    @Override
    public void calculateFinalPrice(){
        List<Product> list = basket.getBasket();
        BigDecimal sum = BigDecimal.ZERO;
        for (Product p: list) {
            System.out.println("Product: " + p.getName() + "  Price: " + p.getPrice() + " PLN");
            sum = sum.add(p.getPrice());
        }
        BigDecimal grossPrice = sum.multiply(BigDecimal.valueOf(100+VAT).divide(BigDecimal.valueOf(100)));
        BigDecimal discountedGrossPrice = grossPrice.multiply(BigDecimal.valueOf(100-discount).divide(BigDecimal.valueOf(100)));

        System.out.println("----------------------------");
        System.out.println("Sum of price: " + sum + "PLN");
        System.out.println("----------------------------");
        System.out.println("Gross price = " + grossPrice + "PLN = " + sum + "PLN * 1." + VAT + " ");
        System.out.println("----------------------------");
        System.out.println("Gross price with discount = " + discountedGrossPrice
                + "PLN = " + grossPrice + "PLN * 0." + (100-discount) + " ");
    }

}
