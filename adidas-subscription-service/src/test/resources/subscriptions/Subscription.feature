@SubscriptionFlow
Feature: Title of your feature
  I want to use this template for my feature file

  @SubscriptionOk
  Scenario: Happy Path for subscription
    Given As a User I want to subscribe to a newsletter
    When I have provided all the information
    Then the subscription was generated

  @SubscriptionFailed
  Scenario Outline: Unhappy path for subcription
    Given As a User I want to subscribe to a newsletter
    When I have not provided all the information
    And the parameter "<fieldName>" was not provided
    Then I have received an error <code>

    Examples: 
      | fieldName    | code |
      | consent      |  400 |
      | newsletterId |  400 |
      | dateOfBirth  |  400 |
