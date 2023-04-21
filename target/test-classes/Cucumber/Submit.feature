@tag
Feature: Purchase the order from Ecommerce site
  I want to use this template for my feature file

  Background:
    Given I laneded on Ecomerce page
  
  
  
  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <email> and password<password>
    When I add the product <productname> from cart
    And Checkout the product <productname> and submit the order
    Then verify "THANKYOU FOR THE ORDER." message is displayed

    Examples:   
      | email                       | password    | productname     |
      | cravi.ravikumar1@gmail.com  |Ravi@5694    | ZARA COAT 3     |
      |anshika@gmail.com            |Iamking@000  | ADIDAS ORIGINAL |
