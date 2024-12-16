package com.keokim.ncphw.service;

import com.keokim.ncphw.domain.LogMessage;
import com.keokim.ncphw.domain.Member;
import com.keokim.ncphw.repository.UserLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final UserLogRepository userLogRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public LogService(UserLogRepository userLogRepository) {
        this.userLogRepository = userLogRepository;
    }

    public void logApi(String apiPath, Member user) {
        LogMessage logMessage = new LogMessage();
        logMessage.setMessage(apiPath);
        logMessage.setUserId(user.getUserId());

        userLogRepository.save(logMessage);
        log.info("user {} called api {}", user.getUsername(), apiPath);
    }
}
