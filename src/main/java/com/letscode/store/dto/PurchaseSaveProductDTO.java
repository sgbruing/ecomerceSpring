package com.letscode.store.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter @Setter
public class PurchaseSaveProductDTO {
    @NotBlank @Length(min = 4, max = 4)
    private int productCode;
    @NotBlank @Positive
    private int quantity;
}
