
package com.example.emanu.boaviagem.model;



public class Travel {

    private int idViagem;
    private String dataChegada;
    private String dataSaida;
    private String endHospedagem;
    private String localViagem;
    private String localHospedagem;
    private String motivoViagem;
    private String telefoneLocal;
    private String valorDiaria;


    public Travel(int idViagem, String dataChegada, String dataSaida, String endHospedagem, String localViagem, String localHospedagem, String motivoViagem, String telefoneLocal, String valorDiaria) {
        this.idViagem = idViagem;
        this.dataChegada = dataChegada;
        this.dataSaida = dataSaida;
        this.endHospedagem = endHospedagem;
        this.localViagem = localViagem;
        this.localHospedagem = localHospedagem;
        this.motivoViagem = motivoViagem;
        this.telefoneLocal = telefoneLocal;
        this.valorDiaria = valorDiaria;
    }

    public Travel(int idViagem, String data_chegada, String valor_diaria) {
    }


    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getEndHospedagem() {
        return endHospedagem;
    }

    public void setEndHospedagem(String endHospedagem) {
        this.endHospedagem = endHospedagem;
    }

    public int getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(int idViagem) {
        this.idViagem = idViagem;
    }

    public String getLocalViagem() {
        return localViagem;
    }

    public void setLocal(String local) {
        this.localViagem = localViagem;
    }

    public String getLocalHospedagem() {
        return localHospedagem;
    }

    public void setLocalHospedagem(String localHospedagem) {
        this.localHospedagem = localHospedagem;
    }

    public String getMotivoViagem() {
        return motivoViagem;
    }

    public void setMotivoViagem(String motivoViagem) {
        this.motivoViagem = motivoViagem;
    }

    public String getTelefoneLocal() {
        return telefoneLocal;
    }

    public void setTelefoneLocal(String telefoneLocal) {
        this.telefoneLocal = telefoneLocal;
    }

    public String getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(String valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

}
