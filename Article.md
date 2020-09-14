# Null Saftey In Kotlin

In writing Java code, a null pointer has been a major problem. To solve this problem, Kotlin comes with a way to avoid null pointer exception. Kotlin aims at being a safe language. By default kotlin does not allow you to define a variable with null value.

For example, when we define a variable, Kotlin requires you to consider whether the variable may be null when defining the variable, and when defining the return type of a method, it is also necessary to consider whether the return value is null. When using variables, if the variable is clearly defined and will not be  null, then the developer can use the variable with confidence, and if the variable is null, Kotlin requires the developer to make a good judgment about that and handle the null situation logic.

Though kotlin is designed such a way that it can handle nullpointer exception, still you can get nullpointer exception

- Null pointer caused by calling external Java code
- There's some data inconsistency with regard to initialization.


In kotlin, you can not set null value directly to a variable.

The following is not allowed.
```
var a: String = "abc"
a = null
```

If you want to allow nulls, then you need to use String? to define:

```
var b: String? = "abc"
b = null // ok
```
Now you can use the variable b with confidence. Because it will not appear null.

When you call the method or property of the variable b , then it will report error.

```
val l = a.length
val l = b.length // error: variable 'b' can be null
```

Kotlin provides other ways for us to access variable b.

### Check for null in the conditional  statement

Like java, we can use,
```
val l = if (b != null) b.length else -1
```

The difference is that the if statement added by the Java code is judged at runtime, and Kotlin does it at compile time. If the null check is done, the variable can be used in the subsequent part of the if statement, for example:

```
if (b != null && b.length > 0) {
    print("String of length ${b.length}")
} else {
    print("Empty string")
}
```

### Safe Calls

You can also use question mark + period to safely call ?. , for example:
```
b?.length
```

This will return the length of b, or return null if b is null. This kind of safe call will save a lot of if statements. For example, we might encounter code like this:

You can also use question mark + period to safely call ?. , for example:
```
bob?.department?.head?.name
```
This will return null if any object in the entire code is null.

?. It can also be used with the let keyword, such as:

```
val listWithNulls: List<String?> = listOf("A", null)
for (item in listWithNulls) {
     item?.let { println(it) } // prints A and ignores null
}
```
The null one will be ignored and will not cause a null pointer exception.

### Elvis operator

```
val l: Int = if (b != null) b.length else -1
```
The above code can also be written as:

```
val l = b?.length ?: -1
```
?:  The expression is equivalent to a shorthand if statement. If  the expression on the left is not null, return the result on the left, otherwise return the expression on the right. In Kotlin, throw and return are also expressions, so they can also appear on the right side of ?:, for example:

```
fun foo(node: Node): String? {
    val parent = node.getParent() ?: return null
    val name = node.getName() ?: throw IllegalArgumentException("name expected")
}
```

### !! operator

If you expect the code to throw an null pointer exception with out explicitly checking and throw, then you can use the!! operator, For example,
```
val l = b!!.length
```

When b is a null, the code execution here will throw a NullPointerException.

### Safe Casts
In Java, if the type conversion does not match, a ClassCastException will be thrown. The safe type conversion can return a null in this case, such as:

```
val aInt: Int? = a as? Int
```
In the above code, if a is not an Int, then aInt is equal to null.


### Collections of Nullable Type
If you have a collection that contains nullable elements and you need to filter out those non-null elements, then you can use filterNotNull:

```
val nullableList: List<Int?> = listOf(1, 2, null, 4)
val intList: List<Int> = nullableList.filterNotNull()
```

### Conclusion

Using the Null Saftey feature, the kotlin helps us to prevent null pointer exceptions in run time.  So it is good to use kotlin over java in Android Projects.


