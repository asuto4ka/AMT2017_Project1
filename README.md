# AMT2017_Project1
## Group List :
- Emmanuel Schmid
- Anastasia Zharkova

# Project description and implementation choises

The purpose of this project is to deploy a web application using Java EE specifications.  We decided to deploy the application on Wildflay server. Our application allows to take a book from a library ( book a book :) )  Every student can check if a book is avalaible on the moment and if not, he/she can reserve a book when it's gonna be returned. In order to be able to take a book, student has to be looged into the system.
This is not the normal way to imlpement a library and renting book service, but we decided to make it this way in order to implement different security level. A book can be taken only for two weeks and we have perfectly responsible students who neved damaged book ou forget to return it. 
Every student, book, and rental agreement has an unique ID. A database (MySQL) will contain all information about books, students, and rental agreements.  Our application will have an api REST in order to execute CRUD commands. All system will be deploy inside a docked contener. 


TODO List  ( improvements ) :
If at the end of doing basic things, we have time to improve some aspects of our applications it will be :
1) For the moment there is no automatic "log out service". Student stay loged in as long as he/she wants. There is only one way to be logged out: do it by ourself
2) Gestion de concurence. It's not possible to make a reservation for a same book by different person at the same time. But for a moment, "first in - first served" concept is applyed. The first one who logged into the system is consider as a "most important"
3) We decided that a book may ba taken only for two weeks, but we wanted let student decide for how long he/she needs it. 

