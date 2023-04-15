package maintain.model;
import java.text.NumberFormat;
import java.io.Serializable;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
public class Product implements Serializable
{
    @NotBlank(message="Code is required")
    private String code;
    @NotBlank(message="Description is required")
    private String description;
    @NotBlank(message="Price is required")
    private double price;
    public Product()
    {
        code = "";
        description = "";
        price = 0;
    }

    public Product(String code, String description, double price) {
        this.code = code;
        this.description = description;
        this.price = price;
    }
}