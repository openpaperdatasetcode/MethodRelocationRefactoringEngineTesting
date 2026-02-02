package test;
import java.sql.SQLException;import java.util.Arrays;import java.util.List;
private class SourceClass {static class StaticNested {}
class SourceInner {/**
Processes TargetClass and returns its instance
@param target Abstract TargetClass implementation
@return TargetClass instance with processed inner class*/TargetClass processTarget(TargetClass target) {class LocalType {}LocalType local = new LocalType();
new Runnable() {@Overridepublic void run() {try {throw new SQLException("Simulated SQL exception");} catch (SQLException e) {System.out.println("Handled SQLException: " + e.getMessage());}}}.run();
List<TargetClass.Inner> innerList = Arrays.asList(target.new Inner(),target.new Inner(),target.new Inner());
for (TargetClass.Inner inner : innerList) {int var = inner.innerField;TargetClass result = switch (var) {case 1 -> callOverload(target, 1);case 2 -> callOverload(target, "two");default -> callOverload(target, 3.0);};}
StaticNested nested = new StaticNested();return target;}
protected TargetClass callOverload(TargetClass target, int val) {return target;}
protected TargetClass callOverload(TargetClass target, String val) {return target;}
protected TargetClass callOverload(TargetClass target, double val) {return target;}}}
/**
Abstract target class with member inner class
Used as dependency in SourceClass's inner class method*/abstract class TargetClass {class Inner {int innerField;}
public abstract void abstractMethod();}