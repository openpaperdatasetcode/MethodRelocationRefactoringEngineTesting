package test;
import java.util.ArrayList;import java.util.List;
sealed abstract class SourceClass permits ConcreteSource {// Static nested class (source_feature)public static class SourceStaticNested {}
// Member inner class (source_inner)protected class SourceInner {// Overloading method 1protected List<String> methodToMove(AbstractTarget target) {return process(target, new ArrayList<>());}
// Overloading method 2protected List<String> methodToMove(AbstractTarget target, List<String> initial) {return process(target, initial);}
private List<String> process(AbstractTarget target, List<String> result) {super(); // Super constuctor invocation// Variable call + contains target field (per_condition)target.toString();
// Type declaration statementString targetField = target.targetField;
// 3 public Name expressions (method names/field names)String name1 = target.getFieldName();String name2 = target.processName(targetField);String name3 = new SourceStaticNested().getClass().getSimpleName();
// Switch caseswitch (targetField.length()) {case 0:result.add("empty");break;case 5:result.add(name1 + "" + targetField);
break;
default:
result.add(name2 + "" + name3);}
// Synchronized statementsynchronized (target) {// Collection operations with target instance method call (this.methodName())result.add((String) target.addToCollection(result, targetField));}
// Raw typeList rawList = target.getRawList();rawList.addAll(result);
// Local inner class (source_feature)class LocalCollector {public void merge() {try {for (Object obj : rawList) {result.add((String) obj);}} catch (Exception e) {// No new exception}}}new LocalCollector().merge();
return result;}}}
non-sealed class ConcreteSource extends SourceClass {}
abstract class AbstractTarget {public String targetField = "targetData"; // Source contains target's field (per_condition)
// Target instance method for method_featurepublic Object addToCollection(List<String> col, String item) {col.add(item.toUpperCase());return item;}
public String getFieldName() {return "field_" + targetField;}
public String processName(String name) {return name + "_processed";}
// Raw type providerpublic List getRawList() {return new ArrayList();}
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {public String getMerged() {return targetField + "_local";}}result.add(new TargetLocalInner().getMerged());}
// Dummy list for local inner class accessprivate List<String> result = new ArrayList<>();}