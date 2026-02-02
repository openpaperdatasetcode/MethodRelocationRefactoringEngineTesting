package test;
private enum TargetEnum {INSTANCE;
static class TargetStaticNested {private char nestedChar;
public TargetStaticNested(char c) {this.nestedChar = c;}
protected int getCharCode() {return (int) this.nestedChar;}}}
strictfp enum SourceEnum {SOURCE_INSTANCE;
static class SourceStaticNested extends TargetEnum.TargetStaticNested {public SourceStaticNested(char c) {super(c);}
@Overrideprotected int getCharCode() {return super.getCharCode() + 1;}}
/**
Instance method to test Move Method refactoring,
interacts with TargetEnum's static nested class.
@param targetNested TargetEnum's static nested class instance*/public final void testMethod(TargetEnum.TargetStaticNested targetNested) {SourceStaticNested sourceNested = new SourceStaticNested('A');
labeledBlock: {synchronized (this) {int code = (sourceNested.getCharCode() > 65) ? this.calculateCode(sourceNested) : targetNested.getCharCode();assert code > 65 : "Character code must be greater than 'A' (65)";break labeledBlock;}}}
private int calculateCode(SourceStaticNested nested) {return nested.getCharCode() * 2;}
public void createLocalInner() {class SourceLocalInner {void printChar() {System.out.println('B');}}new SourceLocalInner().printChar();}}