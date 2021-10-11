# The Mythical Man-Month Summary
> The Mythical Man-Month by Frederick P. Brooks, Jr.

## Chapter 2: The Mythical Man-Month

- Most software projects suffer from lack of time rather than all other causes. Why is that?

> First, our techniques of estimating are poorly developed. More seriously, they reflect an unvoiced assumption which is quite untrue, i.e., that all will go well. (**Optimism**)

> Second, our estimating techniques fallaciously confuse effort with progress, hiding the assumption that men and months are interchangeable.

> Third, because we are uncertain of our estimates, software managers often lack the courteous stubbornness of Antoine's chef. Quote: "Good cooking fakes time. If you are made to wait, it is to serve you better, and to please you."

> Fourth, schedule progress is poorly monitored. Techniques proven and routine in other engineering disciplines are considered radical innovations in software engineering.

> Fifth, when schedule slippage is recognized, the natural (and traditional) response is to add manpower. Like dousing a fire with gasoline, this makes matters worse, much worse. More fire requires more gasoline, and thus begins a regenerative cycle which ends in disaster.

- Programmers tend to think optimistically so that they think delays will not happen to their plans, but the probability for it to happen is really small.

- Men and Months are **not interchangable**.
  - If communication is not needed, it is true that they are interchangable, but this is the ideal case.
  - In reality, communications are needed in project, to collaborate.
  - Each worker must be trained in the technology, the goals of the effort, the overall strategy, and the plan of work.
  - If intercommunication is needed so that two parts of work needs the other, the effort of communication increases drastically.
  - Because of these communication efforts and training, adding more men lengthens the schedule, instead of shortening it.

- Author suggests the scheduling to be **1/3 of planning, 1/6 of coding, 1/4 of component test and early system test, 1/4 of system test, all components in hand**.
  - Planning is larger than normal, but it is not enough.
  - Debugging takes 1/2 of the schedule.
  - Coding is the easiest part to estimate, but it is only 1/6 of the schedule.
  - System test is at the end of the schedule, so delay happens here often.
  - However, delay at system test is highly detrimental, so it is better to allow enough system test time in the schedule.

- Patrons may force you to agree with impossible due. (This is common) Managers use "hunches" to defend their estimates, that their hunches are better than the estimates driven from wishes of patrons. (Gutless Estimating)

- If the schedule is delayed, it is better to reschedule or trim the task, than adding more men to the project.
  - Brook's law : Adding manpower to a late software project makes it later.

### Comments

- It is my first time to know that adding manpower to a late software project makes it later. (Re-training and repartitioning tasks is needed for late-workers to actually work)
- Commnunication overhead is not negligible, and it takes significant effort for communicaiton.
- Coding is only 1/6 of the project, and test takes 1/2 of the project. Planning is larger than it's thought, 1/3 of the project.
- Making estimates of the project is really hard, so we need to develop and share data to develop better methods of making estimates.
