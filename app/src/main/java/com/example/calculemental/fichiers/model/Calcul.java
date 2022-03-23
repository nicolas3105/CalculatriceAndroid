package com.example.calculemental.fichiers.model;

public class Calcul extends BaseEntity{
    String calcul;
    Integer nbCalculs;
    Integer nbCalculsReussis;

    public String getCalcul() {
        return calcul;
    }

    public void setCalcul(String calcul) {
        this.calcul = calcul;
    }

    public Integer getNbCalculs() {
        return nbCalculs;
    }

    public void setNbCalculs(Integer nbCalculs) {
        this.nbCalculs = nbCalculs;
    }

    public Integer getNbCalculsReussis() {
        return nbCalculsReussis;
    }

    public void setNbCalculsReussis(Integer nbCalculsReussis) {
        this.nbCalculsReussis = nbCalculsReussis;
    }

}
