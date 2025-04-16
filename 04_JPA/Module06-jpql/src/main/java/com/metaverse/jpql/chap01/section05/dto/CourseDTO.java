package com.metaverse.jpql.chap01.section05.dto;

public class CourseDTO {
    private int courseId;
    private String title;
    private long lessonCount;

    public CourseDTO(int courseId, String title, long lessonCount) {
        this.courseId = courseId;
        this.title = title;
        this.lessonCount = lessonCount;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseId=" + courseId +
                ", title='" + title + '\'' +
                ", lessonCount=" + lessonCount +
                '}';
    }
}
