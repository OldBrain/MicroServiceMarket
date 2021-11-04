package ru.geekbrains.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.core.model.Category;
import ru.geekbrains.market.core.model.Comments;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {


    @Query("select c from Comments c where c.productId = :productId")
    public List<Comments> findAllByProductIdEquals(Long productId);

    @Query("select c from Comments c where (c.username = :username and c.productId =:productId)")
    public Optional<Comments> findQueryByUsernameAndProductId(String username,Long productId);

//    public List<Comments> findFirstByUsernameEqualsAndProductIdEquals(String username,Long productId);


}
