[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-718a45dd9cf7e7f842a935f5ebbe5719a5e09af4491e668f4dbf3b35d5cca122.svg)](https://classroom.github.com/online_ide?assignment_repo_id=11945726&assignment_repo_type=AssignmentRepo)
# CS-2003-Project-1

## Overview
This first project is intended to reinforce the work that we’ve doing over the past weeks with the List ADT.

You’ve been provided with the following files: BasicArrayList.java, BasicCollectionInterface.java, BasicLinkedList.java, BasicListInterface.java, BasicSetInterface.java, Main.java, and BasicSetTest.java. **BasicArrayList.java** is a basic implementation of the ArrayList class provided by the Java library. **BasicCollectionInterface.java** is a basic implementation of the Collection interface provided by the Java library. **BasicLinkedList.java** is a basic implementation of the LinkedList class provided by the Java library. **BasicListInterface.java** is an interface that describes many of the public methods available in the ADT List interface, which corresponds to the List interface in the Java library. **BasicSetInterface.java** is a basic implementation of the Set interface provided by the Java library. **Main.java** is provided to execute a sample experiment with your code. **BasicSetTest.java** includes the unit tests that are used to score your submission.

You are encouraged to use your own implementation of BasicArrayList or BasicLinkedList.

Use **TUGrader.java** to ensure that all unit tests in the provided test file(s) pass.

## Assignment
Create and implement a new class called **BasicSet.java** in **src/main/java**. It should have the appropriate package declaration at the top of the file. This class should implement the **BasicSetInterface.java**. It is up to you to decide how you would like to implement the Set ADT interface. Some available options would be to use an array, Nodes, a BasicArrayList, a BasicLinkedList, or Java's own ArrayList or LinkedList. Whichever implementation you choose should use generics.

Your implementation of BasicSet must also include a **default constructor**, which constructs an empty set, a **copy constructor**, which clones a BasicSet or BasicSetInterface, and a **parameterized constructor** which takes a single argument whose type is `BasicCollectionInterface<E>`. The parameterized constructor should construct a set representation of the input collection which contains all of its elements but no duplicates. Lastly, you must define a `toString()` method that returns the String representation of the set. The string representation of a set is the same as the string representation of a list except that it uses curly braces '{' instead of square brackets '['.

*Note: You may find it helpful to only include the `implements BasicSetInterface<E>` declaration once you need to. Otherwise, you can stub each method with a default return value or have them throw an `UnsupportedOperationException`.*

## Submission
Please check Harvey for the listed due date.

*To submit your work, please review the **Commits** section below.*

## Grading Rubric
- [ ] (50pts) Can add elements to the set
- [ ] (50pts) Can check if elements are contained in the set or access them via an iterator
- [ ] (50pts) Can remove elements from the set
- [ ] (75pts) Can compute the set difference, intersection, and union
- [ ] (75pts) Project Report - Timings of difference, intersection, and union

## Compiling and Testing Code
Your IDE should provide tools to compile your code. If you're unfamiliar with that process, you can research it online or ask. Most developers compile their code from command line using a shell script, such as a **Makefile** or build script (**build.sh**). I've provided build scripts for you in both *Powershell* and *Bash*. Refer to the following directions on how to use these scripts based on the terminal that you're using. If you're on Windows, please use Windows Subsystem for Linux (WSL), Git Bash, or Powershell, not Command Prompt.

### Windows Users (Powershell)
- To compile your code: `./build.ps1`
- To compile and run your code: `./build.ps1 run` (forwards clargs to program)
- To compile and test your code: `./build.ps1 test` (forwards clargs to TUGrader)
- To format your code: `./build.ps1 fmt`
- To sync your code: `./build.ps1 sync`
- To submit your code: `./build.ps1 submit`
- To remove class files: `./build.ps1 clean`

### Windows Users (WSL, Git Bash), Mac and Linux Users
- To compile your code: `./build.sh`
- To compile and run your code: `./build.sh run` (forwards clargs to program)
- To compile and test your code: `./build.sh test` (forwards clargs to TUGrader)
- To format your code: `./build.sh fmt`
- To sync your code: `./build.sh sync`
- To submit your code: `./build.sh submit`
- To remove class files: `./build.sh clean`

These scripts use the following commands. Note that Windows users need to replace the colon with a semicolon in the Java classpath.
- To compile a Java file: `javac -d target -cp lib/*:src <filepath>.java`
- To execute a Java file: `java -cp lib/*:target <package-path>.<filename>`
- To format a Java file: `java -jar lib/google-java-format.jar --replace --skip-javadoc-formatting <filepath>.java`
- To remove class files: `rm -r target/*`

## Code Style
All code should follow the [Google Java style guidelines](https://google.github.io/styleguide/javaguide.html). If you find anything in the code that does not follow the style guidelines, feel free to fix it, but you are not required to do so. Only your handwritten code will be evaluated for its style. You do not need to follow the style guidelines to the letter but egregious deviations from the style guidelines will be penalized. A submission that passes all test cases but does not use an appropriate style will not receive an A for the assignment.

For those using an IDE, such as Eclipse or VS Code, the IDE should provide a formatting tool. I've included the XML specification of the Google Java Style Guidelines at `.vscode/java-google-style.xml`. You can configure your IDE to use the provided XML as its formatting rules to format your code to the Google Java Style Guidelines, which are the industry standard.

If you're working from command-line, [google-java-format](https://github.com/google/google-java-format) is an open-source formatting tool that you can use to format your files. You can use the following commands to format your code depending on your terminal.
- `./build.ps1 fmt`
- `./build.sh fmt`

## Commits
Commits should be made incrementally. Many commits are always better than few, and commits can always be squashed together later if there are too many. You should try to make a commit every time you've made tangible progress in the development of your code.

Every commit should have a commit message. The standard format for commit messages is to start with a verb in present-tense followed by a concise message (typically less than 50 characters) that summarizes the change that the commit is introducing to the repo. For example, "Updates README", "Implements Array", "Passes testGet".

Popular IDEs, such as Eclipse and VS Code, provide integrated Git tools. If you're on Windows, you can install Git Bash or Windows Subsystem for Linux (WSL). If you're on Mac or Linux, you already have git installed.

If you've just installed git, it will need to be configured. The easy way to configure git is from a terminal. Use the following commands.
- `git config --global user.name "<github-username-goes-here>"`
- `git config --global user.email "<github-email-goes-here>"`
- `git config --global pull.rebase true` (optional)
- `git config --global fetch.prune true` (optional)
- `git config --global diff.colorMoved zebra` (optional)

To sync changes made from another device, use the following command.
- `git fetch origin main`
- `git pull origin main`

To push commits from command line, use the following commands.
- `git add -A`
- `git commit -m "<your message goes here>"`
- `git push origin main`

You can also sync all changes and submit with the following commands depending on your terminal.
- `./build.ps1 submit`
- `./build.sh submit`
