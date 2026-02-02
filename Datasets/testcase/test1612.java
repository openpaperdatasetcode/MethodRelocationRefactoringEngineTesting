package test;
import java.util.ArrayList;import java.util.List;import other.OthersClass;
interface Processor {void process();}
protected class SourceClass implements Processor {protected String outerProtected = "source_protected";
public class MemberInner1 {public class MemberInner2 {// Varargs method in source_innerprotected void handle(TargetClass.Inner... inners) {// Labeled statementprocessLoop:for (TargetClass.Inner inner : inners) {if (inner.data == null) {break processLoop;}
try {// Access outer protected fieldinner.data = outerProtected;
// Access instance method of target innerinner.update();
// Variable call - access target inner's fieldList<String> items = new ArrayList<>(inner.items);
// Lambda with method reference to others_class's private instance methoditems.forEach(OthersClass::processItem);
// Depends on inner classTargetClass.StaticNested helper = new TargetClass.StaticNested();helper.validate(inner);} catch (Exception e) {// requires_try_catch}}}}}
@Overridepublic void process() {}}
class TargetClass {public class Inner {public String data;public List<String> items = new ArrayList<>();
public void update() {data += "_updated";}}
public static class StaticNested {public void validate(Inner inner) {if (inner.items.isEmpty()) {throw new IllegalArgumentException("Empty items");}}}}
package other;
public class OthersClass {// Private instance method for method referenceprivate static void processItem(String item) {System.out.println("Processed: " + item);}}