package com.learn.SpareZone.Security;

import com.learn.SpareZone.Dtos.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	private String token;
	private UserDto userDto;
}
