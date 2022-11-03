Feature: Add new todo item 

@tag1
Scenario Outline: Add new todo item 
    Given Navigate to the url "https://todomvc.com/examples/vue/"
    And Enter todo item in the test box as <ToDoText>
    When Click on enter
    Then Entered todo item <ToDoText> should be display below the text box
    
Examples:
  
  | ToDoText |
  | "First Todo" |
  | "Second Todo" |