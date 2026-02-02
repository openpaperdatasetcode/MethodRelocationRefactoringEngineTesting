package test;
public class SourceClass {protected Object process(TargetClass target) {TargetClass.MemberInner inner = target.new MemberInner();Object result = null;int count = 0;
do {private int val = inner.field1 + inner.field2 + inner.field3;result = inner.m1().m2().m3();count++;} while (count < 3);
return this;}
protected Object process(TargetClass target, int param) {return new Object();}}
public class TargetClass {public class MemberInner {int field1;int field2;int field3;
public MemberInner m1() {return this;}
public MemberInner m2() {return this;}
public Object m3() {return new Object();}}}