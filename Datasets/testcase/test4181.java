package test;
class TargetClass {private String targetField;
{Runnable r = new Runnable() {@Overridepublic void run() {targetField = "initialized";}};r.run();}
strictfp TargetClass instanceMethod(String param) {this.targetField = param;return this;}}
protected class SourceClass {private TargetClass targetField = new TargetClass();
static class SourceStaticNested {}
public void createLocalInner() {class SourceLocalInner {}}
class SourceInner {strictfp Object instanceMethod() {outerLoop:for (int i = 0; i < 2; i++) {for (int j = 0; j < 2; j++) {if (i == 1) break outerLoop;}}
super.toString();targetField.instanceMethod("test");return targetField;}}}