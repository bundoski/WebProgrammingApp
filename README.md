# Web Programming Application (something like a basic Moodle)
-------------------------------------------------------------
Go to localhost:9092 after starting the application to view in browser.
It should direct you to log in to have privileges as an admin, and if you try to open localhost:9092/courses, it will only display the courses that have been added,
without any permissions to change/add or do anything only an admin is privileged to do.
User:admin
Password:admin
After logging this should be the display:
![image](https://github.com/bundoski/WebProgrammingApp/assets/93097556/5fcc5909-33bf-4272-a0ef-1685e10e23af)
![image](https://github.com/bundoski/WebProgrammingApp/assets/93097556/1821eacb-ef99-4922-9018-e9499e893f25)
The element in All Courses, Consultations and News is an Accordion, which will collapse the previously opened element and open the current (clicked one)

The functionalities of the application are mostly CRUD, and i have it connected to a PostgreSQL to save any changes made to it, in order not to be an In Memory storage,
so anything that is added/deleted/edited on the application will be updated in the database even if closed.
Functionalities:
Add a new course, and also Edit, delete or show students attending (available if you click All Courses on the Courses page.)
![image](https://github.com/bundoski/WebProgrammingApp/assets/93097556/0e20b2d9-b806-4304-bb40-e75d0fc05af3)
Show All Students and List Courses, which list which courses does the student take.
![image](https://github.com/bundoski/WebProgrammingApp/assets/93097556/b1fee7d0-7322-4a0d-9d7c-161a44ab987c)
Show All Teachers and Add new teacher. On this page, there is also Edit or Delete a teacher.
![image](https://github.com/bundoski/WebProgrammingApp/assets/93097556/44389c8a-1b84-4a0a-9b0c-c287f5acf150)
Login / Logout option.

