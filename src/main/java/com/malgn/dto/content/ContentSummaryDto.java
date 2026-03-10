package com.malgn.dto.content;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentSummaryDto {
    private Long id;
    private String title;
    private String createdBy;
    private LocalDateTime createdDate;
}
