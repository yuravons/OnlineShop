package online.patologia.shop.repo;

import online.patologia.shop.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbacksRepo extends JpaRepository<Feedback,Long> {
}
