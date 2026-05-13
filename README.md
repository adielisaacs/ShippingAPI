# ShippingAPI
This API was developed for a company ACCSO to manage and solve for duplicate events and to tract all transactions in the system

# Steps to Run The Project
##1. Database Setup
###Requirements
You require MYSQL database installed on your computer.

1.1 Run the Shipments20260513.sql on a MYSQL Datadase to create the Schemas

#2. Application Setup 
###Requirements
Maven
Application server / Spring Boot IDE /

##1. Please downlowd the full project to your local
##2. Build the war File
##2.1 Using Maven run the following commands below, navigate to the project folder in command prompt.
mvn clean 
mvn compile
mvn install
A WAR file would be generated in the target folder in the project directory.
This can be placed on any Application Server run in springbootIDE
##2.2 Using Spring Boot IDE 
Import the project into the IDE, if the maven plugin is installed in the IDE run 
mvn clean 
mvn compile
mvn install

Run the project off your IDE by right clicking the Appplication file in java.com.shipping.api directory. RUN as a spring boot application
