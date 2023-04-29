# ---------------------------------- #
### Room Finder ###

Commands:
> cd room-finder
> mvn clean install
> java -jar target/room-finder-0.0.1-SNAPSHOT.jar C:\RoomFinderInput1.txt

# ---------------------------------- #
### Plan Finder ###

Commands:
> cd plan-finder
> mvn clean install

To run as a webservice
> java -jar target/plan-finder-0.0.1-SNAPSHOT.jar

To run in terminal
> java -jar target/plan-finder-0.0.1-SNAPSHOT.jar C:\PlanFinderInput1.txt email,voice,admin

To use docker image
> docker pull rathandev/plan-finder-image:latest
> docker run -p 8080:8080 rathandev/plan-finder-image:latest

# ---------------------------------- #

>>> Check out Other/pics folder for testing results

# ---------------------------------- #

