package com.zum.repository;

import com.zum.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by joeylee on 2017-04-01.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {


    Image findByBoardBoardId(Long boardId);

}
