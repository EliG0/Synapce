//package ru.lgtu.app.service;
//
//import com.itextpdf.kernel.pdf.*;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.*;
//import com.itextpdf.io.image.ImageDataFactory;
//import com.itextpdf.layout.element.Image;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//import com.itextpdf.kernel.font.PdfFont;
//import com.itextpdf.kernel.font.PdfFontFactory;
//import ru.lgtu.app.model.Mod;
//
//public class ReportService {
//
//    public void generateSingleReport(Mod train)
//            throws FileNotFoundException {
//
//        PdfWriter writer = new PdfWriter("single_report.pdf");
//        PdfDocument pdf = new PdfDocument(writer);
//        Document document = new Document(pdf);
//
//        document.setFont(getFont());
//        document.add(new Paragraph("Отчёт о поезде"));
//        document.add(new Paragraph("Название: " + train.getName()));
//        document.add(new Paragraph("Владелец: " + train.getOwner()));
//        document.add(new Paragraph("Тип: " + train.getType()));
//        document.add(new Paragraph("Вагонов: " + train.getCarCount()));
//        document.add(new Paragraph("Рейс: " + train.getFlightNumber()));
//        document.add(new Paragraph("Дата: " + train.getDate()));
//        document.add(new Paragraph("Время: " + train.getTime()));
//        if (train.getImage() != null && !train.getImage().isBlank()) {
//
//            byte[] imageBytes =
//                    ImageService.decodeImage(train.getImage());
//
//            Image image = new Image(
//                    ImageDataFactory.create(imageBytes)
//            );
//
//            image.setWidth(400);
//
//            document.add(image);
//        }
//
//        document.close();
//    }
//
//    public void generateListReport(List<Mod> trains)
//            throws FileNotFoundException {
//
//        PdfWriter writer = new PdfWriter("list_report.pdf");
//        PdfDocument pdf = new PdfDocument(writer);
//        Document document = new Document(pdf);
//
//        document.setFont(getFont());
//        document.add(new Paragraph("Отчёт по поездам"));
//
//        Table table = new Table(4);
//
//        table.addCell("Название");
//        table.addCell("Тип");
//        table.addCell("Вагонов");
//        table.addCell("Рейс");
//
//        int totalCars = 0;
//
//        for (Mod train : trains) {
//            table.addCell(train.getName());
//            table.addCell(train.getType().toString());
//            table.addCell(String.valueOf(train.getCarCount()));
//            table.addCell(train.getFlightNumber());
//
//            totalCars += train.getCarCount();
//        }
//
//        table.addCell("ИТОГ");
//        table.addCell("-");
//        table.addCell(String.valueOf(totalCars));
//        table.addCell("-");
//
//        document.add(table);
//
//        document.close();
//    }
//
//    private PdfFont getFont() {
//        try {
//            return PdfFontFactory.createFont(
//                    "C:/Windows/Fonts/arial.ttf",
//                    "Identity-H"
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}