
package com.example.demo.service;

import com.example.demo.model.DAO;
import lombok.val;

import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;

import java.util.*;

public class AnalysisService {

    public static  DAO object = new DAO();
    private static HTMLService service = new HTMLService();
    public static HashMap<String,Integer> sortMapByValue(HashMap<String, Integer> map){
        List<Map.Entry<String, Integer> > list
                = new LinkedList<Map.Entry<String, Integer> >(
                map.entrySet());
        Collections.sort(
                list,
                (i1,
                 i2) -> i2.getValue().compareTo(i1.getValue()));
        HashMap<String, Integer> temp
                = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    public static HashMap<String, Integer> getTitle() {

        val data = object.getTitlesCount();
        val count = data.select("count(Title)").as(Encoders.STRING()).collectAsList();
        val titles =  data.select("Title").as(Encoders.STRING()).collectAsList();
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        for (int i = 0; i< count.size();i++){
            map.put(titles.get(i), Integer.valueOf(count.get(i)));
        }

        return sortMapByValue(map);

    }

    public static HashMap<String, Integer> getArea() {

        val data = object.getLocationCount();
        val count = data.select("count(Location)").as(Encoders.STRING()).collectAsList();
        val areas =  data.select("Location").as(Encoders.STRING()).collectAsList();
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        for (int i = 0; i< count.size();i++){
            map.put(areas.get(i), Integer.valueOf(count.get(i)));
        }
        return sortMapByValue(map);

    }

    public static HashMap<String, Integer> getCompanyTitle() {

        val data = object.getJobsCompanyCount();

        val count = data.select("count(Title)").as(Encoders.STRING()).collectAsList();
        val jobs =  data.select("Company").as(Encoders.STRING()).collectAsList();
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        for (int i = 0; i< count.size();i++){
            map.put(jobs.get(i), Integer.valueOf(count.get(i)));
        }

        return sortMapByValue(map);

    }


    public static HashMap<Integer,String> getData() {
        val data = object.getData();
        HashMap<Integer,String> map = new HashMap<>();
        int i = 0;
        for (Row r : data.collectAsList()){
            map.put(i,r.json().trim().toString().replace("\"",""));
            i+= 1;
        }
        return map;

    }
    public static Object getStructure(){
        return object.getStructure();
    }

    public static HashMap<Integer,String> getSummary(){
        val summary = object.getSummary();
        HashMap<Integer,String> map = new HashMap<>();
        int i = 0;
        for (Row r : summary.collectAsList()){
            map.put(i,r.json().trim().toString().replace("\"",""));
            i+= 1;
        }
        return map;
    }


    public static HashMap<String, Integer> getSkillsCount() {

        val data = object.getSkillsCount();
        val count = data.select("count(value)").as(Encoders.STRING()).collectAsList();
        val values = data.select("value").as(Encoders.STRING()).collectAsList();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < count.size(); i++) {
            map.put(values.get(i), Integer.valueOf(count.get(i)));
        }
        return sortMapByValue(map);
    }


    public static void getJobsPerCompanyChart(){
        try {
            object.pieChart(4);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }

    public static void getTitlesChart(){
        try{
        object.barChart(6);
    }
        catch (Exception e){
        System.out.println(e.toString());
    }

    }

    public static void getSkillsChart(){
        try{
        object.barChart(8);
    }
        catch (Exception e){
        System.out.println(e.toString());
    }

    }


    public static String getTitleView() {
        val data = object.getTitlesCount();
        return service.displayrows(data.columns(),data.limit(10).collectAsList());
    }
    public static String getAreaView() {
        val data = object.getLocationCount();
        return service.displayrows(data.columns(),data.limit(10).collectAsList());
    }
    public static String getCompanyTitleView() {
        val data = object.getJobsCompanyCount();
        return service.displayrows(data.columns(),data.limit(10).collectAsList());
    }
    public static String getDataView() {
        val data = object.getData();
        return service.displayrows(data.columns(),data.limit(10).collectAsList());
    }
//    public static String  getStructureView(){
//        val data = object.getStructure().toString();
//        return service.displayrows(data.columns(),data.limit(10).collectAsList());
//    }
    public static String  getSummaryView(){
        val data = object.getSummary();
        return service.displayrows(data.columns(),data.limit(10).collectAsList());
    }
    public static String getSkillsCountView() {
        val data = object.getSkillsCount();
        return service.displayrows(data.columns(),data.limit(10).collectAsList());
    }
    }