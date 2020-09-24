package ru.perfumess.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perfumess.model.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
