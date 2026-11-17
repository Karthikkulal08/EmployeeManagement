package com.karthik.Employee.Configurations;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.env.ClusterEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchbaseConfig {

	private Cluster cluster;
	@Value("${spring.couchbase.connection-string}")
	private String connectionString;

	@Value("${spring.couchbase.username}")
	private String username;

	@Value("${spring.couchbase.password}")
	private String password;

	@Value("${spring.data.couchbase.bucket-name}")
	private String bucketName;



	@Bean(destroyMethod = "disconnect")
	public Cluster couchbaseCluster() {
		// This is the part Spring was missing: explicitly enabling TLS in the Env
		ClusterEnvironment env = ClusterEnvironment.builder()
				.securityConfig(security -> security.enableTls(true))
				.build();

		return Cluster.connect(connectionString,
				ClusterOptions.clusterOptions(username, password).environment(env));
	}
	@Bean
	public Bucket getBucket(Cluster cluster){
		return cluster.bucket(bucketName);
	}

	@Bean
	public Collection collection(Bucket bucket){
		return bucket.collection("karthik_collection");
	}


}
