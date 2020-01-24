package pl.bykowsi.kurs.tydzien1pd.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.bykowsi.kurs.tydzien1pd.model.Basket;
import pl.bykowsi.kurs.tydzien1pd.model.Product;
import pl.bykowsi.kurs.tydzien1pd.service.ShopService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StartShopService implements ShopService {

    private final Basket basket;

    @Override
    public void calculateFinalPrice() {
        List<Product> list = basket.getBasket();
        BigDecimal sum = BigDecimal.ZERO;
        for (Product p : list) {
            System.out.println("Product: " + p.getName() + "  Price: " + p.getPrice() + " PLN");
            sum = sum.add(p.getPrice());
        }
        System.out.println("----------------------------");
        System.out.println("Sum of price: " + sum + "PLN");
    }
}
