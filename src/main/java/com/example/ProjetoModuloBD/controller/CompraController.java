package com.example.ProjetoModuloBD.controller;

import com.example.ProjetoModuloBD.dto.CompraRequest;
import com.example.ProjetoModuloBD.dto.CompraResponse;
import com.example.ProjetoModuloBD.dto.ProdutoRequest;
import com.example.ProjetoModuloBD.dto.ProdutoResponse;
import com.example.ProjetoModuloBD.exceptions.BadRequest;
import com.example.ProjetoModuloBD.exceptions.NotFound;
import com.example.ProjetoModuloBD.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compra")
public class CompraController {

    private final CompraService compraService;

    @GetMapping()
    public ResponseEntity<Object> listCompras(
            @RequestParam(name = "cpf", required = false) String cpf,
            Pageable pageable
    ) throws NotFound {
        Page<CompraResponse> compraReturn = compraService.listByCPF(cpf, pageable);
        if(cpf!=null && compraReturn.isEmpty()){
            throw new NotFound("Compra não encontrada ou cpf incorreto.");
        }else if(compraReturn.isEmpty()){
            return new ResponseEntity(compraReturn, HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(compraReturn);
        }
    }

    @PostMapping()
    public ResponseEntity<CompraResponse> createProduct(@RequestBody @Valid CompraRequest compraRequest, BindingResult bindingResult) throws BadRequest {
        if(bindingResult.hasErrors()){
            throw new BadRequest("O campo " + bindingResult.getFieldError().getField() + " deve ser preenchido.");
        }
        return ResponseEntity.ok(compraService.createCompra(compraRequest));
    }

}
