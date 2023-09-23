# issueHub
"Issue Tracking and Project Management Platform"

The goals of the project include:

1.Implementation of an online platform for issue tracking and project management.
2.Improved communication among collaborators.
3.Timely updates for collaborators.
4.Easy organization of task assignments.
5.Finding collaborators or projects for employment.
6.Management of projects and their tasks.

This project draws inspiration from Jira and GitHub Issues.

The front-end of the project is built using jQuery, JavaScript, Bootstrap 4, HTML, and JSP.
The back-end of the project utilizes Eclipse for Java development, MySQL 8.1 for the database, Java Servlets, 
the Hibernate Framework, and Apache Tomcat 9.1 for deployment.

The application's database utilizes 7 tables
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/eafca43d-f52e-42bd-b6dc-7a2c3fc968d9)

The platform design specifications include:

User login/registration and logout functionality.
Account management (tracking account details, password change, account deletion, invitation control).
Team and member management (tracking team information, different permissions for members and team leaders, request and member control).
Error management (tracking team details, error notification, conversion, resolution, deletion, and error resolution).
Member and team search through the platform based on queries

By completing the form, the user can create their account.
Each form checks both at the client and server side if the responses have the correct attributes (form validation), otherwise it displays an error message.
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/5a63290d-4649-4859-a806-dcd741f554f6)

Login:
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/36b220a2-5edb-4521-b134-2f136f5eba38)

At the profile the user can:
View their account information.
Delete their account by providing the password.
Change their password by providing the old password.
See the groups they have been invited to and accept or decline invitations.
Delete the invitations they have sent themselves.
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/2173a5ed-adf6-467a-9a62-fdde8a310836)

On this tab, they can select one of the projects they oversee to manage its information
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/f6389102-2d2e-4230-9746-f43e4510b7d8)

Management of the project (deletion, modification of its description) and management of the members (member information and the option to delete members)
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/fc8afdfd-d592-4057-82ab-f5dd38b9553b)


The user can view issue information and either delete them, assign them to a member, or edit their fields.
The user can create an issue by initially providing only the summary and description.
The team leader is the only one who can change almost all issue fields.
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/3a8de3d9-7206-4057-9497-d000200e0788)
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/e067b532-13b9-412c-8704-f7f502092bca)

As a member you get a different view:
They can view project information such as issues and members. They can also be removed from the team if desired.
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/adf25977-5271-4fd6-9a5a-d3199f01da8b)

On the request tab:
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/54cd3c62-2ce3-4333-89de-208b20561c6d)

Each user can have as many projects as they desire. If the project already exists in the database, it will display an error message
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/57f007be-d1d4-4393-839a-9fc3aa7a8f75)

On the platform each user can search for members:
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/44aa1870-95e1-4bbd-a5a1-db646f0f0fa8)

or a project to join based on the title and description:
![image](https://github.com/gtsoul-tech/issueHub/assets/56584633/b796602e-138f-4e95-a05b-7544e7235a47)

