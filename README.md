<h1>internet shop
 
<h4>Internet shop, this is my own training project on java, I used and experimented with maven, 
web server Tomcat 9.0.19, some interesting dependencies: log4j, junit, jstl, servlet, lombok, 
mysql and checkstyle-plugin. In this program I connected to the database MySQL from jdbc. 
This program is not like anything else, it works quickly and I spent a very interesting time 
while writing it. I was obsessed with this project and tried to make it as good as possible.</h4>    

 
![](
https://camo.githubusercontent.com/6665a0c8ffeaf2c0caf52baeab22d63849fcbf86/68747470733a2f2f696d672e736869656c64732e696f2f6769746875622f6c616e6775616765732f746f702f53657267697941676565762f696e7465726e65746d61726b6574)
![](https://github.com/MateAcademy/internetshop/blob/26/src/main/webapp/WEB-INF/images/internetshop.png)
# Table of Contents
[Project purpose](#purpose)

[Project structure](#structure)

[For developer](#developer-start)

[Author](#author)


# <a name="purpose"></a>Project purpose
This project is a prototype of an online market. 
Where its main functionality is realized.
<hr>

Available functions for **ALL** users: 
 >- view menu of the store
 >- view items of the store
 >- registration
 >- log in
 >- log out
 
 Available functions for users with a **USER** role only: 
 >- add to user's bucket
 >- delete from user's bucket
 >- view all user's orders
 >- complete order
 >- view a lists of selected items in user`s bucket
 
 Available functions for users with an **ADMIN** role only:
 >- can visit admin menu page
 >- add items to the store
 >- delete items from the store
 >- view a list of all users
 >- delete users from the store
<hr>

# <a name="structure"></a>Project Structure
- Java 11
- Maven 4.0.0
- javax.servlet 3.1.0
- jstl 1.2
- log4j 1.2.17
- maven-checkstyle-plugin
- mysql-connector-java 8.0.18
<hr>

# <a name="developer-start"></a>For developer

Open the project in your IDE.

Add it as maven project.

Import dependencies.

Add sdk 11 in project structure.

Configure **Tomcat** local server:
> add artifact;
>
> add sdk 11;

<hr>
Create a schema in any SQL database.

Execute query from file **init_db.sql** to create all the tables required by this app.
    src                 
     └── main            
         └── resourses        
                └── init_db.sql 
     
<hr>
In **Factory** class input your **DB url**, **username** and **password** from your DB to create a connection.

    src                 
     └── main            
        └── java        
            └── mate
                └── academy
                    └── internetshop
                          └────factory
                                └── Factory.java
     
Change a path in **log4j.properties**. It has to reach your logFile.

    src                 
     └── main            
         └── resourses        
                └── log4j.properties 
                
Run the project.

input **/inject** in address bar in your browser to create 2 users :

##### role = ADMIN
>login = admin
>
>password = admin
>
##### role = USER
>login = user
>
>password = user

<hr>

 <a name="authors"></a>Author:
 
 [Sergiy Klunniy](https://github.com/MateAcademy)
