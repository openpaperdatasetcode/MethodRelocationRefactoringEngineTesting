package test;
/**
TargetClass Javadoc
Normal class with type parameter and inner record
@param <T> Generic type parameter*/class TargetClass<T> {T targetField;
/**
TargetInnerRec Javadoc
Inner record for target_inner_rec
@param Generic type parameter
*/
record TargetInnerRec(U val) {} // target_inner_rec
}
public class SourceClass {class MemberInner {}
public SourceClass() {// Anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}
class SourceInner {record SourceInnerRec() {} // source_inner_rec
// Overloading method 1protected Object methodToMove(TargetClass<String>.TargetInnerRec<Integer> rec, SourceInnerRec sourceRec) {// Type declaration statementclass TypeDecl {}TypeDecl type = new TypeDecl();
// Variable callString targetVar = new TargetClass<String>().targetField;Integer recVal = rec.val();
// Continue statementfor (int i = 0; i < 5; i++) {if (i == 2) continue;targetVar += i;}
return targetVar + recVal;}
// Overloading method 2protected Object methodToMove(TargetClass<Double>.TargetInnerRec<String> rec, SourceInnerRec sourceRec, int arg) {// Type declaration statementclass AnotherTypeDecl {}AnotherTypeDecl anotherType = new AnotherTypeDecl();
// Variable callDouble targetVar = new TargetClass<Double>().targetField;String recVal = rec.val();
// Continue statementfor (int i = 0; i < arg; i++) {if (i % 2 == 0) continue;targetVar += i;}
return targetVar + recVal;}}}