package com.malgn.dto.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentDto {
    private Long id;
    private String title;
    private String content;

    @JsonProperty("view_count")
    private Long viewCount;

    @JsonProperty("created_date")
    private LocalDateTime createdDate;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("last_modified_date")
    private LocalDateTime lastModifiedDate;

    @JsonProperty("last_modified_by")
    private String lastModifiedBy;
}
