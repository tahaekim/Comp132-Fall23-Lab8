[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/f7HU4Fcj)
# COMP 132, Fall 2023

# IMPORTANT

For each lab assignment, **you must include and sign (by writing your name and student ID number) the following Pledge of Honor statement at the **TOP of your Main.java class (if you do not do this, you will get zero)** source code file. After including this statement, make sure that you do the commit and push operation on GitHub. Otherwise, your lab solution will not be graded.**

```
/* *********** Pledge of Honor ************************************************ *

I hereby certify that I have completed this lab assignment on my own
without any help from anyone else. I understand that the only sources of authorized
information in this lab assignment are (1) the course textbook, (2) the
materials posted at the course website and (3) any study notes handwritten by myself.
I have not used, accessed or received any information from any other unauthorized
source in taking this lab assignment. The effort in the assignment thus belongs
completely to me.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Name Surname, Student id>
********************************************************************************/
```
## IMPORTANT NOTE 2: After you complete the tasks, do not forget to commit your changes and push them to your repository on GitHub.

## IMPORTANT NOTE 3: This README.md file only contains the instructions for PRELAB. You will also have INLAB questions.

# Lab-8 Prelab - Server Log Analysis and Processing

![](/log-analysis.png?raw=true "")

In this programming lab, you are asked to develop a Java application to process and analyze server log files using **Regular Expressions**, **Lambdas and Streams** and **File I/O**. This assignment focuses on extracting, parsing, and manipulating structured log data.

**Information About the Provided File (server_logs.txt):**

Each line of this file corresponds to a distinct log entry. The entries are constructed in the following format:

```
[YYYY-MM-DD HH:MM:SS] [ERROR/INFO/WARN] [source_module] - Message
```
Example Entry: 

```
[2023-03-15 08:30:45] [ERROR] [NetworkModule] - Connection timeout at 192.168.1.12
```

Your project should have two packages, namely logsystem and main. There are two classes in the package logsystem, including LogAnalyser Class and LogProcessor Class, the details of which are in the following.

# Task 1: LogAnalyser Class

This class should have a variable keeping the log file pathname and will have the following methods.

#### Important Note: You must use Regular Expressions, Lambdas, and Streams to implement these methods, whenever needed. Unless, you will not get full credit.

- **getEntryCounts()**: Display the number of entries in each category (Error, Info, Warn).

- **getErrors()**: Displays the number of error entries and the first and last entries themselves in the log file.

- **getWarnings()**: Displays the number of warning entries, and the first and last entries themselves in the log file.

- **getInfos()**: Displays the number of info entries, and the first and last entries themselves in the log file.

- **getEntriesInTimeFrame(String startTime, String endTime)**: Displays the first and last entries between the given time frame.

- **getEntriesThatContain(String phrase)**: Display the number of entries and the first and last entries themselves that contain the given phrase.

### Note: For all the above methods, in case of not finding any string, your application should display a message like “There is no such an entry”.

## Task 2: LogProcessor Class

This class will also take the log file pathname as input and will have the following methods.

- **getStatistics()**: Display the average entry length (# of chars), longest and shortest entries, and their lengths. 

- **getCroppedEntries(int maxLength)**: Display the first and last entries such that ones that are originally longer than maxLength are cropped to be precisely maxLength long, and end with “...”.
  
### Hint: You can use map and reduce to implement this method. Think about how to use reduce and a lambda expression to obtain the first and last elements. The elements are passed into the stream in order, so (x, y) -> ???
You need to use exception handling in case of the null string resulting from reduce.

- **sortByDate()**: This method should first sort the entries in the log file by their timestamp in ascending order and then display the first and last one. (check the sample output)

The **main package** includes the **Main.java**, which is given to you to test your project.

## Sample Output 

```
---- LogAnalyser Tests ----

Entry Counts:
Total: 40
[2023-09-23 21:38:03] [ERROR] [UserInterface] - Connection timeout at 192.168.1.150
[2023-04-24 16:44:17] [ERROR] [StorageModule] - Authentication failed for user User3621

Total: 32
[2023-03-24 18:29:41] [INFO] [AuthModule] - New user registered: User3737
[2023-03-09 13:50:56] [INFO] [UserInterface] - Scheduled maintenance complete

Total: 28
[2023-05-09 09:57:16] [WARN] [NetworkModule] - User 2415 attempted invalid operation
[2023-02-01 16:19:26] [WARN] [APIModule] - Network latency higher than usual


Error Entries:
Total: 40
[2023-09-23 21:38:03] [ERROR] [UserInterface] - Connection timeout at 192.168.1.150
[2023-04-24 16:44:17] [ERROR] [StorageModule] - Authentication failed for user User3621


Warning Entries:
Total: 28
[2023-05-09 09:57:16] [WARN] [NetworkModule] - User 2415 attempted invalid operation
[2023-02-01 16:19:26] [WARN] [APIModule] - Network latency higher than usual


Info Entries:
Total: 32
[2023-03-24 18:29:41] [INFO] [AuthModule] - New user registered: User3737
[2023-03-09 13:50:56] [INFO] [UserInterface] - Scheduled maintenance complete


Entries in Time Frame [2023-03-24 00:00:00, 2023-03-25 00:00:00]:
[2023-03-24 18:29:41] [INFO] [AuthModule] - New user registered: User3737
[2023-03-24 14:41:28] [WARN] [NetworkModule] - Network latency higher than usual

Entries that Contain 'User 2415':
Total: 1
[2023-05-09 09:57:16] [WARN] [NetworkModule] - User 2415 attempted invalid operation


---- LogProcessor Tests ----

Statistics:
Average length: 78.67
Longest entry (94 chars): [2023-08-18 18:46:02] [WARN] [UserInterface] - Unexpected input format in module NetworkModule
Shortest entry (62 chars): [2023-09-19 10:21:04] [INFO] [APIModule] - User 2665 logged in

Cropped Entries (maxLength 64):
First entry: [2023-05-09 09:57:16] [WARN] [NetworkModule] - User 2415 attempt...
Last entry: [2023-04-24 16:44:17] [ERROR] [StorageModule] - Authentication f...

Sorted By Date:
First entry: [2023-01-01 01:05:56] [ERROR] [StorageModule] - Connection timeout at 192.168.1.208
Last entry: [2023-12-28 08:16:23] [INFO] [AuthModule] - New user registered: User4368
```
