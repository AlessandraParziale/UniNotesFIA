package Storage.Libretto;

import Storage.Esame.EsameBean;

import java.util.ArrayList;

public class LibrettoBean {
    private ArrayList<EsameBean> listaEsami;
    private int idLibretto,numEsami,cfuCrediti;
    private float media;


    public LibrettoBean() { }

    public LibrettoBean(int idLibretto, ArrayList<EsameBean> esami) {
        this.idLibretto = idLibretto;
        this.listaEsami = esami;
    }

    public LibrettoBean(int nunEsami, int cfuCrediti, float media){
        this.numEsami=nunEsami;
        this.cfuCrediti=cfuCrediti;
        this.media=media;
    }

    public void aggiungiEsame(EsameBean esame){
        if(listaEsami == null){
            listaEsami = new ArrayList<>();
        }
        if(esame != null){
            listaEsami.add(esame);
            numEsami++;
            cfuCrediti+=esame.getCfu();
            media = calcolaMedia();
        }
    }

    private float calcolaMedia(){//media aritmetica
        int totVoti = 0;
        for(EsameBean e : listaEsami){
            totVoti+=e.getVoto();
        }
        if(totVoti > 0)
            return totVoti/numEsami;
        return 0;
    }

    public LibrettoBean(ArrayList<EsameBean> esame) {
        this.listaEsami = esame;
    }

    public int getIdLibretto() {
        return idLibretto;
    }

    public void setIdLibretto(int idLibretto) {
        this.idLibretto = idLibretto;
    }

    public ArrayList<EsameBean> getListaEsami() {
        return listaEsami;
    }

    public void setListaEsami(ArrayList<EsameBean> esami) {
        this.listaEsami = esami;
    }

    public int getNunEsami() {
        return numEsami;
    }

    public void setNunEsami(int nunEsami) {
        this.numEsami = nunEsami;
    }

    public int getCfuCrediti() {
        return cfuCrediti;
    }

    public void setCfuCrediti(int cfuCrediti) {
        this.cfuCrediti = cfuCrediti;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }
}
