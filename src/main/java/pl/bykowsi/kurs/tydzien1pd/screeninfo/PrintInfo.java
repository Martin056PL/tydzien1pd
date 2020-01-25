package pl.bykowsi.kurs.tydzien1pd.screeninfo;

import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSettings;

import java.math.BigDecimal;
import java.util.Locale;


public class PrintInfo {

    public static void StartPrintData(LanguageSettings languageSettings, BigDecimal sum) {
        System.out.println(languageSettings.getMessageSource().getMessage("underline", new Object[]{}, Locale.forLanguageTag(languageSettings.getLanguageVersion())));
        System.out.println(languageSettings.getMessageSource().getMessage("startSum", new Object[]{sum}, Locale.forLanguageTag(languageSettings.getLanguageVersion())));
    }

    public static void PlusPrintData(LanguageSettings languageSettings, BigDecimal sum, BigDecimal grossPrice, BigDecimal vat) {
        StartPrintData(languageSettings, sum);
        System.out.println(languageSettings.getMessageSource().getMessage("underline", new Object[]{}, Locale.forLanguageTag(languageSettings.getLanguageVersion())));
        System.out.println(languageSettings.getMessageSource().getMessage("plusSum", new Object[]{grossPrice, sum, vat}, Locale.forLanguageTag(languageSettings.getLanguageVersion())));

    }

    public static void ProPrintData(LanguageSettings languageSettings, BigDecimal sum, BigDecimal grossPrice, BigDecimal vat, BigDecimal discountedGrossPrice, int discount) {
        PlusPrintData(languageSettings, sum, grossPrice, vat);
        System.out.println(languageSettings.getMessageSource().getMessage("underline", new Object[]{}, Locale.forLanguageTag(languageSettings.getLanguageVersion())));
        System.out.println(languageSettings.getMessageSource().getMessage("proSum", new Object[]{discountedGrossPrice, grossPrice, discount}, Locale.forLanguageTag(languageSettings.getLanguageVersion())));

    }

}
