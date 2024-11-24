package com.coderhouse.dtos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
    private Long id;
    private List<ProductOnCartDTO> products;
}
