package test;
import java.util.ArrayList;
class ParentGenericClass<T> {public String overloadedMethod(T data) {return data.toString();}
public String overloadedMethod(T data, int suffix) {return data.toString() + suffix;}}
protected class SourceGenericClass<S> extends ParentGenericClass<S> {private static final String STATIC_FIELD = "sourceStaticData";
class SourceMemberInner {S getInnerData (S input) {return input;}}
protected <T extends TargetGenericClass<S>> int genericMethod (T target, S data) {
ArrayList rawList = new ArrayList ();rawList.add (target);
assert target != null : "Target cannot be null";assert data != null : "Data cannot be null";
SourceMemberInner inner = new SourceMemberInner ();S varCall1 = inner.getInnerData (data);S varCall2 = target.targetMemberInner.getTargetInnerData (varCall1);
for (int i = 0; i < 3; i++) {if (i == 1) {continue;}; 
rawList.add (i);}
String parentResult = (varCall1.toString ().length () > 5)? this.overloadedMethod (varCall1): this.overloadedMethod (varCall2, 100);
if (!parentResult.contains (STATIC_FIELD)) {throw new IllegalArgumentException ("Missing static field content");}
return parentResult.length () + varCall2.toString ().length ();}
public void createLocalInner () {class SourceLocalInner {void printData (S data) {System.out.println (data);}}new SourceLocalInner ().printData ((S) STATIC_FIELD);}}
protected class TargetGenericClass<T> {
class TargetMemberInner {T getTargetInnerData (T input) {return input;}}
TargetMemberInner targetMemberInner = new TargetMemberInner();}