# SpringJavaProject
## Description 

Application use Wuzzuf Jobs data as dataset load it , clean it and remove duplicates then get some Insights about the data .



## Functions

- Getting Structure and Summary of Data .
- Get Most Popular Job Titles .
- Get Most Popular Jobs per Company .
- Get Most Popular Areas .
- Get The Most Common Required Skills.
- Printing Shunk of data .


## Dependency 

SpringJava Application is managed using Maven.

- Spark dependency
- Spring dependency


## Statistics Routes 

Spring Application is run on port 8088 

1. Summary [http://localhost:8088/summary](http://localhost:8088/summary)
2. Structure [http://localhost:8088/structure](http://localhost:8088/structure)
3. Most populat Job Titles [http://localhost:8088/popularTitles](http://localhost:8088/popularTitles)
4. Most Popular Jobs per Company [http://localhost:8088/popularJobsCompany](http://localhost:8088/popularJobsCompany)
5. Most Required Skills [http://localhost:8088/popularSkills](http://localhost:8088/popularSkills)
6. Most Popular Areas [http://localhost:8088/popularAreas](http://localhost:8088/popularAreas)
7. Printing Top of data [http://localhost:8088/print](http://localhost:8088/print)
8. Viewing 3 in Chart [http://localhost:8088/popularJobsChart](http://localhost:8088/popularJobsChart)
9. Viewing 4 in Chart [http://localhost:8088/popularJobsCompanyChart](http://localhost:8088/popularJobsCompanyChart)
10. Viewing 6 in Chart [http://localhost:8088/popularAreasChart](http://localhost:8088/popularAreasChart)


## Output 
Application can be used now as API as return JSON and as As User App through the following Routes:


1. Summary [http://localhost:8088/Hsummary](http://localhost:8088/Hsummary)
3. Most populat Job Titles [http://localhost:8088/HpopularTitles](http://localhost:8088/HpopularTitles)
4. Most Popular Jobs per Company [http://localhost:8088/HpopularJobsCompany](http://localhost:8088/HpopularJobsCompany)
5. Most Required Skills [http://localhost:8088/HpopularSkills](http://localhost:8088/HpopularSkills)
6. Most Popular Areas [http://localhost:8088/HpopularAreas](http://localhost:8088/HpopularAreas)
7. Printing Top of data [http://localhost:8088/Hprint](http://localhost:8088/Hprint)



Note : Saving Options may have a problem if the user is using different operating system by / or \ .

Note : All the above links will works fine it the application is running .
