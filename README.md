Result
======
[![Build Status](https://travis-ci.org/HadrienMP/result.svg?branch=master)](https://travis-ci.org/HadrienMP/result)
[![codecov](https://codecov.io/gh/HadrienMP/result/branch/master/graph/badge.svg)](https://codecov.io/gh/HadrienMP/result)
[![](https://jitpack.io/v/HadrienMP/result.svg)](https://jitpack.io/#HadrienMP/result)



Provides a result monad for **jvm** based applications. If you don't know what a monad is,
you still can use Result. Just think of Result as a box that can contain either a success result or an error.
Once you have the box, you have access to function to transform the success result, 
extract it, act on successes or failures and more.   

It is very handy for error management and railway programming. A blog post will soon describe how it can be
handy as an error management tool.

Why ?
-----
### Why would you want to use the Result monad instead of the non monadic old fashioned one ?
The old fasioned Result objects, usually consist of only a single class. 
It contains both the result and the error. So once you get it, you have to inspect both and decide what to do.
It is usually done with ifs. __That__ is where the monad shines, it eliminates the ifs in your code. 
The methods inside allow you to have a code with a single flow of execution. Thus, it makes your code __simpler__. 
You will have to learn some vocabulary of course. But this vocabulary has been selected to match the one used in other
famous jvm Monadic object such has Optional. So it should be pretty easy and serve you well in the years to come.

### Why would you want to use result instead of exceptions ?
It is a matter of taste and of error management strategies.  
When you have a function that can fail, you automatically have a function with two outputs, success and error.
You can throw exceptions in this case. But then your two outputs have different technologies, return and throw. 
Result proposes a single type: return. I think it makes the signature of your functions more explicit. 
I also think that the error management code for Result is more elegant than the try catch one.
Depending on what you need in case of error, you can avoid the stack trace generation step, which can be costly.
It would encourage you to try Result instead of exception to see how it looks and then decide which you prefer.


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
