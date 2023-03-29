package com.blom.notifysystems.controller;

import com.blom.notifysystems.service.ReadExcelFromDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/readexcelfromdirectory")
public class ReadExcelFromDirectoryController {
@Autowired
ReadExcelFromDirectoryService readExcelFromDirectoryService;

    @GetMapping("/start")
    public void startReadExcelFromDirectoryService() {
        readExcelFromDirectoryService.startReadExcelFromDirectoryService();
    }


    @GetMapping("/stop")
    public void stopReadExcelFromDirectoryService() {
        readExcelFromDirectoryService.stopReadExcelFromDirectoryService();
    }





}
