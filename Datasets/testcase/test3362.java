package test;
public abstract class SourceClass {private TargetClass targetField;
public static class StaticNested {public class SourceInnerRec {@Overridepublic final void abstractMethod() {super();try {int num = 1;String name1 = "a";String name2 = "b";String name3 = "c";targetField.action();return;} catch (Exception e) {System.out.println(TargetClass.staticField);}}}}
{Runnable r = new Runnable() {public void run() {int val = new StaticNested.SourceInnerRec()::abstractMethod;}};}}
private abstract class TargetClass {public static int staticField;
{Runnable r = new Runnable() {public void run() {}};}
public abstract void action();}