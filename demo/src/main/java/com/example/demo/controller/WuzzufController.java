package com.example.demo.controller;



import com.example.demo.service.AnalysisService;

import lombok.val;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class WuzzufController {


    private static AnalysisService analysisService;

// get the most popular title and print them
    @GetMapping("/popularTitles")
    public ResponseEntity<Object> titles(){

        return new ResponseEntity<Object>(analysisService.getTitle(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));

    }


    @GetMapping("/popularSkills")
    public ResponseEntity<Object> skills(){
        return new ResponseEntity<Object>(analysisService.getSkillsCount(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));

    }



    @GetMapping("/print")
    public ResponseEntity<Object> getData(){
        return new ResponseEntity<Object>(analysisService.getData(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));

    }

    @GetMapping("/summary")
    public ResponseEntity<Object> summary(){

        return new ResponseEntity<Object>(analysisService.getSummary(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));

    }


    @GetMapping("/structure")
    public ResponseEntity<Object> structure(){
        return new ResponseEntity<Object>(analysisService.getStructure(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));
        }


    @GetMapping("/popularAreas")
    public ResponseEntity<Object> areas(){
        return new ResponseEntity<>(analysisService.getArea(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));


    }
    @GetMapping("/popularJobsCompany")
    public  ResponseEntity<Object> Jobs(){
        return new ResponseEntity<Object>(analysisService.getCompanyTitle(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));



    }

    @GetMapping("/popularJobsCompanyChart")
    public  ModelAndView JobsChart(){
        analysisService.getJobsPerCompanyChart();
        ModelAndView model = new ModelAndView("image4.html");
//        model.addObject("path","images/Q04.png");
        model.addObject("description" , "Pie Chart Representing the Popular Jobs per Company");
        return model;
    }

    @GetMapping("/popularAreasChart")
    public  ModelAndView SkillsChart(){
        analysisService.getSkillsChart();
        ModelAndView model = new ModelAndView("image8.html");
//        model.addObject("path","images/Q08.png");
        model.addObject("description" , "Bar Chart Representing the Popular Skills");
        return model;
    }
    @GetMapping("/popularJobsChart")
    public ModelAndView TitlesChart(){
        analysisService.getTitlesChart();
        ModelAndView model = new ModelAndView("image6.html");
//        model.addObject("path","images/Q06.png");
        model.addObject("description" , "Bar Chart Representing the Popular Jobs ");
        return model;
    }

    @GetMapping("/HpopularTitles")
    public ResponseEntity<Object> viewTitles(){
        return new ResponseEntity<Object>(analysisService.getTitleView(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));

    }


    @GetMapping("/HpopularSkills")
    public ResponseEntity<Object> viewSkills(){
        return new ResponseEntity<Object>(analysisService.getSkillsCountView(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));

    }



    @GetMapping("/Hprint")
    public ResponseEntity<Object> viewGetData(){
        return new ResponseEntity<Object>(analysisService.getDataView(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));

    }

    @GetMapping("/Hsummary")
    public ResponseEntity<Object> viewSummary(){

        return new ResponseEntity<Object>(analysisService.getSummaryView(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));

    }


//    @GetMapping("/Hstructure")
//    public ResponseEntity<Object> viewStructure(){
//        return new ResponseEntity<Object>(analysisService.getStructureView(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));
//    }


    @GetMapping("/HpopularAreas")
    public ResponseEntity<Object> viewAreas(){
        return new ResponseEntity<>(analysisService.getAreaView(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));


    }
    @GetMapping("/HpopularJobsCompany")
    public  ResponseEntity<Object> viewJobs(){
        return new ResponseEntity<Object>(analysisService.getCompanyTitleView(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));



    }

}
