package com.daltonvlm.cursomc.repositories;

import com.daltonvlm.cursomc.domain.ClientOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientOrderItemRepository extends JpaRepository<ClientOrderItem, Integer> {
}
