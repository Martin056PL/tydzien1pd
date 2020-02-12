package pl.bykowsi.kurs.tydzien1pd.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = -5716898526007421281L;
    private String name;
    private BigDecimal price;
}
