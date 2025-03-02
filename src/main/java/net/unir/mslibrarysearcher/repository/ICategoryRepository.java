package net.unir.mslibrarysearcher.repository;

import net.unir.mslibrarysearcher.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
