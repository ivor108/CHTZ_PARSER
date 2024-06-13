package ru.cloudcom.chtz.parser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cloudcom.chtz.parser.entity.ChtzEntity;

public interface ChtzRepository  extends JpaRepository<ChtzEntity, Long> {
}
