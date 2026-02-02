package test;
class SourceClass {public class FirstInner {private String data;
public FirstInner(String data) {this.data = data;}}
public class SecondInner {public FirstInner getFirstInner(String data) {return new FirstInner(data);}}
private Object process(TargetClass.Inner targetInner) {// With bounds: declare variable with bounded type parameterTargetClass.Nested<String> boundedNested = new TargetClass.Nested<>();
// Variable call: access target inner class's fieldString value = targetInner.content;
// Expression statement: modify target inner class's fieldtargetInner.content = value + "_processed";
// Use source's member inner classesSecondInner secondInner = new SecondInner();FirstInner firstInner = secondInner.getFirstInner(targetInner.content);
return boundedNested.wrap(firstInner.data);}}
public class TargetClass {public class Inner {public String content;
public Inner(String content) {this.content = content;}}
// Generic class with type boundspublic static class Nested<T extends CharSequence> {public T wrap(T input) {return input;}}}
