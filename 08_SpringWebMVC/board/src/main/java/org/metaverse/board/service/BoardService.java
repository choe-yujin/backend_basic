package org.metaverse.board.service;

import jakarta.transaction.Transactional;
import org.metaverse.board.model.dto.BoardDTO;
import org.metaverse.board.model.entity.Board;
import org.metaverse.board.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private static final Logger logger = LoggerFactory.getLogger(BoardService.class);

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 전체 게시글 조회
    public List<BoardDTO> getAllBoards() {
        logger.info("모든 게시글 조회");
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 게시글 상세 조회
    public BoardDTO getBoardById(Long id) {
        logger.info("게시글 상세 조회 - ID: {}", id);
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다: " + id));
        return convertToDTO(board);
    }

    // 게시글 생성
    @Transactional
    public BoardDTO createBoard(BoardDTO boardDTO) {
        logger.info("게시글 생성 - 제목: {}", boardDTO.getTitle());

        Board board = new Board(boardDTO.getTitle(), boardDTO.getContent());
        Board savedBoard = boardRepository.save(board);

        logger.info("게시글 생성 완료 - ID: {}", savedBoard.getId());
        return convertToDTO(savedBoard);
    }

    // 게시글 수정
    @Transactional
    public BoardDTO updateBoard(Long id, BoardDTO boardDTO) {
        logger.info("게시글 수정 - ID: {}", id);

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다: " + id));

        board.update(boardDTO.getTitle(), boardDTO.getContent());
        Board updatedBoard = boardRepository.save(board);

        logger.info("게시글 수정 완료 - ID: {}", id);
        return convertToDTO(updatedBoard);
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoard(Long id) {
        logger.info("게시글 삭제 - ID: {}", id);

        if (!boardRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다: " + id);
        }

        boardRepository.deleteById(id);
        logger.info("게시글 삭제 완료 - ID: {}", id);
    }

    // Entity를 DTO로 변환
    private BoardDTO convertToDTO(Board board) {
        return new BoardDTO(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getCreatedDate(),
                board.getModifiedDate()
        );
    }
}