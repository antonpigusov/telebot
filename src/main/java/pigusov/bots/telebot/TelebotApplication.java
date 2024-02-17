package pigusov.bots.telebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.tinkoff.piapi.contract.v1.LastPrice;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class TelebotApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(TelebotApplication.class, args);

//		String token = "t.h2uS1UPZu7rV8ABFlJgINPDo0QvQGmMW417DMcHTdAUetpOj0rDB0NnwOWBkDshDQAWQbqkrf-6P_8VlPPNQxw";
//		var api = InvestApi.create(token);
//		String tgcs = api.getInstrumentsService().findInstrument("TCSG").get().get(0).getUid();
//		CompletableFuture<List<LastPrice>> lastPrices = api.getMarketDataService().getLastPrices(List.of(tgcs));
//		System.out.println(lastPrices.get().get(0));
	}
}
