package com.ZenPack.controller;

import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.excel.ZenPackExcelExporter;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ExcelRepository;
import com.ZenPack.repository.ZenPackRepository;
import com.ZenPack.service.Impl.ZenPackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ZenPackController {
    @Autowired
    private ZenPackServiceImpl service;

    @Autowired
    private ZenPackRepository zenPackRepository;

    @Autowired
    private ExcelRepository excelRepository;

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headervalue = "attachment; filename=ZenPack_info.xlsx";

        response.setHeader(headerKey, headervalue);
        List<ZenPack> listStudent = excelRepository.findAll();
        ZenPackExcelExporter exp = new ZenPackExcelExporter(listStudent);
        exp.export(response);

    }

    @PostMapping("/save")
    public ResponseEntity<ZenPack> saveZenPack(@RequestBody ZenPack zenPack) {
        return service.saveZenPack(zenPack);
    }

    @PostMapping("/create")
    public ResponseEntity<ZenPackDto> createZenPack(@RequestBody ZenPackDto createDto){
        return service.createZenPack(createDto);
    }

    @GetMapping("/get_all")
    @ResponseStatus(HttpStatus.OK)
    public List<ZenPack> getAllName(){
        return service.getAllZenpack();
    }

    @GetMapping("/sorting")
    public List<ZenPack> findAllByNameLikeAndCategoryIn(String field){
        return zenPackRepository.findAll();
    }
}
