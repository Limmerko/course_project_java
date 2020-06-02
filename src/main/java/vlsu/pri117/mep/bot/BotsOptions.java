package vlsu.pri117.mep.bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Service
public class BotsOptions {

    private DefaultBotOptions options;
    private static final String s_PROXY_HOST = "54.39.16.26";
    private static final int s_PROXY_PROT = 45100;

    public BotsOptions(){
        options = ApiContext.getInstance(DefaultBotOptions.class);
        options.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        options.setProxyHost(s_PROXY_HOST);
        options.setProxyPort(s_PROXY_PROT);
    }

    public DefaultBotOptions getOptions() {
        return options;
    }
}
