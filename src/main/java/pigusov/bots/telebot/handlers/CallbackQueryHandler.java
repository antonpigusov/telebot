package pigusov.bots.telebot.handlers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.io.IOException;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CallbackQueryHandler {

  public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) throws IOException {
    final String chatId = buttonQuery.getMessage().getChatId().toString();

    String data = buttonQuery.getData();

    return null;
  }
}
