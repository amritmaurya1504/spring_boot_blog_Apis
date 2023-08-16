package com.rajamrit.SpringBoot_Blog_app.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private int Id;
    private String categoryTitle;
    private String categoryDesc;

}
