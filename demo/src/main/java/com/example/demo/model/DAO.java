package com.example.demo.model;
import au.com.bytecode.opencsv.CSVReader;
import lombok.val;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.spark.sql.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.types.StructType;
import org.knowm.xchart.*;
import org.knowm.xchart.internal.chartpart.Chart;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.when;

public class  DAO {
    public static void print(String s) {
        System.out.println(s);
    }

    public static SparkSession spark;
    public static Dataset<Row> dataframe;
    public static Dataset<Row> cleanedDataframe;
    static String FILEPATH = "dataset/Wuzzuf_Jobs.csv";

    public DAO() {
        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);
        spark = SparkSession
                .builder()
                .appName("Java Spark Project")
                .master("local[1]")
                .getOrCreate();

        dataframe = spark.read().format("csv").option("header", "true").load(FILEPATH);
        print("data count: " + dataframe.count());
        dataframe.show(false);
        cleanedDataframe = removeDuplicates();
        cleanedDataframe = removeNulls(cleanedDataframe);
        cleanedDataframe.createOrReplaceTempView("myTable");
        print("cleaned count: " + cleanedDataframe.count());
        cleanedDataframe.show();
    }

    public Dataset<Row> removeNulls(Dataset<Row> df) {
        df.na().drop();
        return df.withColumn("YearsExp", when(col("YearsExp").equalTo("null Yrs of Exp"),
                "0 Yrs of Exp").otherwise(col("YearsExp")));
    }

    public Dataset<Row> removeDuplicates() {
        val distinctDF = dataframe.distinct();
        print("Distinct count: " + distinctDF.count());
        distinctDF.show(false);
        return distinctDF;
    }

    public Dataset<Row> getSummary() {
        print("summary of data is been printed successfully");
        return dataframe.summary();
    }

    public Dataset<Row> getTitlesCount() {

        val sqlDF = spark.sql("SELECT COUNT(Title),Title FROM myTable GROUP BY(Title) ORDER BY COUNT(Title) DESC LIMIT 10;");
        sqlDF.coalesce(1).write().option("header", true).mode(SaveMode.Overwrite).format("com.databricks.spark.csv").save("output/Q06");
        return sqlDF;

    }

    public Dataset<Row> getJobsCompanyCount(){
        Dataset<Row> sqlDF = spark.sql("SELECT COUNT(Title),Company FROM myTable GROUP BY Company ORDER BY COUNT(Title) DESC;");
        sqlDF.coalesce(1).write().option("header", true).mode(SaveMode.Overwrite).format("com.databricks.spark.csv").save("output/Q04");
        return sqlDF;
    }

    public Dataset<Row> getLocationCount(){
        Dataset<Row> sqlDF = spark.sql("SELECT COUNT(Location),Location FROM myTable GROUP BY(Location) ORDER BY COUNT(Location) DESC;");
        sqlDF.coalesce(1).write().option("header", true).mode(SaveMode.Overwrite).format("com.databricks.spark.csv").save("output/Q08");
        return sqlDF;
    }
    public Dataset<Row> getData(){
        return spark.sql("SELECT * FROM myTable LIMIT 10 ");
    }
    public StructType getStructure(){return dataframe.schema();};

    public Dataset<Row> getSkillsCount(){
        val sqlDF = spark.sql("SELECT Skills FROM myTable");
        sqlDF.printSchema();
        //List of records
        List<String> records = sqlDF.as(Encoders.STRING()).collectAsList();
        //detailed List
        ArrayList<String> DetailedRecords = new ArrayList<String>();
        for (int i = 0; i< records.size();i++)
            DetailedRecords.addAll(Arrays.asList(records.get(i).split(",")));
        //Clean Spaces
        for (int i = 0; i< DetailedRecords.size();i++)
            DetailedRecords.set(i,DetailedRecords.get(i).trim());
        //Convert List to Dataset<String>
        Dataset<String> recordsDs = spark.createDataset(DetailedRecords, Encoders.STRING());
        recordsDs.createOrReplaceTempView("myTable2");
        Dataset<Row> recordsDsSqlDF = spark.sql("SELECT COUNT(value),value FROM myTable2 GROUP BY(value) ORDER BY COUNT(value) DESC;");
        recordsDsSqlDF.coalesce(1).write().option("header", true).mode(SaveMode.Overwrite).format("com.databricks.spark.csv").save("output/Q10");
        return recordsDsSqlDF;
    }

    public static String GetCSVFileNames(int questionNumber)
    {
        File directory = new File("./output/Q0"+Integer.toString(questionNumber));
        File[] files = directory.listFiles();
        files = directory.listFiles((FileFilter) new PrefixFileFilter("part"));
        for (File f: files)
        {
            return f.getName();
        }
        return null;

    }

    public static void saveChart(Chart chart, int questionNum) throws IOException {

        BitmapEncoder.saveBitmap(chart, "src/main/resources/static/images/Q0"+Integer.toString(questionNum), BitmapEncoder.BitmapFormat.PNG);
    }
    public static void pieChart(int questionNumber) throws IOException //(4)
    {
        PieChart chart =new PieChartBuilder().width(800).height(600).title("Question"+Integer.toString(questionNumber)).build(); //create chart

        try {
            FileReader filereader = new FileReader("output//Q0"+Integer.toString(questionNumber)+"//"+GetCSVFileNames(questionNumber));
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;


            csvReader.readNext(); //Skip headder
            // we are going to read data line by line
            int i = 0;
            while ((nextRecord = csvReader.readNext()) != null && i <10) {
                i++;
                (chart).addSeries(nextRecord[1], Integer.valueOf(nextRecord[0]));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        new SwingWrapper(chart).displayChart();
        saveChart(chart,questionNumber);
    }


    public static void barChart(int questionNumber) //(6,8)
    {
        try {
            FileReader filereader = new FileReader("output//Q0"+Integer.toString(questionNumber)+"//"+GetCSVFileNames(questionNumber));
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            String headerLable [] = csvReader.readNext(); //Skip headder
            // we are going to read data line by line
            int i = 0;
            List<Float> yData = new ArrayList<Float>();
            List<String> xData = new ArrayList<String>();
            while ((nextRecord = csvReader.readNext()) != null && i <10) {
                i++;
                yData.add(Float.valueOf(nextRecord[0]));
                xData.add(nextRecord[1]);
            }
            CategoryChart chart =new CategoryChartBuilder()
                    .width(1400).height(600).title("Question"+Integer.toString(questionNumber))
                    .xAxisTitle(headerLable[1]).yAxisTitle(headerLable[0]).build(); //create chart

            chart.addSeries("Question"+questionNumber,xData,yData);
            chart.getStyler().setXAxisLabelRotation(90);
            saveChart(chart,questionNumber);
//            new SwingWrapper(chart).displayChart();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}







