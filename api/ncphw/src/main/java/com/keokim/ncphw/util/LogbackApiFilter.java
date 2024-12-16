package com.keokim.ncphw.util;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogbackApiFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {

        if (iLoggingEvent.getLoggerName().equals("com.keokim.ncphw.service.LogService")) {
            return FilterReply.ACCEPT;
        }
        return FilterReply.DENY;
    }
}
