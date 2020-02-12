package pl.bykowsi.kurs.tydzien1pd.screenmessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bykowsi.kurs.tydzien1pd.configuration.LanguageSetting;
import pl.bykowsi.kurs.tydzien1pd.model.Basket;
import pl.bykowsi.kurs.tydzien1pd.model.CalculationPriceData;

import java.util.Locale;

public class MessagePrinter {

    private static Logger logger = LoggerFactory.getLogger(MessagePrinter.class);

    public static void printBasket(Basket basket, LanguageSetting languageSetting) {
        logger.warn("");
        basket.getProducts()
                .forEach(p -> logger.warn(languageSetting.getMessageSource().getMessage(
                        "singleProductPosition",
                        new Object[]{p.getName(), p.getPrice()},
                        Locale.forLanguageTag(languageSetting.getLanguageVersion()))
                        )
                );
    }

    public static void startPrintData(LanguageSetting languageSetting, CalculationPriceData calculationPriceData) {
        printUnderline(languageSetting);
        logger.warn(languageSetting.getMessageSource().getMessage("startSum",
                new Object[]{calculationPriceData.getSum()},
                Locale.forLanguageTag(languageSetting.getLanguageVersion())
                )
        );
    }

    public static void plusPrintData(LanguageSetting languageSetting, CalculationPriceData calculationPriceData) {
        startPrintData(languageSetting, calculationPriceData);
        printUnderline(languageSetting);
        logger.warn(languageSetting.getMessageSource().getMessage(
                "plusSum",
                new Object[]{
                        calculationPriceData.getGrossPrice(),
                        calculationPriceData.getSum(),
                        calculationPriceData.getVAT()
                }, Locale.forLanguageTag(languageSetting.getLanguageVersion())));

    }

    public static void proPrintData(LanguageSetting languageSetting, CalculationPriceData calculationPriceData) {
        plusPrintData(languageSetting, calculationPriceData);
        printUnderline(languageSetting);
        logger.warn(languageSetting.getMessageSource().getMessage(
                "proSum",
                new Object[]{
                        calculationPriceData.getDiscountedGrossPrice(),
                        calculationPriceData.getGrossPrice(),
                        calculationPriceData.getDiscountRatio()
                }, Locale.forLanguageTag(languageSetting.getLanguageVersion())));

    }

    private static void printUnderline(LanguageSetting languageSetting) {
        logger.warn(languageSetting.getMessageSource().getMessage(
                "underline",
                new Object[]{},
                Locale.forLanguageTag(languageSetting.getLanguageVersion())));
    }


}
