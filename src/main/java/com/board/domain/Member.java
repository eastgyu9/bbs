package com.board.domain;

import lombok.Data;

@Data
public class Member {
	private Long id;
	private String memberEmail;
	private String memberPassword;
	private String memberName;
	private int memberAge;
	private String memberPhone;
}
