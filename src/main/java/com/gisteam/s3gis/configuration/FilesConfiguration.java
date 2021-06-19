package com.gisteam.s3gis.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilesConfiguration {

    @Value("${cloud.aws.credentials.access-key}")
    private String AwsKeyAccess;

    @Value("${cloud.aws.credentials.secret-key}")
    private String AwsKeySecret;
    @Value("${cloud.aws.region.static}")
    private String serviceArea;

    // Make service connection with aws credentials
    @Bean
    public AmazonS3 s3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(AwsKeyAccess, AwsKeySecret);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(serviceArea).build();
    }

}