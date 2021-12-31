# Friday calendar

## Getting started

Friday Calendar is an application that prevents its users from being late.

## Prerequisities

* openjdk 17.0.1

## Build the project

```
./mwnw clean install
```

This command will install all the dependencies.
It will even install maven, npm et node if there are not already installed.

## Launch the calendar

```
java -jar target/FridayCalendar.jar
```

It will run the built project and run it on 8080 server port.
In case the port is already taken, the application will not launch.

## Features

This calendar has the following features:

* It can create a user
* It can display an event

## Still on progress

Friday is also on progress and not fully implemented, we are still looking for
those features:

* Synchronization with Google Calendar
* Add/Update/Remove an event from the database
* Determine how much times it takes to go from one location to another
* Prevent event from being accessed by any other user
* Implement calendar import using icalendar norm
* Add recurring event
* Implement session for backend

## Reported bug

* If the user changes the timezone, the application will not run properly 
  (because of `DateFormatter` that uses an incorrect pattern)
* Event is not bounded to its corresponding user when saving the event into the
  database (to fix it, spring session is needed)

## Unit test

Implemented tests covers only the main functionality of each method, for
example if we are adding an event, we are just verify if the event was
correctly added into the database.

We are not running bound test on each method, so we do not guarantee the safety 
of each method in case of unexpected provided value or application state.
