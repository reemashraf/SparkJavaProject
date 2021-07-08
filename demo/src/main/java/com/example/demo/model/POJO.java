package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;

public class POJO implements Serializable {
    public String Title ;
    public String Company;
    public String Location;
    public String Type;
    public String Level;
    public String YearsExp;
    public String Country;
    public ArrayList<String> Skills;

    public POJO(String title,
            String company,
            String location,
            String type,
            String level,
            String yearsExp,
            String country,
            ArrayList<String> skills) {

        Title = title;
        Company = company;
        Location = location;
        Type= type;
        Level = level;
        YearsExp = yearsExp;
        Country = country;
        Skills = skills;
    }


}
