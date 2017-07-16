
package com.example.emanu.boaviagem.model;

import java.util.Date;

public class Expense {

    private Integer id;
    private Date dataGasto;
    private String descricao;
    private Double valor;
    private String metodoPagamento;
    private Integer viagemId;

    public Expense() {
    }

    public Expense(Integer id, Date dataGasto, String descricao, Double valor, String metodoPagamento, Integer viagemId) {
        this.id = id;
        this.dataGasto = dataGasto;
        this.descricao = descricao;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.viagemId = viagemId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataGasto() {
        return dataGasto;
    }

    public void setDataGasto(Date dataGasto) {
        this.dataGasto = dataGasto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Integer getViagemId() {
        return viagemId;
    }

    public void setViagemId(Integer viagemId) {
        this.viagemId = viagemId;
    }
}
