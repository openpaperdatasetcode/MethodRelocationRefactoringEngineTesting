package test;
import java.util.ArrayList;import java.util.List;
class SuperSource {protected String baseInfo;
public SuperSource(String info) {this.baseInfo = info;}}
class Target {public String targetField = "default_value";
void processData(String... items) {// Local inner class in targetclass LocalProcessor {List<String> processed = new ArrayList<>();
void addItem(String item) {processed.add(item.toUpperCase());}
List<String> getResult() {return processed;}}
LocalProcessor processor = new LocalProcessor();for (String item : items) {processor.addItem(item);}}}
private class Source extends SuperSource {static class StaticNested {static final int MAX_ITEMS = 5; // Static field depended on}
class MemberInner {class InnerRec {// Method in source_inner_rec positionvoid handle(Target target, String... data) {// Super constructor invocation (indirect via outer class)super();
// Expression statementtarget.targetField = "updated";
// Variable call (access target field)System.out.println("Target field: " + target.targetField);
// Depends on static fieldif (data.length > StaticNested.MAX_ITEMS) {System.out.println("Too many items");}
// Requires try-catchtry {for (int i = 0; i < data.length; i++) {if (data[i] == null) {continue; // Continue statement}target.processData(data[i]);}} catch (NullPointerException e) {System.err.println("Null data encountered");}}}}}