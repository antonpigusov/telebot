package pigusov.bots.telebot.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TinkoffApiConfig {
  @Value("${tinkoffApi.readonly-token}")
  String tinkoffApiReadonlyToken;
}
