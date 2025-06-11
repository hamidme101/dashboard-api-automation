function fn() {
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);  
  // karate.configure('abortSuiteOnFailure', true);

  var config = {
     baseUrl: 'https://test-chat-022.revechat.com/rest/v1',
     authToken: 'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50SWQiOiI1NDg3OTg3Iiwic3ViIjoiYXV0aG9yaXphdGlvbiIsImV4cCI6MTc1MTg4NDA0MiwiaWF0IjoxNzQ5MjkyMDQyfQ.mCS1ZrMFhWqJyDAEf6-QYFfvZwHaXMmL7Dv0ydU300M',
   //  demoBaseUrl: protocol + '://127.0.0.1:' + port
   };

 /* var port = karate.properties['demo.server.port'] || '8080';
  var protocol = 'http';
  if (karate.properties['demo.server.https'] === 'true') {
    protocol = 'https';
    karate.configure('ssl', true);
  }  
  var config = { demoBaseUrl: protocol + '://127.0.0.1:' + port };
  if (karate.env !== 'mock') {
    // karate.configure('callSingleCache', { minutes: 1 });
    // 'callSingle' is guaranteed to run only once even across all threads
    var result = karate.callSingle('classpath:demo/headers/common-noheaders.feature', config);
    // and it sets a variable called 'authInfo' used in headers-single.feature
    config.authInfo = { authTime: result.time, authToken: result.token };
  } */
  return config;
}

/* function fn() {
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  // karate.configure('abortSuiteOnFailure', true);

  var port = karate.properties['demo.server.port'] || '8080';
  var protocol = 'http';
  if (karate.properties['demo.server.https'] === 'true') {
    protocol = 'https';
    karate.configure('ssl', true);
  }

  var config = {
    baseUrl: 'https://test-chat-02.revechat.com/rest/v1',
    authToken: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50SWQiOiIzMzE5NTcwIiwic3ViIjoiYXV0aG9yaXphdGlvbiIsImV4cCI6MTc0ODk0NTIzNCwiaWF0IjoxNzQ2MzUzMjM0fQ.KQJu1yto_UQGpUdt1sOBjM7KRG4OU5RuXE4xGRRdJoE',
    demoBaseUrl: protocol + '://127.0.0.1:' + port
  };

  if (karate.env !== 'mock') {
    // karate.configure('callSingleCache', { minutes: 1 });
    var result = karate.callSingle('classpath:demo/headers/common-noheaders.feature', config);
    config.authInfo = { authTime: result.time, authToken: result.token };
  }

  return config;
} */
