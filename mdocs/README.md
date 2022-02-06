# PipeTypes

### The problem

You build an ETL pipeline. 
It takes an hour or two to run. 
The ML model produced at the end stopped making sense since the last commit.
After hours of debugging, you find why.
During the feature engineering phase, we take peoples' ages and turn them to a binary value that indicates if they're a minor or adult.
The bug was we executed it twice.
This means, a 49 year old man first has his age mapped to `1` to indicate he is an adult.
The second time, his binary `1` value is treated as his age and the minor/adult flag becomes `0`.
It is not immediately obvious looking at the output of the ML model what the problem is.

### Current solutions

How can we automatically check for this bug?

- We could use unit/integration tests but they only test a transform or function in isolation. 
They would not catch this error.

- We could use tools that check the data for something strange but if we're dealing a subset of the data that deals with neonatal cases, everybody having age `0` is not surprising.

- We could write system tests that test the pipeline from end-to-end but those generally take a long time to run.
So, you're back to square one.

### The requirements

What we want is:

- Something that tells us before deployment that something is amiss.
- Something tha runs on the data scientist/engineer's laptop.
- Something that does not rely on real data as that is often highly confidential and can only be used in situ.

### A better solution

Compile-time type safety would catch this problem. 
However, not all languages are stringent when applying type checking.
So, the solution must be a thin wrapper that potentially wraps other languages and frameworks.
This is a little like how PySpark gives a Python interface on a Java distributed application.

### Pipetypes

PipeTypes is written in Scala. It gives us the type-safety we need, runs on the near ubiquitous JVM and so interfaces with most big data tools out-of-the-box.

Using PipeTypes, we merely describe the pipeline, we don't implement it.

### An example

Let's see how we'd avoid the bug in 'The problem' section above.
```scala mdoc
import uk.co.odinconsultants.pipetypes.data._

type Age    = Validated[GreaterThan[-1] And LowerThan[120]]

```