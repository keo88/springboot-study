package com.keokim.playground.base.alias.item;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Album extends Item {
	private String artist;
	private String etc;
}
