# README

## About

Unfortunately I ran out of time to create the frontend which would actually house the messaging app.  However, you can currently create and save messages, follow users, etc. using a program like Postman. With another half hour to hour I could create a rudimentary web app to actually display the messages.


## Building the project
* The Database is built using postgresql - run the create.sh script to create the database or if you need to reset the database.  Three users, Alice, Bob, and Charlie, are automatically generated.
* Permissions for an app_user are given in create.sh script
* Currently the database will live on port 5432 of your local machine.
* The server provides API endpoints that can be tested through e.g. Postman:
    * You can "Get" at the endpoint "/" to see all messages.
    * You can "Post" a message at the endpoint "/" in the format "{
        "userId": <"value">, 
        "message": <"message text">}" 
    * You can "login" using only the user's name (Alice, Bob, or Charlie) by posting at the endpoint "/login".  The server will return the details of the user including the username, so, you can also easily impersonate any user you'd like.