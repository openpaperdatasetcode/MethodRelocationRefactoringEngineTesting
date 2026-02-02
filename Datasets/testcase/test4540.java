package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.*;
@Target(ElementType.METHOD)@Retention(RetentionPolicy.RUNTIME)@interface CustomAnno {}
interface SourceInterface {static class SourceStaticNested {class InnerRecursive {private List<String> recursiveMethod(TargetInterface target, int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {return result;}
TargetInterface.StaticNested nested = new TargetInterface.StaticNested(target) {@Override@CustomAnnosynchronized TargetInterface abstractMethod() {return TargetInterface.this;}};
result.add(String.valueOf(target.field));variableCall(target, nested);result.addAll(recursiveMethod(target, depth - 1));return result;}
private void variableCall(TargetInterface target, TargetInterface.StaticNested nested) {target.overrideViolation();TargetInterface var = nested.abstractMethod();}}}
default List<String> process(TargetInterface target) {new Runnable() {@Overridepublic void run() {new SourceStaticNested.InnerRecursive().recursiveMethod(target, 3);}}.run();return new ArrayList<>();}}
interface TargetInterface {int field = 5;
static class StaticNested {TargetInterface target;
StaticNested(TargetInterface target) {super();this.target = target;}
abstract synchronized TargetInterface abstractMethod();}
default void overrideViolation() {}}