package com.smartnews.rest.dto;

public class TagDto extends NamedDto {

    //dummy constructor for json parsing
    public TagDto() {
        super(0, null);
    }

    public TagDto(long id, String name) {
        super(id, name);
    }
}
