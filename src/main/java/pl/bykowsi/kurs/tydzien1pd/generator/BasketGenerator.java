package pl.bykowsi.kurs.tydzien1pd.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.bykowsi.kurs.tydzien1pd.model.Basket;

import java.util.stream.IntStream;

@Component
public class BasketGenerator {

    private static final Integer QUANTITY_OF_PRODUCTS = 5;
    private final ProductGenerator productGenerator;

    @Autowired
    public BasketGenerator(ProductGenerator productGenerator) {
        this.productGenerator = productGenerator;
    }

    public Basket createRandomBasket() {
        Basket basket = new Basket();
        IntStream.range(0, QUANTITY_OF_PRODUCTS)
                .forEach(value -> basket.addProduct(productGenerator.drawProduct()));
        return basket;
    }


}
