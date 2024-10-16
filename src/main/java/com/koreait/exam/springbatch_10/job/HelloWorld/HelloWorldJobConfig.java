package com.koreait.exam.springbatch_10.job.HelloWorld;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HelloWorldJobConfig {
    private final JobBuilderFactory jobBuilderFactory; // Job을 빌드해줄 빌더를 만들어준다.
    private final StepBuilderFactory stepBuilderFactory; // Step을 빌드해줄 빌더를 만들어준다.

    @Bean
    public Job helloWorldJob() {
        return jobBuilderFactory.get("helloWorldJob")
                .incrementer(new RunIdIncrementer()) // 강제로 메번 다른 ID를 실행할 때 마다 파라미터로 부여
                .start(helloWorldStep1()).build();
    }

    @Bean
    @JobScope
    public Step helloWorldStep1() {
        return stepBuilderFactory.get("helloWorldStep1").tasklet(helloWorldTasklet()).build();
    }

    @Bean
    @StepScope
    public Tasklet helloWorldTasklet() {
        return (stepContribution, chunkContext) -> {
            System.out.println("헬로월드!!!");
            return RepeatStatus.FINISHED;
        };
    }
    // Job : 여러가지의 Step들로 구성
}

