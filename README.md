# ETLLibrary
Along with source code a folder called executable is delivered. Which contains executable jar, that can demonstrate working of code.
Usage:
1. Open command window at a place where delivered executable folder is extracted.
Execute command
java -jar ETLLibrary.jar

When there are no arguments provided, program will default source and destination directory to C:\test
Program will try to create: (It might happen that you may get Access Denied error, if C:\test can not be created, due to your local machine administrator issues.)
In such a case create C:\test manually, and execute command again.
  A. C:\test directory
  B. C:\test\File1.txt
  C. C:\test\File2.txt
  
After program finishes execution there will be out put files created in C:\test

2. Open command window at a place where delivered executable folder is extracted.
Execute command (Make sure provided directories are present already)
java -jar ETLLibrary.jar sourcedirectory destinationdirectory

eg. java -jar ETLLibrary.jar c:\test c:\test_out

Program will then iterate through all .txt files from source directory. Format contents of them in Init Cap, and write output in destination directory.