package com.codesoom.assignment.storage;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ListStorageEntity {

    public ListStorageEntity(Long id) {
        this.id = id;
    }

    @Setter
    private Long id;
}
