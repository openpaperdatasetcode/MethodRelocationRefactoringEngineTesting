package source;
import target.TargetClass;import java.util.List;
protected abstract class SourceClass {class MemberInner {}static class StaticNested {}
class SourceInner {record SourceInnerRec() {void methodToMove(TargetClass<String> target) {// Variable callString var = target.targetField;
// Raw typeList rawList = null;
// For statementfor (int i = 0; i < 3; i++) {var += String.valueOf(i);}
// Switch statementswitch (var.length()) {case 3:rawList = new java.util.ArrayList();rawList.add(var);break;default:break;}
// Uses outer thisSourceClass outer = SourceClass.this;MemberInner inner = new MemberInner();StaticNested nested = new StaticNested();}}}}
// Different package (target)package target;
public abstract class TargetClass<T> {T targetField;
public void example() {class LocalInner {} // target_feature: local inner class}}