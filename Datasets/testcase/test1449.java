package test;
import java.util.function.Supplier;
/**
Target class with javadoc and anonymous inner class*/private class TargetClass {int field = 1;public static final int STATIC_FIELD = 3;
{new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};}
private class InnerAccessor {public Object getFieldValue() {return super.toString() + ":" + field;}}}
public class SourceClass {{new Supplier<String>() {@Overridepublic String get() {return "Source anonymous inner class";}};}
public Object process(TargetClass target) {class LocalInner {void validateTarget() {assert target.field == 1 : "Target field initial value mismatch";}}
new LocalInner().validateTarget();TargetClass.InnerAccessor accessor = target.new InnerAccessor();Object result = null;int count = 0;
private do {target.field++;count++;} while (count < TargetClass.STATIC_FIELD);
if (target.field > 2) {result = accessor.getFieldValue();} else {result = target.field;}
return result;}
public Object process(TargetClass target, String suffix) {new TargetClass();if (target.field >= 1) {TargetClass.InnerAccessor accessor = target.new InnerAccessor();return accessor.getFieldValue() + suffix;}return target.field;}}