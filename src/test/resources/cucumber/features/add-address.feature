Feature: Add product

  Scenario Outline: adding address
    Given an open browser on store page and sign user
    When create new address with "<alias>" "<firstname>" "<lastname>" "<company>" "<vatNumber>" "<address>" "<addressComplement>" "<city>" "<postcode>" "<phone>"
    Then an address is created with "<alias>" "<firstname>" "<lastname>" "<company>" "<vatNumber>" "<address>" "<addressComplement>" "<city>" "<postcode>" "<phone>"
    Then close browser

    Examples:
      | alias      | firstname | lastname    | company | vatNumber  | address      | addressComplement | city    | postcode | phone     |
      | Test alias | jan       | januszewski | A7IT    | 0000000000 | Test street  | Test Complement   | Gliwice | 44-100   | 500500500 |
      | Test alias | jan       | januszewski |         |            | Test street  |                   | Gliwice | 44-100   | 500500500 |
      | alias2     | jan       | januszewski | XYZ     | 2138932977 | Daszynskiego | complement1       | Gliwice | 44-101   | 602581335 |