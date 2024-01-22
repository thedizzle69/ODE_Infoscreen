# README for ODE_Infoscreen

### Java Infoscreen by Omerovic, Bißmann, Singh

(important note: I updated the dependency management in the pom and updated some javafx 17.0.6 dependencies local in the project structure. I don't know if this effects anybody. The jars should work flawlessly tho as I alraeady compiled them. If you have issues please go to the project structure/ ext libs and kick the java base 11 dependecies (2 libs) and add the java 17.0.6 lib)

This is a project for the course "Object-oriented Software Development" at the University of Applied Sciences Wien.

The project is a Java application that simulates a simple infoscreen. It is a simple JavaFX application that displays a few images and text on the screen. The application is built using Maven and the JavaFX library.

The link to the repository is: https: https://github.com/thedizzle69/ODE_Infoscreen

## Documentation

The docs are located in the "docs" folder in the root directory of the project. You will find everything you need to know about the project in the "index.html" file.

## How to JAVA

Before running the application, make sure you have at least Java 17 installed on your machine.

The project also runs fine on Java 21, tho. (I just wanted to flex that I have Java 21 installed)

If you need any assistance, please refer to the "How_to_install_Java.md" file in the root directory of the project. (this is just a reminder for myself, in case I HAVE THE WRONG JAVA VERSION INSTALLED)

# How to JAVA FX!!! You need JFX to access the apps. I will not provide a script to install it for you. You have to do it yourself. I will provide a HowTo tho.

Before running the application, make sure you have at least JavaFX 17 installed on your machine. You will need it to run the application.

1. Install JAVAFX by running `wget https://download2.gluonhq.com/openjfx/21.0.2/openjfx-21.0.2_linux-x64_bin-sdk.zip`
2. Unzip the file by running `unzip openjfx-21.0.2_linux-x64_bin-sdk.zip -d -d /YourPathToTheJFX/javafx-sdk`

### Please adapt the path and use it in the following commands

## How to run the application

There are 2 ways two run the application. You can either run the jar file or you can run the application compiling it yourself.


### How to run the application by using the jars

1. Open the terminal and navigate to the folder containing the jar file. `cd jar`
2. Run the ClientApp using the following command: `java --module-path /YourPathToTheJFX/javafx-sdk/javafx-sdk-21.0.2/lib --add-modules javafx.controls,javafx.fxml -jar jar/ClientApp.jar`
3. Run the ServerApp using the following command: `java --module-path /YourPathToTheJFX/javafx-sdk/javafx-sdk-21.0.2/lib --add-modules javafx.controls,javafx.fxml -jar jar/ServerApp.jar`

### How to run the application by compiling it yourself

1. Open the terminal and navigate to the main directory of the project. `cd ODE_Infoscreen`
2. Compile the ClientApp by using the following command: `javac --module-path /YourPathToTheJFX/javafx-sdk/javafx-sdk-21.0.2/lib --add-modules javafx.controls,javafx.fxml -d jar/ClientApp src/main/java/client/*.java src/main/java/resources/*.java`
3. Compile the ServerApp by using the following command: `javac --module-path /YourPathToTheJFX/javafx-sdk/javafx-sdk-21.0.2/lib --add-modules javafx.controls,javafx.fxml -d jar/ServerApp src/main/java/client/*.java src/main/java/resources/*.java src/main/java/server/*.java`

4. Create the jar file for the ClientApp by using the following command: `jar cfe jar/ClientApp.jar client.ClientApp -C jar/ClientApp .`
5. Create the jar file for the ServerApp by using the following command: `jar cfe jar/ServerApp.jar server.ServerApp -C jar/ServerApp .`

6. Run the ClientApp using the following command: `java --module-path /YourPathToTheJFX/javafx-sdk/javafx-sdk-21.0.2/lib --add-modules javafx.controls,javafx.fxml -jar jar/ClientApp.jar`
7. Run the ServerApp using the following command: `java --module-path /YourPathToTheJFX/javafx-sdk/javafx-sdk-21.0.2/lib --add-modules javafx.controls,javafx.fxml -jar jar/ServerApp.jar`

## Now you should got the app up and running

In the ClientApp there will pop up a login screen. You can login with the following credentials:
admin adminkey
jaisingh jaisinghkey

### Credentials when using the app

There is a csv file in each app directory containing the credentials for the users. The files are called "client_credentials.csv" and "server_credentials.csv" and are located in the "resources" directory.
The first column represents the username and the second column represents the password. The third column represents the real name of the user.

# Using the apps

## ClientApp

At first you will have to login, as mentioned above. After that you will be able to send content to the server.

The ClientApp lets you either upload a text or upload an image via the file explorer. When having texted or chosen a file you can send via the "Send" button.

## ServerApp

The ServerApp will take the connection by the Client and when it gets sent a content it will verify the user and if the Credentials are correct it will store the content in the queue.

You can now click on the desired content and press the button "Display Chosen Content" and it will open a new window displaying the chosen content.

# This is about it. Have fun using the app.