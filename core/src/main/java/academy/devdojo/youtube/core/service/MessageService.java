package academy.devdojo.youtube.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class MessageService {

    private final MessageSource messageSource;

    public String getMessage(final String term) {
        if (term == null || term.trim().isEmpty()) {
            return "";
        }
        return getMessage(term, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(final String term, Object[] args, Locale locale) {
        return messageSource.getMessage(term, args, locale);
    }

}
