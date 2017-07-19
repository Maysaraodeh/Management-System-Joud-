package com.ensat.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entities.Report;
import com.ensat.repositories.ReportRepository;

@Service
public class ReportService {

	private ReportRepository reportRepository;

    @Autowired
    public void setProductRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Iterable<Report> listAllReports() {
        return reportRepository.findAll();
    }

    public Report getReportById(Integer id) {
        return reportRepository.findOne(id);
    }
    
    public Iterable<Report> getReportByOrderId(Integer orderId) {
        return reportRepository.findByOrderId(orderId);
    }
    
 
    

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

//    @Override not necessary now
//    public void deleteReport(Integer id) {
//        reportRepository.delete(id);
//    }

}

