# Quiz Question Bank
### Author: Nogay Viktoria EEAIR24
### Description: 
This project is a Quiz Application created in Java. It allows users to create, edit, delete, and take quizzes through a simple text-based interface. The application also supports saving and loading quizzes from a CSV file, take the quiz and receive a score and generates a report of operations performed.
### Objectives:
**The main goals of this project are:**
1. Implement a fully functional CRUD system for quiz management.
2. Practice programming with Java.
3. Introduce file operations (CSV reading/writing).
4. Validate user input and handle edge cases.
5. Track user actions for a simple reporting system.
---
### Project Requirement List:
1. **Operations for Quiz Questions**
   - Add one or multiple questions.
   - Delete a question by specifying its index.
   - Edit an existing question by specifying its index.
   - Display all quiz questions without showing the answers.
2. **CSV Integration**
   - Save the quiz data to a CSV file.
   - Load quiz data from an existing CSV file.
3. **Quiz Functionality**
   - Allow the user to take the quiz, answer questions, and calculate the score.
   - Display correct answers for any wrong responses during the quiz.
4. **Report**
   - Track the operations performed and provide a summary report.
---
### Documentation:
**Algorithms:**
1. Main Class: The Main class is the central controller of the quiz 
application. It:
   - Displays the menu.
   - Reads user input.
   - Performs actions (add, delete, edit, take quiz, etc.).
   - Tracks how many times each operation is performed.
   - Coordinates with other classes like Quiz and Question.
2. main() Method – Entry Point:    
Scanner is created to read keyboard input. Quiz object is created to store and manage the quiz questions.
3. Menu Loop
Runs an infinite loop to keep the app active until the user chooses to exit. Displays menu options. Reads the user’s choice.
4. switch (number) – Handles User Choices: Each menu option is handled using a switch statement.
   - Calls createQuestion() to get input for a new question.
   - Adds it to the quiz.
   - Records the operation for the report.
   - Displays all questions one by one.
   - Accepts user answers.
   - Checks correctness and counts the score.
   - Saves the quiz questions into a file called quiz.csv.
5. Operation(String operation) Method: Keeps track of how many times a specific action (like adding, deleting) was performed.
6. showReport() Method: Displays the number of times each action was performed.
7. File Handling Methods
   - saveToCSV() – Writes the quiz to a CSV file.
   - readData() – Reads the quiz from a CSV file and loads it into memory.
8. createQuestion(Scanner input) Method
   - Asks the user to enter a question.
   - Takes 4 answer options.
   - Asks which option is the correct one.
   - Returns a Question object.

**Data Structures:**
HashMap: Used to track the number of times each operation is performed. This allows easy reporting of the frequency of actions such as adding, deleting, and changing questions. ArrayList: Used to store quiz questions and options dynamically. It provides flexibility in managing the questions, adding new ones, and modifying existing ones.

**Functions:**
Main Class: Contains the main logic of the application, including the menu system and interaction with the user. It calls various methods to manage the quiz. Quiz Class: Responsible for managing the quiz questions, including adding, deleting, editing, and displaying them. Question Class: Represents individual quiz questions, including the question text, options, and correct answer. CSV Operations: Methods for saving and reading quiz data to/from a CSV file. This includes serialization and deserialization of quiz data. Reporting: A method to track and print a summary of operations performed on the quiz.

**Challenges:**
At first I couldn't: save questions as a separate quiz. So I decided to create a separate class for this. The next problem was in the csv files. I tried for a long time to figure out how to save them and how to output them. In the end, I managed to solve all these issues.
