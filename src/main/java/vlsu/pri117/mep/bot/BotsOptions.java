package vlsu.pri117.mep.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Component
public class BotsOptions {

    private DefaultBotOptions options;
    private final String proxyHost = BotConstans.getS_PROXY_HOST();
    private final int proxyPort = BotConstans.getS_PROXY_PORT();

    public BotsOptions(){
        options = ApiContext.getInstance(DefaultBotOptions.class);
        options.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
    }

    public DefaultBotOptions getOptions() {
        return options;
    }
}
