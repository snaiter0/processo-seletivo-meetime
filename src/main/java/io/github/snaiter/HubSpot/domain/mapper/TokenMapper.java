package io.github.snaiter.HubSpot.domain.mapper;

import io.github.snaiter.HubSpot.domain.model.TokenCache;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.TokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TokenMapper {


    @Mapping(target = "accessToken", expression = "java(io.github.snaiter.HubSpot.aplication.utils.EncoderUtils.decodePassword(token.getAccessToken()))")
    TokenResponse toTokenResponse(TokenCache token) throws Exception;

    @Mapping(target = "accessToken", expression = "java(io.github.snaiter.HubSpot.aplication.utils.EncoderUtils.encodePassword(tokenResponse.getAccessToken(), null))")
    TokenCache toTokenCache(TokenResponse tokenResponse) throws Exception;

}
