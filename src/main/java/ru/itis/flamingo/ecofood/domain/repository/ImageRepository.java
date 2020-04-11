package ru.itis.flamingo.ecofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flamingo.ecofood.domain.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
