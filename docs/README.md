# !bot User Guide

## Table of Contents

[1. Introduction](https://github.com/hungvo0603/ip/tree/master/docs#1-introduction) <br>
[2. Quick start](https://github.com/hungvo0603/ip/tree/master/docs#2-quick-start) <br>
[3. Features](https://github.com/hungvo0603/ip/tree/master/docs#3-features) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.1 Adding a task](https://github.com/hungvo0603/ip/tree/master/docs#31-adding-a-task-todo-deadline-event) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.2 Listing tasks](https://github.com/hungvo0603/ip/tree/master/docs#32-listing-tasks-list) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.3 Marking task as done](https://github.com/hungvo0603/ip/tree/master/docs#33-marking-a-task-as-done-done) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.4 Finding tasks](https://github.com/hungvo0603/ip/tree/master/docs#34-finding-tasks-find) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.5 Deleting a task](https://github.com/hungvo0603/ip/tree/master/docs#35-deleting-a-task-delete) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.6 Exiting](https://github.com/hungvo0603/ip/tree/master/docs#36-exiting-bye) <br>
[4. Command summary](https://github.com/hungvo0603/ip/tree/master/docs#4-command-summary)
 

## 1. Introduction
!bot is a **command-line (CLI) task list manager program** that helps the absent-minded 
user to track their to-do, deadline and event tasks. !bot can be used by users that can
type fast. !bot can also save your task list to a text file when you exit the program
and load it the next time you enter the program.

## 2. Quick start
1. Ensure that you have `Java 11` or above installed in your computer.
2. Download the iP.jar file here.
3. Copy the .jar file to your preferable home folder for !bot.
4. Launch the .jar file in the !bot home folder by using `java -jar iP.jar` command on 
Command Prompt (Windows) or on Terminal (MacOS). If the setup is correct, you should see
something like this:

    ![Greetings](https://github.com/hungvo0603/ip/blob/master/docs/greetings.PNG)

## 3. Features
In command syntax, there will be parameters in CAPITAL. Those will be provided by the user.
* DESCRIPTION: a string representing the content of the task.
* DATE: a string representing the date of Deadline and Event tasks. 
The format should be dd/MM/YYYY where
  * `dd` is the day,
  * `MM` is the month,
  * `YYYY` is the year
  * Example: 06/03/2001 represents to 6th Mar 2001.
* TIME: a string representing the time of Deadline and Event tasks.
The format should be HHMM where
  * `HH` is the hour,
  * `MM` is the minute
  * Example: 2300 represents 11:00 PM
* INDEX: an integer representing the index of the task in the list.
* KEYWORD: a string representing the words to be searched in the task list.

### 3.1 Adding a task: `todo`, `deadline`, `event`
Tell !bot to add a new task to its database. There are three types of task you can add:
* **`todo`**: add a to-do to the list
  * Syntax: `todo DESCRIPTION`
  * Example of usage: `todo CS2113T iP README.md`
  * Expected output: 

    ![Add Todo Task](https://github.com/hungvo0603/ip/blob/master/docs/addTodo.PNG)
    
* **`deadline`**: add a deadline to the list
  * Syntax: `deadline DESCRIPTION /by DATE TIME`
  * Example of usage: `deadline CS2113T iP /by 02/10/2020 2359`
  * Expected output:
  
    ![Add Deadline Task](https://github.com/hungvo0603/ip/blob/master/docs/addDeadline.PNG)
    
* **`event`**: add an event to the list
  * Syntax: `event DESCRIPTION /at DATE TIME`
  * Example of usage: `event Birthday party /at 27/10/2020 1830`
  * Expected output:
  
    ![Add Event Task](https://github.com/hungvo0603/ip/blob/master/docs/addEvent.PNG)
    
### 3.2 Listing tasks: `list`
List all the tasks that the user has in the list.
* Syntax: `list`
* Expected output:
  
  ![List Command](https://github.com/hungvo0603/ip/blob/master/docs/list.PNG)
  
### 3.3 Marking a task as done: `done`
Mark a task as done when the user has finished the task.
* Syntax: `done INDEX`
* Example of usage: `done 1`
* Expected output:

  ![Done Command](https://github.com/hungvo0603/ip/blob/master/docs/done.PNG)

### 3.4 Finding tasks: `find`
Find all the tasks which contains a specific keyword.
* Syntax: `find KEYWORD`
* Example of usage: `find CS2113T`
* Expected output:

  ![Find Command](https://github.com/hungvo0603/ip/blob/master/docs/find.PNG)

### 3.5 Deleting a task: `delete`
Delete a task in a specific index.
* Syntax: `delete INDEX`
* Example of usage: `delete 1`
* Expected output:

  ![Delete Command](https://github.com/hungvo0603/ip/blob/master/docs/delete.PNG)

### 3.6 Exiting: `bye`
Stop !bot and save all the tasks to hard disk.
* Syntax: `bye`
* Expected output:

  ![Bye Command](https://github.com/hungvo0603/ip/blob/master/docs/bye.PNG)

## 4. Command summary
Following is the summary of all available features in !bot program.

Feature       | Syntax and Example
------------- | ---------------------------
Add to list   | - `todo DESCRIPTION`<br>Example: todo exam<br><br>- `deadline DESCRIPTION /by DATE TIME`<br>Example: deadline assignment /by 02/10/2020 2359<br><br>- `event DESCRIPTION /at DATE TIME`<br>Example: event party /at 27/10/2020 1900 
List tasks    | `list`
Mark as done  | `done INDEX`<br>example: done 1
Delete a task | `delete INDEX`
Find tasks    | `find KEYWORD`<br>example: find CS2113T 
Exit          | `bye`   

