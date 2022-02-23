# An introduction to general refined types

All maths functions have a domain - that is, a class from which the argument is taken.
Let's take a simple function, the inverse function on real numbers. We'll call it```inverse```. We can define it as:

inverse(x) = 1 / x &nbsp;&nbsp;&nbsp;x  &isin; &reals; ; x &ne; 0

Let's write some Scala code to represent this:

```scala mdoc
def inverse(x: Int): Double = 1 / x
```

Now, let's call it:

```scala mdoc
val x = inverse(2)
```

Great! OK, let's be naughty:

```scala mdoc:crash
inverse(0)
```

Well, that wasn't very clever. 