package com.traino.view;
import com.traino.app.interfaces.Schedulable;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.gembox.spreadsheet.*;

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

        int mon=1;
        int tue=1;
        int wed=1;
        int thu=1;
        int fri=1;
        int sat=1;
        int sun=1;

        // Add weekday's (Column headers)
        Object[][] data = {
                { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }
        };

        ExcelFont font = new ExcelFont();
        font.setWeight(700);

        for (int i = 0; i < data.length; i++)
            for (int j = 0; j < data[0].length; j++){
                worksheet.getCell(i, j).setValue(data[i][j]);
                worksheet.getCell(i, j).getStyle().setFont(font);
            }


        for(Schedulable item : schedulableList){
            switch(item.getDay()){
                case MONDAY:
                    worksheet.getCell(mon,0).setValue(item.getScheduleInfo());
                    mon++;
                break;
                case TUESDAY:
                    worksheet.getCell(tue,1).setValue(item.getScheduleInfo());
                    tue++;
                break;
                case WEDNESDAY:
                    worksheet.getCell(wed,2).setValue(item.getScheduleInfo());
                    wed++;
                break;
                case THURSDAY:
                    worksheet.getCell(thu,3).setValue(item.getScheduleInfo());
                    thu++;
                break;
                case FRIDAY:
                    worksheet.getCell(fri,4).setValue(item.getScheduleInfo());
                    fri++;
                break;
                case SATURDAY:
                    worksheet.getCell(sat,5).setValue(item.getScheduleInfo());
                    sat++;
                break;
                case SUNDAY:
                    worksheet.getCell(sun,6).setValue(item.getScheduleInfo());
                    sun++;
                break;
            }
        }

        // Set column widths.
        worksheet.getColumn(0).setWidth(200, LengthUnit.PIXEL);
        worksheet.getColumn(1).setWidth(200, LengthUnit.PIXEL);
        worksheet.getColumn(2).setWidth(200, LengthUnit.PIXEL);
        worksheet.getColumn(3).setWidth(200, LengthUnit.PIXEL);
        worksheet.getColumn(4).setWidth(200, LengthUnit.PIXEL);
        worksheet.getColumn(5).setWidth(200, LengthUnit.PIXEL);
        worksheet.getColumn(6).setWidth(200, LengthUnit.PIXEL);

        workbook.save("Schedule.xlsx");

        Desktop desktop = Desktop.getDesktop();
        try{
            desktop.open(new File("Schedule.xlsx"));
        }catch(Exception e){
            throw new IOException(e);
        }
    }
}
