package test;
strictfp class SourceClass {public static class FirstStaticNested {}public static class SecondStaticNested {}
private Object process(TargetClass target) {if (target == null) {throw new IllegalArgumentException("Target cannot be null");}
SubTargetClass subTarget = new SubTargetClass();for (int i = 0; i < 3; i++) {subTarget.execute();}
switch (target.getStatus()) {case 1:subTarget.callSuperMethod();break;default:break;}
TargetClass.StaticNested targetNested = new TargetClass.StaticNested();String nestedData = targetNested.getData();return new FirstStaticNested() {String getCombined() {return nestedData + target.getName();}};}
private class SubTargetClass extends TargetClass {public SubTargetClass() {super();}
public void execute() {}
public void callSuperMethod() {super.printInfo();}}}
public class TargetClass {private String name;private int status;
public TargetClass() {this.name = "DefaultTarget";this.status = 0;}
public void printInfo() {}
public int getStatus() {return this.status;}
public String getName() {return this.name;}
public static class StaticNested {public String getData() {return "StaticNestedData";}}}