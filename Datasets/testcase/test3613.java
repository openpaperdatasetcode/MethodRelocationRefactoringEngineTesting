package test;
import java.util.ArrayList;import java.util.List;import java.util.ListIterator;
class ParentSourceClass {}
protected class SourceClass extends ParentSourceClass {class FirstMemberInner {public List<String> chainMethod() {return new ArrayList<>(List.of("chain1"));}}
class SecondMemberInner {public ListIterator<String> m2(List<String> list) {return list.listIterator();}
public String m3(ListIterator<String> iterator) {return iterator.next();}}
protected Object varargsMethod(TargetClass<String> target, Object... args) {super.toString();if (target == null) throw new NullPointerException("Target cannot be null");
FirstMemberInner inner1 = new FirstMemberInner();SecondMemberInner inner2 = new SecondMemberInner();String chainResult = inner2.m3(inner2.m2(inner1.chainMethod()));
List rawList = new ArrayList();rawList.add(chainResult);
switch (args.length) {case 1:rawList.add(args[0].toString());break;default:rawList.add("default");}
for (int i = 0; i < 1; i++) {TargetClass<String> subResult = SubClass.FirstInnerClass.staticMethod(target);variableCall(subResult);}
return rawList;}
private void variableCall(TargetClass<String> target) {System.out.println(target.innerClass.getValue());}}
private class TargetClass<T> {T typeParam;TargetMemberInner innerClass = new TargetMemberInner();
class TargetMemberInner {String getValue() {return typeParam.toString();}}}
class SubClass extends SourceClass {class FirstInnerClass {protected static TargetClass<String> staticMethod(TargetClass<String> target) {return target;}}}