package com.malgn.controller;

import com.malgn.dto.content.ContentCreateRequestDto;
import com.malgn.dto.content.ContentDto;
import com.malgn.dto.content.ContentEditRequestDto;
import com.malgn.dto.content.ContentSummaryDto;
import com.malgn.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    // 컨텐츠 추가
    @PostMapping("/create")
    public ResponseEntity<Long> createContent(@AuthenticationPrincipal User user,
                                              @RequestBody ContentCreateRequestDto contentCreateRequestDto) {
        Long contentId = contentService.addNewContent(user.getUsername(), contentCreateRequestDto);
        return ResponseEntity.ok(contentId);
    }

    // 요약된 컨텐츠 리스트를 반환
    @GetMapping("/list")
    public ResponseEntity<Page<ContentSummaryDto>> getContentList(@PageableDefault(size = 5, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ContentSummaryDto> contentList = contentService.getContentList(pageable);
        return ResponseEntity.ok(contentList);
    }

    // 특정 콘텐츠 단일 상세 조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<ContentDto> getContentDetail(@PathVariable Long id) {
        ContentDto content = contentService.getContent(id);
        return ResponseEntity.ok(content);
    }

    // 특정 콘텐츠 수정
    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> editContent(@AuthenticationPrincipal User user,
                                            @PathVariable Long id,
                                            @RequestBody ContentEditRequestDto contentEditRequestDto) throws AccessDeniedException {
        contentService.updateContent(id, user, contentEditRequestDto);
        return ResponseEntity.noContent().build();
    }

    // 특정 콘텐츠 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContent(@AuthenticationPrincipal User user,
                                              @PathVariable Long id) throws AccessDeniedException {
        contentService.deleteContent(user, id);
        return ResponseEntity.noContent().build();
    }


}
