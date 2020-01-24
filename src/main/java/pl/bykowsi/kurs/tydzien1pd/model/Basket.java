package pl.bykowsi.kurs.tydzien1pd.model;

import lombok.Getter;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.RandomGeneratorFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Basket {

    private List<Product> productsList;
    private static final RandomGenerator randomGenerator = RandomGeneratorFactory.createRandomGenerator(new Random());
    private final int MaxPrice;
    private final int MinPrice;

    private Basket(@Value("${price.MaxPrice}")int maxPrice, @Value("${price.MinPrice}")int minPrice) {
        this.MaxPrice = maxPrice;
        this.MinPrice = minPrice;
    }

    public List<Product> getBasket() {
        return productsList = createRandomBasket();
    }

    private List<Product> createRandomBasket() {
        List<Product> defaultList = createListOfDifferentProducts();
        List<Product> finalList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int productIndex = randomGenerator.nextInt(5);
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
