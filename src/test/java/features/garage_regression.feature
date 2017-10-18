#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios 
#<> (placeholder)
#""
## (Comments)

#@tag6
#Scenario: Case 22 - Write a review
#Given link "WRITE A REVIEW" exists on the page
#  And user click on link WRITE A REVIEW
#Then Pop-up Write a review is opened
#When user click icon x to close Write a review
#Then Pop-up Write a review is closed 

@tag
Feature: Exemple of Regression Tests for the Garage site.
The browser is Google Chrome, FireFox or Internet Explorer. 

Scenario: Given user is on Garage home page
Then user selects a category "CLOTHING"
	