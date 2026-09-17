# ThreadForge

## About the Project

ThreadForge is a command-line Java application I built to understand how multiple jobs can be managed and executed at the same time.

The idea was to create a small job management system where users can submit different types of jobs, put them into a queue, and let multiple worker threads execute them. The system also keeps track of job status, stores job history, and provides basic statistics.

The main focus of the project was to apply Java concepts like multithreading, synchronization, inheritance, interfaces, exception handling, JDBC, and file handling in one practical project.

## Problem

Managing multiple tasks at the same time can become difficult when everything is handled manually.

Some common problems are:

- Jobs need to be processed in an organized way
- Different jobs may have different priorities
- Multiple jobs should be able to run concurrently
- It should be possible to track the status of each job
- Previous job information should not be lost
- There should be a simple way to view execution statistics

## Solution

ThreadForge solves this by creating a simple job execution system:

- Users can submit different types of jobs
- Jobs are placed into a priority-based queue
- Multiple worker threads execute jobs concurrently
- Each job has a status such as queued, running, completed, failed, or cancelled
- Job information is stored using SQLite and JDBC
- Execution statistics can be viewed from the command line
- Logs and reports can be generated for completed jobs

## Concepts Used

This project uses several concepts from Programming in Java:

- Multithreading
- Thread synchronization
- wait() and notifyAll()
- Inheritance
- Abstract classes
- Interfaces
- Method overriding
- Polymorphism
- Encapsulation
- Enums
- Collections
- Exception handling
- Java I/O
- JDBC
- SQLite
- File handling

## Technologies and Libraries

- Java 24
- Maven
- SQLite
- JDBC
- JUnit 5

## Features

- Submit Compute Jobs
- Submit File Jobs
- Submit Database Jobs
- Priority based job scheduling
- Multiple worker threads
- Job status tracking
- Job cancellation
- SQLite database storage
- Job execution statistics
- Database job history
- File-based logging
- Report generation
- Command-line interface

## Job Types

### Compute Job

Performs a factorial calculation using the numbers provided by the user.

### File Job

Takes a file path and checks whether the specified file exists before processing it.

### Database Job

Executes an SQL query using JDBC and displays the returned results when available.

## Project Structure

text
ThreadForge/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── threadforge/
│   │               ├── database/
│   │               ├── engine/
│   │               ├── exception/
│   │               ├── interfaces/
│   │               ├── io/
│   │               ├── model/
│   │               ├── service/
│   │               ├── util/
│   │               └── Main.java
│   │
│   └── test/
│       └── java/
│
├── pom.xml
├── .gitignore
|── ThreadForge Project Report
└── README.md


## How to Run the Project

- Download or clone the project and open the root folder:
 
  cd ThreadForge
  
- Make sure Java 24 or later is installed:
 
  java -version
  
- Make sure Maven is installed:
 
  mvn -version
  
- Build the project:
 
  mvn clean package
  
- Run the Main class from the command line using the compiled project and its dependencies.
- The ThreadForge menu will appear in the terminal.
- Select an option and enter the required information when prompted.

## Example Flow

- User starts ThreadForge
- A job type is selected
- Job details are entered
- A unique job ID is generated
- Job is added to the scheduler
- Scheduler places the job according to its priority
- A worker thread takes the job
- Job status changes to running
- Job is executed
- Job status changes to completed or failed
- Job information is stored in SQLite
- Statistics and history can be viewed

## Execution Flow

text
User
  ↓
JobService
  ↓
JobScheduler
  ↓
Priority Queue
  ↓
Worker Pool
  ↓
Job Executor
  ↓
Job Execution
  ↓
SQLite / Logs / Reports


## Database

ThreadForge uses SQLite with JDBC for storing job information.

The application automatically creates:

- `threadforge.db`

The database stores information such as:

- Job ID
- Job name
- Job type
- Priority
- Status
- Worker ID
- Creation time
- Start time
- Completion time

No external database server is required.

## Output

The program provides:

- Job submission confirmation
- Job execution results
- Current job status
- Worker status
- Job statistics
- Database history
- Generated reports
- Execution logs

Example:

```text
----- Jobs -----
JOB-1001 | Compute Test | COMPUTE | COMPLETED
```

Statistics example:

```text
----- Statistics -----
Total jobs: 1
Completed: 1
Running: 0
Failed: 0
Cancelled: 0
Average execution time: 6.0 ms
```

## Runtime Files

The application generates some files while running:

- `threadforge.db` — SQLite database
- `threadforge.log` — execution logs
- `threadforge-report.txt` — generated job report

These runtime files are excluded from Git using `.gitignore`.

## Challenges Faced

Building this project was not completely straightforward. Some parts took time to understand and test:

- Managing multiple worker threads correctly
- Understanding synchronization between threads
- Making the job queue work safely with multiple threads
- Implementing priority-based scheduling
- Connecting Java with SQLite using JDBC
- Handling job failures without stopping the worker threads
- Keeping job status updated during execution
- Testing different job types
- Making sure database records were updated correctly

Most of these problems were solved by testing each part separately and then connecting everything together.

## What I Learned

While building ThreadForge, I got a better understanding of:

- How Java threads work
- How synchronization helps when multiple threads access shared data
- How `wait()` and `notifyAll()` can be used for thread communication
- How priority queues can be used for scheduling
- How inheritance and interfaces can be used to design different job types
- How JDBC connects Java applications with databases
- How Java I/O can be used for logs and reports
- How different Java concepts can be combined into one application

## Testing

The main features were tested through the command line.

- Compute Job — PASS
- File Job — PASS
- Database Job — PASS
- Job Status Tracking — PASS
- Worker Pool — PASS
- Statistics — PASS
- Database History — PASS
- Report Generation — PASS
- Application Exit — PASS

The application also completed successfully with exit code 0.

## Future Improvements

Some things I would like to improve in the future:

- Add configurable number of worker threads
- Improve cancellation of running jobs
- Load previous jobs when the application starts
- Add more types of jobs
- Improve error handling and messages
- Add more advanced scheduling options
- Add a monitoring dashboard
- Add better automated test coverage

## Conclusion

ThreadForge helped me understand how Java can be used to build a small concurrent job management system.

The main goal was not to make a huge application, but to use the concepts learned in Programming in Java in a practical way. Building the project also helped me understand how multithreading, synchronization, JDBC, file handling, and object-oriented programming work together.

## Final Note

This project was built as part of my Programming in Java course.

The main focus was on understanding the concepts and implementing them in a working command-line application rather than just using the concepts separately.
