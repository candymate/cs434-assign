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

### Conclusion

Use codes only when needed. Improve codes rather than improving comments.

## Chapter 7: Error Handling

> Error handling is important, but it obscures logic, it's wrong.

- Use exceptions than return codes
- Write try-catch-finally statement first when the code is expected to raise exceptions
- Use Unchecked Exceptions - check exceptions on runtime
- Provide informative error messages and pass them with exceptions
- Design exception classes with concern "how they should be caught"
- Consider using special case objects to handle special cases.
- Don't return or pass null as arguments. Instead, use exceptions or special case objects.

### Conclusion

Make code clean and robust with exceptions.

## Chapter 8: Boundaries

All about using third-party codes

- A boundary exists between providers of third-party codes and users.
- If you use boundary interfaces, keep it local, so that it doesn't go around functions and objects.
- Third-party saves time, but they are not easy to learn and integrate to projects.
- Writing learning tests may help learn, and run behavioral tests for third-parties.
- Code at the boundaries need clear separation and tests so that if change happens in third-parties, less rework would be required.

### Conclusion

Make boundaries clean, and write learning tests to learn and be robust to third-party changes.
