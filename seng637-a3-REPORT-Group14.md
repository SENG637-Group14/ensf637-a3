**SENG 637 - Dependability and Reliability of Software Systems**

**Lab. Report #3 – Code Coverage, Adequacy Criteria, and Test Case Correlation**

| Group #: 14     |     |
| -------------- | --- |
| **Student Names:** |     |
| Ayodele Oluwabusola |     |
| Gabriel Gabari |     |
| Remi Oyediji   |     |
| Taiwo Oyewole  |     |


- [1 Introduction](#1-introduction)
- [2 Manual data-flow coverage calculations for X and Y methods](#2-manual-data-flow-coverage-calculations-for-x-and-y-methods)
- [3 A detailed description of the testing strategy for the new unit test](#3-a-detailed-description-of-the-testing-strategy-for-the-new-unit-test)
- [4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage](#4-a-high-level-description-of-five-selected-test-cases-you-have-designed-using-coverage-information-and-how-they-have-increased-code-coverage)
- [5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)](#5-a-detailed-report-of-the-coverage-achieved-of-each-class-and-method-a-screen-shot-from-the-code-cover-results-in-green-and-red-color-would-suffice)
- [6 Pros and Cons of coverage tools used and Metrics you report](#6-pros-and-cons-of-coverage-tools-used-and-metrics-you-report)
- [7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.](#7-a-comparison-on-the-advantages-and-disadvantages-of-requirements-based-test-generation-and-coverage-based-test-generation)
    - [Requirements-Based Test Generation](#requirements-based-test-generation)
    - [Coverage-Based Test Generation](#coverage-based-test-generation)
- [8 A discussion on how the teamwork/effort was divided and managed](#8-a-discussion-on-how-the-teamworkeffort-was-divided-and-managed)
- [9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab](#9-any-difficulties-encountered-challenges-overcome-and-lessons-learned-from-performing-the-lab)
- [10 Comments/feedback on the lab itself](#10-commentsfeedback-on-the-lab-itself)


# 1 Introduction
Software testing is an essential part of software development, ensuring that programs function correctly and meet user requirements. One approach to testing is **white-box testing**, which involves analyzing the internal structure and logic of the code to design test cases. This report documents our team's implementation of white-box testing strategies for JFreeChart's **DataUtilities** and **Range** classes.

We used **EclEmma**, a code coverage tool, to measure how well our test suite covered different parts of the code. Specifically, we evaluated three key control-flow metrics:

- **Statement Coverage** – the percentage of executed statements during testing.  
- **Branch Coverage** – the percentage of executed decision paths (e.g., if-else conditions).  
- **Condition Coverage** – the percentage of individual boolean expressions tested within conditions.  

Additionally, we manually calculated **data-flow coverage** for selected methods to understand how data is used throughout execution paths. By analyzing variable definitions and usages, we ensured comprehensive test adequacy.

This assignment highlights how **coverage criteria influence test case design**, helping testers improve test suite effectiveness while balancing practical trade-offs. Through this process, we learned the importance of achieving high coverage without unnecessary redundancy and ensuring meaningful test cases that catch potential software defects.

The test suite can be found in the `org.jfree.data` package of the source directory.

# 2 Manual data-flow coverage calculations for X and Y methods

**Figure 1 below shows the data flow graph for the calculateColumnTotal function from the DataUtilities class:**

<img src="media/Dataflow graph for DataUtilities.png" alt="media/Dataflow graph for DataUtilities.png" >

| `Definition` | `Variables`        |
|--------------|--------------------|
| `def(1)`     | `{data, column}`  |
| `def(3)`     | `{total}`         |
| `def(4)`     | `{rowCount}`      |
| `def(5)`     | `{r}`             |
| `def(7)`     | `{n}`             |
| `def(9)`     | `total`           |
| `def(10)`    | `{r}`             |
| `def(11)`    | `{r2}`            |
| `def(13)`    | `{n}`             |
| `def(15)`    | `{total}`         |
| `def(16)`    | `{r2}`            |


| `Usage`  | `Variables`              |
|-----------|----------------------------|
| `use(2)`  | `{data}`                |
| `use(4)`  | `{data}`                |
| `use(6)`  | `{r, rowCount}`         |
| `use(7)`  | `{data, column, r}`     |
| `use(8)`  | `{n}`                   |
| `use(9)`  | `{total, n}`            |
| `use(10)` | `{r}`                   |
| `use(12)` | `{r2, rowCount}`        |
| `use(13)` | `{data, r2, column}`    |
| `use(14)` | `{n}`                   |
| `use(15)` | `{total, n}`            |
| `use(16)` | `{r2}`                  |
| `use(17)` | `{total}`               |

| Variable   | DU-Pairs                                                                    |
|----------- |---------------------------------------------------------------------------- |
| data       | (1,2), (1,4), (1,7), (1,13)                                                 |
| column     | (1,7), (1,13)                                                               |
| total      | (3,9), (3,15), (3,17), (9,9), (9,15), (9,17), (15,15), (15,17)               |
| rowCount   | (4,6), (4,12)                                                               |
| r          | (5,6), (5,7), (5,10), (10,6)                                                |
| r2         | (11,12), (11,13), (11,16), (16,12)                                          |
| n          | (7,8), (7,9), (13,14), (13,15)                                              |

| `Variable` | `Defined at node` | `dcu(v,n)`         | `dpu(v,n)`                               |
|-------------|---------------------|----------------------|--------------------------------------------|
| `data`     | `1`               | `{4,7,13}`          | `{(2,End),(2,3)}`                        |
| `column`   | `1`               | `{7,13}`            | `{}`                                      |
| `total`    | `3`               | `{9,15,17}`         | `{}`                                      |
| `total`    | `9`               | `{9,15,17}`         | `{}`                                      |
| `total`    | `15`              | `{15,17}`           | `{}`                                      |
| `rowCount` | `4`               | `{}`                | `{(6,7),(6,11),(12,13),(12,17)}`          |
| `n`        | `7`               | `{9,15}`            | `{(8,10),(8,9)}`                          |
| `n`        | `13`              | `{9,15}`            | `{(14,16),(14,15)}`                       |

**Dataflow graph for Range.png**
<img src="media/Dataflow graph for Range.png" alt="media/Dataflow graph for Range.png" >

**DU pair statements for getupperbound().png**
<img src="media/DU pair statements for getupperbound().png" alt="media/DU pair statements for getupperbound().png" >


# 3 A detailed description of the testing strategy for the new unit test

Our objective was to achieve **90% instruction coverage (statement), 70% branch coverage, and 60% method coverage (condition)** for `DataUtilities` and `Range` classes using **EclEmma**.  

**Approach**  
1. **Analyze Initial Coverage** – Ran Assignment 2 tests with EclEmma to identify untested code.  
2. **Identify Gaps** – Examined missed statements, branches, and methods.  
3. **Refine & Expand Tests** – Added test cases focusing on **edge cases, loops, and decision points**.  
4. **Optimize Coverage** – Iteratively reran tests, adjusted where needed, and ensured we met the **coverage thresholds**.  

**Key Testing Techniques**  
| **Technique**            | **Application**                                      |
|--------------------------|------------------------------------------------------|
| **Equivalence Partitioning** | Grouped inputs into categories to reduce redundancy. |
| **Boundary Value Analysis** | Focused on edge cases for method boundaries.       |
| **Mocking**              | Used mock objects for dependency isolation.         |

**Challenges & Adjustments**  
- **EclEmma Limitations** – Lacked **condition coverage**, so we substituted **method coverage**.  
- **Coverage Discrepancies** – Different members saw inconsistent coverage results (e.g., **0.0%, 0.4%, 0.7%**) for the same tests, highlighting tool setup inconsistencies.  
- **Handling Overloaded Methods** – Discovered additional method signatures (e.g., `calculateColumnTotal`) requiring extra test cases.  

By refining our test suite through iterative improvements, we ensured higher code coverage and meaningful validation while balancing practical test design.  

**Test Execution & Environment**

| **Component**            | **Version / Setup**   |
|-------------------------|----------------------|
| **JUnit**               | 4                 |
| **Mocking Library**     | JMock 2.12.0            |
| **IDE**                 | Eclipse              |
| **Code Coverage Tool**  | EclEmma (JaCoCo)     |
| **SUT**                 | JFreeChart           |

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

To improve **statement, branch, and method coverage** for `calculateColumnTotal` in the `DataUtilities` class, we designed five test cases that cover different input scenarios. These tests ensure correct behavior across various conditions while eliminating untested paths.  

**1. Summing Negative Values** (`testCalculateColumnTotalForNegativeValues`) 
**Purpose**: Ensures the method correctly sums only negative numbers in a column.  
**Coverage Improvement**: Exercises paths where all values are negative, ensuring branch conditions for addition logic are covered.  

**Code Snippet:** Showing how `Values2D` is mocked to return negative values.  
```java
@Test
public void testCalculateColumnTotalForNegativeValues() {
    Mockery context = new Mockery();
    Values2D values = context.mock(Values2D.class);
    context.checking(new Expectations() {{
        allowing(values).getRowCount(); will(returnValue(3));
        allowing(values).getValue(0, 0); will(returnValue(-5.0));
        allowing(values).getValue(1, 0); will(returnValue(-10.0));
        allowing(values).getValue(2, 0); will(returnValue(-3.0));
    }});
    assertEquals(-18.0, DataUtilities.calculateColumnTotal(values, 0), 0.0001);
}
```

**2. Summing Positive Values** (`testCalculateColumnTotalForPositiveValues`) 
**Purpose**: Verifies that the method returns the correct sum when all values are positive.  
**Coverage Improvement**: Covers basic statement execution and verifies correct handling of positive-only data.  

**Code Snippet:**

```java
@Test
public void testCalculateColumnTotalForPositiveValues() {
    Mockery context = new Mockery();
    Values2D values = context.mock(Values2D.class);
    context.checking(new Expectations() {{
        allowing(values).getRowCount(); will(returnValue(3));
        allowing(values).getValue(0, 0); will(returnValue(4.0));
        allowing(values).getValue(1, 0); will(returnValue(6.0));
        allowing(values).getValue(2, 0); will(returnValue(8.0));
    }});
    assertEquals(18.0, DataUtilities.calculateColumnTotal(values, 0), 0.0001);
}
```

**3. Summing Mixed Values** (`testCalculateColumnTotalForMixedValues`) 
**Purpose**: Tests how the method handles a mix of positive and negative values in a column.  
**Coverage Improvement**: Increases branch coverage by testing sign variations in the summation logic.  

**Code Snippet:**

```java
@Test
public void testCalculateColumnTotalForMixedValues() {
    Mockery context = new Mockery();
    Values2D values = context.mock(Values2D.class);
    context.checking(new Expectations() {{
        allowing(values).getRowCount(); will(returnValue(3));
        allowing(values).getValue(0, 0); will(returnValue(10.0));
        allowing(values).getValue(1, 0); will(returnValue(-5.0));
        allowing(values).getValue(2, 0); will(returnValue(3.0));
    }});
    assertEquals(8.0, DataUtilities.calculateColumnTotal(values, 0), 0.0001);
}
```


**4. Handling an Empty Data Set** (`testCalculateColumnTotalForEmptyDataSet`)  
**Purpose**: Ensures that an empty dataset returns zero, as specified in the Javadoc.  
**Coverage Improvement**: Covers an important edge case where no data is present, ensuring correct handling of null or empty inputs.  

**Code Snippet:**@Test

```java
@Test
public void testCalculateColumnTotalForEmptyDataSet() {
    Mockery context = new Mockery();
    Values2D values = context.mock(Values2D.class);
    context.checking(new Expectations() {{
        allowing(values).getRowCount(); will(returnValue(0));
    }});
    assertEquals(0.0, DataUtilities.calculateColumnTotal(values, 0), 0.0001);
}
```

**5. Single Value Case** (`testCalculateColumnTotalForSingleValue`)
**Purpose**: Verifies that when a single value is present, it returns that value as the total.  
**Coverage Improvement**: Ensures correct handling of minimal input, covering a simple yet critical execution path.  
**Code Snippet:**

```java
@Test
public void testCalculateColumnTotalForSingleValue() {
    Mockery context = new Mockery();
    Values2D values = context.mock(Values2D.class);
    context.checking(new Expectations() {{
        allowing(values).getRowCount(); will(returnValue(1));
        allowing(values).getValue(0, 0); will(returnValue(7.5));
    }});
    assertEquals(7.5, DataUtilities.calculateColumnTotal(values, 0), 0.0001);
}
```


**Overall Impact on Code Coverage**  
- **Statement Coverage**: Ensures all major execution paths are exercised.  
- **Branch Coverage**: Validates correct behavior under different logical conditions (positive, negative, mixed, empty cases).  
- **Method Coverage**: Confirms that method execution includes all relevant input scenarios.  

By systematically testing different value combinations, we eliminated untested paths, ensuring a more robust and reliable test suite. 


# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

Text…

# 6 Pros and Cons of coverage tools used and Metrics you report

For our project, we used  JaCoCo in Eclipse (it comes integrated with EclEmma). Due to its seamless integration with Eclipse, making it convenient to use without requiring additional installations. Since all team members were already familiar with Eclipse, adopting EclEmma ensured a short learning curve and a streamlined workflow. The tool provided essential coverage metrics, including **method, line, and branch coverage**, aligning with key concepts discussed in class.

However, **EclEmma does have limitations**. It does not natively support **condition coverage**, which is a critical metric for evaluating how boolean expressions within decision statements are tested. Additionally, **statement coverage** was not explicitly provided, so we adapted by using **line coverage** as a proxy. While line coverage gives insight into how much of the code is executed, it does not offer the same level of granularity as statement coverage, especially in languages where multiple statements can exist on a single line.  

**Comparison of Coverage Metrics and Adjustments**  
To align our results with the required metrics, we made the following adjustments:  

| **Required Metric**  | **EclEmma Alternative Used** | **Description** |  
|----------------------|----------------------------|-----------------|  
| **Statement Coverage** | **Line Coverage** | Measures the percentage of lines executed during testing. While similar to statement coverage, it may be less precise in cases where multiple statements exist on one line. |  
| **Branch Coverage** | **Branch Coverage (Used as-is)** | Ensures that each possible decision outcome (e.g., both true and false branches of an if-statement) is executed at least once. |  
| **Condition Coverage** | **Instruction Coverage** | Since condition coverage evaluates individual boolean expressions, we approximated it using instruction coverage, which measures whether each instruction in the bytecode was executed. |  

**Pros and Cons of EclEmma/JaCoCo**  

**Advantages**  
- **Ease of Use:** Fully integrated with Eclipse, requiring no additional setup.  
- **Immediate Feedback:** Displays coverage results in real time, allowing rapid iterations of test refinement.  
- **Visual Representation:** Highlights covered and uncovered lines directly in the code editor, making it easier to identify gaps.  
- **Supports Key Metrics:** Provides line, branch, and method coverage, which helped in evaluating the effectiveness of our test suite.  
- **Lightweight and Fast:** Runs efficiently within the Eclipse environment without significant performance overhead.  

**Limitations**  
- **No Condition Coverage:** Lacks native support for evaluating individual boolean sub-expressions, requiring alternative metrics.  
- **Lack of Customization:** The tool does not offer options to generate custom reports based on specific coverage needs.  
- **Limited Outside Eclipse:** Since it is designed primarily for Eclipse, it does not integrate as well with other IDEs or testing environments.  

In conclusion, despite its limitations, EclEmma proved to be the most practical choice for our testing needs. The ease of use and real-time feedback outweighed the drawbacks, allowing our team to efficiently assess and refine our test cases. To compensate for the missing condition coverage, we used instruction coverage as a substitute and ensured our test cases were robust enough to cover a wide range of logical conditions. Ultimately, our approach balanced practicality and accuracy, enabling us to achieve the required code coverage goals.  

# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

In this assignment, our team carefully considered the benefits and trade-offs of requirements-based test generation and coverage-based test generation to ensure a well-rounded testing strategy. After discussions, we decided that neither approach alone would be sufficient, so we aimed to combine their strengths for effective and practical test design. Each has distinct advantages and limitations that influence test design and effectiveness.  

### **Requirements-Based Test Generation**  
This method derives test cases from **functional and non-functional requirements**, ensuring that the software meets stakeholder expectations. It primarily follows **black-box testing principles**, meaning tests are created based on specifications rather than code structure.  

**Advantages**  
- **Ensures functional correctness** – Verifies that the system meets user needs and expected behaviors.  
- **Early defect detection** – Identifies issues early in development, reducing later debugging costs.  
- **Unbiased testing** – Since testers do not see the code, test cases are purely based on system requirements.  
- **Direct traceability** – Links test cases to requirements, ensuring all specified functionalities are covered.  

**Disadvantages**  
- **Potential coverage gaps** – If a requirement is unclear or missing, important code paths may go untested.  
- **Limited edge-case detection** – May overlook unintended behaviors that are not explicitly defined in the requirements.  
- **Human error** – Test quality depends on how well testers interpret and implement the requirements.  

### **Coverage-Based Test Generation**  
This method emphasizes the **extent to which the test suite exercises the code**, often using white-box testing techniques. Tests are designed to maximize code execution, measured through metrics like **statement, branch, and condition coverage**.  

**Advantages**  
- **Identifies untested code** – Helps detect unexecuted portions of the program, reducing hidden defects.  
- **Quantifiable test adequacy** – Provides measurable metrics (e.g., branch coverage percentage) to assess test suite completeness.  
- **Automation-friendly** – Coverage-based testing can be integrated into continuous testing pipelines for efficiency.  
- **Useful when requirements are unclear** – Provides a baseline level of testing when formal specifications are lacking.  

**Disadvantages**  
- **Does not guarantee correctness** – High coverage does not mean the software functions correctly; only that it has been executed.  
- **Can be resource-intensive** – Achieving high coverage may require excessive effort, leading to diminishing returns.  
- **Potential focus on irrelevant paths** – Some covered code paths may be unimportant, leading to inefficient testing.  

---

**Balancing Both Approaches**  
The best strategy combines **requirements-based testing** to validate system functionality with **coverage-based testing** to identify untested areas. By integrating both methods, we achieved a high coverage while ensuring that key functionalities meet user expectations.  

As a team, we agreed that neither method alone would be enough to ensure a strong test suite. Instead, we:

- Started with requirements-based testing to ensure core functionalities were properly validated.

- Used coverage analysis to identify untested areas and refine our test cases.

- Balanced practicality and thoroughness by prioritizing meaningful tests over just hitting high coverage numbers.

By integrating both methods, we maximized test effectiveness while ensuring our testing approach remained practical, structured, and aligned with real-world software testing best practices. This hybrid approach leads to **higher software quality, improved test reliability, and a more comprehensive testing strategy**.

# 8 A discussion on how the teamwork/effort was divided and managed

Every member of the team was actively involved in the entire exercise. The project was executed as follows:

Teamwork and Responsibilities

| **Task**                           | **Responsibility** |
|-------------------------------------|--------------------|
| **Requirement Analysis**           | All team members reviewed the Javadoc API specifications for the Range and DataUtilities classes, focusing on white-box testing requirements. |
| **Test-Case Design**               | Each member designed test cases for a subset of methods, aiming to improve statement, branch, and condition coverage. |
| **Test Code Implementation**       | Each member implemented the test cases they designed, ensuring they meet the required coverage criteria using JUnit. |
| **Test Execution & Coverage Analysis** | All team members participated in running the test suite, measuring code coverage using EclEmma, analyzing results, and refining test cases to maximize coverage. |
| **Code Review & Test Consolidation** | The team came together to review each other's test cases and coverage reports, identifying redundant or ineffective tests. We then consolidated the most effective tests into a final suite that best met the required coverage criteria for our report. |

The report was collaboratively written, with each member contributing to different sections. |

**Communication & Collaboration**: Regular meetings (both in-person and virtual) and a shared online document were used to facilitate communication. This ensured that all team members were aware of the progress and challenges encountered.

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

Our team faced a few challenges while completing this Lab, particularly in understanding and achieving the required coverage metrics. Initially, some members had difficulty distinguishing between statement, branch, and condition coverage**, especially since EclEmma did not provide condition coverage by default. To compensate, we substituted instruction coverage as an approximation.  

Another major challenge was **coverage discrepancies in EclEmma**—despite running the same code, different team members saw **0.0%, 0.4%, and 0.7% coverage**. This inconsistency highlighted the importance of **consistent testing environments and tool configurations**. Additionally, our initial test suite did not meet the required coverage thresholds, so we had to refine our test cases to improve adequacy.  

We also explored **Cobertura and CodeCover**, but integration issues and setup difficulties prevented their effective use. Debugging was another obstacle, as some test cases failed unexpectedly due to incorrect assumptions about method behaviors, requiring us to revisit the Javadoc specifications.  

**Lessons Learned:**  
- **Coverage tools can report inconsistent results** if not properly configured.  
- **Achieving high coverage requires multiple iterations** and refinements to test cases.  
- **Tool limitations must be considered early**, as not all metrics are available by default.  

Despite these difficulties, we successfully improved our test coverage and gained a deeper understanding of **coverage-based testing strategies**.

# 10 Comments/feedback on the lab itself

- The instructions and guidelines for this assignment were clear and well-structured, making it easier to understand and implement the test cases effectively.
- The lab exercise provided valuable experience in designing and executing structured test cases using white-box testing and coverage analysis.
