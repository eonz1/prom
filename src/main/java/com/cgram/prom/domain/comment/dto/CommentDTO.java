package com.cgram.prom.domain.comment.dto;

import com.cgram.prom.domain.feed.domain.Feed;
import com.cgram.prom.domain.profile.domain.Profile;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDTO {

    private UUID id;

    private UUID feedId;

    private UUID userId;

    private UUID profileImageId;

    private String profileImagePath;

    private String userEmail;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private boolean isPresent;

    private int likesCount;
}
