Feature: Get All BOT Info

Background:
    * def botPayload = read('classpath:demo/bot_info_rest_controller/data/botInfo.json')
    * def addFaqPayload = botPayload.createFaq
    * def JsonModifier = Java.type('demo.bot_info_rest_controller.util.JsonModifier')
    * def updated = JsonModifier.updateFaqName(botPayload)
    * def modifiedFaqName = updated.createFaq.name
    * print modifiedFaqName
    * url baseUrl
    * header Accept = '*/*'
    * header Content-Type = 'application/json'
    * header Authorization = globalAuthToken
    * header User-Agent = 'REVECHAT-MOBILE-APP'
    * header Cookie = 'JSESSIONID=YjA5OTQyNDMtMGJlMi00YmQ5LWE0OGYtNTcxNmY1YTllNjhh; revechatLocaleCookie=en'

Scenario: Fetch all BOT.
  Given url baseUrl + '/bot-info/5487987'
  When method get
  Then status 200
  * match response.status == 'success'
  * match response.message == 'found'

Scenario: Add FAQ.
    Given url baseUrl + '/bot/faq/add/13424/5487987'
    And request updated.createFaq
    When method post
    Then status 200
    * print response.body.name
    * print modifiedFaqName
    * match response.body.name == modifiedFaqName