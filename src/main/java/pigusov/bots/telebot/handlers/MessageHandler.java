package pigusov.bots.telebot.handlers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MessageHandler {
  public BotApiMethod<?> answerMessage(Message message) {
    String chatId = message.getChatId().toString();

    String inputText = message.getText();

    if (inputText.equals("/start")) {
      return new SendMessage(chatId, "Hello world!");
    }

    return null;
  }
}
