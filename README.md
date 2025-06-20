Automation framework for testing 5element.by with parallel execution across browsers, devices, and environments.

🚀 Features
✔ Parallel Testing on Chrome, Firefox, Edge, Safari (desktop/mobile)
✔ Multiple Environments: Test, Stage, Production
✔ Allure Reports with screenshots and device info
✔ Page Object Model (POM) design
✔ CI/CD Integration (Jenkins, GitHub Actions)
✔ Selenoid/Docker support for cross-browser testing

⚡ Quick Start
Prerequisites
Java 21+

Maven 3.9+

Chrome/Firefox/Edge browsers

1. Clone & Build
   sh
   git clone https://github.com/your-repo/5element-autotests.git  
   cd 5element-autotests  
   mvn clean install
2. Run Tests
   Command	Description
   mvn test	Run on Test env (default)
   mvn test -Pstage	Run on Stage env
   mvn test -Pprod	Run on Production env
   mvn test -Dbrowser=firefox	Run on Firefox only
   mvn test -Dbrowser=chrome_mobile -Ddevice=iphone12	Mobile test on iPhone 12
3. Generate Allure Report
   sh
   allure serve target/allure-results  
   📂 Project Structure
   text
   src/  
   ├── main/  
   │   ├── java/com/element5/  
   │   │   ├── config/       # Environment configurations  
   │   │   ├── enums/        # Browser, Device, Environment  
   │   │   ├── pages/        # Page Objects  
   │   │   ├── services/     # API/UI services  
   │   │   └── utils/        # DriverFactory, Allure utils  
   │   └── resources/  
   │       ├── config/       # test/stage/prod.properties  
   │       └── testng/       # TestNG XMLs  
   └── test/  
   ├── java/com/element5/  
   │   ├── api/          # API tests  
   │   └── ui/           # UI tests  
   └── resources/        # Test data (JSON/CSV)  
   ⚙️ Configuration
   Environment Setup
   Edit src/main/resources/config/[env].properties:

properties
# test.properties
base.url=https://test.5element.by  
browser=chrome,firefox  
parallel.threads=5  
TestNG XML
Configure parallel runs in testng-parallel.xml:

xml
<suite name="Parallel Suite" parallel="tests" thread-count="5">  
<test name="Chrome Tests">  
<parameter name="browser" value="chrome"/>  
<classes>  
<class name="com.element5.tests.HomePageTest"/>  
</classes>  
</test>  
</suite>  
📊 Reporting
Allure Report includes:
✅ Test steps with screenshots
✅ Environment details (URL, browser)
✅ Device info (for mobile tests)
✅ Error logs