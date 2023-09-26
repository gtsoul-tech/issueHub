# issueHub
<h1>"Issue Tracking and Project Management Platform"</h1>

The goals of the project include:

1.Implementation of an online platform for issue tracking and project management.<br>
2.Improved communication among collaborators.<br>
3.Timely updates for collaborators.<br>
4.Easy organization of task assignments.<br>
5.Finding collaborators or projects for employment.<br>
6.Management of projects and their tasks.<br>

This project draws inspiration from Jira and GitHub Issues.

<h2>The front-end of the project is built using <bold>jQuery</bold>, <bold>JavaScript</bold>, <bold>Bootstrap 4</bold>, <bold>HTML</bold>, and <bold>JSP</bold>.</h2><br>

<h2>The back-end of the project utilizes <bold>Eclipse for Java development</bold>, <bold>MySQL 8.1</bold> for the database, <bold>Java Servlets</bold>,
the <bold>Hibernate Framework</bold>, and <bold>Apache Tomcat</bold> 9.1 for deployment.</h2><br>

<h1>The application's database utilizes 7 tables:</h1><br>

![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/474a254e-62ef-4dd8-bfce-cd7ded21c8b3)

<h1>The platform design specifications include:</h1>

User login/registration and logout functionality.
Account management (tracking account details, password change, account deletion, invitation control).<br>
Team and member management (tracking team information, different permissions for members and team leaders, request and member control).<br>
Error management (tracking team details, error notification, conversion, resolution, deletion, and error resolution).<br>
Member and team search through the platform based on queries<br>

<h1>By completing the form, the user can create their account</h1>
Each form checks both at the client and server side if the responses have the correct attributes (form validation), otherwise it displays an error message.<br>

![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/5192aead-987d-4982-9720-af663304bd77)

<h1>Login:</h1>

![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/5809dc84-07be-49f6-9741-a88a426c81bc)

<h1>At the profile the user can:</h1>
View their account information.<br>
Delete their account by providing the password.<br>
Change their password by providing the old password.<br>
See the groups they have been invited to and accept or decline invitations.<br>
Delete the invitations they have sent themselves.<br>

![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/a67e0e79-c6e5-4009-99b9-87d4e7a23b1f)

On this tab, they can select one of the projects they oversee to manage its information<br>
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/ae1225e2-b943-49bb-bb8a-25d4b5b51862)

Management of the project (deletion, modification of its description) and management of the members (member information and the option to delete members)<br>

![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/a747ff2a-3757-45f2-91fe-11a08bdeafd2)

The user can view issue information and either delete them, assign them to a member, or edit their fields.<br>
The user can create an issue by initially providing only the summary and description.<br>
The team leader is the only one who can change almost all issue fields.<br>
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/4f970d81-18f8-4f2c-9af9-c99b653064bc)
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/20d9eca4-a53c-471a-84ae-7a3cb25076e3)

<h1>As a member you get a different view:</h1>
They can view project information such as issues and members. They can also be removed from the team if desired.<br>

![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/f9cf6ba2-f255-4bdc-95c7-4a075a8d1254)

<h1>On the request tab:</h1><br>

![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/3a0bc692-e78a-4fd1-b2cf-d5663fca4e3b)

Each user can have as many projects as they desire. If the project already exists in the database, it will display an error message<br>
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/dadf8c1e-0ccc-45fb-a8f4-6ca915556766)

On the platform each user can search for members:<br>
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/1e8ca2f0-afc0-4b52-a55c-7812edab0b4f)

or a project to join based on the title and description:<br>
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/d156f594-254e-40d7-8865-7772fdef7406)

