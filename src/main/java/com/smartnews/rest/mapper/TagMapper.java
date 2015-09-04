package com.smartnews.rest.mapper;

import com.smartnews.model.Tag;
import com.smartnews.rest.dto.TagDto;
import org.springframework.stereotype.Component;

@Component
public class TagMapper implements RestMapper<TagDto, Tag> {
    @Override
    public TagDto mapToDto(Tag tag) {
        return new TagDto(tag.getId(), tag.getName());
    }

    @Override
    public Tag mapToEntity(TagDto dto) {
        return null;
    }
}
