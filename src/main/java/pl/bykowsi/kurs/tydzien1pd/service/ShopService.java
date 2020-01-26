package pl.bykowsi.kurs.tydzien1pd.service;

import pl.bykowsi.kurs.tydzien1pd.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ShopService {

    void calculateFinalPrice();

    default List<Product> CreateBasket(Basket basket){
        return basket.createAndGetRandomBasket();
    }

    default BigDecimal calculateBasket(List<Product> list){
        return list.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
