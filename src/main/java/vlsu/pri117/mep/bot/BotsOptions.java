package vlsu.pri117.mep.bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Service
public class BotsOptions {

    private DefaultBotOptions options;
    private final String proxyHost = BotConstans.getS_PROXY_HOST();
    private static final int s_PROXY_PROT = BotConstans.getS_PROXY_PORT();

    public BotsOptions(){
        options = ApiContext.getInstance(DefaultBotOptions.class);
        options.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        options.setProxyHost(proxyHost);
        options.setProxyPort(s_PROXY_PROT);
    }

    public DefaultBotOptions getOptions() {
        return options;
    }
}
