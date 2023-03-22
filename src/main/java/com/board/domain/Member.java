package com.board.domain;

import lombok.Data;

@Data
public class Member {
	private Long id;
	private String memberName;
	private String memberEmail;
	private String memberPassword;
	private String memberPhone;
	private int memberAge;
}
