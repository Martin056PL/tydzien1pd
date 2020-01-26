package pl.bykowsi.kurs.tydzien1pd.screeninfo;

import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSettings;
import pl.bykowsi.kurs.tydzien1pd.model.PriceCalculationsData;

import java.util.Locale;


public class PrintMessages {

    public static void StartPrintData(LanguageSettings languageSettings, PriceCalculationsData priceCalculationsData) {
        printUnderline(languageSettings);
        System.out.println(languageSettings.getMessageSource().getMessage("startSum", new Object[]{
                priceCalculationsData.getSum()
        }, Locale.forLanguageTag(languageSettings.getLanguageVersion())));
    }

    public static void PlusPrintData(LanguageSettings languageSettings, PriceCalculationsData priceCalculationsData) {
        StartPrintData(languageSettings, priceCalculationsData);
        printUnderline(languageSettings);
        System.out.println(languageSettings.getMessageSource().getMessage("plusSum", new Object[]{
                priceCalculationsData.getGrossPrice(),
                priceCalculationsData.getSum(),
                priceCalculationsData.getVAT()
        }, Locale.forLanguageTag(languageSettings.getLanguageVersion())));

    }

    public static void ProPrintData(LanguageSettings languageSettings, PriceCalculationsData priceCalculationsData) {
        PlusPrintData(languageSettings, priceCalculationsData);
        printUnderline(languageSettings);
        System.out.println(languageSettings.getMessageSource().getMessage("proSum", new Object[]{
                priceCalculationsData.getDiscountedGrossPrice(),
                priceCalculationsData.getGrossPrice(),
                priceCalculationsData.getDiscountRatio()
        }, Locale.forLanguageTag(languageSettings.getLanguageVersion())));

    }

    private static void printUnderline(LanguageSettings languageSettings) {
        System.out.println(languageSettings.getMessageSource().getMessage("underline", new Object[]{}, Locale.forLanguageTag(languageSettings.getLanguageVersion())));
    }

}
