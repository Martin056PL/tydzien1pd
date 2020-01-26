package pl.bykowsi.kurs.tydzien1pd.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
public class PriceCalculationsData {

    private BigDecimal sum;
    private BigDecimal VAT;
    private BigDecimal grossPrice;
    private BigDecimal discountedGrossPrice;
    private BigDecimal discountRatio;

}
