package pl.bykowsi.kurs.tydzien1pd.screeninfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSettings;
import pl.bykowsi.kurs.tydzien1pd.model.PriceCalculationsData;
import pl.bykowsi.kurs.tydzien1pd.model.Product;

import java.util.List;
import java.util.Locale;


public class PrintMessages {

    private static Logger logger = LoggerFactory.getLogger(PrintMessages.class);

    public static void printBasket(List<Product> list, LanguageSettings languageSettings) {
        logger.warn("");
        list.forEach(p -> logger.warn(languageSettings.getMessageSource().getMessage("singleProductPosition", new Object[]{p.getName(), p.getPrice()}, Locale.forLanguageTag(languageSettings.getLanguageVersion()))));
    }

    public static void StartPrintData(LanguageSettings languageSettings, PriceCalculationsData priceCalculationsData) {
        printUnderline(languageSettings);
        logger.warn(languageSettings.getMessageSource().getMessage("startSum", new Object[]{
                priceCalculationsData.getSum()
        }, Locale.forLanguageTag(languageSettings.getLanguageVersion())));
    }

    public static void PlusPrintData(LanguageSettings languageSettings, PriceCalculationsData priceCalculationsData) {
        StartPrintData(languageSettings, priceCalculationsData);
        printUnderline(languageSettings);
        logger.warn(languageSettings.getMessageSource().getMessage("plusSum", new Object[]{
                priceCalculationsData.getGrossPrice(),
                priceCalculationsData.getSum(),
                priceCalculationsData.getVAT()
        }, Locale.forLanguageTag(languageSettings.getLanguageVersion())));

    }

    public static void ProPrintData(LanguageSettings languageSettings, PriceCalculationsData priceCalculationsData) {
        PlusPrintData(languageSettings, priceCalculationsData);
        printUnderline(languageSettings);
        logger.warn(languageSettings.getMessageSource().getMessage("proSum", new Object[]{
                priceCalculationsData.getDiscountedGrossPrice(),
                priceCalculationsData.getGrossPrice(),
                priceCalculationsData.getDiscountRatio()
        }, Locale.forLanguageTag(languageSettings.getLanguageVersion())));

    }

    private static void printUnderline(LanguageSettings languageSettings) {
        logger.warn(languageSettings.getMessageSource().getMessage("underline", new Object[]{}, Locale.forLanguageTag(languageSettings.getLanguageVersion())));
    }


}
