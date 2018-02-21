package dev5.chermenin.service.api;

import dev5.chermenin.service.dto.impl.GroupDto;

import java.util.List;

public interface GroupService extends GenericService<GroupDto, Long> {
    void incrementCountOfAllUsersInGroup(long id);

    void calculatePassMark(long id);

    List<GroupDto> getGroupsByQualifyLike(String id);
}

