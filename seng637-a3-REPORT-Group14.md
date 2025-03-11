**SENG 637 - Dependability and Reliability of Software Systems**

**Lab. Report #3 – Code Coverage, Adequacy Criteria, and Test Case Correlation**

| Group \#: 14     |     |
| -------------- | --- |
| **Student Names:** |     |
| Ayodele Oluwabusola |     |
| Gabriel Gabari |     |
| Remi Oyediji   |     |
| Taiwo Oyewole  |     |

(Note that some labs require individual reports while others require one report
for each group. Please see each lab document for details.)

# 1 Introduction
Software testing is an essential part of software development, ensuring that programs function correctly and meet user requirements. One approach to testing is **white-box testing**, which involves analyzing the internal structure and logic of the code to design test cases. This report documents our team's implementation of white-box testing strategies for JFreeChart's **DataUtilities** and **Range** classes.

We used **EclEmma**, a code coverage tool, to measure how well our test suite covered different parts of the code. Specifically, we evaluated three key control-flow metrics:

- **Statement Coverage** – the percentage of executed statements during testing.  
- **Branch Coverage** – the percentage of executed decision paths (e.g., if-else conditions).  
- **Condition Coverage** – the percentage of individual boolean expressions tested within conditions.  

Additionally, we manually calculated **data-flow coverage** for selected methods to understand how data is used throughout execution paths. By analyzing variable definitions and usages, we ensured comprehensive test adequacy.

This assignment highlights how **coverage criteria influence test case design**, helping testers improve test suite effectiveness while balancing practical trade-offs. Through this process, we learned the importance of achieving high coverage without unnecessary redundancy and ensuring meaningful test cases that catch potential software defects.

The test suite can be found in the org.jfree.data.test package of the source directory.

# 2 Manual data-flow coverage calculations for X and Y methods

Text…

# 3 A detailed description of the testing strategy for the new unit test

Text…

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Text…

# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

Text…

# 6 Pros and Cons of coverage tools used and Metrics you report

Text…

# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

Text…

# 8 A discussion on how the team work/effort was divided and managed

Every member of the team was actively involved in the entire exercise. The project was executed as follows:

## Teamwork and Responsibilities

| **Task**                           | **Responsibility** |
|-------------------------------------|--------------------|
| **Requirement Analysis**           | All team members reviewed the Javadoc API specifications for the Range and DataUtilities classes, focusing on white-box testing requirements. |
| **Test-Case Design**               | Each member designed test cases for a subset of methods, aiming to improve statement, branch, and condition coverage. |
| **Test Code Implementation**       | Each member implemented the test cases they designed, ensuring they meet the required coverage criteria using JUnit. |
| **Test Execution & Coverage Analysis** | All team members participated in running the test suite, measuring code coverage using EclEmma, analyzing results, and refining test cases to maximize coverage. |
| **Code Review & Test Consolidation** | The team came together to review each other's test cases and coverage reports, identifying redundant or ineffective tests. We then consolidated the most effective tests into a final suite that best met the required coverage criteria for our report. |

The report was collaboratively written, with each member contributing to different sections. |

**Communication & Collaboration**: Regular meetings (both in-person and virtual) and a shared online document were used to facilitate communication. This ensured that all team members were aware of progress and challenges encountered.

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

Text…

# 10 Comments/feedback on the lab itself

Text…
