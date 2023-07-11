package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.AssetStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetStockRepo extends JpaRepository<AssetStock,Integer> {
}
