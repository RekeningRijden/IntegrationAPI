package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Eric on 17-06-16.
 */

/**
 * Class used for parsing the json to an object
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

    /**
     * Combines all country names in different languages in a single list
     */
    public void organize() {
        List<String> tempCountryNames = new ArrayList<>();
        tempCountryNames.add(nativeName.toLowerCase());
        tempCountryNames.add(name.toLowerCase());

        for(String altSpelling : altSpellings) {
            tempCountryNames.add(altSpelling.toLowerCase());
        }

        if(translations.getDe() != null) tempCountryNames.add(translations.getDe().toLowerCase());
        if(translations.getEs() != null) tempCountryNames.add(translations.getEs().toLowerCase());
        if(translations.getFr() != null) tempCountryNames.add(translations.getFr().toLowerCase());
        if(translations.getJa() != null) tempCountryNames.add(translations.getJa().toLowerCase());
        if(translations.getIt() != null) tempCountryNames.add(translations.getIt().toLowerCase());
        cleanup(tempCountryNames);
    }

    /**
     * Removes all duplicates
     * @param tempCountryNames List with country names in different languages
     */
    private void cleanup(List<String> tempCountryNames) {
        this.countryNames = new ArrayList<>();
        Set<String> tempSet = new TreeSet<>();
        tempSet.addAll(tempCountryNames);
        this.countryNames.addAll(tempSet);
    }
}

class Translation {
    private String de;
    private String es;
    private String fr;
    private String ja;
    private String it;

    public Translation(){}

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
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
    //</editor-fold>
}