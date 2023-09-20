# Team Management Web Application

## Project Description
The Team Management Web Application is a monolithic backend developed using Spring Boot that provides a RESTful API which allows admin users to create and manage youth sports teams. Users can register, create teams, assign coaches, and add players to their teams. This application simplifies team management for coaches and team managers and allows players to view their assigned team and coaches.

## Tools and Technologies Used
- **Spring Boot:** Powers the backend.
- **H2 Database:** Manages data storage.
- **Spring Security:** Ensures data security.
- **JWT Tokens:** Provides authentication and authorization.
- **Tomcat Server:** Hosts the application.
- **MVC Architecture:** Follows a clean code structure.
- **Documentation:** Well-documented codebase.
- **GitHub:** Version control and collaboration platform.
- **Spring RESTful API:** Offers user-friendly interaction. 

## Project Approach
I started the project by writing user stories and creating an Entity-Relationship Diagram (ERD) to design the database schema. In addition, I established a GitHub Project to meticulously outline the project into manageable deliverables and associated timelines. 

### User Stories
My project began with a clear set of user stories that guided my development process. The user stories were written with various user roles in mind, including new users, registered users, admins, coaches, and players.
[Link to User Stories](./User%20Stories.txt)
### ERD Diagram
My project contains multiple models, including User, Coach, Player, and Team. The models were connected as such:
- **User Model:** The User model represents individuals who interact with the Team Management Web Application. It serves as the foundation for user accounts, enabling authentication and authorization for various actions within the application.
- **Team Model:** The Team model represents a sports team within the application. Its primary purpose is to manage information related to sports teams, including their name, location, and associations with other entities. 
- **Coach Model:** The Coach model represents individuals who serve as coaches for sports teams within the application. Coaches are associated with the specific teams they coach as well as responsible for training and coaching multiple players.
- **Player Model:** The Player model represents individual players who are part of a sports team. Its primary purpose is to store player-specific data and manage player-team relationships.
[Link to Entity-Relationship Diagram](./Sports%20Team%20Management%20ERD%20Diagram.png)
### Planning Documentation
This project management board encompassed columns such as "Todo," "In Progress," "Done," and "Tested." The thorough planning documentation provided a structured roadmap, facilitating a streamlined workflow that minimized errors and ensured the project's smooth progression.
[Link to GitHub Project](https://github.com/users/ashleyshakir/projects/1)


## Hurdles
- **Hurdle:** Initially, I named the ID column for the User entity as "userId" and for the Team entity as "teamId." This decision led to issues when creating custom findBy methods in the team repository. I intended to use findByNameAndId, but I needed to use findByNameAndUser_UserId. This adjustment was necessary to instruct Spring Data JPA to search for the "UserId" property within the "User" property.

- **Lesson learned:** Spring Data JPA relies on the method name to auto-generate queries based on entity properties and method parameters. Therefore, precise method naming is critical for Spring Data JPA to interpret the method correctly.
