package pigusov.bots.telebot.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import pigusov.bots.telebot.bot.WriteReadBot;
import pigusov.bots.telebot.handlers.CallbackQueryHandler;
import pigusov.bots.telebot.handlers.MessageHandler;

@Configuration
@AllArgsConstructor
public class MainConfig {
  private final BotConfig botConfig;
  private final TinkoffApiConfig tinkoffApiConfig;

  @Bean
  public SetWebhook setWebhookInstance() {
    return SetWebhook.builder().url(botConfig.getWebhookPath()).build();
  }

  @Bean
  public WriteReadBot springWebhookBot(SetWebhook setWebhook,
                                       MessageHandler messageHandler,
                                       CallbackQueryHandler callbackQueryHandler) {
    WriteReadBot bot = new WriteReadBot(setWebhook, messageHandler, callbackQueryHandler);

    bot.setBotPath(botConfig.getWebhookPath());
    bot.setBotUsername(botConfig.getBotName());
    bot.setBotToken(botConfig.getBotToken());

    return bot;
  }
}
