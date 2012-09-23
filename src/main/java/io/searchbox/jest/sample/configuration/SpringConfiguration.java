package io.searchbox.jest.sample.configuration;

import io.searchbox.client.ElasticSearchClient;
import io.searchbox.client.ElasticSearchClientFactory;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashSet;

/**
 * @author ferhat
 */
@Configuration
public class SpringConfiguration {

    public @Bean
    ClientConfig clientConfig(){
        ClientConfig clientConfig = new ClientConfig();
        LinkedHashSet<String> servers = new LinkedHashSet<String>();
        servers.add(StringUtils.isNotBlank(System.getenv("SEARCHBOX_URL")) ? System.getenv("SEARCHBOX_URL") : "http://localhost:9200");
        clientConfig.getServerProperties().put(ClientConstants.SERVER_LIST,servers);
        clientConfig.getClientFeatures().put(ClientConstants.IS_MULTI_THREADED,false);
        clientConfig.getClientFeatures().put(ClientConstants.DEFAULT_INDEX,"articles");
        clientConfig.getClientFeatures().put(ClientConstants.DEFAULT_TYPE,"article");
        return clientConfig;
    }

    public @Bean
    ElasticSearchClient elasticSearchClient(){
        ElasticSearchClientFactory factory = new ElasticSearchClientFactory();
        factory.setClientConfig(clientConfig());
        return factory.getObject();
    }
}
