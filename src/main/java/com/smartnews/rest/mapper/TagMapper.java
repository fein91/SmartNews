package com.smartnews.rest.mapper;

import com.google.common.collect.Lists;
import com.smartnews.model.Tag;
import com.smartnews.rest.dto.TagDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagMapper implements RestMapper<TagDto, Tag> {
    @Override
    public TagDto mapToDto(Tag tag) {
        return new TagDto(tag.getId(), tag.getName());
    }

    @Override
    public List<TagDto> mapToDtos(List<Tag> tags) {
        return tags.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Tag mapToEntity(TagDto dto) {
        return null;
    }

    @Override
    public List<Tag> mapToEntities(List<TagDto> dtos) {
        return null;
    }
}
