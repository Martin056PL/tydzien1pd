package pl.bykowsi.kurs.tydzien1pd.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class LanguageSetting {

    private final MessageSource messageSource;
    private final String languageVersion;

    @Autowired
    public LanguageSetting(MessageSource messageSource, @Value("${language.languageVersion}") String languageVersion) {
        this.messageSource = messageSource;
        this.languageVersion = languageVersion;
    }
}
