Result
======
Provides a result monad for **jvm** based applications. If you don't know what a monad is,
you still can use Result. Just think of Result as a box that can contain either a success result or an error.
Once you have the box, you have access to function to transform the success result, 
extract it, act on successes or failures and more.   

It is very handy for error management and railway programming. A blog post will soon describe how it can be
handy as an error mangement tool.

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