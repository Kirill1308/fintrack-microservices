package com.popov.collaborator.mapper;

import com.popov.collaborator.dto.CollaboratorRequest;
import com.popov.collaborator.dto.CollaboratorResponse;
import com.popov.collaborator.entity.Collaborator;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollaboratorMapper {

    Collaborator toEntity(CollaboratorRequest request);

    CollaboratorResponse toResponse(Collaborator entity);

    List<CollaboratorResponse> toResponse(List<Collaborator> entities);

}
