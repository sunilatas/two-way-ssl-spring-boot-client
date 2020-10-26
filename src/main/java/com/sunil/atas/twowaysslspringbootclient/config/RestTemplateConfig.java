package com.sunil.atas.twowaysslspringbootclient.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.KeyStore;

@Configuration
public class RestTemplateConfig {

    @Bean
    public SSLContext sslContext() throws Exception {
        /* load the keystore file as input stream */
        ClassPathResource classPathResourceForKeystore = new ClassPathResource("jks/ssl-client-keystore.jks");
        InputStream keystoreStream = classPathResourceForKeystore.getInputStream();
        KeyManagerFactory keyManagerFactory = KeyManagerFactory
                .getInstance(KeyManagerFactory.getDefaultAlgorithm());
        /* get instance of keystore depends on type of store here we have JKS */
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try {
            keyStore.load(keystoreStream, "changeit".toCharArray());
        } finally {
            keystoreStream.close();
        }
        keyManagerFactory.init(keyStore, "changeit".toCharArray());
        KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();


        /* load trust store file as input stream */
        KeyStore trustStore = KeyStore.getInstance("JKS");
        ClassPathResource classPathResourceForTruststore = new ClassPathResource("jks/client-truststore.jks");
        InputStream truststoreSteam = classPathResourceForTruststore.getInputStream();
        try {
            /* load trust store */
            trustStore.load(truststoreSteam, "changeit".toCharArray());
        } finally {
            truststoreSteam.close();
        }
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);

        SSLContext sslcontext = SSLContext.getInstance("TLS");
        /* initialize ssl context with both key manager and trust manager */
        sslcontext.init(keyManagers, tmf.getTrustManagers(), null);
        return sslcontext;
    }

    @Bean
    public HttpClient httpClient(SSLContext sslContext) {
        return HttpClients.custom().setSSLContext(sslContext).build();
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Bean
    public RestTemplate sslRestTemplate(ClientHttpRequestFactory clientHttpRequestFactory) throws Exception {
        return new RestTemplate(clientHttpRequestFactory);
    }

}
