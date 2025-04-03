package com.keokim.vanilla.dto;

import lombok.Builder;

@Builder
public record SomeRecordResponse(String someString, int someInt) {

}
