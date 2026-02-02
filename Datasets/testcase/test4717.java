package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass {private TargetClass targetField = new TargetClass();
protected List<String> process(String... items) {super();List<String> result = new ArrayList<>();
for (String item : items) {targetField.count++;targetField.values.add(item);result.add(targetField.prefix + item);}
return result;}
void useProcess() {class LocalInner {void execute() {List<String> res = SourceClass.this.process("a", "b", "c");}}new LocalInner().execute();}
static class StaticNested {void callProcess() {SourceClass source = new SourceClass();List<String> res = source.process("x", "y");}}
// Override violation: No superclass method to overridepublic String toString() {return "SourceClass";}}
/**
TargetClass with static nested class
Manages a list of values with a prefix*/class TargetClass {String prefix = "item-";int count = 0;List<String> values = new ArrayList<>();
static class TargetStaticNested {static void log(TargetClass target) {System.out.println("Count: " + target.count);}}}