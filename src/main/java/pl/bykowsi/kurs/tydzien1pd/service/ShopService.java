package pl.bykowsi.kurs.tydzien1pd.service;

import pl.bykowsi.kurs.tydzien1pd.generator.BasketGenerator;
import pl.bykowsi.kurs.tydzien1pd.model.Basket;

public interface ShopService {

    void calculateFinalPrice();

    default Basket createBasket(BasketGenerator basketGenerator) {
        return basketGenerator.createRandomBasket();
    }

}
