package source.pkg;
import target.pkg.TargetClass;
class SourceClass {private TargetClass targetField = new TargetClass() {@Overridepublic int getValue() {return 10;}};
public int processData() {SourceInnerFirst innerFirst = new SourceInnerFirst();int firstVal = innerFirst.useTargetMethod();
SourceInnerSecond innerSecond = new SourceInnerSecond();innerSecond.setTargetField(targetField);int secondVal = innerSecond.callTargetMethod();
return firstVal + secondVal;}
class SourceInnerFirst {int useTargetMethod() {type declaration statementTargetClass target = source.pkg.SourceClass.this.targetField;return target.getValue();}}
class SourceInnerSecond {private TargetClass targetField;
void setTargetField(TargetClass target) {this.targetField = target;}
int callTargetMethod() {return targetField.getValue();}}}
package target.pkg;
interface DataInterface {int getValue();}
abstract class TargetClass implements DataInterface {@Overridepublic abstract int getValue();
public void printInfo() {System.out.println("TargetClass instance");}}