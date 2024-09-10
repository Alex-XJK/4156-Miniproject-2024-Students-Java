# Welcome Students of 4156

Please follow the assignment specifications on Courseworks when completing this project.

---

## Notice

Please note that when running my test suite, make sure that you have completed
the setup step first as required by the professor, i.e. you have the correct
`data.txt` in the directory.

In addition, given that my tests include tests for some manipulation api,
such as `changeCourseTime()`, it is possible that there may be changes to the
database during the testing process. So if you want to repeat the test,
make sure you resetup before each test, i.e. you have the original data.

---

## Static Bug Finder

I chose the provided static bug finder, **PMD**, in my assignment.

The first PMD report have been appended to the `pmd-init.txt` file.
The command used to generate this file after you have properly configured PMD is

```
pmd check -d ./4156-Miniproject-2024-Students-Java/IndividualProject -R rulesets/java/quickstart.xml -f text | sed -e 's/.\/4156-Miniproject-2024-Students-Java\/IndividualProject\/src\/main\/java\/dev\/coms4156\/project\/individualproject/.../g' > pmd-init.txt
```

, and obviously the shell commands that follow are only used to beautify the
original PMD output. Therefore, the most basic command is as follows
(make sure you have the correct path to the file).

```
pmd check -d ./4156-Miniproject-2024-Students-Java/IndividualProject -R rulesets/java/quickstart.xml -f text
```
