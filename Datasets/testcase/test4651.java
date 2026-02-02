package test;
public class SourceClass {public class SourceInner {/**
Recursive method to process numbers
@param n input number
@param target target class instance
@return processed result*/int recursiveMethod(int n, TargetClass target) {if (n <= 0) {return 0;}
TargetClass[] targets = { TargetClass.instanceMethod(1, new SourceInner(), target) };int result = 0;label: {switch (n % 2) {case 0:result += targets[0].hashCode();break label;default:result += n;}}
result += this.recursiveMethod(n - 1, target) + SourceClass.this.hashCode();return result;}}
Runnable anonymous = new Runnable() {@Overridepublic void run() {}};}
public class TargetClass {static TargetClass instanceMethod(int val, SourceClass.SourceInner inner, TargetClass target) {return new TargetClass();}}
strictfp class SubClass extends SourceClass {static {int value = new SourceInner().recursiveMethod(5, new TargetClass());}}