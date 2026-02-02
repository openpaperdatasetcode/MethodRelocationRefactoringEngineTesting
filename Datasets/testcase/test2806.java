package test;
import java.util.List;import java.util.ArrayList;
interface TargetInterface {}
protected record SourceRecord<T extends CharSequence & Comparable<T>>(T data) {{// Anonymous inner class (source_feature)new Runnable() {};}
public void createLocalInner() {// Local inner class (source_feature)class LocalInnerSource {}}
@Overridepublic int methodToMove(StrictfpTargetRecord<T> target) {// EnhancedForStatement with super.field and count 1 (diff_package_others)for (String str : target.getSuperFieldList()) {String superFieldVal = target.superField;}
super();
// Try statement + exception handling with generic method from sub_classtry {SubSourceRecord<T> sub = new SubSourceRecord<>(data);sub.genericMethod(target, "arg");} catch (Exception e) {}
// Variable call + contains target parameter (per_condition)target.toString();T targetField = target.value();
return target.superField.length();}}
class SubSourceRecord<U extends CharSequence & Comparable> extends SourceRecord {
public SubSourceRecord(U data) {
super(data);
}
public <V extends U> void genericMethod(StrictfpTargetRecord<V> target, V arg) {target.getInner().innerMethod();}}
/**
Javadoc for StrictfpTargetRecord (target_feature: javadoc)*/strictfp record StrictfpTargetRecord(U value) implements TargetInterface {
// super.field (target_feature)
public final String superField = "targetSuperField";
// Member inner class (target_feature)class TargetInner {public void innerMethod() {}}
public List<String> getSuperFieldList() {return new ArrayList<>(List.of(superField));}
public TargetInner getInner() {return new TargetInner();}
public int methodToMove(StrictfpTargetRecord target) {
return superField.hashCode();
}
}