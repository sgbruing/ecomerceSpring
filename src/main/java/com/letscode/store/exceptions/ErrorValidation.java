package com.letscode.store.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ErrorValidation {
    private String campo;
    private String messagem;
}
