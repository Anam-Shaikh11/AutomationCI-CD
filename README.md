**Automation CI/CD**

This repository demonstrates how to **automate** Selenium UI tests written in Java 8 using a CI/CD pipeline with Git and Jenkins.

 Tech Stack

- **Language**: Java 8 (Maven/Gradle project structure).  
- **Test framework**: Selenium WebDriver for browser automation.
- **CI/CD**: Jenkins (Freestyle or Pipeline with `Jenkinsfile`)
- **Version control**: Git, hosted on GitHub

**Project Structure**

**Typical structure**

- `src/test/java` – Selenium test classes.  
- `src/main/java` – Application or helper code.  
- `pom.xml`  – Build configuration and Selenium/test dependencies.
- `Jenkinsfile` – Jenkins Pipeline definition (stages for build, test, and reporting).

**Prerequisites**

- Java 8 and Maven  installed locally.
- Jenkins server with Git and Maven plugins configured.
- Browser driver setup (e.g., ChromeDriver) or remote grid/cloud provider for running Selenium tests.

**Running Tests Locally**

1. Clone the repository:
   ```bash
   git clone https://github.com/Anam-Shaikh11/AutomationCI-CD.git
   cd AutomationCI-CD
   ```
2. Run the tests with Maven:
   ```bash
   mvn clean test
   ```
   or with Gradle (if applicable):
   ```bash
   ./gradlew clean test
   ```  
   This will execute the Selenium test suite and produce test reports under `target/surefire-reports` or `build/test-results`.

 **Jenkins CI/CD Pipeline**

A typical Jenkins setup for this project includes:

- Source Code Management: Point Jenkins to this GitHub repository (HTTPS or SSH URL).  
- Triggers: Poll SCM or webhook so jobs run on each commit or pull request.  
- Build steps:  
  - `mvn clean test` to compile and run Selenium tests.  
  - Optionally archive artifacts (reports, screenshots) and publish JUnit reports.  

If using a declarative pipeline, the `Jenkinsfile` usually defines stages like:

- `Checkout` – Clone the Git repo.  
- `Build` – Resolve dependencies and compile tests.  
- `Test` – Run Selenium tests on a configured node or agent.  
- `Post` – Publish JUnit results and mark the build status.  

Customization

- Add more test suites (smoke, regression) and map them to separate Jenkins jobs or stages.
- Integrate parallel test execution or cloud browser platforms for faster feedback.
- Configure notifications (email/Slack) on Jenkins for failed builds and test runs.
