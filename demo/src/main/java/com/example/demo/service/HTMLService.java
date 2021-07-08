package com.example.demo.service;

import org.apache.spark.sql.Row;

import java.util.List;

public class HTMLService {
    private static HTMLTableBuilder builder ;

    public static String displayrows(String []head, List<Row> ls){

        builder=new HTMLTableBuilder(null,true,3,head.length);
        builder.addTableHeader(head);
        for (Row r : ls) {
            String[] s = r.toString().replace("[","").replace("]","")
                    .split(",", head.length);
            builder.addRowValues(s);

        }
        return builder.build();


    }
}
