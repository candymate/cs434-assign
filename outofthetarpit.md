# Out of The Tar Pit Summary

## 1. Introduction

The biggest problem in the development and maintenance of large-scale software systems is **complexity**.

The paper argues that it is possible to take useful ideas from both OOP and functional programming to simplify the construction of large-scale SW systems.

## 2. Complexity

Brooks identified four properties of SW systems that make the problem hard: (1) Complexity, (2) Conformity, (3) Changability, (4) Invisiblity. The paper argues that the only significant one is complexity.

Being able to **think** and **reason** about SW systems is of **crucial** importance. However, **complexity** makes large systems hard to **understand**, makes the developers to expend huge resources in creeating and maintaining large systems.

**Simplicity is hard**

## 3. Approaches to Understanding

**Testing** and **Informal Reasoning** are two methods to understand systems. Informal reasoning is more important than testing - there is an inherent limits to understand systems with testing.

Improvements in informal reasoning leads to "less errors being created". Improvements in testing leads to "more errors being detected"

### Testing

An attempt to understand a system from the **outside**.

The unavoidable concern in testing is "Is the test right?". Using a particular test suite doesn't tell anything about the behaviour of the system or component, of the different test suite.

Dependent upon the accuracy of a specification

### Informal Reasoning

An attempt to understand a system from the **inside**.

Limited in scope, imprecise, so hence prone to error.

## 4. Causes of Complexity

### State

State, espicially mutable states, contributes to complexity.

#### Testing

Like test suites, Testing with a particular state doesn't tell anything about another state.

The test relies upon the assumption that the system would perform the same way, regardless of hidden internal states. However, it is not always possible to ignore these hidden states.

State is one of the main causes introducing uncertainty to testing.

#### Informal Reasoning

As the number of states grow, it becomes harder and harder to *reason*.

For every single bit of state added, the number of possible states grows double, which means the number of states grows exponentially.

Procedures which are not stateful are **contaminated** when they call (even indirectly) stateful procedures. Which means, reasoning them also needs considering states.

### Control

The order in which things happen.

When control is required to be specified, it is being forced to specify **how** the system should work rather than **what** is desired. This leads to **over-specifying** the problem, like choosing an arbitrary control flow in specifying only a relationship between values.

#### Informal Reasoning

When reading through code, the ordering specified in the code is first considered significant, then later determined that it is not.

The **determination** of importance of ordering may lead to subtle and hard-to-find bugs.

#### Concurrency

Concurrency is **explicitly** specified in most languages.

Similarly to states, adding further to the *number of scenarios* must be mentally considered when reading the program. (Informal Reasoning)

Even when state and test suites are the same, the result might not be reproduced. (Testing)

### Code Volume

Secondary effect. Much concern is put into state and control.

Code volume is the **easiest form to measure complexity**, and it **interacts** with the other causes.

In most systems, complexity *does* show non-linear increase with code volume. (Maybe square of program length)

### Other causes

Duplicated code, dead code, unnecessary abstraction, missed abstraction, poor modularity, poor documentation, ...

#### Complexity breeds complexity

This covers all complexity introduced as a result of **not being able to clearly understand** a system.

#### Simplicity is hard

Simplicity can only be attained if it is *recognized*, *sought* and *prized*.

#### Power corrupts

The more **powerful** a language is (the more something is *possible* within the language), the harder it is to **understand** systems constructed in it.

## 5. Classical approaches to managing complexity

### Object-orientation

#### State

Object is seen as some **states** + set of **procedures** (encapsulation)

Two problems: (1) if several access procedures access / manipulate the same bit of state, there may be several places where a given constraint must be enforced, (2) encapsulation-based integrity constraint enforcement is strongly biased towared *single-object* constraints and it is awkward to enforce constraints involving multiple objects.

*Object identity* adds the complexity, requires switching between *intensional identity* (objects are different even when attributes are same) and *extensional identity* (objects are same when attributes are same), when doing the informal reasoning.

#### Control

Most OOP languages offer standard sequential control flow, explicit classical "shared-state concurrency". These introduce complexity problems which are explained before. (Ch.4)

### Funtional Programming

#### State

Avoids *state* and *side-effects* completely, so a function will **always** return the same result when supplied with the same input. (referential transparency)

Functional values are used to simulate state.

Adding new state to functional programs requires all components that uses that invokes it to be changed. (Compared to stateful framework. It is possible to add state without adjusting the components invoking it) - *trade-off* between two approaches.

If state must be used to implement a system, functional programming show weaknesses.

#### Control

Implicit sequencing is used, so the same issues as imperative languages happen.

However, slight advantage is that functional languages encourage a more abstract use of control using functionals. (i.e. fold / map)

Avoidance of states benefits concurrency.

### Logic Programming

The approach of making statements about the problem, stating a set of axioms which describe the problem, and the attributes required for a solution. Running means finding a proof of each solution.

#### State

Pure logic programming doesn't use of mutable state. However, many logic programming languages provide some stateful mechanisms.

#### Control

Pure Prolog specifies both an **implicit ordering** for processing of sub-goals (left-to-right), and also an **implicit ordering** of clause application. (top-down) These work as control flow.

Some modern logic languages offer more flexibility over control.

## 6. Accidents and Essence

**Essential Complexity** is inherent in the **user's problem**. Should be considered even in an ideal world.

**Accidental Complexity** is all the rest, which is in the real world, dev team should deal with this accidental complexity. (i.e. complexity from performance issues, ...)

Complexity iteslf is **not** an inherent property of software. In the ideal world, there is an infrastructure that solves the user's problem completely. Therefore, all inherent complexities come from user's problem.

## Comments (+ Important Lessons)

1. Using immutable states is really crucial in reducing complexity.

2. Testing stateful programs is really challenging. Due to my own experience, fuzzing and finding bugs in hypervisors, hypervisors are highly stateful so that they require a lot of techniques such that resetting states, exploring states, ... . States work like the technical and mental barrier that blocks bug hunters to review (read) the codes to find bug.

3. Simplicity is hard.
