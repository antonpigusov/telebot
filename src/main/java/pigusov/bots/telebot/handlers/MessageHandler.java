package pigusov.bots.telebot.handlers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tinkoff.piapi.contract.v1.LastPrice;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageHandler {

  String tinkoffApiReadonlyToken;

  public MessageHandler(@Value("${tinkoffApi.readonly-token}") String tinkoffApiReadonlyToken) {
    this.tinkoffApiReadonlyToken = tinkoffApiReadonlyToken;
  }

  public BotApiMethod<?> answerMessage(Message message) throws ExecutionException, InterruptedException {
    String chatId = message.getChatId().toString();

    String inputText = message.getText();

    if (inputText.equals("/start")) {
      var api = InvestApi.create(tinkoffApiReadonlyToken);
      String tgcs = api.getInstrumentsService().findInstrument("TCSG").get().get(0).getUid();
      CompletableFuture<List<LastPrice>> lastPrices = api.getMarketDataService().getLastPrices(List.of(tgcs));
      String value = lastPrices.get().get(0).getPrice().toString();
      return new SendMessage(chatId, value);
    }

    return null;
  }
}
