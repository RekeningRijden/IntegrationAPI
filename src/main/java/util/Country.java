package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 17-06-16.
 */
public class Country {

    private String name;

    private List<String> altSpellings;

    private Translation translations;

    private String nativeName;

    private String alpha2Code;

    private List<String> countryNames;

    public Country(){
        altSpellings = new ArrayList<>();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public Translation getTranslations() {
        return translations;
    }

    public void setTranslations(Translation translations) {
        this.translations = translations;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public List<String> getCountryNames() {
        return countryNames;
    }

    public void setCountryNames(List<String> countryNames) {
        this.countryNames = countryNames;
    }

    //</editor-fold>

    public void organize() {
        this.countryNames = new ArrayList<>();
        this.countryNames.add(nativeName.toLowerCase());
        this.countryNames.add(name.toLowerCase());
        for(String altSpelling : altSpellings) {
            this.countryNames.add(altSpelling.toLowerCase());
        }
        this.countryNames.add(translations.getDe().toLowerCase());
        this.countryNames.add(translations.getEs().toLowerCase());
        this.countryNames.add(translations.getFr().toLowerCase());
        this.countryNames.add(translations.getJa().toLowerCase());
        this.countryNames.add(translations.getIt().toLowerCase());
    }
}

class Translation {
    private String de;
    private String es;
    private String fr;
    private String ja;
    private String it;

    public Translation(){}

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }
}