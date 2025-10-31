package com.test.config;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
@Configuration
public class DynamoDbConfig {
	@Bean
    public DynamoDbClient dynamoDbClient() {
		
		//note - certificate issues will come so we added this below.
		//our java application is not trusting the aws credentials 

        // Trust-all TrustManager (for testing only!)
        X509TrustManager trustAll = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) {}
            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
        };

        // Build SdkHttpClient with custom trust manager
        SdkHttpClient httpClient = ApacheHttpClient.builder()
                .tlsTrustManagersProvider(() -> new TrustManager[]{trustAll})
                .build();

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create("//", "//");

        return DynamoDbClient.builder()
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .httpClient(httpClient) // pass as SdkHttpClient
                .build();
    }
}
