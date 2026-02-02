package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
interface TargetInterface {}
abstract class ParentSourceClass {// Parent class varargs method for method_featurepublic int parentVarargsMethod(String... args) {return args.length;}}
public abstract class SourceClass extends ParentSourceClass {// Member inner class (source_feature)public class SourceInner {}
public List<String> methodToMove(AbstractTarget... targets) {super(); // Super constuctor invocationList<String> result = new ArrayList<>();
for (AbstractTarget target : targets) {// Variable call + contains target field (per_condition)target.toString();String targetField = target.targetField;result.add(targetField);
// Lambda expressions with parent_class varargs method (super.methodName())Function<AbstractTarget, Integer> varargsFunc = t -> super.parentVarargsMethod(t.targetField, "processed");result.add(targetField + "_count:" + varargsFunc.apply(target));
// Anonymous inner class (source_feature)Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Added: " + targetField);}};anon.run();
try {// Trigger target local inner classtarget.createLocalInner();} catch (Exception e) {// No new exception}}
return result;}}
abstract class AbstractTarget implements TargetInterface {public String targetField = "targetValue"; // Source contains target's field (per_condition)
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {public String getProcessed() {return targetField + "_local";}}new TargetLocalInner().getProcessed();}}