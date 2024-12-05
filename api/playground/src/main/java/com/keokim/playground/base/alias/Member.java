package com.keokim.playground.base.alias;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.keokim.playground.base.dto.MemberForm;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@Table(name = "member", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Embedded
	private Address address;

	@Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	@Column(name = "last_login_at")
	private LocalDateTime lastLoginAt;

	@OneToMany(mappedBy = "member")
	private List<PurchaseOrder> purchaseOrders = new ArrayList<>();

	public static Member of(MemberForm memberForm) {
		return Member.builder()
			.name(memberForm.getName())
			.password(memberForm.getPassword())
			.address(new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode()))
			.build();
	}

}
