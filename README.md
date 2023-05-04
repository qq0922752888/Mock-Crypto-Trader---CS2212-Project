# CS2212 CryptoTrader
 Final deliverable for CS2212 group 33              
 Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI               

# Running Program          
After unzipping import it as a new maven project. Ensure that the project compliance and JRE is Java-1.7 to run.
- If it is not 1.7, Go to LoginModel class and hover over BufferedReader error. The quickfix will ensure the
java project compliance and JRE are updated to 1.7         
Main method is located in the StartProgram.java, simply run that to run the login view.      
Package: ~/CryptoTrader/src/main/java/cryptoTrader/controllers/startProgramController/StartProgram.java       

# Username & passwords 
Can add new username/password combination in PasswordAuthenticationDatabase.txt        
Username: Dog       
Password: cat       

# Design Patterns
Factory: TradingStrategyFactory.java           
Strategy: TradingStrategy.java           
Facade: All controllers (Interaction between View & Model through controller).           
Singleton: All Model/View/Controller             
Observor: TradeView --> Updates TradeGraphView & TradeTableView             

# Third Party Code Used
Accessed on March 27, 2022 from  http://www.camick.com/java/source/TableCellListener.java  
Written by Rob Camick   
Used in the TableCellListener.java for listening to changes in table cells. 

