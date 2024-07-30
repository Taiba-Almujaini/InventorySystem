package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import jakarta.mail.internet.MimeMessage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class ReportService {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    JavaMailSender mailSender;

    public void createInventoryReport() throws Exception {
        List<InventoryDTO> inventoryDTOListDTOList = inventoryService.getInventory();
        UUID uuid = UUID.randomUUID();
        String timeStamp = DateHelperUtils.formatDate(new Date(), "yyyy-MM-dd_HH-mm-ss");

        String pathToSave = "C:\\Users\\TRA\\Desktop\\";

        File templateFile = ResourceUtils.getFile("C:\\Users\\TRA\\Desktop\\TaibasInventorySystem\\InventorySystem\\src\\main\\resources\\Inventory_Report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(templateFile.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(inventoryDTOListDTOList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,new HashMap<>(),dataSource);
        String fileName =  pathToSave + "Report_" + timeStamp + ".pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint,fileName );
        System.out.println("Report is printed: "+ fileName);

        sendReportViaEmail(fileName);
    }
    public void sendReportViaEmail(String filePath) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("");
        helper.setSubject("Inventory Report");
        helper.setText("Please find the attached inventory report.");

        File file = new File(filePath);
        helper.addAttachment(file.getName(), file);

        mailSender.send(message);
        System.out.println("Email sent with attachment: " + filePath);
    }


}
