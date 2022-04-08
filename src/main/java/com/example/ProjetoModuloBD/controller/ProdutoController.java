package com.example.ProjetoModuloBD.controller;

import com.example.ProjetoModuloBD.dto.ProdutoRequest;
import com.example.ProjetoModuloBD.dto.ProdutoResponse;
import com.example.ProjetoModuloBD.exceptions.BadRequest;
import com.example.ProjetoModuloBD.exceptions.NotFound;
import com.example.ProjetoModuloBD.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(name = "codigo", required = false) String codigo, Pageable pageable) throws NotFound {
        Page<ProdutoResponse> produtoReturn = produtoService.listByCodigo(codigo, pageable);
        if(codigo!=null && produtoReturn.isEmpty()){
            throw new NotFound("Produto não encontrado.");
        }else if(produtoReturn.isEmpty()){
            return new ResponseEntity(produtoReturn, HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(produtoReturn);
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> createProduct(@RequestBody @Valid ProdutoRequest produtoRequest, BindingResult bindingResult) throws BadRequest {
        if(bindingResult.hasErrors()){
            throw new BadRequest("O campo " + bindingResult.getFieldError().getField() + " deve ser preenchido.");
        }
        return ResponseEntity.ok(produtoService.createProduct(produtoRequest));
    }

}
