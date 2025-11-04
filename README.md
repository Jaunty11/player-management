⚽ Player Management System (Spring Boot + H2)
A lightweight backend REST API for managing players, teams, coaches, and matches — built with Spring Boot, JPA, and H2 in-memory database.


🧩 Overview
The Player Management System provides RESTful APIs to handle teams, players, matches, coaches, and player statistics.
It demonstrates proper entity relationships, cascading, and orphan removal using JPA and Spring Data.
🏗️ Architecture
src/main/java/com/yourname/player_management
├── controller   →  REST Controllers  
├── dto          →  Data Transfer Objects  
├── entity       →  JPA Entities  
├── enums        →  Enum Definitions  
├── exception    →  Global Exception Handlers  
├── mapper       →  DTO ↔ Entity Mappers  
├── repository   →  Spring Data JPA Repositories  
└── service      →  Business Logic Layer


🧬 Entity Relationships (H2 Database)
Team 1 ─── 1 Coach  
Team 1 ─── * Players  
Player 1 ─── 1 Statistics  
Player * ─── * Match  
The schema auto-generates in H2 (in-memory).
Access it here 👉 http://localhost:8080/h2-console


🧱 Entity Summary
Entity	Fields	Relationships	Cascade	Orphan Removal
Team	id, name, city	@OneToOne Coach, @OneToMany Players	CascadeType.ALL	✅ Yes (for Players)
Player	id, firstName, lastName, position	@ManyToOne Team, @OneToOne Statistics, @ManyToMany Matches	CascadeType.ALL (on Statistics)	✅ Yes (for Statistics)
Coach	id, name, experience	@OneToOne Team	No cascade	❌ No
Match	id, matchDate, opponent, venue	@ManyToMany Players	CascadeType.MERGE, PERSIST	❌ No
Statistics	id, goals, assists, yellowCards	@OneToOne Player	No cascade	❌ No


💡 Behavior Example
Deleting a Team deletes all its Players automatically due to cascade = CascadeType.ALL and orphanRemoval = true.
Deleting a Player deletes their associated Statistics (1:1).
Deleting a Match does not delete Players (since it’s ManyToMany).


⚙️ Tech Stack
Layer	Technology
Backend	Spring Boot 3
ORM	Spring Data JPA
Database	H2 (In-Memory)
Build Tool	Maven
Language	Java 17
Logging	SLF4J / Spring Boot Logs
Lombok	Boilerplate Reduction


🧠 Features
✅ Proper Entity Mapping (OneToOne, OneToMany, ManyToMany)
✅ Cascade Operations & Orphan Removal
✅ Layered Architecture (Controller → Service → Repository)
✅ DTO ↔ Entity Mapping (Lombok Builder Pattern)
✅ Centralized Exception Handling
✅ Auto Schema Creation in H2
✅ Easy migration to MySQL/PostgreSQL


🧾 How to Run
mvn spring-boot:run
Then open:
API Base URL: http://localhost:8080/api
H2 Console: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:player_management
Username: sa
Password: (leave blank)



🧪 Testing via Postman
Base URL: http://localhost:8080/api
Action	Method	Endpoint	Example Body
Create Team	POST	/teams	{ "name": "Alpha FC", "city": "London", "coachId": 1 }
Get All Teams	GET	/teams	—
Add Player	POST	/players	{ "firstName": "John", "lastName": "Doe", "teamId": 1 }
Get Players	GET	/players	—
Create Match	POST	/matches	{ "matchDate": "2025-01-01", "opponent": "Beta FC", "venue": "Stadium A" }
Get Matches	GET	/matches	—
🧩 Tip: Always include header Content-Type: application/json when sending POST or PUT requests.




🧭 Architecture Diagram
         +----------------------+
         |   REST Controller    |
         +----------+-----------+
                    |
                    v
          +---------+----------+
          |       Service      |
          +---------+----------+
                    |
                    v
          +---------+----------+
          |     Repository      |
          +---------+----------+
                    |
                    v
          +---------+----------+
          |      H2 Database    |
          +--------------------+
📊 ER Diagram (Conceptual)
  +--------+       1 ─── *      +----------+
  |  Team  |--------------------|  Player  |
  +--------+                    +----------+
      | 1                             |
      |                               |
      | 1                             | 1
  +--------+                      +------------+
  | Coach  |                      | Statistics |
  +--------+                      +------------+

           * ─── *  
  +------------------+
  |      Match       |
  +------------------+
