package com.selcum.runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CucumberTestRunner.class, GenerateReport.class})
public class JunitTestSuite {

}

