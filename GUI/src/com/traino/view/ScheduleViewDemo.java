package com.traino.view;

import com.traino.app.Weekday;
import com.traino.app.Workout;
import com.traino.app.interfaces.Actionable;
import com.traino.app.interfaces.Schedulable;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gembox.spreadsheet.*;
import com.gembox.spreadsheet.tables.*;

public class ScheduleViewDemo {

    private Scanner scanner;

    public ScheduleViewDemo(){
        scanner = new Scanner(System.in);
    }

    public void showSchedule(List<Schedulable> schedulableList) throws IOException {
        // If using Professional version, put your serial key below.
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");

        ExcelFile workbook = new ExcelFile();
        ExcelWorksheet worksheet = workbook.addWorksheet("Schedule");

        //TODO: reformat schedule items into 2d-array ordered by Weekday

        List<Schedulable> mondayItems = new ArrayList<>();
        List<Schedulable> tuesdayItems = new ArrayList<>();
        List<Schedulable> wednesdayItems = new ArrayList<>();
        List<Schedulable> thursdayItems = new ArrayList<>();
        List<Schedulable> fridayItems = new ArrayList<>();
        List<Schedulable> saturdayItems = new ArrayList<>();
        List<Schedulable> sundayItems = new ArrayList<>();

        int maxItems = 0;

        for(Schedulable item: schedulableList){
            switch(item.getDay()){
                case MONDAY:
                    mondayItems.add(item);
                    maxItems = mondayItems.size() > maxItems ? mondayItems.size() : maxItems;
                    break;
                case TUESDAY:
                    tuesdayItems.add(item);
                    maxItems = tuesdayItems.size() > maxItems ? tuesdayItems.size() : maxItems;
                    break;
                case WEDNESDAY:
                    wednesdayItems.add(item);
                    maxItems = tuesdayItems.size() > maxItems ? tuesdayItems.size() : maxItems;
                    break;
                case THURSDAY:
                    thursdayItems.add(item);
                    maxItems = tuesdayItems.size() > maxItems ? tuesdayItems.size() : maxItems;
                    break;
                case FRIDAY:
                    fridayItems.add(item);
                    maxItems = tuesdayItems.size() > maxItems ? tuesdayItems.size() : maxItems;
                    break;
                case SATURDAY:
                    saturdayItems.add(item);
                    maxItems = tuesdayItems.size() > maxItems ? tuesdayItems.size() : maxItems;
                    break;
                case SUNDAY:
                    sundayItems.add(item);
                    maxItems = tuesdayItems.size() > maxItems ? tuesdayItems.size() : maxItems;
                    break;
            }
        }

        Schedulable dummy = new Workout("",null,null);

        while(mondayItems.size() < maxItems){
            mondayItems.add(dummy);
        }
        while(tuesdayItems.size() < maxItems){
            tuesdayItems.add(dummy);
        }
        while(wednesdayItems.size() < maxItems){
            wednesdayItems.add(dummy);
        }
        while(thursdayItems.size() < maxItems){
            thursdayItems.add(dummy);
        }
        while(fridayItems.size() < maxItems){
            fridayItems.add(dummy);
        }
        while(saturdayItems.size() < maxItems){
            saturdayItems.add(dummy);
        }
        while(sundayItems.size() < maxItems){
            sundayItems.add(dummy);
        }

        System.out.println(mondayItems);

        // Add some data.
        Object[][] data = {
                { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" },
                {mondayItems.get(0).getScheduleInfo(),tuesdayItems.get(0).getScheduleInfo(),"","","","",""}
        };

        for (int i = 0; i < data.length; i++)
            for (int j = 0; j < data[0].length; j++)
                worksheet.getCell(i, j).setValue(data[i][j]);

        // Set column widths.
        worksheet.getColumn(0).setWidth(140, LengthUnit.PIXEL);
        worksheet.getColumn(1).setWidth(140, LengthUnit.PIXEL);
        worksheet.getColumn(2).setWidth(140, LengthUnit.PIXEL);
        worksheet.getColumn(3).setWidth(140, LengthUnit.PIXEL);
        worksheet.getColumn(4).setWidth(140, LengthUnit.PIXEL);
        worksheet.getColumn(5).setWidth(140, LengthUnit.PIXEL);
        worksheet.getColumn(6).setWidth(140, LengthUnit.PIXEL);
        worksheet.getColumn(7).setWidth(140, LengthUnit.PIXEL);

        worksheet.getColumn(2).getStyle().setNumberFormat("\"$\"#,##0.00");
        worksheet.getColumn(3).getStyle().setNumberFormat("\"$\"#,##0.00");

        // Create table and enable totals row.
        Table table = worksheet.addTable("Headers", "A1:G10", true);
        //table.setHasTotalsRow(true);

//        // Add new column.
//        TableColumn column = table.addColumn();
//        column.setName("Total");
//
//        // Populate column.
//        for (ExcelCell cell : column.getDataRange())
//            cell.setFormula("=Table1[Hours] * Table1[Price]");
//
//        // Set totals row function for newly added column and calculate it.
//        column.setTotalsRowFunction(TotalsRowFunction.SUM);
//        column.getRange().calculate();

        // Set table style.
        table.setBuiltInStyle(BuiltInTableStyleName.TABLE_STYLE_MEDIUM_2);

        workbook.save("Schedule.xlsx");

        Desktop desktop = Desktop.getDesktop();
        try{
            desktop.open(new File("Schedule.xlsx"));
        }catch(Exception e){
            throw new IOException(e);
        }
    }
}
