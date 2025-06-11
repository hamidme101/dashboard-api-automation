Feature: Get All Ticket List

Scenario: Fetch all tickets with pagination.
  Given url baseUrl + '/external-ticket/all-with-last-msg/5487987'
  And header authorization = authToken
  And param tzoff = '-360'
  And param isPagedResult = 'true'
  And param pageNo = '0'
  And param pageSize = '10'
  When method get
  Then status 200
  ## Then status 201
  And print response