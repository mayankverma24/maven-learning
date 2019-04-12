Feature: Application Login 

Scenario Outline: Home page default login 
	Given User is on NetBanking landing page 
	When User login into application with <username> and password <password> 
	Then Home page is displayed 
	And Cards displayed are <result> 
	
Examples:
|username	|password	|result	|
|ram		|12345		|true	|
|shyam		|345678		|false	|