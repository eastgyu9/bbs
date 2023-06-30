package com.board.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO { 
	private Long id;
	private String memberEmail;
	private String memberPassword;
	private String memberName; 
	private int memberAge;
	private String memberPhone;
	private Timestamp createAt;
	private Timestamp updateAt;
}
