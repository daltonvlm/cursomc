package com.daltonvlm.cursomc.repositories;

import com.daltonvlm.cursomc.domain.Client;
import com.daltonvlm.cursomc.domain.ClientOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Integer> {
    @Transactional(readOnly = true)
    Page<ClientOrder> findByClient(Client client, Pageable pageRequest);
}
