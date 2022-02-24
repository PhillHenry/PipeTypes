# An introduction to general refined types

All maths functions have a domain - that is, a class from which the argument is taken.
Let's take a simple function, the inverse function on real numbers. We'll call it```inverse```. We can define it as:

`inverse(x) = 1 / x where x ∈ ℝ ; x ≠ 0`

Let's attempt to write some Scala code to represent this:

```scala mdoc
def inverse(x: Double): Double = 1d / x
```

Now, let's call it:

```scala mdoc
val x = inverse(2.0)
```

Great! OK, let's be naughty:

```scala mdoc
inverse(0.0)
```

Well, that wasn't very clever. We've hit a [mathematical singularity](https://en.wikipedia.org/wiki/Singularity_(mathematics)).
The function is simply not defined at `x=0`. 
To be fair, the `where` clause in the original equation did warn us.
So, how can we code this in computer code?