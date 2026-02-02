package test;
import java.sql.SQLException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnot {}
private class SourceClass {@MyAnnotTargetClass<String>.TargetStaticNested.TargetInnerRec methodToMove() throws SQLException {TargetClass<String> target = new TargetClass<>();TargetClass<String>.TargetStaticNested nested = new TargetClass.TargetStaticNested<>();TargetClass<String>.TargetStaticNested.TargetInnerRec innerRec = nested.new TargetInnerRec();
// Switch statementswitch (innerRec.count) {case 0:innerRec.value = "zero";break;case 1:innerRec.value = "one";break;default:innerRec.value = "default";}
// ConditionalExpression (3)int cond1 = (innerRec.count > 0) ? 1 : 0;String cond2 = (innerRec.value != null) ? innerRec.value : "null";boolean cond3 = (cond1 > 0) ? true : false;
// Variable callString var = innerRec.value;
// Access instance fieldinnerRec.count = 3;
// Depends on inner classtarget.process(innerRec);
return innerRec;}}
public class TargetClass<T> {static class TargetStaticNested {
class TargetInnerRec {
U value;
int count;
}
}
void process(TargetStaticNested<T>.TargetInnerRec rec) {}}