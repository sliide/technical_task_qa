# Sliide QA Technical Test
## Congratulations, you have reached the next stage which is solving a Sliide practical test.
##### Please create your own repo and share the solution with us

### Description
During the test we'd like you to imagine yourself as a member of our team, which has a collective goal of getting our sprint tasks completed.

Let’s start!

We are in the middle of the sprint and the following 2 user stories were just moved to the QA testing column on our Jira board:

##
### 1 - As a user I want to log in to the app

#### Scenario 1 - user opens the android app first time (when not logged in yet)

Given - The user opens the app for the first time (when not logged in yet)

Then - The login screen with user name and password entries and login button is displayed

#### Scenario 2 - User login failed

Given - The user provided wrong user name and/or password

When - The Login button is clicked

Then - The error markers are displayed by user name and/or password entries

#### Scenario 3 - User login succeed (credentials provided below)

Given - The user provided correct credentials

When - Login button is clicked

Then - User is taken to the news screen

#### Scenario 4 - User opens app next time (when previously logged in)

Given -The user opens app next time (when previously logged in)

Then - User is taken straight to the news screen

 ##

### 2 - As a user I want to see my news

#### Scenario 1 - News images are loaded

Given - tThe user successfully logged in to the app

When - There is internet connection

Then - Images are displayed in the rows on the list (row can have one or more images scrollable horizontally)

#### Scenario 2 - Failed to load images

Given - The user successfully logged in to the app

When - There is no internet connection

Then - “Failed to load news” error message is displayed with a Retry button

#### Scenario 3 - News card is clicked

Given - The news cards are successfully loaded on the screen

When - The user clicks one of the cards

Then - User is navigated to the external browser with a corresponding article loaded

#### Login credentials:
#### user: password
#### password: password

##

Now it’s your turn. You need to verify if we can move these two tickets to the Done column on our Jira board.
We expect that these functions will be tested both manually and automatically by you.

### Manual tests - We expect that any bugs will be reported in clear form

### Automated tests - Using jetpack compose test or any other tool of your choosing (explain why)

* At Sliide we love clean code so please try to write your tests neatly.

* It’s not mandatory but using an additional abstraction level for your tests (like POM or your own framework to facilitate writing tests) will be very much appreciated

* As a note, we won't consider any automation task submission created with a test recorder.

At Sliide we highly appreciate good communication at all times. If you have any questions, please don’t hesitate to ask us :)

### Next Steps
Once we have received your test along with any other documentation which you feel is necessary for your submission, we will review it. If we like what we see, we'll invite you into our office for
a face to face discussion where you'll ask you questions about your test.

## Good luck!
