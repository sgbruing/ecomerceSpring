package com.letscode.store.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter @Setter
public class RequestPurchaseDTO {

    @CPF
    private String cpf;
    @NotBlank
    private List<PurchaseSaveProductDTO> products;
}
