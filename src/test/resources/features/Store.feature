Feature: E-Cart Application

Scenario: Valid login
Given User is on launch page
When User enters credentials
Then Should display Home Page

Scenario Outline: Adding Items
When User adds "<Items>" to cart
Then Should display success message

Examples:
 | Items |
 | Nokia lumia 1520 |
 | Nexus 6 |
 | Samsung galaxy s7 |
 
Scenario: Deleting Item
When User deletes an item
Then Item should be deleted from the cart

Scenario: Placing Order
When User enters credentials and places order
Then Order must be placed