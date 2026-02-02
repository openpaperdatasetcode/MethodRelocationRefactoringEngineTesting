package test;
abstract class SuperTarget {protected int superField = 5;}
protected class Source {protected int sourceField = 10;
class SourceInner {protected int calculate(AbstractTarget target) {AbstractTarget localTarget = new AbstractTarget() {};super.toString();
static int temp = 0;if (target.superField > sourceField) {temp = target.superField;} else {temp = sourceField;}
return temp + target.getLocalValue();}
protected int calculate(String str) {return str.length();}}
{String result = new SourceInner().calculate(new AbstractTarget() {});}
private String getSourceData() {return new SourceInner().calculate(5).toString();}}
abstract class AbstractTarget extends SuperTarget {int getLocalValue() {class LocalCalculator {int compute() {return superField * 2;}}return new LocalCalculator().compute();}}