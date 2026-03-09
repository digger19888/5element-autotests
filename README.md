# 5element.by Automation Framework

1. предварительно почистить папку target\allure-results
2. запустить команду: mvn clean test
3. для формирования отчёта запустить команду: allure serve target\allure-results


## Quick Start

### Prerequisites
- Java 21+
- Maven 3.6+
- Allure CLI
- Docker & Docker Compose (optional, for Selenium Grid)

### Running Tests

### Desktop Tests (Default)

```bash
# 1. Clean target directory
rm -rf target/allure-results  # On Windows: del /q /s target\allure-results

# 2. Run all tests (desktop only)
mvn clean test

# 3. Run only smoke tests
mvn clean test -Dtest.groups=smoke

# 4. Run tests excluding regression
mvn clean test -Dtest.excludedGroups=regression

# 5. Run all tests (including regression)
mvn clean test -Dtest.groups= -Dtest.excludedGroups=
```

### Mobile Tests (Requires Appium)

**Prerequisites:**
1. Install Appium: `npm install -g appium`
2. Install Android SDK and platform tools
3. Install Appium drivers: `appium driver install uiautomator2` (for Android)
4. Start Appium server: `appium`

```bash
# Run tests on mobile device
mvn clean test -Pmobile

# Or specify mobile platform via command line
mvn clean test -Pmobile -Dmobile.platform=android
```

**Configure mobile device** in `src/test/resources/application.properties`:
```properties
mobile.testing=true
mobile.platform=android
mobile.browser=Chrome
mobile.device.name=Pixel 5
```

### Tablet Tests (Requires Appium)

**Prerequisites:**
1. Install Appium: `npm install -g appium`
2. Install Appium drivers: `appium driver install xcuitest` (for iPad) or `appium driver install uiautomator2` (for Android)
3. Start Appium server: `appium`
4. Connect iPad/Android tablet to computer or use simulator/emulator

```bash
# Enable tablet testing in application.properties:
tablet.testing=true
tablet.platform=iOS
tablet.device.name=iPad Pro (12.9-inch) (6th generation)

# Then run tests with mobile profile
mvn clean test -Pmobile
```

**Configure tablet** in `src/test/resources/application.properties`:
```properties
# iPad Configuration
tablet.testing=true
tablet.name=ipad_pro
tablet.platform=iOS
tablet.platform.version=16
tablet.device.name=iPad Pro (12.9-inch) (6th generation)
tablet.device.browser=Safari
tablet.device.orientation=LANDSCAPE

# Android Tablet Configuration (comment out iPad, uncomment these)
#tablet.testing=true
#tablet.name=samsung_tab_s7
#tablet.platform=Android
#tablet.platform.version=12
#tablet.device.name=Samsung Galaxy Tab S7
#tablet.device.browser=Chrome
#tablet.device.orientation=LANDSCAPE
```

### Code Quality

```bash
# Generate code coverage report
mvn clean test jacoco:report

# Run Checkstyle validation
mvn checkstyle:check
```

### Allure Report

The report launches automatically after tests. Or run manually:

```bash
allure serve target/allure-results
```

### Selenium Grid (Optional)

```bash
# Start the grid
docker-compose up -d

# Access hub at http://localhost:4444
# Stop the grid
docker-compose down
```

## Configuration

Edit `src/test/resources/application.properties`:

```properties
# Environment
base.url = https://5stage.patio-minsk.by/

# Browser: CHROME, FIREFOX, EDGE, SAFARI
browser=CHROME
browser.headless=false

# Mobile testing
mobile.testing=false

# Tablet testing
tablet.testing=false

# Browser size
browser.size=1920x1080
```

## Test Categories

| Tag | Description | Command |
|-----|-------------|---------|
| `smoke` | Critical path tests | `-Dgroups=smoke` |
| `regression` | Full regression suite | `-DexcludedGroups=regression` |
| `ui` | UI tests | `-Dgroups=ui` |
| `api` | API tests | `-Dgroups=api` |

## Project Structure

```
src/test/java/
├── config/          # Configuration interfaces
├── core/            # Base classes (BaseTest, BasePage)
├── drivers/         # WebDriver factories
├── pages/           # Page Object classes
├── tests/
│   ├── ui/          # UI tests
│   └── api/         # API tests
├── models/          # Data models (User, Product)
├── utils/           # Utilities (TestDataGenerator)
└── rest/            # REST API client
```


| Command | Description |
|---------|-------------|
| `mvn clean test` | Run all tests |
| `mvn test -Dgroups=smoke` | Run smoke tests only |
| `mvn test -DexcludedGroups=regression` | Exclude regression tests |
| `mvn test jacoco:report` | Generate coverage report |
| `mvn checkstyle:check` | Validate code style |
| `allure serve` | Open Allure report |
| `docker-compose up -d` | Start Selenium Grid |

## Reports

- **Allure Report**: Auto-generated after tests or via `allure serve`
- **JaCoCo Coverage**: `target/site/jacoco/index.html`
- **Surefire Report**: `target/site/surefire-report.html`

## Technologies

- **Testing**: JUnit 5, Selenide, Rest Assured, Appium
- **Reporting**: Allure, JaCoCo
- **Build**: Maven
- **CI/CD**: Docker, Selenium Grid
