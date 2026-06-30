# UI Testing Automation of Amazon Website using Selenium WebDriver



## Project Overview



This project automates the user interface testing of the Amazon India website using **Selenium WebDriver**, **Java**, **TestNG**, and **Maven**.



The automation script simulates a real user's shopping journey by performing a complete end-to-end workflow on the Amazon India website.



## Features



The test script performs the following actions automatically:



- Opens the Amazon India website.

- Searches for the keyword **"iPhone"**.

- Skips sponsored products in the search results.

- Selects the **5th non-sponsored product**.

- Opens the selected product in a new browser tab.

- Adds the product to the shopping cart.

- Opens the cart page.

- Verifies that the selected product has been successfully added to the cart using TestNG assertions.



---



## Technologies Used



- Java 17/25

- Selenium WebDriver 4

- TestNG

- Maven

- Google Chrome

- ChromeDriver

- WSL (Ubuntu)



---



## Project Structure



```

amazon-ui-testing/

│── src/

│   ├── main/

│   └── test/

│       └── java/

│           └── tests/

│                 └── tests/AmazonTest.java

│

├── pom.xml

├── testng.xml

├── README.md

└── target/

```



---



## Prerequisites



Before running the project, ensure the following are installed:



- Java JDK 17 or later

- Apache Maven

- Google Chrome

- ChromeDriver (compatible with your Chrome version)

- Git



Verify installation:



```bash

java -version

mvn -version

```



---



## Installation



Clone the repository:



```bash

git clone https://github.com/Anshika141/Selenium_WebDriver_test_script.git 

```



Navigate to the project directory:



```bash

cd amazon-ui-testing

```



Install dependencies:



```bash

mvn clean install

```



---



## Running the Test



Execute the TestNG test using Maven:



```bash

mvn test

```



Or run a specific TestNG suite:



```bash

mvn test 

```



---



## Test Workflow



1. Launch Google Chrome.

2. Navigate to Amazon India.

3. Search for **iPhone**.

4. Ignore sponsored products.

5. Select the 5th non-sponsored product.

6. Open the product in a new tab.

7. Add the product to the cart.

8. Open the shopping cart.

9. Verify that the product has been added successfully.



---



## Selenium Concepts Demonstrated



- Browser automation

- Web element identification

  - ID

  - CSS Selectors

  - XPath

- Explicit Waits

- Handling dynamic web elements

- Window and tab switching

- TestNG Assertions

- End-to-end UI testing

- Maven project management



---



## Expected Output



The automation should successfully:



- Launch Amazon India.

- Search for "iPhone".

- Open the selected product.

- Add it to the shopping cart.

- Verify that the cart contains the selected product.



If all validations pass, the test completes successfully.



---



## Future Enhancements



- Cross-browser testing (Firefox, Edge)

- Page Object Model (POM) implementation

- Data-driven testing using Excel or CSV

- Logging with Log4j

- Test reporting using Extent Reports or Allure Reports

- CI/CD integration using GitHub Actions or Jenkins



---



## Author



**Anshika Patel** 



---
