Http service example. 
Task: create http-method, which returns information about some user from Siebel CRM. 
Method description: POST, consumes JSON. 
Input parameters:
1) sessionId - identifier of calling user. Front system has it after user passed the authentication. 
2) userSiebelId - user's siebel identifier. 
Steps:
1) Check inpur parameters. All parameters are required. 
2) Check of availability. 
2.1) Call auth-service method to make sure that session validity is not expired. 
2.2) Make sure that calling user has admin role. 
3) Get user information from Siebel CRM by calling SOAP method. 
4) Return information from Siebel. 
