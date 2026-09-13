# Java Assignment 3 (Module 3)

25 Java programs on threads, synchronization, locks, I/O streams, file handling and Java packages.
Each topic has its own folder, and each program is a single file you can run by itself.

## Folder structure

| Folder | Questions |
|---|---|
| [01_Thread_Concepts](01_Thread_Concepts) | Q1–Q6: Extending Thread, Runnable, sleep, countdown timer, naming and priority, daemon threads |
| [02_Thread_Synchronization](02_Thread_Synchronization) | Q6–Q10: Synchronized method, synchronized block, dining philosophers deadlock, wait/notify (producer-consumer, odd-even), stopping a thread |
| [03_Lock_Interface](03_Lock_Interface) | Q11–Q12: ReentrantLock counter, deadlock fixed with tryLock() |
| [04_IO_Streams](04_IO_Streams) | Q13–Q17: FileInputStream, FileOutputStream, FileReader, FileWriter, buffered I/O |
| [05_File_Handling](05_File_Handling) | Q18–Q22: File exists/create, list directory, copy, delete, RandomAccessFile |
| [06_Java_Packages](06_Java_Packages) | Q23–Q25: java.lang Math, java.util Date/Calendar, java.util.regex email check |

> The assignment sheet numbers two questions as "6" (Daemon Threads and Synchronized Method).
> The file names keep the sheet's numbers, so both folders 01 and 02 have a Q06 file.
> Question 9 has two parts: `Q09a` and `Q09b`.

## All programs

| # | File | Needs input? |
|---|---|---|
| 1 | `01_Thread_Concepts/Q01_EvenNumbersThread.java` | No |
| 2 | `01_Thread_Concepts/Q02_ReverseStringRunnable.java` | No |
| 3 | `01_Thread_Concepts/Q03_ThreadSleep.java` | No |
| 4 | `01_Thread_Concepts/Q04_CountdownTimer.java` | No |
| 5 | `01_Thread_Concepts/Q05_NamingAndPriority.java` | No |
| 6 | `01_Thread_Concepts/Q06_DaemonThread.java` | No |
| 6 | `02_Thread_Synchronization/Q06_SynchronizedMethod.java` | No |
| 7 | `02_Thread_Synchronization/Q07_SynchronizedBlock.java` | No |
| 8 | `02_Thread_Synchronization/Q08_DiningPhilosophersDeadlock.java` | No |
| 9a | `02_Thread_Synchronization/Q09a_ProducerConsumer.java` | No |
| 9b | `02_Thread_Synchronization/Q09b_OddEvenPrinter.java` | No |
| 10 | `02_Thread_Synchronization/Q10_StoppingThread.java` | Yes: press Enter to stop |
| 11 | `03_Lock_Interface/Q11_ReentrantLockCounter.java` | No |
| 12 | `03_Lock_Interface/Q12_DeadlockAndTryLock.java` | No |
| 13 | `04_IO_Streams/Q13_ReadFileByteStream.java` | Yes: file name |
| 14 | `04_IO_Streams/Q14_WriteFileByteStream.java` | No |
| 15 | `04_IO_Streams/Q15_ReadFileCharacterStream.java` | Yes: file name |
| 16 | `04_IO_Streams/Q16_WriteFileCharacterStream.java` | Yes: text to write |
| 17 | `04_IO_Streams/Q17_BufferedIO.java` | Yes: lines to write |
| 18 | `05_File_Handling/Q18_FileExistenceCheck.java` | Yes: file name |
| 19 | `05_File_Handling/Q19_ListFilesInDirectory.java` | Yes: directory path |
| 20 | `05_File_Handling/Q20_CopyFile.java` | Yes: source and destination |
| 21 | `05_File_Handling/Q21_DeleteFile.java` | Yes: file name and confirmation |
| 22 | `05_File_Handling/Q22_RandomAccessFileDemo.java` | No |
| 23 | `06_Java_Packages/Q23_JavaLangMath.java` | Yes: numbers |
| 24 | `06_Java_Packages/Q24_DateAndCalendar.java` | No |
| 25 | `06_Java_Packages/Q25_EmailValidationRegex.java` | Yes: email addresses |

## How to run

You need Java 11 or newer. Open a terminal **inside the topic folder** so the programs can find files like `sample.txt`.

**Option 1: run the file directly (easiest)**

```bash
cd 04_IO_Streams
java Q13_ReadFileByteStream.java
```

**Option 2: compile first, then run**

```bash
cd 04_IO_Streams
javac Q13_ReadFileByteStream.java
java Q13_ReadFileByteStream
```

## Suggested order for the file programs

- **Q14 → Q13:** Q14 writes `output.txt`, then Q13 reads it (or read `sample.txt`).
- **Q16 → Q15:** Q16 writes `example.txt`, then Q15 reads it.
- **Q18 → Q21:** Q18 creates `notes.txt`, then Q21 deletes it.
- **Q20:** Copy `sample.txt` to `sample_copy.txt`.
