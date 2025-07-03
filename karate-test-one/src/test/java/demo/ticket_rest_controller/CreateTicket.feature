Feature: Create Ticket via REVE Chat API

  Background:
    * def requestPayload = read('classpath:demo/ticket_rest_controller/data/create-ticket-request.json')
    ## * def expected = read('classpath:demo/ticket_rest_controller/data/expected-ticket-response.json')
    * def JsonModifier = Java.type('demo.ticket_rest_controller.util.JsonModifier')
    * def subject = JsonModifier.generateRandomSubject()
    * def email = 'dynamic.email@example.com'
    * def visitorName = 'Hamid Khan-5'
    * def modifiedJson = JsonModifier.updateField(requestPayload, 'ticket_subject', subject)
    * def modifiedJson = JsonModifier.updateField(requestPayload, 'ticket_recipient_email', email)
    * def modifiedJson = JsonModifier.updateField(requestPayload, 'ticket_recipient_name', visitorName)
    * url baseUrl
    * header Accept = '*/*'
    * header Content-Type = 'application/json'
    * header Authorization = globalAuthToken
    * header User-Agent = 'REVECHAT-MOBILE-APP'
    * header Cookie = 'JSESSIONID=YjA5OTQyNDMtMGJlMi00YmQ5LWE0OGYtNTcxNmY1YTllNjhh; revechatLocaleCookie=en'

  Scenario: Create a new support ticket
    Given path '/ticket'
    And request requestPayload
    When method post
    Then status 200
    * def responseBody = response.body
    * match responseBody.accountId == "5487987"
    * match responseBody.subject == subject
    * match responseBody.visitorInfo.email == email
    * match responseBody.visitorInfo.name == visitorName

