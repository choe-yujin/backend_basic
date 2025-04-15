package com.devyujin.jdbc.model;

import java.time.LocalDateTime;

public class Lesson {
    private int lessonId;
    private int courseId;
    private String title;
    private String content;
    private String videoUrl ;
    private LocalDateTime createdAt;

    public Lesson(int lessonId, int courseId, String title, String content, String videoUrl, LocalDateTime createdAt) {
        this.lessonId = lessonId;
        this.courseId = courseId;
        this.title = title;
        this.content = content;
        this.videoUrl = videoUrl;
        this.createdAt = createdAt;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Lessons{" +
                "lessonId=" + lessonId +
                ", courseId=" + courseId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}