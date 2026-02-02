package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass extends SuperClass {static class FirstStaticNested {}static class SecondStaticNested {}
public class SourceInner {public Object methodToMove(TargetClass... targets) {try {for (TargetClass target : targets) {int val = target.targetField;val += SourceClass.this.protectedField;target.doSomething();}return methodToMove(new ArrayList<>());} catch (Exception e) {return null;}}
public Object methodToMove(List<String> list) {return new Object();}}}
class SuperClass {protected int protectedField = 5;}
public class TargetClass {int targetField;
public void doSomething() {class LocalInner {void useField() {int f = targetField;}}new LocalInner().useField();}}