Feature: Validate Complete functionality of todo item 

@tag2
Scenario: Validate Complete functionality of todo item 
    And Get the text of to complete Todo item
    When Click on check box of any item
    Then Check the item moved to completed list or not
    