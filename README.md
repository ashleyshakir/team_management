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
I started the project by writing user stories and creating an Entity-Relationship Diagram (ERD) to design the database schema. In addition, I established a GitHub Project to meticulously outline the project into manageable deliverables and associated timelines. As I built the application, I documented REST API endpoints so that users could be aware of the functionality the app provides.

### User Stories
My project began with a clear set of user stories that guided my development process. The user stories were written with various user roles in mind, including new users, registered users, admins, coaches, and players.

[Link to User Stories](./User%20Stories.txt)<br>
### ERD Diagram
My project contains multiple models, including User, Coach, Player, and Team. The models were connected as such:
- **User Model:** The User model represents individuals who interact with the Team Management Web Application. It serves as the foundation for user accounts, enabling authentication and authorization for various actions within the application.
- **Team Model:** The Team model represents a sports team within the application. Its primary purpose is to manage information related to sports teams, including their name, location, and associations with other entities. 
- **Coach Model:** The Coach model represents individuals who serve as coaches for sports teams within the application. Coaches are associated with the specific teams they coach as well as responsible for training and coaching multiple players.
- **Player Model:** The Player model represents individual players who are part of a sports team. Its primary purpose is to store player-specific data and manage player-team relationships.

[Link to Entity-Relationship Diagram](./Sports%20Team%20Management%20ERD%20Diagram.png)<br>
### Planning Documentation
This project management board encompassed columns such as "Todo," "In Progress," "Done," and "Tested." The thorough planning documentation provided a structured roadmap, facilitating a streamlined workflow that minimized errors and ensured the project's smooth progression.

[Link to GitHub Project](https://github.com/users/ashleyshakir/projects/1)<br>
### REST API Endpoints
My project includes the following features:
- CRUD operations for teams, coaches, and players. 
- Secure authentication and personalization of endpoints using JWT tokens.
- Exception handling for graceful error responses to users.

Detailed documentation for the REST API endpoints can be found [here](./Spring-RestAPI-CRUD-Sports-Team-Management-App.xlsx). <br>

## Hurdles
- **Hurdle:** Initially, I named the ID column for the User entity as "userId" and for the Team entity as "teamId." This decision led to issues when creating custom findBy methods in the team repository. I intended to use findByNameAndId, but I needed to use findByNameAndUser_UserId. This adjustment was necessary to instruct Spring Data JPA to search for the "UserId" property within the "User" property.

- **Lesson learned:** Spring Data JPA relies on the method name to auto-generate queries based on entity properties and method parameters. Therefore, precise method naming is critical for Spring Data JPA to interpret the method correctly.

## Installation Instructions

To run the project, follow these steps:

1. **Prerequisites:**
   - Make sure you have Java and Spring Boot installed on your system.
   - You'll need an integrated development environment (IDE) like IntelliJ IDEA for a smoother development experience.

2. **Clone the Repository:**
   - Clone the project repository to your local machine using your preferred Git client or by running the following command in your terminal:
     ```
     git clone <https://github.com/ashleyshakir/team_management.git>
     ```

3. **Database Configuration (if necessary):**
   - If you're using a database other than H2, you'll need to configure the database settings.
   - Open the `application.properties` or `application.yml` file in your project's `src/main/resources` directory.
   - Set the database connection properties, including the database URL, username, and password, as needed.
   - Save the changes.

4. **Spring Profiles (if necessary):**
   - Spring profiles help manage different configurations for different environments (e.g., development, production).
   - Specify the active profile in the same `application.properties` or `application.yml` file:
     ```
     spring.profiles.active=dev
     ```
   - Create separate configuration files (e.g., `application-dev.properties`, `application-prod.properties`) for different profiles, if required.
   - Define environment-specific properties in these profile-specific configuration files.

5. **Run the Application:**
   - With the database settings and profiles configured, run your Spring Boot application using your IDE or by executing the appropriate Gradle/Maven command.
   - The application will automatically use the specified profile and database settings based on your environment.

By following these steps, you'll be able to set up and run the project smoothly.

## Credits
I would like to give credit to the following sources and individuals who have contributed to this project:
- [Spring Framework Documentation](https://spring.io/): Spring Boot and Spring Security documentation were invaluable.
- [JWT.io](https://jwt.io/): Resources on JSON Web Tokens helped implement secure authentication.
- [Suresh Sigera](https://github.com/sureshmelvinsigera): My incredible instructor for all the guidance, patience and amazing knowledge he has given me during this lesson!
## Future Enhancements
While the project meets its core objectives, future enhancements may include implementing user roles as well as including features like scheduling matches and managing team finances. 
## Conclusion
The Team Management Web Application simplifies the task of managing youth sports teams and enhances the user experience for administrators, coaches, team managers, and players. The use of Spring Boot, H2, Spring Security, and JWT tokens ensures a secure and efficient application.

