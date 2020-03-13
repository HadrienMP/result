Result
======
Provides a result monad for **jvm** based applications. If you don't know what a monad is,
you still can use Result. It is a just an interface that has two implementations, **Success**
and **Error**. It is a good way to represent the output of a function that can fail.  

You can store the result of your function in Success if it succeeded. 
And store the result of your function, and store the reason for the failure
in Error.  

An Arolla blog post will explore in further details, how this can be useful in error
management.

Install
-------
Result is not yet in the maven central repository.
To install it you need to define the jitpack repository. To do it add the following
code to your maven repositories.
```xml
<repositories>
    ...
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
    ...
</repositories>
``` 


Now you can add result to your dependencies. Put the following code 
in your maven dependencies.
```xml
<dependencies>
    ...
    <dependency>
        <groupId>com.github.HadrienMP</groupId>
        <artifactId>result</artifactId>
        <version>1.0</version>
    </dependency>
    ...
</dependencies>
```

Usage
-----
### Creation
The Result interface has a bunch of static factory methods that you should use to create
you results.

todo insert the examples like success, error, ofFailable etc.

### Manipulation
### Extraction
todo insert examples for getResult, getError, fold, orElseThrow etc.