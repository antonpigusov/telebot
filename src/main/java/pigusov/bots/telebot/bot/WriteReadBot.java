package pigusov.bots.telebot.bot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import pigusov.bots.telebot.handlers.CallbackQueryHandler;
import pigusov.bots.telebot.handlers.MessageHandler;
import pigusov.bots.telebot.utils.BotMessageEnum;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriteReadBot extends SpringWebhookBot {
  String botPath;
  String botUsername;
  String botToken;

  MessageHandler messageHandler;
  CallbackQueryHandler callbackQueryHandler;

  public WriteReadBot(SetWebhook setWebhook, MessageHandler messageHandler, CallbackQueryHandler callbackQueryHandler) {
    super(setWebhook);
    this.messageHandler = messageHandler;
    this.callbackQueryHandler = callbackQueryHandler;
  }

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    try {
      return handleUpdate(update);
    } catch (IllegalArgumentException e) {
      return new SendMessage(update.getMessage().getChatId().toString(),
          BotMessageEnum.EXCEPTION_ILLEGAL_MESSAGE.toString());
    } catch (Exception e) {
      return new SendMessage(update.getMessage().getChatId().toString(),
          BotMessageEnum.UNEXPECTED_EXCEPTION.toString());
    }
  }

  private BotApiMethod<?> handleUpdate(Update update) throws IOException, ExecutionException, InterruptedException {
    if (update.hasCallbackQuery()) {
      CallbackQuery callbackQuery = update.getCallbackQuery();
      return callbackQueryHandler.processCallbackQuery(callbackQuery);
    } else {
      Message message = update.getMessage();
      if (message != null) {
        return messageHandler.answerMessage(update.getMessage());
      }
    }
    return null;
  }
}
