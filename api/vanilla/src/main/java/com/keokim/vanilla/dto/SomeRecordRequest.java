package com.keokim.vanilla.dto;

public record SomeRecordRequest(int someInt) {

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SomeRecordRequest(int anInt))) return false;

		return someInt == anInt;
	}
}
