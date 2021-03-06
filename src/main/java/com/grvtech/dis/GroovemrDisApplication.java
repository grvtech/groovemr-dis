package com.grvtech.dis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
public class GroovemrDisApplication {
	private static final Logger logger = LoggerFactory.getLogger(GroovemrDisApplication.class);

	@Bean(name = "processExecutor")
	public TaskExecutor workExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix("GRVDIS-");
		threadPoolTaskExecutor.setCorePoolSize(3);
		threadPoolTaskExecutor.setMaxPoolSize(3);
		threadPoolTaskExecutor.setQueueCapacity(600);
		threadPoolTaskExecutor.afterPropertiesSet();
		logger.info("ThreadPoolTaskExecutor set");
		return threadPoolTaskExecutor;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(GroovemrDisApplication.class, args);
		String license = ctx.getBean("license").toString().replaceAll("\"", "");
		System.out.println("------------------------------------------------------");
		System.out.println("licence is " + ctx.getBean("license"));
		System.out.println("------------------------------------------------------");

		if (license.equals("")) {
			System.out.println("------------------------------------------------------");
			System.out.println("licence is empty");
			System.out.println("------------------------------------------------------");
			exitApplication(ctx);

		}

	}

	public static void exitApplication(ConfigurableApplicationContext ctx) {
		int exitCode = SpringApplication.exit(ctx, new ExitCodeGenerator() {
			@Override
			public int getExitCode() {
				// TODO Auto-generated method stub
				return 0;
			}
		});

		System.exit(exitCode);
	}

}
