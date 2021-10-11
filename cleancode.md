# Clean Code Summary
> Clean Code by Robert C Martin

## Chapter 4: Comments

- Comments can hardly follow changes of the code, becoming inaccurate.
- **Codes** are the only thing that provides accurate information
- Updating the comments might seem to be a good idea, but rather than that, fix the code to express clearly.
- Good Comments
  - Legal Comments
  - Informative Comments : provide basic information with a comment. (ex. regex information)
  - Explanation of Intent : explain intent behind a decision
  - Clarification : translate some arguments, which are not alterable, in a more readable form.
  - Warning of Consequences (something like side effects, race condition, ...)
  - TODO
  - Amplification : amplifify the importance of some code.
  - Javadocs in Public APIs
  - Generally, it might be better just to improve the code.
- Bad Comments
  - Mumbling : meanings are not clear.
  - Redundant Comments : not informative than the code
  - Misleading Comments : Comments are wrong
  - Mandated Comments - ex) providing javadocs for every functions
  - Journal Comments - logging as comments
  - Noise Comments : restate the obvious information
  - Can be expressed by a function or variable
  - Position Markers
  - Comments after Closing Braces
  - Adding who wrote the code
  - Commented-out code
  - HTML Comments
  - Explaining the code far away
  - TMI
  - Inobvious Comments
  - Function Headers
  - Javadocs in Nonpublic Code

### Comments

- Use codes only when needed. Improve codes rather than improving comments.
- It is my first time to know that when Javadocs are misused, it is better not to use it.
- Intent and explaining reasons in comments is a powerful method to express meanings of the code.

## Chapter 7: Error Handling

> Error handling is important, but it obscures logic, it's wrong.

- Use exceptions than return codes
- Write try-catch-finally statement first when the code is expected to raise exceptions
- Use Unchecked Exceptions - check exceptions on runtime
- Provide informative error messages and pass them with exceptions
- Design exception classes with concern "how they should be caught"
- Consider using special case objects to handle special cases.
- Don't return or pass null as arguments. Instead, use exceptions or special case objects.

### Comments

- Make code clean and robust with exceptions.
- I disagree with the idea of unchecked exceptions. Using checked exceptions helps a lot in debugging the program.

## Chapter 8: Boundaries

All about using third-party codes

- A boundary exists between providers of third-party codes and users.
- If you use boundary interfaces, keep it local, so that it doesn't go around functions and objects.
- Third-party saves time, but they are not easy to learn and integrate to projects.
- Writing learning tests may help learn, and run behavioral tests for third-parties.
- Code at the boundaries need clear separation and tests so that if change happens in third-parties, less rework would be required.

### Comments

- Make boundaries clean, and write learning tests to learn and be robust to third-party changes.
- Writing learning tests is much better than struggling with poor documentations of third-parties.

## Chapter 9: Unit Tests

All about tests

- The Three Laws of TDD* (Test Driven Development)
  - You may not write production code until you have written a failing unit test.
  - You may not write more of a unit test than is sufficient to fail, and not compiling is failing.
  - You may not write more production code than is sufficient to pass the currently failing test.
- Tests help robustness in changing the code, but if test suite is dirty, it is better than nothing.
- **Readability** is the most important thing for clean tests
- One assert per test, single concept per test
- **F.I.R.S.T** rule for clean tests
  - Tests should be **fast**.
  - Tests should be **independent**.
  - Tests should be **repeatable**, regardless of environment.
  - Tests should have a boolean output, so they pass or fail. (**Self-Validating**)
  - Tests should be written in a **timely** fashion, so that they are written just before the production code.

### Comments

- Test suites have to be well-designed as well.
- I disagree with "one assert per test". But, I agree with "single concept per test".
  - Multiple asserts may be needed to test a single concept.
- It was my first time to learn TDD, writing test codes before production codes.
  - However, I don't think writing test codes before production codes is always efficient. It is sometimes to select other development method in some situations.

## Chapter 10: Classes

All about classes

- Classes should be small.
- SRP (Single Responsibility Principle): a class or module should have only one reason to change. In other words, it should have one responibility.
- Classes should be designed cohesive. They should have clear purposes and well-defined jobs.

### Comments

- Do classes should always be small? I don't think so. If classes are well-defined and cohesive, it doesn't need to break up into several classes. In other words, SRP and cohesion should be prioritized than making classes small.
- From my personal experience, big classes with well-written document are fine. However, small classes should be preferred.
- I personally think one big class is better than small classes that have complicated inheritances and dependencies. However, that big class should be written well.
