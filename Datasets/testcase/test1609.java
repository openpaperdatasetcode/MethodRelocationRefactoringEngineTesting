package test;
import java.util.List;import java.util.ArrayList;
protected class SourceClass {public static class StaticNested {public String process(String input) {return "processed: " + input;}}
public class MemberInner {public class InnerRec {// Method in source_inner_recint process(TargetClass.StaticNested.InnerRec targetRec) {int total = 0;
// LabeledStatement (private, 1 occurrence, accesses this.field)private processLoop: {if (targetRec.count == 0) {break processLoop;}total += targetRec.count;}
// Enhanced for statementfor (String item : targetRec.items) {// Variable call - access target inner rec's fieldtotal += item.length();}
return total;}}}}
class TargetClass {public static class StaticNested {public class InnerRec {public int count;public List<String> items = new ArrayList<>();
// this.field used in LabeledStatementpublic void setCount(int count) {this.count = count;}}}}