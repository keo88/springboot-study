package com.keokim.ncphw.repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.keokim.ncphw.domain.Member;

@Repository
public class JdbcMemberRepository implements MemberRepository {

	private final static RowMapper<Member> memberRowMapper = (rs, rowNum) -> {
		Member member = new Member();
		member.setUserId(rs.getLong("user_id"));
		member.setUsername(rs.getString("username"));
		member.setPassword(rs.getString("password"));
		member.setCreatedAt(rs.getDate("created_at"));
		member.setCreatedAt(rs.getDate("updated_at"));
		return member;
	};
	private final JdbcTemplate jdbcTemplate;

	public JdbcMemberRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Member save(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		String sql = "insert into member (username, password) values (?, ?)";
		jdbcTemplate.update(conn -> {
			PreparedStatement ps = conn.prepareStatement(sql, new String[] {"user_id"});
			ps.setString(1, member.getUsername());
			ps.setString(2, member.getPassword());
			return ps;
		}, keyHolder);

		member.setUserId(Objects.requireNonNull(keyHolder.getKey()).longValue());
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		String sql = "select * from member where user_id = ?";
		Member member;
		try {
			member = jdbcTemplate.queryForObject(sql, memberRowMapper, id);

		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}

		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		String sql = "select * from member where username = ?";
		Member member;
		try {
			member = jdbcTemplate.queryForObject(sql, memberRowMapper, name);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByNameAndPassword(String name, String password) {
		String sql = "select * from member where username = ? and password = ?";
		Member member;
		try {
			member = jdbcTemplate.queryForObject(sql, memberRowMapper, name, password);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
		return Optional.ofNullable(member);
	}

	@Override
	public List<Member> findAll() {
		String sql = "select * from member";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class));
	}
}
