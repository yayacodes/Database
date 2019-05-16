# Database
3 SplayTrees that make up a database 

This was for my final project in my Data Structures class. The application is a database implementation that records 3 fields "population" "economic net worth" "name". Each of these fields are placed in either the popTree, econTree, nameTree that correspond to the values 1, 2, 3 respectively. 

The commands for the project are as follows:

`insert population economy name` 
- Ex: insert 1000 5000 NewYork
- Ex: insert 250 6000 California
- The app will then let you know if it was successfully placed in the database or if there were any duplicate fields.

`find field value` 
- This command will look through the appropriate tree based on the field value (1, 2, 3) for the value `value` it will then return the whole record across all 3 trees. 
- Ex: find 1 1000
- Ex: find 3 California 

`remove field value` 
- This command will remove the record across all trees by accessing the appropriate tree from the input (1, 2, 3) and obtaining the value. It will then subsequently remove its counterparts from the other corresponding trees. This command will return an error if the user is trying to remove a value that does not exist. 
- Ex: remove 2 5000
- Ex: remove 1 NewYork

`list field` 
- This command will preform a reverse in-order traversal of the field tree selected (1, 2, 3) and print out the values in 2D.
- Ex: list 1
- Ex: list 3

`makenull` reintializes an empty database

Hope you enjoy! All feedback is appreciated! 
