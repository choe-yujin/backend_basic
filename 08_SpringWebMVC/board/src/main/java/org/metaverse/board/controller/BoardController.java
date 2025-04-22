package org.metaverse.board.controller;

import jakarta.validation.Valid;
import org.metaverse.board.model.dto.BoardDTO;
import org.metaverse.board.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 1. 전체 게시글 조회
    @GetMapping
    public String getAllBoards(Model model) {
        logger.info("전체 게시글 목록 페이지 요청");
        List<BoardDTO> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "boards/list";
    }

    // 2. 게시글 등록 폼
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        logger.info("게시글 작성 폼 페이지 요청");
        model.addAttribute("boardDTO", new BoardDTO());
        return "boards/new";
    }

    // 2. 게시글 등록 처리
    @PostMapping
    public String createBoard(@Valid @ModelAttribute("boardDTO") BoardDTO boardDTO,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        logger.info("게시글 등록 요청 - 제목: {}", boardDTO.getTitle());

        if (result.hasErrors()) {
            logger.warn("게시글 등록 양식 오류 발생");
            return "boards/new";
        }

        try {
            BoardDTO savedBoard = boardService.createBoard(boardDTO);
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 등록되었습니다.");
            return "redirect:/boards/" + savedBoard.getId();
        } catch (IllegalArgumentException e) {
            logger.error("게시글 등록 실패: {}", e.getMessage());
            result.rejectValue("title", "error.boardDTO", e.getMessage());
            return "boards/new";
        }
    }

    // 3. 게시글 상세 조회
    @GetMapping("/{id}")
    public String getBoardById(@PathVariable Long id, Model model) {
        logger.info("게시글 상세 페이지 요청 - ID: {}", id);

        try {
            BoardDTO board = boardService.getBoardById(id);
            model.addAttribute("board", board);
            return "boards/detail";
        } catch (IllegalArgumentException e) {
            logger.error("게시글 상세 조회 실패: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "error/error";
        }
    }

    // 4. 게시글 수정 폼
    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        logger.info("게시글 수정 폼 페이지 요청 - ID: {}", id);

        try {
            BoardDTO board = boardService.getBoardById(id);
            model.addAttribute("boardDTO", board);
            return "boards/edit";
        } catch (IllegalArgumentException e) {
            logger.error("게시글 수정 폼 로드 실패: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "error/error";
        }
    }

    // 4. 게시글 수정 처리
    @PostMapping("/{id}")
    public String updateBoard(@PathVariable Long id,
                              @Valid @ModelAttribute("boardDTO") BoardDTO boardDTO,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        logger.info("게시글 수정 요청 - ID: {}", id);

        if (result.hasErrors()) {
            logger.warn("게시글 수정 양식 오류 발생");
            return "boards/edit";
        }

        try {
            boardDTO.setId(id); // ID 설정
            BoardDTO updatedBoard = boardService.updateBoard(id, boardDTO);
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 수정되었습니다.");
            return "redirect:/boards/" + updatedBoard.getId();
        } catch (IllegalArgumentException e) {
            logger.error("게시글 수정 실패: {}", e.getMessage());
            result.rejectValue("title", "error.boardDTO", e.getMessage());
            return "boards/edit";
        }
    }

    // 5. 게시글 삭제
    @PostMapping("/{id}/delete")
    public String deleteBoard(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        logger.info("게시글 삭제 요청 - ID: {}", id);

        try {
            boardService.deleteBoard(id);
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
            return "redirect:/boards";
        } catch (IllegalArgumentException e) {
            logger.error("게시글 삭제 실패: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/boards";
        }
    }

    // 예외 처리
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        logger.error("컨트롤러 예외 발생: {}", e.getMessage());
        model.addAttribute("error", "요청을 처리하는 중 오류가 발생했습니다: " + e.getMessage());
        return "error/error";
    }
}