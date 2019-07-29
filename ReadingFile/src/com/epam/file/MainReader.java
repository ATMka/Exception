package com.epam.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andrey_Vaganov on 12/5/2016.
 */
public class MainReader {

    /**
     * Формат даты
     */
    private static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";

    /**
     * Форматтер, используется для преобразования строк в даты и обратно
     */
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);


    /**
     * Точка входа в программу
     * @param args
     */
    public static void main(String[] args) {
        try {
            readFile();
        }catch (ExceptionsForThisProject e){
            System.out.println("Моё исключение выдало вот такую забавную ошибку: " + e.getMessage());
        }

    }

    /**
     * Метод для чтения дат из файла
     */
    public static void readFile() throws ExceptionsForThisProject {
        //Открываем потоки на чтение из файла
        try(FileReader reader = new FileReader("file.txt");){
            BufferedReader byfReader = new BufferedReader(reader);
            //Читаем первую строку из файла
            String strDate = byfReader.readLine();
            while(strDate != null) {
                try{
                    //Преобразуем строку в дату
                    Date date = parseDate(strDate);

                    //Выводим дату в консоль в формате dd-mm-yy
                    System.out.printf("%1$td-%1$tm-%1$ty \n", date);

                }catch (ParseException e){
                    System.out.println("Неверный формат даты. Текст ошибки: " + e.getMessage());
                }

                //Читаем следующую строку из файла
                strDate = byfReader.readLine();

            }
        }catch(IOException e) {
            throw new ExceptionsForThisProject("Не могу найти файл. Может его там и нет вовсе.");
        }




    }

    /**
     * Метод преобразует строковое представление даты в класс Date
     * @param strDate строковое представление даты
     * @return
     */
    public static Date parseDate(String strDate) throws ParseException{
        return dateFormatter.parse(strDate);
    }
}
