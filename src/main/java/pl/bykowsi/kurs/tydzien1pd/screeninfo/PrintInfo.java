package pl.bykowsi.kurs.tydzien1pd.screeninfo;

import org.springframework.context.MessageSource;

import java.math.BigDecimal;
import java.util.Locale;


public class PrintInfo {

    public static void StartPrintData(MessageSource messageSource, BigDecimal sum, String languageVersion) {
        System.out.println(messageSource.getMessage("underline", new Object[]{}, Locale.forLanguageTag(languageVersion)));
        System.out.println(messageSource.getMessage("startSum", new Object[]{sum}, Locale.forLanguageTag(languageVersion)));
    }

    public static void PlusPrintData(MessageSource messageSource, BigDecimal sum, String languageVersion, BigDecimal grossPrice, BigDecimal vat) {
        StartPrintData(messageSource, sum, languageVersion);
        System.out.println(messageSource.getMessage("underline", new Object[]{}, Locale.forLanguageTag(languageVersion)));
        System.out.println(messageSource.getMessage("plusSum", new Object[]{grossPrice, sum, vat}, Locale.forLanguageTag(languageVersion)));

    }

    public static void ProPrintData(MessageSource messageSource, BigDecimal sum, String languageVersion, BigDecimal grossPrice, BigDecimal vat, BigDecimal discountedGrossPrice, int discount) {
        PlusPrintData(messageSource, sum, languageVersion, grossPrice, vat);
        System.out.println(messageSource.getMessage("underline", new Object[]{}, Locale.forLanguageTag(languageVersion)));
        System.out.println(messageSource.getMessage("proSum", new Object[]{discountedGrossPrice, grossPrice, discount}, Locale.forLanguageTag(languageVersion)));

    }

}
