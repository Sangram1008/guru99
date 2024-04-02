Implemented the Hybrid Framework of Guru 99 site to Understand the Framework structure and how does it Work.
Which combines elements of Data-Driven Framework, Keyword-Driven Framework, and Page Object Model (POM),

OverView of Project Structure  
guru99
│
├── .idea
│   └── (IDEA specific configuration files)
├── Configuration
│   └── (Configuration files for the project)
├── Drivers
│   └── (Driver executables for Selenium WebDriver)
├── Screenshots
│   └── (Screenshots captured during test execution)
├── src
│   ├── main
│   │   └── java
│   │       └── org
│   │           └── example
│   │               └── Main.java
│   └── test
│       └── java
│           └── netBanking
│               ├── pageObject
│               │   └── LoginPage.java
│               ├── testCase
│               │   ├── BaseClass.java
│               │   ├── TC_LoginDDT_002.java
│               │   └── TC_LoginTest_001.java
│               ├── testData
│               │   ├── LoginData.xlsx
│               │   └── ~$LoginData.xlsx
│               └── utilities
│                   ├── ReadConfig.java
│                   ├── Reporting.java
│                   └── XLUtils.java
├── target
│   └── (Compiled Java classes and other build artifacts)
├── TestNg.xml
├── extent.config.xml
├── guru99.iml
├── log4j.properties
├── pom.xml
├── qodana.yaml
└── README.md
