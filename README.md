# Midterm Project (Audiofile)
![Album Page](https://drive.google.com/uc?export=view&id=1vyVArZDWAd9We46cfYoZShZpNfvaS_sw)
![Album Comment Page](https://drive.google.com/uc?export=view&id=16AcTJqHZSmqX05cG6bIaG1Xv4-UCFg3W)


## Overview
This was a team project to build a full-stack web application over the course of approximately 11 days. The website was developed by four individuals remotely using Zoom, Slack, Trello, Git, and Github for collaboration.
<br>
<br>
Our application is primarily a musical database centered around artists, songs, and albums. Users can create an account, login, and start adding/editing these entities to the database through our front-end UI. There are also basic interaction features between users that allow them leave comments on albums and place ratings on both songs and albums.

### File Structure
[Entites](https://github.com/KPalasti/MidtermProject/tree/main/JPAAudiophile/src/main/java/com/skilldistillery/audiophile/entities)
[DAOs](https://github.com/KPalasti/MidtermProject/tree/main/AudioFile/src/main/java/com/skilldistillery/audiophile/data)
[Database Files](https://github.com/KPalasti/MidtermProject/tree/main/DB)
[Controllers](https://github.com/KPalasti/MidtermProject/tree/main/AudioFile/src/main/java/com/skilldistillery/audiophile/controller)
[Stylesheets](https://github.com/KPalasti/MidtermProject/tree/main/AudioFile/src/main/webapp/css)
[Webpages (JSPs)](https://github.com/KPalasti/MidtermProject/tree/main/AudioFile/src/main/webapp/WEB-INF)

## Practices Used
- Object Relational Mapping
- CRUD
- Model View Controller
- Database Design
- Database Accessor Objects
- Test Driven Development
- Pair Programming

## Features
- Account creation
- Login/Logout
- User permissions (you can only edit what you create)
- Creation and updating of custom songs, albums, and artists
- Search (with category options)
- Ratings/Reviews for songs and album
- Comments on albums and replies to comments

## Technologies
Java 1.8, JUnit 5, SpringMVC, Spring Boot, Spring Tool Suite, Tomcat Server, Apache Web Server, JSP, JSTL, HTML, CSS, MAMP, SQL (MySQL), Git terminal, MAC OS, Bootstrap 3, Google (a lot), Github, Java Persistence API & Hibernate, JPQL, JDBC, MySQL Workbench, Gradle, Trello, Amazon Web Services, Chrome Developer Tools

## Lessons Learned
- **CSS and the pitfalls of templates**
<br>
Templates are great for beginners, however, it quickly became apparent after moving on to the front-end that the template we had used for the navbar, sidebar, and footer created significant headaches with CSS inheritance while working with other pages. In hindsight, after seeing the amount of generic styling used in it, the template should've been cleaned up for use on other pages. This was our first real dive into CSS, however, so this could be expected. Another point to this is to in the future achieve a mutual understanding of page layout and design of things such as buttons, links, interaction.
<br>
<br>
- **Chrome Developer Tools Are Your Friend**
<br>
A bit more to the point of CSS. The Chrome Developer tools greatly helped in troubleshooting issues and were extremely useful with their capability to live edit CSS/HTML
<br>
<br>

- **Git branches**
<br>
Git branches and pull requests were used to great affect. They significantly improved our flexibility to work as a team.
<br>
<br>

- **Working as a team**
<br>
The most significant step in our education with this project was a larger team effort for us. Our previous group projects saw (mostly) work split between 2 people on much smaller applications. Even doubling to four revealed the true need for communication and delegation of tasks to achieve our goals. One thing that should be stressed are coding standards. While we didn't have the time to write a comprehensive documentation on the subject, it was not discussed. In the future, it would be wise for efficiency sake and debugging to have more dialogue about how things should look/be named.

## Project Owners
[Dan Faulkner](https://github.com/DanFaulkner)
<br>
[Kyle Palasti](https://github.com/KPalasti)
<br>
[Wangcheng Ni](https://github.com/wangchengni)
<br>
[Patrek Gill](https://github.com/PatrekGill)

## What Could've Been Done Better
- As discussed above, a better more uniform approach to actually coding/naming conventions would be ideal.
- The CSS and styling also could've used more touch-up across several pages.
- Another level of interfaces to be used on several DAO objects would've been good for keeping a more focused approach to the naming and behavior of their methods.

## The Cutting Room Floor
- UI cleanup
- User profile image changing
- The ability to add friends
- The ability to favorite songs and albums

## Database Schema
![Database Schema](https://drive.google.com/uc?export=view&id=1tm6plsI2CzVoAF95C8qtbAp0b1hncMR2)
