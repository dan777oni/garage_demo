$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("garage_regression.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#Author: your.email@your.domain.com"
    },
    {
      "line": 2,
      "value": "#Keywords Summary :"
    },
    {
      "line": 3,
      "value": "#Feature: List of scenarios."
    },
    {
      "line": 4,
      "value": "#And,But: To enumerate more Given,When,Then steps"
    },
    {
      "line": 5,
      "value": "#Scenario Outline: List of steps for data-driven as an Examples and \u003cplaceholder\u003e"
    },
    {
      "line": 6,
      "value": "#Examples: Container for s table"
    },
    {
      "line": 7,
      "value": "#Background: List of steps run before each of the scenarios"
    },
    {
      "line": 8,
      "value": "#\"\"\" (Doc Strings)"
    },
    {
      "line": 9,
      "value": "#| (Data Tables)"
    },
    {
      "line": 10,
      "value": "#@ (Tags/Labels):To group Scenarios"
    },
    {
      "line": 11,
      "value": "#\u003c\u003e (placeholder)"
    },
    {
      "line": 12,
      "value": "#\"\""
    },
    {
      "line": 13,
      "value": "## (Comments)"
    },
    {
      "line": 15,
      "value": "#@tag6"
    },
    {
      "line": 16,
      "value": "#Scenario: Case 22 - Write a review"
    },
    {
      "line": 17,
      "value": "#Given link \"WRITE A REVIEW\" exists on the page"
    },
    {
      "line": 18,
      "value": "#  And user click on link WRITE A REVIEW"
    },
    {
      "line": 19,
      "value": "#Then Pop-up Write a review is opened"
    },
    {
      "line": 20,
      "value": "#When user click icon x to close Write a review"
    },
    {
      "line": 21,
      "value": "#Then Pop-up Write a review is closed"
    }
  ],
  "line": 24,
  "name": "Exemple of Regression Tests for the Garage site.",
  "description": "The browser is Google Chrome, FireFox or Internet Explorer.",
  "id": "exemple-of-regression-tests-for-the-garage-site.",
  "keyword": "Feature",
  "tags": [
    {
      "line": 23,
      "name": "@tag"
    }
  ]
});
formatter.before({
  "duration": 9889717145,
  "status": "passed"
});
formatter.scenario({
  "line": 27,
  "name": "Given user is on Garage home page",
  "description": "",
  "id": "exemple-of-regression-tests-for-the-garage-site.;given-user-is-on-garage-home-page",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 28,
  "name": "user selects a category \"CLOTHING\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "CLOTHING",
      "offset": 25
    }
  ],
  "location": "Steps_Garage.user_selects_a_category(String)"
});
formatter.result({
  "duration": 6303315985,
  "status": "passed"
});
formatter.after({
  "duration": 1564478854,
  "status": "passed"
});
});