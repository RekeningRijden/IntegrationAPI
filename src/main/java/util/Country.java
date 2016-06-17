package util;

/**
 * Created by Eric on 17-06-16.
 */
public class Country {

    private String cca2;

    private Name name;

    private Translation translations;

    public Country() {}

    public String getCca2() {
        return cca2;
    }

    public void setCca2(String cca2) {
        this.cca2 = cca2;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Translation getTranslations() {
        return translations;
    }

    public void setTranslations(Translation translations) {
        this.translations = translations;
    }
}

class Name {
    private String common;

    public Name() {}

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }
}

class Translation {
    private  Lang deu;
    private  Lang fra;
    private  Lang hrv;
    private  Lang ita;
    private  Lang jpn;
    private  Lang nld;
    private  Lang por;
    private  Lang rus;
    private  Lang spa;
    private  Lang fin;
    private  Lang zho;

    public Translation(){}

    public Lang getDeu() {
        return deu;
    }

    public void setDeu(Lang deu) {
        this.deu = deu;
    }

    public Lang getFra() {
        return fra;
    }

    public void setFra(Lang fra) {
        this.fra = fra;
    }

    public Lang getHrv() {
        return hrv;
    }

    public void setHrv(Lang hrv) {
        this.hrv = hrv;
    }

    public Lang getIta() {
        return ita;
    }

    public void setIta(Lang ita) {
        this.ita = ita;
    }

    public Lang getJpn() {
        return jpn;
    }

    public void setJpn(Lang jpn) {
        this.jpn = jpn;
    }

    public Lang getNld() {
        return nld;
    }

    public void setNld(Lang nld) {
        this.nld = nld;
    }

    public Lang getPor() {
        return por;
    }

    public void setPor(Lang por) {
        this.por = por;
    }

    public Lang getRus() {
        return rus;
    }

    public void setRus(Lang rus) {
        this.rus = rus;
    }

    public Lang getSpa() {
        return spa;
    }

    public void setSpa(Lang spa) {
        this.spa = spa;
    }

    public Lang getFin() {
        return fin;
    }

    public void setFin(Lang fin) {
        this.fin = fin;
    }

    public Lang getZho() {
        return zho;
    }

    public void setZho(Lang zho) {
        this.zho = zho;
    }
}

class Lang {
    private String common;

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }
}