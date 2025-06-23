# 5element.by Automation Framework

![Java](https://img.shields.io/badge/Java-21%2B-blue)
![Maven](https://img.shields.io/badge/Maven-3.9.6-red)
![TestNG](https://img.shields.io/badge/TestNG-7.8.0-green)
![Appium](https://img.shields.io/badge/Appium-8.5.1-orange)

Framework for automated testing of 5element.by website with support for:
- Cross-browser testing (Chrome, Firefox, Edge, Safari)
- Mobile devices (iOS/Android smartphones and tablets)
- Parallel test execution
- Multiple environments (test, stage, prod)
- Allure reporting

## Framework Structure
src/
├── main/
│ ├── java/
│ │ └── by/element5/
│ │ ├── config/ # Configuration classes
│ │ ├── drivers/ # Driver factories
│ │ ├── pages/ # Page Objects
│ │ ├── rest/ # API clients
│ │ └── utils/ # Utility classes
│ └── resources/
│ ├── browsers/ # Browser configs
│ ├── devices/ # Device configs
│ └── environments/ # Environment configs
└── test/
├── java/
│ └── by/element5/tests/ # Test classes
└── resources/
└── test-suites/ # TestNG suites

text

## Prerequisites

- Java 21+
- Maven 3.9.6+
- Allure Commandline
- Node.js (for Appium)
- Appium Server 2.0+
- Browsers:
   - Chrome
   - Firefox
   - Edge
   - Safari (macOS only)

## Installation

1. Clone repository:
```bash
git clone https://github.com/yourrepo/5element-autotests.git
cd 5element-autotests
Install dependencies:

bash
mvn clean install
Install Appium (for mobile tests):

bash
npm install -g appium
Configuration
1. Environment Setup
Edit configuration files in src/main/resources/environments/:

test.json

stage.json

prod.json

Example:

json
{
  "name": "test",
  "baseUrl": "https://test.5element.by",
  "apiUrl": "https://api.test.5element.by"
}
2. Device Configuration
Add device configs to src/main/resources/devices/:

mobile/ - for smartphones

tablet/ - for tablets

Example smartphone config:

json
{
  "name": "samsung_s21",
  "platformName": "Android",
  "platformVersion": "13",
  "deviceName": "Samsung Galaxy S21"
}
Running Tests
Desktop Browsers
bash
mvn test -Dbrowser=chrome -Denvironment=test
Mobile Devices
bash
mvn test -Ddevice=iphone12 -DisMobile=true -Denvironment=stage
Tablets
bash
mvn test -Ddevice=ipad_pro -DisTablet=true -Denvironment=test
Parallel Execution
Run TestNG suite:

bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/test-suites/parallel-tests.xml
Reporting
Generate Allure report:

bash
mvn allure:serve
CI/CD Integration
Example GitHub Actions workflow:

yaml
name: CI Pipeline

on: [push]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 21
      uses: actions/setup-java@v3
      with:
        java-version: '21'
    - name: Run Tests
      run: mvn test -Denvironment=test
    - name: Generate Report
      run: |
        mvn allure:report
        mkdir -p allure-results
        cp -R target/allure-results/* allure-results/