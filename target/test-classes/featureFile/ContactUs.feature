Feature: Contact Us Form

  Scenario Outline: Validating form error message display
    Given that user is on the contact us page
    Then user enter "<Name>"
    And user select "<Company Size>"
    And select the "<Service>" user is looking for
    And user click on the check box to receive other communications
    And user click on submit button
    Then user Validate if "<Validated Text>" is displayed

    Examples:
      | Name     | Validated Text                       | Company Size | Service                              |  |
      | Ndivhuwo | Please complete all required fields. | 50-100       | Scale engineering with managed teams |  |