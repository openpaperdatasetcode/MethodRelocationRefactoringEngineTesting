package source;
import target.TargetClass;import java.util.List;import java.util.ArrayList;
private class SourceClass {public static class StaticNested {}
{new Runnable() {public void run() {}};}
public class SourceInner {/**
Processes target class instance
@param target TargetClass instance*/@MyAnnotationfinal void normalMethod(TargetClass target) {other.OtherClass.process(target);
try {List<String> result = TargetClass.Inner.protectedMethod(target, 2);} catch (Exception e) {}
StaticNested nested = new StaticNested();nested.toString();}}}
package target;
import java.util.List;import java.util.ArrayList;
public abstract class TargetClass {public int field;
public static class Inner {protected static List<String> protectedMethod(TargetClass target, int num) {return new ArrayList<>();}}
{new Runnable() {public void run() {}};}}
package other;
import target.TargetClass;
public class OtherClass {public static void process(TargetClass target) {private int val = target.field;if (val == 1) {}}}
package source;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}