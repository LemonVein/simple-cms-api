package com.malgn.service;

import com.malgn.domain.Contents;
import com.malgn.domain.ContentsRepository;
import com.malgn.dto.content.ContentCreateRequestDto;
import com.malgn.dto.content.ContentDto;
import com.malgn.dto.content.ContentEditRequestDto;
import com.malgn.dto.content.ContentSummaryDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentsRepository contentsRepository;

    // 콘텐츠 추가 (콘텐츠의 아이디 반환)
    @Transactional
    public Long addNewContent(String username, ContentCreateRequestDto contentCreateRequestDto) {
        if (contentCreateRequestDto.getTitle() == null || contentCreateRequestDto.getTitle().isEmpty()) {
            throw new IllegalArgumentException("제목을 입력해주세요");
        }
        if (contentCreateRequestDto.getDescription() == null || contentCreateRequestDto.getDescription().isEmpty()) {
            throw new IllegalArgumentException("내용을 입력해주세요");
        }

        Contents contents = Contents.builder()
                .createdBy(username)
                .title(contentCreateRequestDto.getTitle())
                .description(contentCreateRequestDto.getDescription())
                .viewCount(0L)
                .createdDate(LocalDateTime.now())
                .build();

        contentsRepository.save(contents);

        return contents.getId();
    }

    // 콘텐츠 리스트 리턴
    @Transactional
    public Page<ContentSummaryDto> getContentList(Pageable pageable) {
        Page<Contents> contents = contentsRepository.findAll(pageable);

        return contents.map(content -> ContentSummaryDto.builder()
                .id(content.getId())
                .title(content.getTitle())
                .createdBy(content.getCreatedBy())
                .createdDate(content.getCreatedDate())
                .build());
    }

    // 단일 콘텐츠 상세
    @Transactional
    public ContentDto getContent(Long id) {
        Contents content = contentsRepository.findById(id).orElse(null);
        if (content == null) {
            throw new EntityNotFoundException("콘텐츠를 찾을 수 없습니다.");
        }
        return ContentDto.builder()
                .id(content.getId())
                .title(content.getTitle())
                .content(content.getDescription())
                .createdBy(content.getCreatedBy())
                .createdDate(content.getCreatedDate())
                .viewCount(content.getViewCount())
                .createdDate(content.getCreatedDate())
                .lastModifiedBy(content.getLastModifiedBy())
                .lastModifiedDate(content.getLastModifiedDate())
                .build();
    }

    // 특정 콘텐츠 수정
    @Transactional
    public void updateContent(Long id, User user, ContentEditRequestDto contentEditRequestDto) throws AccessDeniedException {
        Contents content = contentsRepository.findById(id).orElse(null);
        if (content == null) {
            throw new EntityNotFoundException("콘텐츠를 찾을 수 없습니다.");

        }
        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !content.getCreatedBy().equals(user.getUsername())) {
            throw new AccessDeniedException("콘텐츠를 수정할 권한이 없습니다.");
        }

        content.setTitle(contentEditRequestDto.getTitle());
        content.setDescription(contentEditRequestDto.getDescription());
        content.setLastModifiedBy(user.getUsername());
        content.setLastModifiedDate(LocalDateTime.now());
        contentsRepository.save(content);
    }

    // 콘텐츠 삭제
    @Transactional
    public void deleteContent(User user, Long id) throws AccessDeniedException {
        Contents content = contentsRepository.findById(id).orElse(null);
        if (content == null) {
            throw new EntityNotFoundException("콘텐츠를 찾을 수 없습니다.");
        }

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !content.getCreatedBy().equals(user.getUsername())) {
            throw new AccessDeniedException("콘텐츠를 수정할 권한이 없습니다.");
        }

        contentsRepository.delete(content);
    }
}
