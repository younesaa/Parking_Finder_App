Parking Finder App
The Parking Finder App server application built with Spring Boot and designed to help users find nearby parking locations in any city. The app uses data from a publicly available API to display information about available parking locations, including location details and the number of available spaces.

Getting Started
To get started with the Parking Finder App, follow these steps:

Clone the repository to your local machine:

git clone https://github.com/younesaa/Parking_Finder_App


Open the project in your preferred Java IDE.

Update the application.properties file with your own database configuration details. You will need to create a database and specify the database URL, username, and password.

Build and run the application using your IDE or by running the following command in the terminal:

./mvnw spring-boot:run

Once the application is running, open your web browser and navigate to http://localhost:8080 to view the app.

Or insert all classes and interfaces provided with you builded app 

Features
The Parking Finder App includes the following features:

View a list of available parking locations in any city.

cmd : curl -X GET http://localhost:8080/api/parkings

Sort parking locations by distance or availability.

cmd : curl -X GET 'http://localhost:8080/api/parkings?latitude=46.5802&longitude=0.3403&radius=500'
