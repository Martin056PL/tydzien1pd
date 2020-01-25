package pl.bykowsi.kurs.tydzien1pd.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class LanguageSettings {

    private MessageSource messageSource;
    private final String languageVersion;

    @Autowired
    public LanguageSettings(MessageSource messageSource, @Value("${language.languageVersion}") String languageVersion) {
        this.messageSource = messageSource;
        this.languageVersion = languageVersion;
    }
}
