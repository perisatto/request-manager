package com.perisatto.fiapprj.request_manager.infra.gateways;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;

import com.perisatto.fiapprj.request_manager.application.interfaces.FileRepositoryManagement;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

public class S3RepositoryManagement implements FileRepositoryManagement{

	static final Logger logger = LogManager.getLogger(S3RepositoryManagement.class);
	
	private final Environment env;
	
	public S3RepositoryManagement(Environment env) {
		this.env = env;
	}

	@Override
	public String generateUploadFileURL(String id, String videoFile) {
		
		String awsS3Bucket = env.getProperty("spring.aws.s3bucket");
		String awsAccessKeyId = env.getProperty("spring.aws.accessKeyId");
		String awsSecretAccessKey = env.getProperty("spring.aws.secretAccessKey");
		String awsSessionToken = env.getProperty("spring.aws.sessionToken");
		Region awsRegion = Region.of(env.getProperty("spring.aws.region"));
		
		AwsCredentials credentials = AwsSessionCredentials.create(awsAccessKeyId, awsSecretAccessKey, awsSessionToken);
		StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);
		
		PutObjectRequest objectRequest = PutObjectRequest.builder()
		.bucket(awsS3Bucket)
		.key(id + "_" + videoFile)
		//.metadata(metadata)
		.build();
				
		PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
		.signatureDuration(Duration.ofMinutes(2))  // The URL expires in 10 minutes.
		.putObjectRequest(objectRequest)
		.build();

        S3Presigner s3Presigner = S3Presigner.builder()
                .credentialsProvider(credentialsProvider)
                .region(awsRegion)
                .build();
        
        PresignedPutObjectRequest presignedPutObjectRequest  = s3Presigner.presignPutObject(presignRequest);
        
		String myURL = presignedPutObjectRequest.url().toString();
		logger.info("Presigned URL to upload a file to: [{}]", myURL);
		logger.info("HTTP method: [{}]", presignedPutObjectRequest.httpRequest().method());

		return presignedPutObjectRequest.url().toExternalForm();             
	}
}
