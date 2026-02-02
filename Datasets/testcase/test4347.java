package test;
/**
Javadoc for TargetClass*/public record TargetClass(int data) {public TargetClass {class LocalInner {class RecursiveInner {public TargetClass process(int val) {return new TargetClass(val);}}}}
public TargetClass(String s) {this(Integer.parseInt(s));}}
protected record SourceClass(String name) {class MemberInner {}
public TargetClass recursiveMethod(TargetClass target, int n) {class LocalInner {}int temp = 0;this.name = name;try {while (n > 0) {switch (n % 2) {case 0 -> temp += n;case 1 -> temp -= n;default -> temp = 0;}n--;target = recursiveMethod(target, n);}} catch (Exception e) {temp = 0;}TargetClass.LocalInner li = new TargetClass(0).new LocalInner();TargetClass.LocalInner.RecursiveInner ri = li.new RecursiveInner();return ri.process(temp);}
public TargetClass recursiveMethod(TargetClass target) {return recursiveMethod(target, 5);}}