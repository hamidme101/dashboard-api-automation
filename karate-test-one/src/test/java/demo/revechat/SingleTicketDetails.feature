Feature: Get Single Ticket Details

Scenario: Fetch single ticket by ID
  Given url baseUrl + '/external-ticket/681b1127b5c1704e019545cc'
  And header authorization = authToken
  When method get
  Then status 200
  ## Then status 201
  And print response