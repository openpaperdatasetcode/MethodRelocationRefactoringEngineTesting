package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
protected class SourceClass {private Object privateVarargsMethod(TargetClass... targets) {class LocalInnerFirst {private int getTargetField(TargetClass target) {return target.field;}}
class LocalInnerSecond {protected int processTarget(TargetClass target) {Function<TargetClass, Integer> func = TargetClass.InnerTarget::getValue;return func.apply(target.new InnerTarget());}}
LocalInnerFirst first = new LocalInnerFirst();LocalInnerSecond second = new LocalInnerSecond();List<Object> results = new ArrayList<>();
for (TargetClass target : targets) {if (target == null) {break;}
TargetClass.InnerTarget inner = target.new InnerTarget();int fieldValue = first.getTargetField(target);int processed = second.processTarget(target);
results.add(fieldValue);results.add(processed);
if (fieldValue == 1) {return results;}}
return results;}}
/**
Abstract target class with Javadoc and member inner class*/abstract class TargetClass {protected int field;
public TargetClass() {super();this.field = 0;}
public TargetClass(int field) {super();this.field = field;}
/**
Member inner class of TargetClass*/public class InnerTarget {private int innerField;
public InnerTarget() {this.innerField = TargetClass.this.field;}
public int getValue() {return innerField;}}}
class OtherClass {public int invokeInnerMethod(TargetClass.InnerTarget inner) {return inner.getValue() * 2;}}
