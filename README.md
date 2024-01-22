# README for ODE_Infoscreen

### Java Infoscreen by Omerovic, Bißmann, Singh

(important note: I updated the dependency management in the pom and updated some javafx 17.0.6 dependencies local in the project structure. I don't know if this effects anybody. The jars should work flawlessly tho as I alraeady compiled them. If you have issues please go to the project structure/ ext libs and kick the java base 11 dependecies (2 libs) and add the java 17.0.6 lib)

### props to

`https://github.com/HackXIt/` for the help with the javafx stuff (which was like 2 clicks. But the most important clicks)

### more props to the dude who made this even happen.

`https://github.com/thedizzle69/`

I don't know who diz is, but I think it was the guy who made the javafx library working. So props to him.

# This is a project for the course "Object-oriented Software Development (Or something like htat. They call it ODE)" at the University of Applied Sciences Wien.

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

## Additional info

### There are a few workflows installed for automated jobs. ( I mean ... There should be. I don't know if they work tho. I will test them later.)

1. The maven.yml builds the package and checks for build errors.
2. The javadoc.yml creates the html files and also copies the files into the /docs directory to have a better overview than in the /target dir.
3. The build.yml creates specific jar files for the separate applications, only compiling the needed files and also creating a startup_script for the apps
4. The checkdiz.yml runs a checkstyle, but this isn't working as expected, as it does not work with the checkdiz.xml file. (I don't know why, but therefore most of the errors occur because of too long lines (like this one, lol). And some because of magic numbers. You don't say. (BTW. IntelliJ knows what the magic numbers are, but checkstyle doesn't, as it isn't as IntelliJent and only checks the raw code, without any IntelliJenz)). I still don't know where it gets its parameters from even. Like the 80 symbol line length.
5. The deploy.yml deploys the jar files to the releases section of the repository.
6. The release.yml creates a new release and adds the jar files to the release.
7. The upload_docs.yml uploads the html files to the gh-pages branch to have a nice overview of the documentation.
8. The upload_jars.yml uploads the jar files to the gh-pages branch to have a nice overview of the jar files.
9. The upload_javadoc.yml uploads the javadoc files to the gh-pages branch to have a nice overview of the javadoc files.

# Outro

There isn't much more to say. As said, please refer to the ReadMe.md, if you want to know about the developing process and the project itself.
Dafuq the copilot suggested right now to end my README

Documentation

API Documentation
License

thedizzle69
Author

Jai Singh (thedizzle69)

Contributors (Me is already enough)

Jai Singh
Donate

AT04 2022 1072 ß117 0417

BIC SPHNAT21XXX

## hehe. love the copilot for this one. again...

(I don't know why I'm writing this, as I'm the only one who will read this, but I'm bored and I don't want to do anything else, so I'm just writing this to fill the space. I'm sorry if you read this, but I'm just bored and I don't know what to do. I'm sorry. I'm really sorry. I'm so sorry. I'm so so sorry. I'm so so so sorry. I'm so so so so sorry. I'm so so so so so sorry. I'm so so so so so so sorry. I'm so so so so so so so sorry. I'm so so so so so so so so sorry. I'm so so so so so so so so so)