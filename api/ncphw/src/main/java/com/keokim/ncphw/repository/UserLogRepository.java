package com.keokim.ncphw.repository;

import com.keokim.ncphw.domain.LogMessage;

public interface UserLogRepository {
    LogMessage save(LogMessage message);
}
