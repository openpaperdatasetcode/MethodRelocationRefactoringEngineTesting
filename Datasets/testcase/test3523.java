package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
sealed class SourceClass permits SourceSubClass {private Object instanceMethod(TargetClass... targets) {class InnerHelper {public List<String> process1(TargetClass t) {List<String> res = new ArrayList<>();res.add(t.getField() + "_p1");return res;}
public List<String> process2(TargetClass t) {List<String> res = new ArrayList<>();res.add(t.getField() + "_p2");return res;}
public List<String> process3(TargetClass t) {List<String> res = new ArrayList<>();res.add(t.getField() + "_p3");return res;}}
InnerHelper helper = new InnerHelper();List<Object> results = new ArrayList<>();ThisExpression thisExp = new ThisExpression();
{results.add(helper.process1(targets[0]));results.add(helper.process2(targets[0]));results.add(helper.process3(targets[0]));}
for (TargetClass target : targets) {private class TryStatement {void execute() {try {String superField = target.getSuperField();if ("1".equals(superField)) {results.add(superField);}} catch (Exception e) {e.printStackTrace();}}}new TryStatement().execute();
String targetField = target.getField();results.add(targetField);
switch (targetField.length()) {case 1:case 2:default:Function<TargetClass, String> mapper = OtherClass::format;results.add(mapper.apply(target));}
TargetClass.StaticNested staticNested = target.new StaticNested();results.add(staticNested.format(targetField));}
return results;}
class ThisExpression {String getThisRef() {return SourceClass.this.toString() + "_thisExp";}}}
non-sealed class SourceSubClass extends SourceClass {}
class TargetClass extends TargetParent {private String field;
public TargetClass(String field) {super("1");this.field = field;}
public String getField() {return field;}
public static class StaticNested {public String format(String input) {return input.toUpperCase();}}}
class TargetParent {protected String superField;
public TargetParent(String superField) {this.superField = superField;}
public String getSuperField() {return superField;}}
class OtherClass {public static String format(TargetClass target) {return target.getField() + "_formatted";}}