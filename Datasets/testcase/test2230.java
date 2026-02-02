package test;
public class SourceClass {class SourceInner {int value;
default int methodToMove(TargetClass.Nested.Inner nested) {int result = 0;try {do {Runnable r = () -> System.out.println(this.value);r.run();String str = new SubClass().overloadMethod(1);result = nested.field;} while (result < 5);} catch (Exception e) {result = -1;}return result;}}
{new Runnable() {public void run() {}};new Runnable() {public void run() {}};}}
public class TargetClass extends SuperClass {static class Nested {static class Inner {int field;}}}
class SuperClass {}
class SubClass {default String overloadMethod(int num) {return "int: " + num;}
default String overloadMethod(String str) {return "str: " + str;}}