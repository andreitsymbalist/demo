package com.pioneerpixel.demo.adapter.web.mapper;

import com.pioneerpixel.demo.adapter.web.dto.GetUserRequest;
import com.pioneerpixel.demo.adapter.web.dto.GetUserResponse;
import com.pioneerpixel.demo.business.dto.UserFilteringAndPagingParams;
import com.pioneerpixel.demo.domain.User;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Pageable;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract UserFilteringAndPagingParams mapToDomain(GetUserRequest params, Pageable pageable);

    public abstract GetUserResponse mapToDto(User user);
}
