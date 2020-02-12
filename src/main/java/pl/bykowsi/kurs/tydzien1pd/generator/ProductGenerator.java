package pl.bykowsi.kurs.tydzien1pd.generator;

import org.apache.commons.math3.random.RandomGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.bykowsi.kurs.tydzien1pd.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductGenerator {

    private final RandomGenerator randomGenerator;
    private final int MaxPrice;
    private final int MinPrice;
    private final List<Product> defaultProducts;

    public ProductGenerator(RandomGenerator randomGenerator, @Value("${price.MaxPrice}") int maxPrice, @Value("${price.MinPrice}") int minPrice) {
        this.randomGenerator = randomGenerator;
        MaxPrice = maxPrice;
        MinPrice = minPrice;
        defaultProducts = createListOfDifferentProducts();
    }

    public Product drawProduct() {
        int productIndex = randomGenerator.nextInt(defaultProducts.size());
        return defaultProducts.get(productIndex);
    }

    private List<Product> createListOfDifferentProducts() {
        List<Product> defaultProducts = new ArrayList<>();
        defaultProducts.add(createProduct("PenDrive"));
        defaultProducts.add(createProduct("Speaker"));
        defaultProducts.add(createProduct("Band"));
        defaultProducts.add(createProduct("MP3"));
        defaultProducts.add(createProduct("PowerBank"));
        return defaultProducts;
    }

    private Product createProduct(String penDrive) {
        return new Product(penDrive, generateRandomPrice());
    }

    private BigDecimal generateRandomPrice() {
        int randomPrice = randomGenerator.nextInt(MaxPrice);
        return (randomPrice < MinPrice) ? BigDecimal.valueOf(randomPrice + MinPrice) : BigDecimal.valueOf(randomPrice);
    }

}
