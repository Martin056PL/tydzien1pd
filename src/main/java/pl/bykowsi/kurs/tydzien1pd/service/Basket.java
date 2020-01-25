package pl.bykowsi.kurs.tydzien1pd.service;

import org.apache.commons.math3.random.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.bykowsi.kurs.tydzien1pd.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Basket {

    private final RandomGenerator randomGenerator;
    private final int MaxPrice;
    private final int MinPrice;
    private static final Integer quantityOfProducts = 5;

    @Autowired
    private Basket(RandomGenerator randomGenerator, @Value("${price.MaxPrice}") int maxPrice, @Value("${price.MinPrice}") int minPrice) {
        this.randomGenerator = randomGenerator;
        this.MaxPrice = maxPrice;
        this.MinPrice = minPrice;
    }

    public List<Product> createAndGetRandomBasket() {
        List<Product> defaultList = createListOfDifferentProducts();
        List<Product> finalList = new ArrayList<>();
        for (int i = 0; i < quantityOfProducts; i++) {
            int productIndex = randomGenerator.nextInt(defaultList.size());
            finalList.add(defaultList.get(productIndex));
        }
        return finalList;
    }

    private List<Product> createListOfDifferentProducts() {
        List<Product> defaultList = new ArrayList<>();
        defaultList.add(new Product("PenDrive", generateRandomPrice()));
        defaultList.add(new Product("Speaker", generateRandomPrice()));
        defaultList.add(new Product("Band", generateRandomPrice()));
        defaultList.add(new Product("MP3", generateRandomPrice()));
        defaultList.add(new Product("PowerBank", generateRandomPrice()));
        return defaultList;
    }

    private BigDecimal generateRandomPrice() {
        int randomPrice = randomGenerator.nextInt(MaxPrice);
        return (randomPrice < MinPrice) ? BigDecimal.valueOf(randomPrice + MinPrice) : BigDecimal.valueOf(randomPrice);
    }
}
