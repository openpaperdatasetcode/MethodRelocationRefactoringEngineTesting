#  **Testing Method Relocation Algorithms via Template-Based Systematic Structural Traversal and Precondition Filtering**

#  **Bug list：** 

All bugs identified in the case study are explained in detail so that they can be validated by other researchers. The bug list is publicly available [here](https://openpaperdatasetcode.github.io/openpaperdatasetcode/)

#  **How to reproduce the case study**

##  **1.Requirements**

. JDK == 17

• Eclipse, version 4.35.0

• IntelliJ IDEA, version 2025.1.3

##  **2.Step-by-step tutorial**

###  • Download the implementation at: git clone https://github.com/openpaperdatasetcode/MethodRelocationRefactoringEngineTesting.git

###  • A tool that performs automated execution of refactoring by calling refactoring engines.

**1 Eclipse**

    • Open Eclipse, click File > Import in the top menu bar.
    
    • In the pop-up window, select General > Existing Projects into Workspace and click Next.
    
    • Check Select root directory, click Browse to select the reproduction package/plugin project folder in the cloned repository.
    
    • Check the target plugin project in the Projects list, keep other default settings, and click Finish.
    
    • Wait for Eclipse to load dependencies and build the project (ensure JDK 17 is configured as the project SDK).
    
**2 IntelliJ IDEA**

•Open IntelliJ IDEA, click File > Open in the top menu bar (or Import Project for the initial startup interface).

•Select the reproduction package/plugin project folder in the cloned repository and click OK.

•In the import prompt, select Import project from external model (if needed) or keep the default, and click Next until the end.

•Go to File > Project Structure > Project SDK, select JDK 17 as the project SDK, and click Apply > OK.

•Wait for IntelliJ IDEA to index the project and download relevant dependencies.

###• Open dataset：[Dataset](https://github.com/openpaperdatasetcode/MethodRelocationRefactoringEngineTesting/tree/main/Datasets).

