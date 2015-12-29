package com.geekcap.javaworld;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		String[] springConfig  =
            {    
                "applicationContext.xml" 
            };
        
        ApplicationContext context =  new ClassPathXmlApplicationContext(springConfig);
        
        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("simpleFileImportJob");
        try {

            JobExecution execution = jobLauncher.run(job, 
            	new JobParametersBuilder() .addString("inputFile", "D:\\JavaProject\\springbatchdemo\\src\\test\\resources\\sample.csv").toJobParameters());
            System.out.println("Exit Status : " + execution.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done");
	}
}
