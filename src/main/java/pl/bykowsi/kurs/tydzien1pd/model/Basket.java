package pl.bykowsi.kurs.tydzien1pd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basket implements Serializable {

    private static final long serialVersionUID = -793643875039638787L;
    private List<Product> products;

    public void addProduct(Product product) {
        if (Objects.isNull(products)) {
            products = new ArrayList<>();
        }
        products.add(product);
    }
}
