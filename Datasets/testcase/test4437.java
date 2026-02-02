package test;
class TargetClass {public void methodWithLocalInner() {class TargetLocalInner {}}}
class SourceClass {private int outerPrivateField;
private TargetClass overloadedMethod(TargetClass targetParam) {if (targetParam == null) {throw new IllegalArgumentException();}targetParam.methodWithLocalInner();int val = outerPrivateField;return targetParam;}
private TargetClass overloadedMethod(TargetClass targetParam, String str) {if (str == null) {throw new NullPointerException();}targetParam.methodWithLocalInner();outerPrivateField = 5;return targetParam;}
public void createFirstLocalInner() {class SourceLocalInnerOne {}}
public void createSecondLocalInner() {class SourceLocalInnerTwo {}}}