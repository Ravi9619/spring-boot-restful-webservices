package com.sprinboot.springbootrestfulwebservices.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sprinboot.springbootrestfulwebservices.dto.UserDto;
import com.sprinboot.springbootrestfulwebservices.entity.User;

@Mapper
public interface AutoUserMapper {
	
	AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class); 
	
	UserDto mapToUserDto(User user);
	
	User mapUser(UserDto userDto);

}
