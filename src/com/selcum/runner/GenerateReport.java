package com.selcum.runner;

import junit.framework.*;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GenerateReport extends TestCase {


    public void testGenerateReport(){
        Date reportDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
        String reportGenerationTimeStamp = formatDate.format(reportDate);

        System.out.println("Generating report on: " + reportGenerationTimeStamp);


        try {
            Process p1 = Runtime.getRuntime().exec("xcopy report\\cumcumber-html-report cucumber-reports\\HTMLReport-" + reportGenerationTimeStamp + "/i");
            p1.waitFor();

            Process p2 = Runtime.getRuntime().exec("cmd /c copy report\\cucumber-report.json cucumber-reports\\Report-" + reportGenerationTimeStamp + ".json");
            p2.waitFor();

            File reportOutputDirectory = new File("cucumber-reporting\\CucumberReport-" + reportGenerationTimeStamp);
            List<String> jsonReportFiles = new ArrayList<String>();
            jsonReportFiles.add("cucumber-reports\\Report-" + reportGenerationTimeStamp + ".json" );

            String buildNumber = "1";
            String buildProjectName = "Google Search";
            Boolean skippedFails = false;
            Boolean undefinedFails = false;
            Boolean flashCharts = false;
            Boolean runWithJenkins = false;
            Boolean artifactsEnabled = false;
            String artifactConfig = "";
            Boolean highCharts = true;
            String pluginUrlPath = "";
            ReportBuilder reportBuilder = new ReportBuilder(jsonReportFiles,reportOutputDirectory,pluginUrlPath,buildNumber,buildProjectName,skippedFails,undefinedFails,flashCharts,runWithJenkins,artifactsEnabled,artifactConfig,highCharts);
            reportBuilder.generateReports();
        }

        catch(Exception e){
            System.out.println(e);
        }




    }
}
