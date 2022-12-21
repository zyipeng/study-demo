package com.zhouyp.batch.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 周毅鹏
 * @since 2022-06-30
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String objectId;

    private String content;

    private LocalDateTime lastModifiedTime;

    private LocalDateTime createdTime;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Message{" +
            "objectId=" + objectId +
            ", content=" + content +
            ", lastModifiedTime=" + lastModifiedTime +
            ", createdTime=" + createdTime +
        "}";
    }
}
