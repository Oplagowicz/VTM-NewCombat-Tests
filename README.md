# QA Automation Tests (Selenium + TestNG + Allure)# 

- Requirements -
1. Java 25 or higher
2. Maven 3.9 or higher
3. Google Chrome and Mozilla Firefox installed

@ Run Tests command

$ mvn clean test

(Executes all TestNG suites defined in testng.xml.
Allure results are generated in target/allure-results.)

@ Generate Allure Report

$ mvn allure:report

(Builds a static HTML report located at:
target/site/allure-maven-plugin/index.html)

@ View Allure Report Locally

$ mvn allure:serve

(Starts a local server and automatically opens the report in a browser.
The server stops when the terminal process is closed.)

@ Typical Workflow

$ mvn clean test && mvn allure:serve

(Runs the entire test suite and opens the Allure report immediately after execution.)
