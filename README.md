# MCDM Methods - Implementation
This application implements Multiple Criteria Decision Making methods like SAW, TOPSIS, which are used for evaluating a number of variants in terms of a number of decision criteria.

## Getting Started

### Prerequisites
* Download the JDK 8 or OpenJDK 8 and install it.
* Download Maven and install it.

### Decision problem data
Test data are includes in resources directory, if you want use your own data you should edit file TestData.json.

### Usage
To run application go to main folder and run commands:
```
mvn clean install
```
```
java -jar  target/MCDM-1.0-SNAPSHOT.jar MethodNumber
```
Where MethodNumber is a number of in the range 1-4.
* 1 - TOPSIS method with MaxMin normalization.  
* 2 - TOPSIS method with Vector normalization.
* 3 - SAW method with MaxMin normalization.
* 4 - SAW method with Vector normalization.

## Build with

* Maven 3.6.1
* Java 8
* JUnit 5
