package com.ensat.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.ensat.entities.Report;

public interface ReportRepository extends CrudRepository<Report, Integer> {

	Iterable<Report> findByOrderId(Integer orderId);

}