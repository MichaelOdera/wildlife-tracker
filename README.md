# Wild-Life Tracker

This project was generated with [JAVA](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html) version 11.

## Author
[Michael Ochieng' Odera](https://www.github.com/MichaelOdera)

## Date Published
17th February 2020


## Contributors
Michael Ochieng' Odera


## Contacts
In case of any bugs or improvements, contact me through enmail:michogelira@gmail.com or phone:+254733258286

## Development server
developed to heroku

## Code Beat Badge
[![codebeat badge](https://codebeat.co/badges/d4ca79eb-00e0-4379-8976-158bda8572d0)](https://codebeat.co/projects/github-com-michaelodera-wildlife-tracker-dev)
## Bugs Encountered
There were no major bugs to be encountered and the console would prove to be most helpful in case of any need to log out the errors

## Technologies used
* Java11
* CSS3
* HTML5
* Gradle
* Spark Framework
* Heroku
* Loaders
* Maven
* Postgres
* SQL


## Installation
It is imperative that you install a JavaRuntimeEnvironment IDEA and Gradle dependencies else the system won't funtion as required. Knwoledge 
of HTML5 and CSS3 is also mandatory. Knowledge of working with Java is also important.

## Description
The project above, allows the user as a ranger to enter the sightings of an animal and the animal is to be determined as either endangered or non-endangered. The various animals entered can be determined as related to the various and particular rangers who entered especially relating to their various IDs..

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.



## Live Link
This is the live link to my website https://mikes-wildlifetracker.herokuapp.com/

Run `gradle run` for a dev server. Navigate to `http://localhost:4567/`. The app will not automatically change if you make any changes white the app is still live. A rerun of the command will b needed in this case.

## Code scaffolding

Run `netstat -pliten |grep java` to check for ports that are being used by java processes. You can also use `gradle run|build`.

## Build

Run `gradle build` to build the project. The `build.gradle` file is important for any of the required process dependencies to function properly.


## PSQL
You can create your various tables using `In PSQL:
                                          CREATE DATABASE wildlife_tracker;
                                          CREATE TABLE rangers (id serial PRIMARY KEY, rangerName varchar, badgeNumber varchar, email varachar);
                                          CREATE TABLE animals (id serial PRIMARY KEY, animalName varchar, animalAge varchar, animalHealth varchar, rangerId int, type varchar);
                                          CREATE TABLE sightings(id serial PRIMARY KEY, animalLocation varchar, rangerId int);
                                          CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;`
## Running unit tests

Run `run test` to execute the unit tests on your local directory after git cloning or downloading.


## Further help

To get more help on the Spark 11 use go check out the [APACHE SPARK SITE](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html).

##  LICENSE
MIT [LICENSE](LICENSE)