package test;
import java.util.ArrayList;import java.util.List;
class SuperTarget {protected String baseField = "base_value";}
class Target extends SuperTarget {String targetField = "target_data";
class Inner {List<String> items = new ArrayList<>();
void addItem(String item) {items.add(item);}}
{// Anonymous inner class in targetRunnable initTask = new Runnable() {@Overridepublic void run() {targetField = "initialized_by_anon";}};initTask.run();}}
public class Source {static class StaticNested {static Target.Inner createInner(Target target) {return target.new Inner();}}
strictfp Target.Inner process(Target target, String... args) {// Type declaration statementTarget.Inner targetInner = StaticNested.createInner(target);
// Access target fieldtargetInner.addItem(target.targetField);
// Constructor invocationTarget newTarget = new Target();
// Anonymous inner class in sourceRunnable processor = new Runnable() {@Overridepublic void run() {for (String arg : args) {targetInner.addItem(arg);}}};processor.run();
// Try statementtry {// Variable callint index = 0;while (index < args.length) {if (args[index].isEmpty()) {// Break statementbreak;}targetInner.addItem(args[index].toUpperCase());index++;}} catch (Exception e) {// No new exception}
return targetInner;}}