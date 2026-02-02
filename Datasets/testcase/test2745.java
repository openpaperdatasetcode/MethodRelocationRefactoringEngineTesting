package test.same;
import java.util.List;import java.util.ArrayList;
private class SourceClass {static class StaticNested {}
Object instanceMethod(TargetClass target) {super.toString();Object var = target.field;return var;}
Runnable anon = new Runnable() {public void run() {}};}
private class TargetClass {Object field;
protected TargetClass() {this(superMethod());}
private TargetClass(List<String> list) {}
protected List<String> superMethod() {return new SourceClass().instanceMethod(this);}
@Overridepublic String toString() {return super.toString();}
Runnable anon = new Runnable() {public void run() {}};}