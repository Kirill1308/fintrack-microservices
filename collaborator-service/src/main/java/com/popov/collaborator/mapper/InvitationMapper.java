package com.popov.collaborator.mapper;

import com.popov.collaborator.dto.InvitationRequest;
import com.popov.collaborator.dto.InvitationResponse;
import com.popov.collaborator.entity.Invitation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvitationMapper {

    Invitation toEntity(InvitationRequest request);

    InvitationResponse toResponse(Invitation entity);
}
