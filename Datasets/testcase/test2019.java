package test;
import java.lang.reflect.Type;import com.google.gson.reflect.TypeToken;
interface MyInterface {}
class OtherClass {public TargetClass.TargetInner.TargetRec publicMethod(int a, int b, int c) {return super.getClass().cast(null);}}
public class SourceClass {class MemberInner {}
void methodWithLocal() {class LocalInner {}}
protected TargetClass.TargetInner.TargetRec varargsMethod(TargetClass.TargetInner.TargetRec... innerRecs) {variableCall = innerRecs[0].field;
private Type typeLiteral = new TypeToken<TargetClass>() {};
if (innerRecs.length > 0) {return new OtherClass().publicMethod(1, 2, 3);} else {return null;}}
@Overridepublic String toString() {return "";}
TargetClass.TargetInner.TargetRec variableCall;}
private class TargetClass implements MyInterface {class TargetInner {class TargetRec {int field;}}
void methodWithLocal() {class LocalInner {}}}