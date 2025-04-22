package org.metaverse.board.repository;

import org.metaverse.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // JpaRepository를 상속받으면 기본 CRUD 메서드가 자동으로 제공됨

    // 제목으로 게시글 찾기
    Optional<Board> findByTitle(String title);

    // 제목으로 게시글 찾기 (ID 제외)
    Optional<Board> findByTitleAndIdNot(String title, Long id);
}