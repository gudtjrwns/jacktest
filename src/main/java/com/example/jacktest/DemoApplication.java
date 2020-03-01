package com.example.jacktest;

import com.example.jacktest.entities.Notice;
import com.example.jacktest.repositories.NoticeRepository;
import com.example.jacktest.utils.ToolsUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		NoticeRepository noticeRepository = context.getBean(NoticeRepository.class);
		ToolsUtil toolsUtil = context.getBean(ToolsUtil.class);

		noticeRepository.save(new Notice("1번째 제목", "1번째 내용", "홍길동", 0, 0, "NONE", "NONE", toolsUtil.currentDateInTimestamp(), toolsUtil.currentDateInTimestamp(), null));
		noticeRepository.save(new Notice("2번째 제목", "2번째 내용", "임꺽정", 0, 0, "NONE", "NONE", toolsUtil.currentDateInTimestamp(), toolsUtil.currentDateInTimestamp(), null));
		noticeRepository.save(new Notice("3번째 제목", "3번째 내용", "신동엽", 0, 0, "NONE", "NONE", toolsUtil.currentDateInTimestamp(), toolsUtil.currentDateInTimestamp(), null));
		noticeRepository.save(new Notice("4번째 제목", "4번째 내용", "임창정", 0, 0, "NONE", "NONE", toolsUtil.currentDateInTimestamp(), toolsUtil.currentDateInTimestamp(), null));
		noticeRepository.save(new Notice("5번째 제목", "5번째 내용", "김장훈", 0, 0, "NONE", "NONE", toolsUtil.currentDateInTimestamp(), toolsUtil.currentDateInTimestamp(), null));
	}

}
